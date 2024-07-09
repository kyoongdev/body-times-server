package kyoongdev.body_times.config;

import java.util.Objects;
import kyoongdev.body_times.common.annotations.V1RestController;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

  @Override
  protected void registerHandlerMethod(Object handler, java.lang.reflect.Method method,
      RequestMappingInfo mapping) {
    Class<?> handlerType =
        handler instanceof String ? Objects.requireNonNull(getApplicationContext())
            .getType((String) handler) : handler.getClass();

    Assert.isTrue(handlerType != null, "CustomRequestMappingHandlerMapping 에서 오류가 발생했습니다.");

    V1RestController typeAnnotation = AnnotatedElementUtils.findMergedAnnotation(handlerType, V1RestController.class);
    if (typeAnnotation != null) {
      String prefix = typeAnnotation.value();

      RequestMappingInfo.Builder builder = RequestMappingInfo
          .paths("/api/v1/" + prefix)
          .methods(mapping.getMethodsCondition().getMethods().toArray(new RequestMethod[0]));


      if (!mapping.getParamsCondition().isEmpty()) {
        builder = builder.params(mapping.getParamsCondition().getExpressions().toArray(new String[0]));
      }
      if (!mapping.getHeadersCondition().isEmpty()) {
        builder = builder.headers(mapping.getHeadersCondition().getExpressions().toArray(new String[0]));
      }
      if (!mapping.getConsumesCondition().isEmpty()) {
        builder = builder.consumes(mapping.getConsumesCondition().getExpressions().toArray(new String[0]));
      }
      if (!mapping.getProducesCondition().isEmpty()) {
        builder = builder.produces(mapping.getProducesCondition().getExpressions().toArray(new String[0]));
      }


      RequestMappingInfo modifiedMapping = builder.build();


      super.registerHandlerMethod(handler, method, modifiedMapping);
    } else {
      super.registerHandlerMethod(handler, method, mapping);
    }
  }
}
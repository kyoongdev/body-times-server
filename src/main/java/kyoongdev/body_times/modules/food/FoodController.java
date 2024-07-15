package kyoongdev.body_times.modules.food;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import kyoongdev.body_times.common.dto.EmptyResponseDTO;
import kyoongdev.body_times.common.dto.ResponseWithIdDTO;
import kyoongdev.body_times.common.paging.PaginationDTO;
import kyoongdev.body_times.common.paging.Paging;
import kyoongdev.body_times.common.paging.PagingDTO;
import kyoongdev.body_times.modules.food.dto.CreateFoodDTO;
import kyoongdev.body_times.modules.food.dto.FindFoodsResponseDTO;
import kyoongdev.body_times.modules.food.dto.FoodDTO;
import kyoongdev.body_times.modules.food.dto.UpdateFoodDTO;
import kyoongdev.body_times.modules.food.dto.query.FindFoodsQuery;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;


@Tag(name = "음식")
@RequestMapping("/foods")
@RestController
@RequiredArgsConstructor
public class FoodController {

  private final FoodService foodService;

  @GetMapping("{foodId}/detail")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FoodDTO.class)))
  })
  public FoodDTO findFood(@PathVariable(value = "foodId") String foodId) {
    return foodService.findFoodById(UUID.fromString(foodId));
  }


  @GetMapping("")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindFoodsResponseDTO.class)))
  })
  public PaginationDTO<FoodDTO> findFoods(@Paging PagingDTO paging,
      @ParameterObject FindFoodsQuery query) {
    return foodService.findFoods(paging, query);
  }

  @PostMapping("")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ResponseWithIdDTO.class)))
  })
  public ResponseWithIdDTO createFood(@RequestBody CreateFoodDTO body) {

    return foodService.createFoods(body);
  }

  @PatchMapping("{foodId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = EmptyResponseDTO.class, contentMediaType = "application/json")))
  })
  public void updateFood(@PathVariable("foodId") String foodId, @RequestBody UpdateFoodDTO body) {
    foodService.updateFood(UUID.fromString(foodId), body);
  }


  @DeleteMapping("{foodId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = EmptyResponseDTO.class, contentMediaType = "application/json")))
  })
  public void deleteFood(@PathVariable("foodId") String foodId) {
    foodService.deleteFood(UUID.fromString(foodId));
  }


}

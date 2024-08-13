package kyoongdev.body_times.modules.meal;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyoongdev.body_times.common.annotations.GetUser;
import kyoongdev.body_times.common.dto.EmptyResponseDTO;
import kyoongdev.body_times.common.dto.ResponseWithIdDTO;
import kyoongdev.body_times.common.paging.PaginationDTO;
import kyoongdev.body_times.common.paging.Paging;
import kyoongdev.body_times.common.paging.PagingDTO;
import kyoongdev.body_times.modules.meal.dto.CreateMealDTO;
import kyoongdev.body_times.modules.meal.dto.MealDTO;
import kyoongdev.body_times.modules.meal.dto.UpdateMealDTO;
import kyoongdev.body_times.modules.meal.dto.query.FindMealQuery;
import kyoongdev.body_times.modules.user.CustomUserDetail;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "식사")
@RequestMapping("/meals")
@RestController
@RequiredArgsConstructor
public class MealController {

  private final MealService mealService;


  @GetMapping()
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('USER')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindMealsResponseDTO.class)))
  })
  public PaginationDTO<MealDTO> findMeals(@GetUser CustomUserDetail user, @Paging PagingDTO paging,
      @ParameterObject FindMealQuery query) {
    return mealService.findMyMeals(user.getId(), paging, query);
  }


  @GetMapping("{mealId}/detail")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('USER')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MealDTO.class)))
  })
  public MealDTO findMeal(@PathVariable(value = "mealId") String mealId,
      @GetUser CustomUserDetail user) {
    return mealService.findMyMealById(mealId, user.getId());
  }

  @PostMapping("")
  @SecurityRequirement(name = "Bearer Authentication")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ResponseWithIdDTO.class)))
  })
  @PreAuthorize("hasAuthority('USER')")
  public ResponseWithIdDTO createMeal(@GetUser CustomUserDetail user,
      @RequestBody CreateMealDTO body) {

    return mealService.createMeal(user.getId(), body);
  }

  @PatchMapping("{mealId}")
  @SecurityRequirement(name = "Bearer Authentication")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = EmptyResponseDTO.class)))
  })
  @PreAuthorize("hasAuthority('USER')")
  public void updateMeal(@GetUser CustomUserDetail user, @PathVariable("mealId") String mealId,
      @RequestBody UpdateMealDTO body) {
    mealService.updateMeal(mealId, user.getId(), body);
  }

  @DeleteMapping("{mealId}")
  @SecurityRequirement(name = "Bearer Authentication")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = EmptyResponseDTO.class)))
  })
  @PreAuthorize("hasAuthority('USER')")
  public void deleteMeal(@GetUser CustomUserDetail user, @PathVariable("mealId") String mealId) {
    mealService.deleteMeal(mealId, user.getId());

  }

}

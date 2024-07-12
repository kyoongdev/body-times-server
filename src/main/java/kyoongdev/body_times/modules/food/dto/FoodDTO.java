package kyoongdev.body_times.modules.food.dto;

import kyoongdev.body_times.modules.food.entities.Food;
import lombok.Builder;


@Builder

public class FoodDTO {

  private String id;
  private String name;
  private Double carbo;
  private Double protien;
  private Double fat;
  private Double calorie;


  public static FoodDTO fromEntity(Food food) {
    return FoodDTO.builder().id(food.getId().toString()).name(food.getName()).carbo(food.getCarbo())
        .protien(food.getProtein()).fat(food.getFat()).calorie(food.getCalorie()).build();
  }

}

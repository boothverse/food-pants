package org.boothverse.foodpants.persistence;

import java.util.List;

public class Recipe extends Food {
  private String instructions;
  private List<FoodInstance> ingredients;
  private Double servings;

  public Recipe(String id, String name, FoodGroup foodGroup, NutritionDescriptor nutrition, String instructions, List<FoodInstance> ingredients, Double servings) {
    super(id, name, foodGroup, nutrition);
    this.instructions = instructions;
    this.ingredients = ingredients;
    this.servings = servings;
  }
}

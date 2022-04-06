package org.boothverse.foodpants.business.services;

import lombok.Getter;
import org.boothverse.foodpants.persistence.FoodInstance;

import java.util.Map;

public abstract class FoodInstanceService {
  @Getter
  protected Map<String, FoodInstance> items;
}

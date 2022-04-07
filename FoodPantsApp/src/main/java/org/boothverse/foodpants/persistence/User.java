package org.boothverse.foodpants.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;

@AllArgsConstructor
@Getter @Setter
public class User {
  protected String name;
  protected Quantity<Mass> weight;
  protected String gender;
  protected Quantity<Length> height;
}

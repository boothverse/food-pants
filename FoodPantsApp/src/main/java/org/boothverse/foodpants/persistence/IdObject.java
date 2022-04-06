package org.boothverse.foodpants.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class IdObject {
  @Getter
  private final String id;
}
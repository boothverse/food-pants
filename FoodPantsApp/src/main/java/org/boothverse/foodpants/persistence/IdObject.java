package org.boothverse.foodpants.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class IdObject {
    @Getter
    protected final String id;
}
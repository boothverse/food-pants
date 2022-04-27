package org.boothverse.foodpants.persistence;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @ToString
public abstract class IdObject {
    @Getter @Setter
    protected String id;
}
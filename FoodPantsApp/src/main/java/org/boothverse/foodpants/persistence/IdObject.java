package org.boothverse.foodpants.persistence;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @ToString
@EqualsAndHashCode
public abstract class IdObject {
    @Getter @Setter
    protected String id;
}
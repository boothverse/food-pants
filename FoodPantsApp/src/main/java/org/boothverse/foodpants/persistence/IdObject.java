package org.boothverse.foodpants.persistence;

import lombok.*;

/**
 * An abstract class which represents an object id. Used in other persistence objects.
 */
@AllArgsConstructor @NoArgsConstructor @ToString
@EqualsAndHashCode
public abstract class IdObject {
    @Getter @Setter
    protected String id;
}
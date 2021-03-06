package org.boothverse.foodpants.persistence;

import lombok.*;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.util.Date;

/**
 * An object representing a user
 */
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class User {
    protected String name;
    protected String gender;
    protected Quantity<Length> height;
    protected Quantity<Mass> weight;
    protected Date dob;
}

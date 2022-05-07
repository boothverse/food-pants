package org.boothverse.foodpants.persistence;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * An object representing a nutrition report
 */
@Getter @NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReportPeriod extends IdObject {

    private Date startDate;
    private Date endDate;

    /**
     * Creates a new ReportPeriod
     *
     * @param id the id of the report period
     * @param startDate the start dat eof the report period
     * @param endDate the end date of the report period
     */
    public ReportPeriod(String id, Date startDate, Date endDate) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

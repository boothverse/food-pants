package org.boothverse.foodpants.persistence;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter @NoArgsConstructor
@EqualsAndHashCode
public class ReportPeriod extends IdObject {

    private Date startDate;
    private Date endDate;

    /**
     * Creates a new ReportPeriod
     *
     * @param id
     * @param startDate
     * @param endDate
     */
    public ReportPeriod(String id, Date startDate, Date endDate) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

package org.boothverse.foodpants.persistence;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter @NoArgsConstructor
//@JsonTypeName("reportperiod")
public class ReportPeriod extends IdObject {

    Date startDate;
    Date endDate;

    public ReportPeriod(String id, Date startDate, Date endDate) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

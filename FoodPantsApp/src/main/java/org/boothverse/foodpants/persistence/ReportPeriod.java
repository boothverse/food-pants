package org.boothverse.foodpants.persistence;

import lombok.Getter;

import java.util.Date;

@Getter
public class ReportPeriod extends IdObject {

    Date startDate;
    Date endDate;

    public ReportPeriod(String id, Date startDate, Date endDate) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

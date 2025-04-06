package com.admin.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Date;

@Getter
@Embeddable
public class PeriodDate {
    @Column(name = "period_start_date")
    private Date startDate;

    @Column(name = "period_end_date")
    private Date endDate;

    public void end() {
        this.endDate = new Date();
    }

    protected PeriodDate() {
        this.startDate = new Date();
    }

    public static PeriodDate start() {
        return new PeriodDate();
    }
}

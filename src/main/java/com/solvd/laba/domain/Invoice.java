package com.solvd.laba.domain;

import java.util.Date;

public class Invoice {

    private Long id;
    private Double amount;
    private Date issueDate;



    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Double getAmount() {

        return amount;
    }

    public void setAmount(Double amount) {

        this.amount = amount;
    }

    public Date getIssueDate() {

        return issueDate;
    }

    public void setIssueDate(Date issueDate) {

        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", amount=" + amount +
                ", issueDate=" + issueDate +
                '}';
    }
}

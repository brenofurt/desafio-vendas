package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SaleReportProjectionDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SaleReportProjectionDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}

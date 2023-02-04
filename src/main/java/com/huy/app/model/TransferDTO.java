package com.huy.app.model;



import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Immutable
@Subselect(value = "SELECT t.id as id,\n" +
                   "       t.amount,\n" +
                   "       t.feeAmount,\n" +
                   "       t.feeRate,\n" +
                   "       t.totalAmount,\n" +
                   "       c.fullName  as sentCustomer,\n" +
                   "       c2.fullName as receivedCustomer\n" +
                   "FROM transfers t\n" +
                   "         JOIN customers c on c.id = t.sentCustomer_id\n" +
                   "         JOIN customers c2 on c2.id = t.receivedCustomer_id")
public class TransferDTO {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "sentCustomer")
   private String sentCustomer;
    @Column(name = "receivedCustomer")
   private String receivedCustomer;

    private double feeRate;
    private double amount;
    private double feeAmount;
    private double totalAmount;

    public TransferDTO() {
    }

    public TransferDTO(Long id, String sentCustomer, String receivedCustomer, double feeRate, double amount, double feeAmount, double totalAmount) {
        this.id = id;
        this.sentCustomer = sentCustomer;
        this.receivedCustomer = receivedCustomer;
        this.feeRate = feeRate;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSentCustomer() {
        return sentCustomer;
    }

    public void setSentCustomer(String sentCustomer) {
        this.sentCustomer = sentCustomer;
    }

    public String getReceivedCustomer() {
        return receivedCustomer;
    }

    public void setReceivedCustomer(String receivedCustomer) {
        this.receivedCustomer = receivedCustomer;
    }

    public double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(double feeRate) {
        this.feeRate = feeRate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

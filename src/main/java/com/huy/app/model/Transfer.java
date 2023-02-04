package com.huy.app.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @PrimaryKeyJoinColumn(name = "senderId", foreignKey = @ForeignKey(name = "fk_sender_customer"))
    @NotNull(message = "The sender is not valid")
    private Customer sentCustomer;

    @ManyToOne()
    @PrimaryKeyJoinColumn(name = "recipientId", foreignKey = @ForeignKey(name = "fk_recipient_customer"))
    @NotNull(message = "The recipient is not valid")
    private Customer receivedCustomer;
    @NotNull(message = "The fee rate is not valid")
    @Range(min = 0, max = 50,message = "Rate must be between 0 and 50%")
    private double feeRate;
    @NotNull(message = "The amount is not valid")
    @Range(min = 10000,max = 1000000000, message = "Transfer amount must be from 10000 to 1 billion")
    private double amount;
    @NotNull(message = "The amount is not valid")
    @Range(min = 1,max =50 , message = "Transfer fee must be from 1 to 50 percent")
    private double feeAmount;
    @NotNull(message = "The total amount is not valid")
    private double totalAmount;

    public Transfer() {
    }

    public Transfer(Long id, Customer sentCustomer, Customer receivedCustomer, double feeRate, double amount, double feeAmount, double totalAmount) {
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

    public Customer getSentCustomer() {
        return sentCustomer;
    }

    public void setSentCustomer(Customer sentCustomer) {
        this.sentCustomer = sentCustomer;
    }

    public Customer getReceivedCustomer() {
        return receivedCustomer;
    }

    public void setReceivedCustomer(Customer receivedCustomer) {
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

package com.huy.app.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    @Column(name = "fees")
    private BigDecimal feeRate;

    @NotNull(message = "The amount is not valid")
    @Range(min = 10000,max = 1000000000, message = "Transfer amount must be from 10000 to 1 billion")
    private BigDecimal amount;

    @NotNull(message = "The amount is not valid")
    @Column(name = "fees_amount")
    private BigDecimal feeAmount;

    @NotNull(message = "The total amount is not valid")
    @Column(name = "transfer_amount")
    private BigDecimal totalAmount;

    public Transfer() {
    }

    public Transfer(Long id, Customer sentCustomer, Customer receivedCustomer, BigDecimal feeRate, BigDecimal amount, BigDecimal feeAmount, BigDecimal totalAmount) {
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

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}

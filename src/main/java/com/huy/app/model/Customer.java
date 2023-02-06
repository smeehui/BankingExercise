package com.huy.app.model;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotEmpty(message = "Full name can not be empty")
    @Valid
    @NotBlank(message = "Full name can not be empty")
    @Column(name = "full_name")
    private String fullName;

    //    @NotEmpty(message = "Email can not be empty")
    @Valid
    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email is not valid")
    private String email;

    //    @NotEmpty(message = "Phone can not be empty")
    @Valid
    @NotBlank(message = "Phone can not be empty")
    private String phone;

    //    @NotEmpty(message = "Address can not be empty")
    @Valid
    @NotBlank(message = "Address can not be empty")
    private String address;

    @Valid
    @Range(min=0,max = 999999999, message = "Balance is not valid")
    private BigDecimal balance;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
     private boolean deleted;

    public Customer() {
    }

    public Customer(Long id,
                    @Valid @NotBlank String fullName,
                    @Valid @NotBlank @Email String email,
                    @Valid @NotBlank String phone,
                    @Valid @NotBlank String address,
                    BigDecimal balance,
                    boolean deleted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}

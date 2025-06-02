package com.data.ss17.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Column(length = 1000)
    private String listProduct;

    private String recipientName;
    private String phoneNumber;
    private String address;

    private BigDecimal totalMoney;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    private String status;
}

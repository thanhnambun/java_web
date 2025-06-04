package com.data.ss18.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;

    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;

    private String recipientName;
    private String phoneNumber;
    private String address;

    private BigDecimal totalMoney;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    private String status;
}

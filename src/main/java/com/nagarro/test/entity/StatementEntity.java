package com.nagarro.test.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "statement")
@Data
public class StatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "datefield")
    private String dateField;

    @Column(name= "amount")
    private String amount;



}

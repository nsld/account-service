package com.nsld.snekkanti.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name="ID")
    private int id;

    @JsonProperty("firstName")
    @Column(name="FIRST_NAME")
    private String firstName;

    @JsonProperty("secondName")
    @Column(name="SECOND_NAME")
    private String secondName;

    @JsonProperty("accountNumber")
    @Column(name="ACCOUNT_NUMBER")
    private Integer accountNumber;
}

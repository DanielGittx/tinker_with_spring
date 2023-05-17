package com.gittx.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
//@NoArgsConstructor
@Data
@Entity
@Table(	name = "kyc")
public class Kyc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    byte[] national_id;
    byte[] kra_pin;
    byte[] logbook;
    byte[] bank_statement;
    byte[] mpesa_statement;
    byte[] postdated_cheque;
    byte[] insurance;
    byte[] importation_document;
    byte[] dealer_proforma_invoice;
    Kyc() {
        this.date_created = new Date();
    }
    Date date_created;
    Date date_modified;


    @JsonIgnore
    @OneToOne(mappedBy = "kyc")
    Application application;



}

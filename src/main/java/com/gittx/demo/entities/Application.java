package com.gittx.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.time.DateTimeException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(	name = "application")
public class Application {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        Long kyc_id;
        String first_name ;
        String middle_name ;
        String last_name ;
        @Enumerated(EnumType.STRING)
        Stat status ;
        String national_id ;
        String kra_pin ;
        double car_valuation ;
        String latitude ;
        String longitude ;
        String kyc_docs ;
        Date date_created ;
        Date date_modified;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(
            name = "kyc_id", referencedColumnName = "id",
            updatable = false,
            insertable = false
            )
         Kyc kyc;

}

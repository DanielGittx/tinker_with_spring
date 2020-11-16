package com.gittx.demo.data_objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountsDto {
    private String name;
    private long   id;
    private String status;
    private String branch;
    private Date dateCreated;

}

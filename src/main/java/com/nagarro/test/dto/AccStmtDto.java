package com.nagarro.test.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccStmtDto {

    private String accountNumber;
    private List<StmtDto> statement;
}

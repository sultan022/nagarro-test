package com.nagarro.test.service;

import com.nagarro.test.dto.AccStmtDto;
import com.nagarro.test.entity.AccountEntity;
import com.nagarro.test.mapper.AccountEntityToDtoMapper;
import com.nagarro.test.repo.AccountRepo;
import com.nagarro.test.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccStmtService {

    private final AccountEntityToDtoMapper mapper;
    private final AccountRepo accountRepo;
    public AccStmtDto getAccountStmt(Long accId, String fromDate, String toDate, String fromAmount, String toAmount) throws Exception {

      Optional<AccountEntity> entity=   accountRepo.findById(accId);

        AccStmtDto accStmtDto=  mapper.sourceToDestination(entity.orElse(null));
        if(accStmtDto==null)
            return null;

        if(fromDate== null && toDate==null
                && fromAmount==null && toAmount==null){

            //get past three months statement
            LocalDate startDate= LocalDate.now().minusMonths(3);
            LocalDate endDate = LocalDate.now();

            accStmtDto.setStatement(accStmtDto.getStatement().stream()
                    .filter(one-> (one.getDate().isAfter(startDate) || one.getDate().compareTo(startDate)==0) &&
                            (one.getDate().isBefore(endDate) || one.getDate().compareTo(endDate)==0))
                    .collect(Collectors.toList()));


        }


        //apply date range
        if(fromDate != null && toDate!=null) {

            LocalDate startDate = LocalDate.parse(
                    DateUtil.changeStringDateFormat(fromDate, "dd.MM.yyyy", "yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(
                    DateUtil.changeStringDateFormat(toDate, "dd.MM.yyyy", "yyyy-MM-dd"));

                accStmtDto.setStatement(accStmtDto.getStatement().stream()
                        .filter(one-> (one.getDate().isAfter(startDate) || one.getDate().compareTo(startDate)==0) &&
                                (one.getDate().isBefore(endDate) || one.getDate().compareTo(endDate)==0))
                        .collect(Collectors.toList()));

        }

        //apply amount range
        if(fromAmount!=null && toAmount!=null){

            BigDecimal startAmount = new BigDecimal(fromAmount);
            BigDecimal endAmount = new BigDecimal(toAmount);

            accStmtDto.setStatement(accStmtDto.getStatement().stream()
                    .filter(one-> (one.getAmount().compareTo(startAmount)==0 || one.getAmount().compareTo(startAmount)==1) &&
                            (one.getAmount().compareTo(endAmount)==0 || one.getAmount().compareTo(endAmount)==-1))
                    .collect(Collectors.toList()));

        }

        return accStmtDto;
    }


    public AccStmtDto getAccountStmt(Long accId) throws Exception {

        Optional<AccountEntity> entity=   accountRepo.findById(accId);

        AccStmtDto accStmtDto=  mapper.sourceToDestination(entity.orElse(null));
        if(accStmtDto==null)
            return null;


            //get past three months statement
            LocalDate startDate= LocalDate.now().minusMonths(3);
            LocalDate endDate = LocalDate.now();

            accStmtDto.setStatement(accStmtDto.getStatement().stream()
                    .filter(one-> (one.getDate().isAfter(startDate) || one.getDate().compareTo(startDate)==0) &&
                            (one.getDate().isBefore(endDate) || one.getDate().compareTo(endDate)==0))
                    .collect(Collectors.toList()));



        return accStmtDto;
    }
}

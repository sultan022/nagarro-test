package com.nagarro.test.mapper;

import com.nagarro.test.dto.AccStmtDto;
import com.nagarro.test.dto.StmtDto;
import com.nagarro.test.entity.AccountEntity;
import com.nagarro.test.util.DateUtil;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class AccountEntityToDtoMapperImpl implements AccountEntityToDtoMapper {
    @Override
    public AccStmtDto sourceToDestination(AccountEntity source) throws NoSuchAlgorithmException {
        if (source == null)
            return null;

        AccStmtDto obj = new AccStmtDto();

        obj.setAccountNumber(generateHash(source.getAccountNumber()));

        if (source.getAccStmtList() == null)
            return obj;

        obj.setStatement(new ArrayList<>());

        source.getAccStmtList().forEach(one->{

            obj.getStatement().add(new StmtDto(LocalDate.parse(
                    DateUtil.changeStringDateFormat(one.getDateField(), "dd.MM.yyyy", "yyyy-MM-dd")),
                    new BigDecimal(one.getAmount())));
        });

        return obj;
    }

    private String generateHash(String accountNumber) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(accountNumber.getBytes());
        byte[] digest = md.digest();
        String hashed = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hashed;
    }
}

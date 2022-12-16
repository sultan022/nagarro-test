package com.nagarro.test.mapper;

import com.nagarro.test.dto.AccStmtDto;
import com.nagarro.test.entity.AccountEntity;
import org.mapstruct.Mapper;

import java.security.NoSuchAlgorithmException;

@Mapper
public interface AccountEntityToDtoMapper {

    AccStmtDto sourceToDestination(AccountEntity source) throws NoSuchAlgorithmException;
}
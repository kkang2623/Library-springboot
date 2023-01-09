package com.korit.library.service;

import com.korit.library.exception.CustomValidationException;
import com.korit.library.repository.AccountRepository;
import com.korit.library.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void duplicateUsername(String username) {
        UserDto user = accountRepository.findUserByUsername(username);
        log.info("{}", user);
        log.info("ROLE_DTL{}", user.getRoleDtlDto());
        log.info("ROLE_MST{}", user.getRoleDtlDto().get(0));
        log.info("ROLE_MST{}", user.getRoleDtlDto().get(1));
    }

    public void compareToPassword(String password, String repassword) {
        if(!password.equals(repassword)){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("repassword", "비밀번호가 일치하지 않습니다.");

            throw new CustomValidationException(errorMap);
        }
    }

}

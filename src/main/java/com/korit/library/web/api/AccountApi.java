package com.korit.library.web.api;

import com.korit.library.aop.annotation.ValidAspect;
import com.korit.library.service.AccountService;
import com.korit.library.web.dto.CMRespDto;
import com.korit.library.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;




@RestController
@RequestMapping("/api/account")
public class AccountApi {
    @Autowired
    private AccountService accountService;

    @ValidAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        // 회원가입 정보를 받는다. // 밸리데이션을 체크하기 위해서는 바인딩리절트를 입력한다.
        return ResponseEntity
                .created(null)
                .body(new CMRespDto<>("Create a new User", null));
    }
}
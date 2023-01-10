package com.korit.library.web.api;

import com.korit.library.aop.annotation.ValidAspect;
import com.korit.library.security.PrincipalDetails;
import com.korit.library.service.AccountService;
import com.korit.library.web.dto.CMRespDto;
import com.korit.library.web.dto.UserDto;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@Api(tags = {"Account Rest API Controller"})
@RestController
@RequestMapping("/api/account")
public class AccountApi {
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "회원가입",notes = "회원가입 요청 메소드")
    @ValidAspect
    @PostMapping("/register")
    public ResponseEntity<? extends CMRespDto<? extends UserDto>> register(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        // 회원가입 정보를 받는다. // 밸리데이션을 체크하기 위해서는 바인딩리절트를 입력한다.

        accountService.duplicateUsername(userDto.getUsername());
        accountService.compareToPassword(userDto.getPassword(), userDto.getRepassword());

        UserDto user = accountService.registerUser(userDto);

        return ResponseEntity
                .created(URI.create("/api/account/user/" + user.getUserId()))
                .body(new CMRespDto<>(HttpStatus.CREATED.value(), "Create a new User", user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 식별 코드", required = true, dataType = "int")
//            밑에 @ApiParam 안 적어도 된다 // 클린코드
    })
    @ApiResponses({
    @ApiResponse(code = 400, message ="클라이언트가 잘못했음."),
    @ApiResponse(code = 401, message ="클라이언트가 잘못했음2.")
    })
    @ApiOperation(value = "회원가입하기이",notes = "회원가이이입")
    @GetMapping("/user/{userId}")
    public ResponseEntity<? extends CMRespDto<? extends UserDto>> getUser(
//            @ApiParam(value = "사용자 식별 코드")
            @PathVariable int userId) {
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Success", accountService.getUser(userId)));
    }

    @ApiOperation(value = "Get Principal", notes = "로그인 된 사용자 정보 가져오기")
    @GetMapping("/principal")
    public ResponseEntity<CMRespDto<? extends PrincipalDetails>> getPrincipalDetails(@ApiParam(hidden = true) @AuthenticationPrincipal PrincipalDetails principalDetails){

        principalDetails.getAuthorities().forEach(role ->{
            log.info("로그인 된 사용자의 권한 : {}", role.getAuthority());
        });

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Success", principalDetails));
    }

}
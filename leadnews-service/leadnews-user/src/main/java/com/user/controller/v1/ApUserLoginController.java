package com.user.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Api(value = "用户登录value",tags = "用户登录tags")

@RestController
@RequestMapping("/api/v1/login")
public class ApUserLoginController {
@Autowired
//http://localhost:8801/app/user/api/v1/login/login_auth/
private ApUserService apUserService;
//"/api/v1/login/login_auth/"

    @PostMapping("/login_auth")
    @ApiOperation("用户登陆 login")
    public ResponseResult login(@RequestBody LoginDto dto){
        return apUserService.login(dto);
    }
}

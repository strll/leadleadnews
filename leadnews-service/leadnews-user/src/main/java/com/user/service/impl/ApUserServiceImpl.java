package com.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.common.AppJwtUtil;
import com.user.mapper.ApUserMapper;
import com.user.service.ApUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService  {


    @Override
    public ResponseResult login(LoginDto dto) {
        //1 正常登陆
        if(StringUtils.isNotBlank(dto.getPhone())&&StringUtils.isNotBlank(dto.getPassword())){
            //1.1 获取手机号密码 如果为空就报错
            //1.2 查询数据库的盐和密码 然后密码加盐比对数据库的密码
            String dtopassword = dto.getPassword();
            String dtophone = dto.getPhone();
            QueryWrapper<ApUser> apUserQueryWrapper = new QueryWrapper<>();
            apUserQueryWrapper.eq("phone", dtophone);
            ApUser one = getOne(apUserQueryWrapper);

            String s = DigestUtils.md5DigestAsHex((dtopassword + one.getSalt()).getBytes());

            if(!s.equals(one.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            //1.3 成功就返回登陆的token和dto dto密码和盐制空
            String token = AppJwtUtil.getToken(one.getId().longValue());
            one.setSalt("");
            one.setPassword("");
            Map<String,Object> map=new HashMap();
            map.put("token",token);
            map.put("user",one);
            return ResponseResult.okResult(map);

        }else{
            //2 游客登陆
            Map<String,Object> map=new HashMap();
            String token = AppJwtUtil.getToken(0l);
            map.put("token",token);
            return ResponseResult.okResult(map);
        }








    }
}

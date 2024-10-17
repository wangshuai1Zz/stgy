package WebAdmin.service.impl;

import Common.exception.MyApiError;
import Common.service.RedisService;
import Common.utils.jwtUtils;
import WebAdmin.service.LoginService;
import WebAdmin.service.SystemUserService;
import WebAdmin.vo.login.CaptchaVo;
import WebAdmin.vo.login.LoginVo;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import model.entity.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static Common.common.Static.MD5_SALTY;

@Service
public class LoginServiceImpl implements LoginService {
    private final RedisService redisService;

    private final SystemUserService systemUserService;

    public LoginServiceImpl(RedisService redisService, SystemUserService systemUserService) {
        this.redisService = redisService;
        this.systemUserService = systemUserService;
    }

    @Override
    public CaptchaVo getCaptcha() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(130, 40, 4, 3);
        captcha.createCode();
        String imageBase64Data = captcha.getImageBase64Data();
        String uuid = STR."Login:\{IdUtil.simpleUUID()}";
        redisService.set(uuid,captcha.getCode(),180);
        return new CaptchaVo(imageBase64Data,uuid);
    }

    @Override
    public String login(LoginVo loginVo) {
        boolean empty = ObjectUtils.isEmpty(loginVo);
        if (empty) throw new MyApiError("err",200);
        String string = redisService.get(loginVo.getCaptchaKey(), String.class);
        boolean verify = StringUtils.equalsIgnoreCase(loginVo.getCaptchaCode(),string);
        if (!verify){
            redisService.del(loginVo.getCaptchaKey());
            throw new MyApiError("err",200);
        }
        redisService.del(loginVo.getCaptchaKey());
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        if (!StringUtils.isAllBlank(username,password)){
            String newPassword = DigestUtil.md5Hex(password+MD5_SALTY);
            LambdaQueryWrapper<SystemUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SystemUser::getUsername,username).eq(SystemUser::getPassword,newPassword);
            SystemUser user = systemUserService.getOne(lambdaQueryWrapper);
            if (user==null) throw new MyApiError("err",200);
            return jwtUtils.genAccessToken(user.getId());
        }
        throw new MyApiError("err",200);
    }
}

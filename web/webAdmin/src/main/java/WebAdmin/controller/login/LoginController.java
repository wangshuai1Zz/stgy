package WebAdmin.controller.login;

import Common.utils.ThreadUtils;
import WebAdmin.service.LoginService;
import WebAdmin.service.SystemUserService;
import WebAdmin.vo.login.CaptchaVo;
import WebAdmin.vo.login.LoginVo;
import WebAdmin.vo.system.user.SystemUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.SystemUser;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

@Tag(name = "后台管理系统登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {
    private final LoginService loginService;
    private final SystemUserService systemUserService;

    public LoginController(LoginService loginService, SystemUserService systemUserService) {
        this.loginService = loginService;
        this.systemUserService = systemUserService;
    }

    @Operation(summary = "获取图形验证码")
    @GetMapping("login/captcha")
    public Result<CaptchaVo> getCaptcha() {
        return Result.ok(loginService.getCaptcha());
    }

    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        return Result.ok(loginService.login(loginVo));
    }

    @Operation(summary = "获取登陆用户个人信息")
    @GetMapping("info")
    public Result<SystemUserInfoVo> info() {
        Long userid = ThreadUtils.getLocal();
        SystemUser user = systemUserService.getById(userid);
        SystemUserInfoVo systemUserInfoVo = new SystemUserInfoVo();
        systemUserInfoVo.setName(user.getName());
        systemUserInfoVo.setAvatarUrl(user.getAvatarUrl());
        ThreadUtils.delLocal();
        return Result.ok(systemUserInfoVo);
    }
}
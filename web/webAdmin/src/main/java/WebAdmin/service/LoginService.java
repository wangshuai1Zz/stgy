package WebAdmin.service;

import WebAdmin.vo.login.CaptchaVo;
import WebAdmin.vo.login.LoginVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);
}

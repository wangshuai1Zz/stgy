package WebAdmin.service;

import WebAdmin.vo.system.user.SystemUserItemVo;
import WebAdmin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import model.entity.SystemUser;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface SystemUserService extends IService<SystemUser> {

    IPage<SystemUserItemVo> getPage(long current, long size, SystemUserQueryVo queryVo);

    SystemUserItemVo getUser(Long id);
}

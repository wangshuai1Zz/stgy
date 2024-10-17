package WebAdmin.mapper;

import WebAdmin.vo.system.user.SystemUserItemVo;
import WebAdmin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import model.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.SystemUser
*/
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    List<SystemUserItemVo> getPage(IPage<SystemUserItemVo> page, SystemUserQueryVo query);

    SystemUserItemVo getUser(Long id);
}





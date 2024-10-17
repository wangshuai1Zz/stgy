package WebAdmin.service.impl;

import Common.exception.MyApiError;
import WebAdmin.mapper.SystemUserMapper;
import WebAdmin.service.SystemUserService;
import WebAdmin.vo.system.user.SystemUserItemVo;
import WebAdmin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import model.entity.SystemUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

    @Override
    public IPage<SystemUserItemVo> getPage(long current, long size, SystemUserQueryVo queryVo) {
        IPage<SystemUserItemVo> page = new Page<>(current,size);
        List<SystemUserItemVo> userList = baseMapper.getPage(page,queryVo);
        if (userList.isEmpty()) throw new MyApiError("err",200);
        userList.forEach(user -> user.setPassword(""));
        page.setRecords(userList);
        return page;
    }

    @Override
    public SystemUserItemVo getUser(Long id) {
        return baseMapper.getUser(id);
    }
}





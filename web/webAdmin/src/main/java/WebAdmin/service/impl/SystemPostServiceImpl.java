package WebAdmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.SystemPost;
import WebAdmin.service.SystemPostService;
import WebAdmin.mapper.SystemPostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【system_post(岗位信息表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class SystemPostServiceImpl extends ServiceImpl<SystemPostMapper, SystemPost>
    implements SystemPostService{

    @Override
    public IPage<SystemPost> getPage(long current, long size) {
        IPage<SystemPost> page = new Page<>(current,size);
        List<SystemPost> list = baseMapper.getList(page);
        page.setRecords(list);
        return page;
    }
}





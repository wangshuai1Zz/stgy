package WebAdmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import model.entity.SystemPost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【system_post(岗位信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface SystemPostService extends IService<SystemPost> {

    IPage<SystemPost> getPage(long current, long size);
}

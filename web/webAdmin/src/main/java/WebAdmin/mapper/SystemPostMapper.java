package WebAdmin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import model.entity.SystemPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【system_post(岗位信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.SystemPost
*/
public interface SystemPostMapper extends BaseMapper<SystemPost> {

    List<SystemPost> getList(IPage<SystemPost> page);
}





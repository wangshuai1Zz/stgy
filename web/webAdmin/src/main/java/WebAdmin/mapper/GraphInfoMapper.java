package WebAdmin.mapper;

import WebAdmin.vo.graph.GraphVo;
import model.entity.GraphInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> useIdByList(Long id);

    List<GraphVo> useIdByRoomList(Long id);
}





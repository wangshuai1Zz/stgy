package WebAdmin.service;

import WebAdmin.vo.graph.GraphVo;
import model.entity.GraphInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface GraphInfoService extends IService<GraphInfo> {
    List<GraphVo> getGraphList(Long id);

    List<GraphVo> getRoomGraphList(Long id);
}

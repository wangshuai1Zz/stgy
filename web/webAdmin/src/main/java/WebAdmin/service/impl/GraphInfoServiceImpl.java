package WebAdmin.service.impl;

import WebAdmin.mapper.GraphInfoMapper;
import WebAdmin.service.GraphInfoService;
import WebAdmin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.GraphInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class GraphInfoServiceImpl extends ServiceImpl<GraphInfoMapper, GraphInfo>
    implements GraphInfoService {
    @Override
    public List<GraphVo> getGraphList(Long id) {
        return baseMapper.useIdByList(id);
    }

    @Override
    public List<GraphVo> getRoomGraphList(Long id) {
        return baseMapper.useIdByRoomList(id);
    }
}





package WebAdmin.service.impl;

import WebAdmin.mapper.RoomLabelMapper;
import WebAdmin.service.RoomLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.LabelInfo;
import model.entity.RoomLabel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_label(房间&标签关联表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class RoomLabelServiceImpl extends ServiceImpl<RoomLabelMapper, RoomLabel>
    implements RoomLabelService {

    @Override
    public List<LabelInfo> getLabelList(Long id) {
        return baseMapper.getLabelListById(id);
    }
}





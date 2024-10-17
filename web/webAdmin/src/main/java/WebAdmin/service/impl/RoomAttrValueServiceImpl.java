package WebAdmin.service.impl;

import WebAdmin.mapper.RoomAttrValueMapper;
import WebAdmin.service.RoomAttrValueService;
import WebAdmin.vo.attr.AttrValueVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.RoomAttrValue;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_attr_value(房间&基本属性值关联表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class RoomAttrValueServiceImpl extends ServiceImpl<RoomAttrValueMapper, RoomAttrValue>
    implements RoomAttrValueService {

    @Override
    public List<AttrValueVo> getAttrById(Long id) {
        return baseMapper.getAttrById(id);
    }
}





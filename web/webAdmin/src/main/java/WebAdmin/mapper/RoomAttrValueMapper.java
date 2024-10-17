package WebAdmin.mapper;

import WebAdmin.vo.attr.AttrValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.entity.RoomAttrValue;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_attr_value(房间&基本属性值关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.RoomAttrValue
*/
public interface RoomAttrValueMapper extends BaseMapper<RoomAttrValue> {

    List<AttrValueVo> getAttrById(Long id);
}





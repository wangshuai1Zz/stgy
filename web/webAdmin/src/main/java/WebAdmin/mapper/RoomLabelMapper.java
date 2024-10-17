package WebAdmin.mapper;

import model.entity.LabelInfo;
import model.entity.RoomLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_label(房间&标签关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.RoomLabel
*/
public interface RoomLabelMapper extends BaseMapper<RoomLabel> {

    List<LabelInfo> getLabelListById(Long id);
}





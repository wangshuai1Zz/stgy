package WebAdmin.service;

import model.entity.LabelInfo;
import model.entity.RoomLabel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_label(房间&标签关联表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface RoomLabelService extends IService<RoomLabel> {

    List<LabelInfo> getLabelList(Long id);
}

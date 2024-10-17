package WebAdmin.service;

import model.entity.FacilityInfo;
import model.entity.RoomFacility;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_facility(房间&配套关联表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface RoomFacilityService extends IService<RoomFacility> {

    List<FacilityInfo> getFacilityInfoList(Long id);
}

package WebAdmin.mapper;

import model.entity.FacilityInfo;
import model.entity.RoomFacility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_facility(房间&配套关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.RoomFacility
*/
public interface RoomFacilityMapper extends BaseMapper<RoomFacility> {

    List<FacilityInfo> getFacilityListById(Long id);
}





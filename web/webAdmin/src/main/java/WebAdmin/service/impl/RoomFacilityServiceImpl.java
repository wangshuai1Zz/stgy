package WebAdmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.FacilityInfo;
import model.entity.RoomFacility;
import WebAdmin.service.RoomFacilityService;
import WebAdmin.mapper.RoomFacilityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_facility(房间&配套关联表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class RoomFacilityServiceImpl extends ServiceImpl<RoomFacilityMapper, RoomFacility>
    implements RoomFacilityService{

    @Override
    public List<FacilityInfo> getFacilityInfoList(Long id) {
        return baseMapper.getFacilityListById(id);
    }
}





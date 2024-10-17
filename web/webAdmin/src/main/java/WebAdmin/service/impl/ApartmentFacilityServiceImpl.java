package WebAdmin.service.impl;

import WebAdmin.mapper.ApartmentFacilityMapper;
import WebAdmin.service.ApartmentFacilityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.ApartmentFacility;
import model.entity.FacilityInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_facility(公寓&配套关联表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class ApartmentFacilityServiceImpl extends ServiceImpl<ApartmentFacilityMapper, ApartmentFacility>
    implements ApartmentFacilityService {

    @Override
    public List<FacilityInfo> getFacilityInfoList(Long id) {
        return baseMapper.getFacilityByApartmentId(id);
    }
}





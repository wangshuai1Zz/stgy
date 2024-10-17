package WebAdmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.entity.ApartmentFacility;
import model.entity.FacilityInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_facility(公寓&配套关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.ApartmentFacility
*/
public interface ApartmentFacilityMapper extends BaseMapper<ApartmentFacility> {

    List<FacilityInfo> getFacilityByApartmentId(Long id);
}





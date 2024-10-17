package WebAdmin.mapper;

import WebAdmin.vo.fee.FeeValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.entity.ApartmentFeeValue;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_fee_value(公寓&杂费关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.ApartmentFeeValue
*/
public interface ApartmentFeeValueMapper extends BaseMapper<ApartmentFeeValue> {

    List<FeeValueVo> getFeeValueListByApartmentId(Long id);
}





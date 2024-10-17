package WebAdmin.service.impl;

import WebAdmin.mapper.ApartmentFeeValueMapper;
import WebAdmin.service.ApartmentFeeValueService;
import WebAdmin.vo.fee.FeeValueVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.ApartmentFeeValue;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_fee_value(公寓&杂费关联表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
    implements ApartmentFeeValueService {

    @Override
    public List<FeeValueVo> getApartMentFeeValuelist(Long id) {
        return baseMapper.getFeeValueListByApartmentId(id);
    }
}





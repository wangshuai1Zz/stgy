package WebAdmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.PaymentType;
import model.entity.RoomPaymentType;
import WebAdmin.service.RoomPaymentTypeService;
import WebAdmin.mapper.RoomPaymentTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_payment_type(房间&支付方式关联表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class RoomPaymentTypeServiceImpl extends ServiceImpl<RoomPaymentTypeMapper, RoomPaymentType>
    implements RoomPaymentTypeService{

    @Override
    public List<PaymentType> getPayList(Long id) {
        return baseMapper.getPaymentById(id);
    }
}





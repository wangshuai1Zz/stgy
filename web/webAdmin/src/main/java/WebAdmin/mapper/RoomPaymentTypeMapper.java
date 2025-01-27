package WebAdmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import model.entity.PaymentType;
import model.entity.RoomPaymentType;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_payment_type(房间&支付方式关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.RoomPaymentType
*/
public interface RoomPaymentTypeMapper extends BaseMapper<RoomPaymentType> {

    List<PaymentType> getPaymentById(Long id);
}





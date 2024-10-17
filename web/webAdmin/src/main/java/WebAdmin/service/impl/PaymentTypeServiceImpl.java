package WebAdmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.PaymentType;
import WebAdmin.service.PaymentTypeService;
import WebAdmin.mapper.PaymentTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
    implements PaymentTypeService{

}





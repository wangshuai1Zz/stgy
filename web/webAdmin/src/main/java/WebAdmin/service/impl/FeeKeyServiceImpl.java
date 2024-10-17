package WebAdmin.service.impl;

import WebAdmin.vo.fee.FeeKeyVo;
import model.entity.FeeKey;
import WebAdmin.mapper.FeeKeyMapper;
import WebAdmin.service.FeeKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey>
    implements FeeKeyService{

    @Override
    public List<FeeKeyVo> getAll() {
        return baseMapper.getAll();
    }
}





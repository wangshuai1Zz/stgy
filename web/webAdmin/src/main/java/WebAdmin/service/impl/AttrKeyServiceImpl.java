package WebAdmin.service.impl;

import WebAdmin.mapper.AttrKeyMapper;
import WebAdmin.service.AttrKeyService;
import WebAdmin.vo.attr.AttrKeyVo;
import model.entity.AttrKey;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
    implements AttrKeyService {

    @Override
    public List<AttrKeyVo> getAll() {
        return baseMapper.getAll();
    }
}





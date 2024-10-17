package WebAdmin.service.impl;

import WebAdmin.mapper.RoomLeaseTermMapper;
import WebAdmin.service.RoomLeaseTermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import model.entity.LeaseTerm;
import model.entity.RoomLeaseTerm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_lease_term(房间租期管理表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class RoomLeaseTermServiceImpl extends ServiceImpl<RoomLeaseTermMapper, RoomLeaseTerm>
    implements RoomLeaseTermService {

    @Override
    public List<LeaseTerm> getLeaseList(Long id) {
        return baseMapper.getLeaseListById(id);
    }
}





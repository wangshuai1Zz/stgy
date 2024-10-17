package WebAdmin.service;

import model.entity.LeaseTerm;
import model.entity.RoomLeaseTerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_lease_term(房间租期管理表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface RoomLeaseTermService extends IService<RoomLeaseTerm> {

    List<LeaseTerm> getLeaseList(Long id);
}

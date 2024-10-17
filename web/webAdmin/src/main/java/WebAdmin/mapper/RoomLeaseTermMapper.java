package WebAdmin.mapper;

import model.entity.LeaseTerm;
import model.entity.RoomLeaseTerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_lease_term(房间租期管理表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity model.RoomLeaseTerm
*/
public interface RoomLeaseTermMapper extends BaseMapper<RoomLeaseTerm> {

    List<LeaseTerm> getLeaseListById(Long id);
}





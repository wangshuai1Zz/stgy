package WebAdmin.service;

import WebAdmin.vo.room.RoomDetailVo;
import WebAdmin.vo.room.RoomItemVo;
import WebAdmin.vo.room.RoomQueryVo;
import WebAdmin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import model.entity.RoomInfo;
import model.enums.ReleaseStatus;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface RoomInfoService extends IService<RoomInfo> {

    void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);

    IPage<RoomItemVo> getPage(long current, long size, RoomQueryVo queryVo);

    void removeRoomById(Long id);

    RoomDetailVo getRoomById(Long id);

    void updateStatus(Long id, ReleaseStatus status);

    List<RoomInfo> getByApartmentId(Long id);
}

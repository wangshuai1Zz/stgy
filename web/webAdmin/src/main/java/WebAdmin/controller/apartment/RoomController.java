package WebAdmin.controller.apartment;

import WebAdmin.aop.AuthCheck;
import WebAdmin.service.RoomInfoService;
import WebAdmin.vo.room.RoomDetailVo;
import WebAdmin.vo.room.RoomItemVo;
import WebAdmin.vo.room.RoomQueryVo;
import WebAdmin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.RoomInfo;
import model.enums.ReleaseStatus;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {
    private final RoomInfoService roomInfoService;

    public RoomController(RoomInfoService roomInfoService) {
        this.roomInfoService = roomInfoService;
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        roomInfoService.saveOrUpdateRoom(roomSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size, RoomQueryVo queryVo) {
        return Result.ok(roomInfoService.getPage(current,size,queryVo));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(roomInfoService.getRoomById(id));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        roomInfoService.removeRoomById(id);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(Long id, ReleaseStatus status) {
        roomInfoService.updateStatus(id,status);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
        return Result.ok(roomInfoService.getByApartmentId(id));
    }

}



















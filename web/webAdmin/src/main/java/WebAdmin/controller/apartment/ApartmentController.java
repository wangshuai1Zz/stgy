package WebAdmin.controller.apartment;



import WebAdmin.aop.AuthCheck;
import WebAdmin.service.ApartmentInfoService;
import WebAdmin.service.RoomInfoService;
import WebAdmin.vo.apartment.ApartmentDetailVo;
import WebAdmin.vo.apartment.ApartmentItemVo;
import WebAdmin.vo.apartment.ApartmentQueryVo;
import WebAdmin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.ApartmentInfo;
import model.enums.ReleaseStatus;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

import java.util.List;


@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {

    private final ApartmentInfoService apartmentInfoService;

    public ApartmentController(ApartmentInfoService apartmentInfoService, RoomInfoService roomInfoService) {
        this.apartmentInfoService = apartmentInfoService;
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
        apartmentInfoService.saveOrUpdateApartment(apartmentSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo) {
        return Result.ok(apartmentInfoService.getPage(current, size, queryVo));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据ID获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(apartmentInfoService.getApartment(id));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        apartmentInfoService.removeApartment(id);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {
        apartmentInfoService.setStatusById(id,status);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {
        return Result.ok(apartmentInfoService.getListByDistrict(id));
    }
}
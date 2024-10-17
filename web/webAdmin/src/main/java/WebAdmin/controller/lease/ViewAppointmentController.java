package WebAdmin.controller.lease;

import Common.exception.MyApiError;
import WebAdmin.aop.AuthCheck;
import WebAdmin.service.ViewAppointmentService;
import WebAdmin.vo.appointment.AppointmentQueryVo;
import WebAdmin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.ViewAppointment;
import model.enums.AppointmentStatus;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;


@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
public class ViewAppointmentController {
    private final ViewAppointmentService viewAppointmentService;

    public ViewAppointmentController(ViewAppointmentService viewAppointmentService) {
        this.viewAppointmentService = viewAppointmentService;
    }

    @Operation(summary = "分页查询预约信息")
    @GetMapping("page")
    public Result<IPage<AppointmentVo>> page(@RequestParam long current, @RequestParam long size, AppointmentQueryVo queryVo) {
        return Result.ok(viewAppointmentService.getViewPage(current,size,queryVo));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id更新预约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {
        LambdaUpdateWrapper<ViewAppointment> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(ViewAppointment::getAppointmentStatus, status)
                .eq(ViewAppointment::getId, id);
        boolean update = viewAppointmentService.update(new ViewAppointment(), lambdaUpdateWrapper);
        if (!update) throw new MyApiError("err",200);
        return Result.ok();
    }

}

package WebAdmin.controller.lease;

import Common.exception.MyApiError;
import WebAdmin.aop.AuthCheck;
import WebAdmin.service.LeaseAgreementService;
import WebAdmin.vo.agreement.AgreementQueryVo;
import WebAdmin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.LeaseAgreement;
import model.enums.LeaseStatus;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;


@Tag(name = "租约管理")
@RestController
@RequestMapping("/admin/agreement")
public class LeaseAgreementController {

    private final LeaseAgreementService leaseAgreementService;

    public LeaseAgreementController(LeaseAgreementService leaseAgreementService) {
        this.leaseAgreementService = leaseAgreementService;
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        if (leaseAgreement == null) throw new MyApiError("err",200);
        boolean save = leaseAgreementService.saveOrUpdate(leaseAgreement);
        if (!save) throw new MyApiError("err",200);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<IPage<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        return Result.ok(leaseAgreementService.getAgreementPage(current,size,queryVo));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id查询租约信息")
    @GetMapping(name = "getById")
    public Result<AgreementVo> getById(@RequestParam Long id) {
        return Result.ok(leaseAgreementService.getAgreement(id));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        if (id == null || id <= 0 ) throw new MyApiError("err",200);
        boolean removeById = leaseAgreementService.removeById(id);
        if (!removeById) throw new MyApiError("err",200);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        LambdaUpdateWrapper<LeaseAgreement> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(LeaseAgreement::getStatus,status)
                .eq(LeaseAgreement::getId,id);
        boolean update = leaseAgreementService.update(new LeaseAgreement(), lambdaUpdateWrapper);
        if (!update) throw new MyApiError("err",200);
        return Result.ok();
    }

}


package WebAdmin.controller.apartment;

import WebAdmin.aop.AuthCheck;
import WebAdmin.service.LeaseTermService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.LeaseTerm;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

import java.util.List;

@Tag(name = "租期管理")
@RequestMapping("/admin/term")
@RestController
public class LeaseTermController {
    private final LeaseTermService leaseTermService;

    public LeaseTermController(LeaseTermService leaseTermService){
        this.leaseTermService = leaseTermService;
    }

    @GetMapping("list")
    @Operation(summary = "查询全部租期列表")
    public Result<List<LeaseTerm>> listLeaseTerm() {
        return Result.ok(leaseTermService.list());
    }

    @AuthCheck(mustBeAdmin = true)
    @PostMapping("saveOrUpdate")
    @Operation(summary = "保存或更新租期信息")
    public Result saveOrUpdate(@RequestBody LeaseTerm leaseTerm) {
        boolean saveOrUpdate = leaseTermService.saveOrUpdate(leaseTerm);
        if (saveOrUpdate){
            return Result.ok();
        }
        return Result.fail();
    }

    @AuthCheck(mustBeAdmin = true)
    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除租期")
    public Result deleteLeaseTermById(@RequestParam Long id) {
        boolean b = leaseTermService.removeById(id);
        if (b){
            return Result.ok();
        }
        return Result.fail();
    }
}

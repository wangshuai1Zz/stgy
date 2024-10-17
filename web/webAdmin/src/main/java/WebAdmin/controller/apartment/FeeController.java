package WebAdmin.controller.apartment;

import Common.exception.MyApiError;
import WebAdmin.aop.AuthCheck;
import WebAdmin.service.FeeKeyService;
import WebAdmin.service.FeeValueService;
import WebAdmin.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.FeeKey;
import model.entity.FeeValue;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

import java.util.List;

import static Common.result.ResultCodeEnum.FAIL;


@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
public class FeeController {
    private final FeeKeyService feeKeyService;
    private final FeeValueService feeValueService;

    public FeeController(FeeKeyService feeKeyService, FeeValueService feeValueService) {
        this.feeKeyService = feeKeyService;
        this.feeValueService = feeValueService;
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "保存或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
        boolean saveOrUpdate = feeKeyService.saveOrUpdate(feeKey);
        if (!saveOrUpdate){
            return Result.fail();
        }
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "保存或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateFeeValue(@RequestBody FeeValue feeValue) {
        boolean saveOrUpdate = feeValueService.saveOrUpdate(feeValue);
        if (!saveOrUpdate){
            return Result.fail();
        }
        return Result.ok();
    }


    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList() {
        return Result.ok(feeKeyService.getAll());
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id删除杂费名称")
    @DeleteMapping("key/deleteById")
    @Transactional
    public Result deleteFeeKeyById(@RequestParam Long feeKeyId) {
        LambdaQueryWrapper<FeeValue> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(FeeValue::getFeeKeyId,feeKeyId);
        boolean removeKey = feeKeyService.removeById(feeKeyId);
        boolean removeValue = feeValueService.remove(lambdaQueryWrapper);
        if (!removeKey || !removeValue){
            throw MyApiError.Fail(FAIL);
        }
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id删除杂费值")
    @DeleteMapping("value/deleteById")
    public Result deleteFeeValueById(@RequestParam Long id) {
        boolean removeById = feeValueService.removeById(id);
        if (!removeById){
            return Result.fail();
        }
        return Result.ok();
    }
}

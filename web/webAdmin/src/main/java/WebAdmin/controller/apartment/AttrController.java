package WebAdmin.controller.apartment;

import Common.exception.MyApiError;
import WebAdmin.aop.AuthCheck;
import WebAdmin.service.AttrKeyService;
import WebAdmin.service.AttrValueService;
import WebAdmin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.AttrKey;
import model.entity.AttrValue;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

import java.util.List;

import static Common.result.ResultCodeEnum.FAIL;


@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {
    private final AttrKeyService attrKeyService;
    private final AttrValueService attrValueService;

    public AttrController(AttrKeyService attrKeyService, AttrValueService attrValueService){
        this.attrKeyService = attrKeyService;
        this.attrValueService = attrValueService;
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        boolean saveOrUpdate = attrKeyService.saveOrUpdate(attrKey);
        if (saveOrUpdate){
            return Result.ok();
        }
       return Result.fail();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        boolean saveOrUpdate = attrValueService.saveOrUpdate(attrValue);
        if (saveOrUpdate){
            return Result.ok();
        }
        return Result.fail();
    }

    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        return Result.ok(attrKeyService.getAll());
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    @Transactional
    public Result removeAttrKeyById(@RequestParam Long attrKeyId) {
        LambdaQueryWrapper<AttrValue> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AttrValue::getAttrKeyId,attrKeyId);
        boolean removeKey = attrKeyService.removeById(attrKeyId);
        boolean removeValue = attrValueService.remove(lambdaQueryWrapper);
        if (!removeKey||!removeValue){
            throw MyApiError.Fail(FAIL);
        }
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result removeAttrValueById(@RequestParam Long id) {
        boolean remove = attrValueService.removeById(id);
        if (!remove){
            return Result.fail();
        }
        return Result.ok();
    }
}

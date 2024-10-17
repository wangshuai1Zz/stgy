package WebAdmin.controller.apartment;

import WebAdmin.aop.AuthCheck;
import WebAdmin.service.LabelInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.LabelInfo;
import model.enums.ItemType;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

import java.util.List;

@Tag(name = "标签管理")
@RestController
@RequestMapping("/admin/label")
public class LabelController {
    private final LabelInfoService labelInfoService;

    public LabelController(LabelInfoService labelInfoService) {
        this.labelInfoService = labelInfoService;
    }

    @Operation(summary = "（根据类型）查询标签列表")
    @GetMapping("list")
    public Result<List<LabelInfo>> labelList(@RequestParam(required = false) ItemType type) {
        if (type!=null){
            LambdaQueryWrapper<LabelInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(LabelInfo::getType,type);
            return Result.ok(labelInfoService.list(lambdaQueryWrapper));
        }
        return Result.ok(labelInfoService.list());
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "新增或修改标签信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdateLabel(@RequestBody LabelInfo labelInfo) {
        boolean saveOrUpdate = labelInfoService.saveOrUpdate(labelInfo);
        if (saveOrUpdate){
            return Result.ok();
        }
        return Result.fail();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据id删除标签信息")
    @DeleteMapping("deleteById")
    public Result deleteLabelById(@RequestParam Long id) {
        boolean removeById = labelInfoService.removeById(id);
        if (removeById){
            return Result.ok();
        }
        return Result.fail();
    }
}

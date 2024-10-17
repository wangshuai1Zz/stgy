package WebAdmin.controller.system;

import Common.exception.MyApiError;
import Common.result.Result;
import WebAdmin.aop.AuthCheck;
import WebAdmin.service.SystemPostService;
import WebAdmin.service.SystemUserService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.SystemPost;
import model.entity.SystemUser;
import model.enums.BaseStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "后台用户岗位管理")
@RequestMapping("/admin/system/post")
public class SystemPostController {
    private final SystemPostService systemPostService;

    @Autowired
    public SystemPostController(SystemPostService systemPostService) {
        this.systemPostService = systemPostService;
    }

    @NotNull
    @Operation(summary = "分页获取岗位信息")
    @GetMapping("page")
    public Result<IPage<SystemPost>> page(@RequestParam long current, @RequestParam long size) {
        return Result.ok( systemPostService.getPage(current,size));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "保存或更新岗位信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SystemPost systemPost) {
        boolean update = systemPostService.saveOrUpdate(systemPost);
        if (!update) throw new MyApiError("err",200);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @DeleteMapping("deleteById")
    @Operation(summary = "根据id删除岗位")
    public Result removeById(@RequestParam Long id) {
        systemPostService.removeById(id);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @GetMapping("getById")
    @Operation(summary = "根据id获取岗位信息")
    public Result<SystemPost> getById(@RequestParam Long id) {
        return Result.ok(systemPostService.getById(id));
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "获取全部岗位列表")
    @GetMapping("list")
    public Result<List<SystemPost>> list() {
        return Result.ok(systemPostService.list());
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据岗位id修改状态")
    @PostMapping("updateStatusByPostId")
    public Result updateStatusByPostId(@RequestParam Long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<SystemPost> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(SystemPost::getStatus,status)
                .eq(SystemPost::getId,id);
        boolean update = systemPostService.update(new SystemPost(), lambdaUpdateWrapper);
        if (!update) throw new MyApiError("err",200);
        return Result.ok();
    }
}

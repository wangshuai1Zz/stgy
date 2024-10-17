package WebAdmin.controller.system;

import Common.exception.MyApiError;
import WebAdmin.aop.AuthCheck;
import WebAdmin.service.SystemUserService;
import WebAdmin.vo.system.user.SystemUserItemVo;
import WebAdmin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.SystemUser;
import model.enums.BaseStatus;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;


@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {
    private final SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @Operation(summary = "根据条件分页查询后台用户列表")
    @GetMapping("page")
    public Result<IPage<SystemUserItemVo>> page(@RequestParam long current, @RequestParam long size, SystemUserQueryVo queryVo) {
        return Result.ok(systemUserService.getPage(current,size,queryVo));
    }
    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据ID查询后台用户信息")
    @GetMapping("getById")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        SystemUserItemVo user = systemUserService.getUser(id);
        user.setPassword("");
        return Result.ok(user);
    }
    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "保存或更新后台用户信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SystemUser systemUser) {
        if (systemUser.getId() != null){
            systemUser.setPassword("");
        }
        boolean update = systemUserService.saveOrUpdate(systemUser);
        if (!update) throw new MyApiError("err",200);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "判断后台用户名是否可用")
    @GetMapping("isUserNameAvailable")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        LambdaQueryWrapper<SystemUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemUser::getUsername,username);
        SystemUser user = systemUserService.getOne(lambdaQueryWrapper);
        return Result.ok(user == null );
    }

    @AuthCheck(mustBeAdmin = true)
    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result removeById(@RequestParam Long id) {
        boolean remove = systemUserService.removeById(id);
        if (!remove) throw new MyApiError("err",200);
        return Result.ok();
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据ID修改后台用户状态")
    @PostMapping("updateStatusByUserId")
    public Result updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<SystemUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(SystemUser::getStatus,status)
                .eq(SystemUser::getId,id);
        systemUserService.update(new SystemUser(),lambdaUpdateWrapper);
        return Result.ok();
    }
}

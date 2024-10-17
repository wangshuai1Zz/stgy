package WebAdmin.controller.user;

import Common.exception.MyApiError;
import WebAdmin.aop.AuthCheck;
import WebAdmin.service.UserInfoService;
import WebAdmin.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import model.entity.UserInfo;
import model.enums.BaseStatus;
import org.springframework.web.bind.annotation.*;
import Common.result.Result;

import java.util.List;

@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/admin/user")
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Operation(summary = "分页查询用户信息")
    @GetMapping("page")
    public Result<IPage<UserInfo>> pageUserInfo(@RequestParam long current, @RequestParam long size, UserInfoQueryVo queryVo) {
        IPage<UserInfo> page = new Page<>(current,size);
        String phone = queryVo.getPhone();
        BaseStatus status = queryVo.getStatus();
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (phone != null) lambdaQueryWrapper.eq(UserInfo::getPhone,phone);
        if (status != null) lambdaQueryWrapper.eq(UserInfo::getStatus,status);
        List<UserInfo> list = userInfoService.list(page, lambdaQueryWrapper);
        if (list.isEmpty()) throw new MyApiError("err",200);
        page.setRecords(list);
        return Result.ok(page);
    }

    @AuthCheck(mustBeAdmin = true)
    @Operation(summary = "根据用户id更新账号状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<UserInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(UserInfo::getStatus,status)
                .eq(UserInfo::getId,id);
        userInfoService.update(new UserInfo(),lambdaUpdateWrapper);
        return Result.ok();
    }
}

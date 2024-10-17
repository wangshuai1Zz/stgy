package model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.enums.BaseStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户信息表")
@TableName(value = "user_info")
@Data
public class UserInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号码（用做登录用户名）")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "密码")
    @TableField(value = "password")
    private String password;

    @Schema(description = "头像url")
    @TableField(value = "avatar_url")
    private String avatarUrl;

    @Schema(description = "昵称")
    @TableField(value = "nickname")
    private String nickname;

    @Schema(description = "账号状态")
    @TableField(value = "status")
    private BaseStatus status;

}
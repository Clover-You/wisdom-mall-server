package top.ctong.wisdom.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀     ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒      ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░      ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄      ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄     ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒     ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 * Copyright 2023 Clover You.
 * <p>
 * 用户表
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 21:56
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
@Schema(description = "用户表")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 7716678547495664955L;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @TableId(type = IdType.ASSIGN_ID)
    @TableField(jdbcType = JdbcType.BIGINT)
    private Long userId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @TableField(jdbcType = JdbcType.VARCHAR)
    private String userName;

    /**
     * 用户绑定的手机号
     */
    @Schema(description = "用户绑定的手机号")
    @TableField(jdbcType = JdbcType.CHAR)
    private String phone;

    /**
     * 账号
     */
    @Schema(description = "账号")
    @TableField(jdbcType = JdbcType.INTEGER)
    private Integer userAccount;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @TableField(jdbcType = JdbcType.CHAR)
    private String userPassword;

    /**
     * 是否封禁
     */
    @Schema(description = "是否封禁")
    @TableField(jdbcType = JdbcType.BOOLEAN)
    private Boolean banned;

    /**
     * 用户头像
     */
    @Schema(description = "头像")
    @TableField(jdbcType = JdbcType.VARCHAR)
    private String avatar;

    /**
     * 创建时间
     */
    @Schema(description = "用户创建时间")
    @TableField(jdbcType = JdbcType.TIMESTAMP)
    private Date createAt;

    /**
     * 修改时间
     */
    @Schema(description = "账户信息修改时间")
    @TableField(jdbcType = JdbcType.TIMESTAMP)
    private Date updateAt;

}

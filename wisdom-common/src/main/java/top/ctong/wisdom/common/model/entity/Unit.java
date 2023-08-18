package top.ctong.wisdom.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * 单位
 * </p>
 *
 * @author Clover
 * @date 2023-07-26 10:04
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_unit")
@Schema(description = "单位表")
public class Unit implements Serializable {

    @Serial
    private static final long serialVersionUID = 7716678547495664955L;

    /**
     * 单位id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "单位id")
    private Long unitId;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 单位名称
     */
    @Schema(description = "单位名称")
    private String unitName;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String unitRemark;

    /**
     * 启用状态 0=禁用；1=启用
     */
    @Schema(description = "启用状态 0=禁用；1=启用")
    private Short enable;

    /**
     * 是否允许小数 0=不支持；1=支持
     */
    @Schema(description = "是否允许小数 0=不支持；1=支持")
    private Short isDecimal;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(description = "是否删除")
    private Short isDel;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createAt;

    /**
     * 删除时间
     */
    @Schema(description = "删除时间")
    private Date deleteAt;
}

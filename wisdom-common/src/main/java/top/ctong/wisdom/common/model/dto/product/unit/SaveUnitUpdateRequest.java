package top.ctong.wisdom.common.model.dto.product.unit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

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
 * 单位保存修改参数
 * </p>
 *
 * @author Clover
 * @date 2023-07-27 11:26
 */
@Data
@Schema(description = "单位保存修改")
public class SaveUnitUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 7716678547495664955L;

    @Schema(description = "单位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位不能为空")
    private Long unitId;

    /**
     * 单位名称
     */
    @Schema(description = "单位名称")
    @Size(max = 256, message = "单位名称长度不能大于256位")
    private String unitName;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String unitRemark;

    /**
     * 启用状态 0=禁用；1=启用
     */
    @Schema(description = "启用状态 0=禁用；1=启用", type = "integer")
    @Pattern(regexp = "^[01]$", message = "「启用状态」参数错误，只能允许 0 或 1")
    private String enable;

    /**
     * 是否允许小数 0=不支持；1=支持
     */
    @Schema(description = "是否允许小数 0=不支持；1=支持", type = "integer")
    @Pattern(regexp = "^[01]$", message = "「是否允许小数」参数错误，只能允许 0 或 1")
    private String isDecimal;

    /**
     * 单位排序
     */
    @Schema(description = "单位排序")
    private Integer sort;

}

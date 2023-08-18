package top.ctong.wisdom.common.model.dto.product.unit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * 添加单位
 * </p>
 *
 * @author Clover
 * @date 2023-07-26 13:51
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "添加单位")
public class AddUnitRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 7716678547495664955L;

    /**
     * 单位名称
     */
    @Schema(description = "单位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "单位名称不能为空")
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

}

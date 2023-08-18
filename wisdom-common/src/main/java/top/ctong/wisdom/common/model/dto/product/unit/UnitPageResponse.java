package top.ctong.wisdom.common.model.dto.product.unit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 分页查询结果
 * </p>
 *
 * @author Clover
 * @date 2023-07-26 16:27
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询结果")
public class UnitPageResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 7716678547495664955L;

    /**
     * 单位id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "单位id")
    private Long unitId;

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
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm ss")
    private Date createAt;
}

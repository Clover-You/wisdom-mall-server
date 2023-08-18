package top.ctong.wisdom.common.model.dto.product.unit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import top.ctong.wisdom.common.model.dto.PageParams;

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
 * 单位分页参数
 * </p>
 *
 * @author Clover
 * @date 2023-07-26 14:31
 */
@Data
@Schema(description = "单位分页参数")
@EqualsAndHashCode(callSuper = true)
public class UnitPageRequest extends PageParams implements Serializable {

    @Serial
    private static final long serialVersionUID = 7716678547495664955L;

    /**
     * 单位名称
     */
    @Schema(description = "单位名称")
    private String unitName;

}

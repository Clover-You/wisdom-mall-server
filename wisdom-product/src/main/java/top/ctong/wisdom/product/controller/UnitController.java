package top.ctong.wisdom.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ctong.wisdom.common.log.Log;
import top.ctong.wisdom.common.model.dto.product.unit.AddUnitRequest;
import top.ctong.wisdom.common.model.dto.product.unit.SaveUnitUpdateRequest;
import top.ctong.wisdom.common.model.dto.product.unit.UnitPageRequest;
import top.ctong.wisdom.common.model.dto.product.unit.UnitPageResponse;
import top.ctong.wisdom.common.utils.PageResp;
import top.ctong.wisdom.common.utils.R;
import top.ctong.wisdom.product.service.UnitService;

import java.security.Principal;

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
 * 单位前端控制器
 * </p>
 *
 * @author Clover
 * @date 2023-07-26 13:48
 */
@RestController
@RequestMapping("/product/unit")
@Tag(description = "商品单位前端控制器", name = "unit-controller")
public class UnitController {

    private final static Logger log = LoggerFactory.getLogger(UnitController.class);

    @Resource
    private UnitService unitService;

    /**
     * 添加商品单位
     *
     * @param params 单位信息
     * @return R<?>
     * @author Clover You
     * @date 2023/7/26 14:04
     */
    @PostMapping("add")
    @Operation(summary = "添加商品单位")
    @Log(name = "添加单位")
    public R<?> add(@Valid @RequestBody AddUnitRequest params, Principal principal) {
        log.debug("添加单位参数 ===>>> {}", params);
        unitService.save(params, Long.valueOf(principal.getName()));
        return R.ok();
    }

    /**
     * 分页获取单位信息
     *
     * @param params    请求参数
     * @param principal 登录用户信息
     * @return R<?>
     * @author Clover You
     * @date 2023/7/26 14:43
     */
    @GetMapping("page")
    @Log(name = "分页获取单位信息")
    @Operation(summary = "分页获取单位信息")
    public R<PageResp<UnitPageResponse>> page(@Valid UnitPageRequest params, Principal principal) {
        var results = unitService.page(params, Long.valueOf(principal.getName()));
        return R.ok(results);
    }

    /**
     * 修改单位信息
     *
     * @param params 单位信息
     * @return R<?>
     * @author Clover You
     * @date 2023/7/27 11:20
     */
    @Log(name = "修改单位信息")
    @PostMapping("/saveUpdate")
    @Operation(summary = "修改单位信息")
    public R<?> update(@RequestBody @Validated SaveUnitUpdateRequest params, Principal principal) {
        log.debug("修改单位信息请求参数 ===>>> {}", params);
        var userId = Long.valueOf(principal.getName());
        unitService.updateSave(params, userId);
        return R.ok();
    }

}

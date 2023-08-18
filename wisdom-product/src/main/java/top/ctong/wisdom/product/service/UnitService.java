package top.ctong.wisdom.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ctong.wisdom.common.model.dto.product.unit.AddUnitRequest;
import top.ctong.wisdom.common.model.dto.product.unit.SaveUnitUpdateRequest;
import top.ctong.wisdom.common.model.dto.product.unit.UnitPageRequest;
import top.ctong.wisdom.common.model.dto.product.unit.UnitPageResponse;
import top.ctong.wisdom.common.model.entity.Unit;
import top.ctong.wisdom.common.utils.PageResp;

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
 * 单位服务
 * </p>
 *
 * @author Clover
 * @date 2023-07-26 14:05
 */
public interface UnitService extends IService<Unit> {

    /**
     * 保存单位信息
     *
     * @param params 单位信息
     * @param userId 用户id
     * @return boolean
     * @author Clover You
     * @date 2023/7/26 14:10
     */
    boolean save(AddUnitRequest params, Long userId);

    /**
     * 分页查询单位信息
     *
     * @param params 查询参数
     * @param userId 用户id
     * @return PageResp<UnitPageResponse>
     * @author Clover You
     * @date 2023/7/26 17:14
     */
    PageResp<UnitPageResponse> page(UnitPageRequest params, Long userId);

    /**
     * 保存修改
     *
     * @param params 单位信息
     * @param userId 用户id
     * @return boolean
     * @author Clover You
     * @date 2023/7/27 11:33
     */
    boolean updateSave(SaveUnitUpdateRequest params, Long userId);

    /**
     * 查询单位信息
     *
     * @param userId 用户id
     * @param unitId 单位id
     * @return Unit
     * @author Clover You
     * @date 2023/7/27 11:39
     */
    Unit getOne(Long userId, Long unitId);
}

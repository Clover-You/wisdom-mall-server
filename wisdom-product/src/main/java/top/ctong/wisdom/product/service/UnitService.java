package top.ctong.wisdom.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ctong.wisdom.common.model.dto.product.unit.*;
import top.ctong.wisdom.common.model.entity.Unit;
import top.ctong.wisdom.common.utils.PageResp;

import java.util.List;

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


    List<UnitListResponse> list(Long userId);

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

    /**
     * 通过单位 id 删除单位信息
     *
     * @param unitId 单位 id
     * @param userId 用户 id
     * @return boolean
     * @author Clover You
     * @date 2023/8/27 00:15
     */
    boolean removeById(Long userId, Long unitId);

    /**
     * 检查单位是否存在
     *
     * @param userId 用户 id
     * @param unitId 单位 id
     * @return boolean
     * @author Clover You
     * @date 2023/8/27 00:24
     */
    boolean exists(Long userId, Long unitId);

    /**
     * 重置单位顺序
     *
     * @param userId 用户id
     * @param begin 开始索引
     * @return boolean
     * @author Clover You
     * @date 2023/9/3 14:48
     */
    boolean resetSort(Long userId, int begin);
}

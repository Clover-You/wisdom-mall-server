package top.ctong.wisdom.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.exception.ThrowUtils;
import top.ctong.wisdom.common.model.dto.product.unit.AddUnitRequest;
import top.ctong.wisdom.common.model.dto.product.unit.SaveUnitUpdateRequest;
import top.ctong.wisdom.common.model.dto.product.unit.UnitPageRequest;
import top.ctong.wisdom.common.model.dto.product.unit.UnitPageResponse;
import top.ctong.wisdom.common.model.entity.Unit;
import top.ctong.wisdom.common.utils.PageResp;
import top.ctong.wisdom.common.utils.StringUtils;
import top.ctong.wisdom.product.mapper.UnitMapper;
import top.ctong.wisdom.product.service.UnitService;

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
 * @date 2023-07-26 14:06
 */
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements UnitService {

    /**
     * 保存单位信息
     *
     * @param params 单位信息
     * @param userId 用户id
     * @return boolean
     * @author Clover You
     * @date 2023/7/26 14:10
     */
    @Override
    public boolean save(AddUnitRequest params, Long userId) {
        // 获取数据库中最大的排序编号
        var sort = baseMapper.getMaxSortNum() + 1;

        // 检查单位是否存在
        var existsQueryWrapper = new LambdaQueryWrapper<Unit>();
        existsQueryWrapper.eq(Unit::getUserId, userId);
        existsQueryWrapper.eq(Unit::getUnitName, params.getUnitName().trim());
        existsQueryWrapper.eq(Unit::getIsDel, 0);

        var isExists = this.baseMapper.exists(existsQueryWrapper);
        ThrowUtils.throwIf(isExists, ErrorCode.PARAMS_ERROR, "单位已存在，不能再重复新增!");

        // 构造单位表
        var entity = Unit.builder()
            .unitName(params.getUnitName().trim())
            .unitRemark(params.getUnitRemark())
            .sort(sort)
            .userId(userId);

        if (params.getEnable() != null) {
            entity.enable(Short.valueOf(params.getEnable()));
        }

        if (params.getIsDecimal() != null) {
            entity.isDecimal(Short.valueOf(params.getIsDecimal()));
        }

        return this.save(entity.build());
    }

    /**
     * 分页查询单位信息
     *
     * @param params 查询参数
     * @param userId 用户id
     * @return PageResp<UnitPageResponse>
     * @author Clover You
     * @date 2023/7/26 17:14
     */
    @Override
    public PageResp<UnitPageResponse> page(UnitPageRequest params, Long userId) {
        var queryWrapper = new LambdaQueryWrapper<Unit>();

        queryWrapper.eq(Unit::getUserId, userId);
        queryWrapper.eq(Unit::getIsDel, 0);

        // 如果单位名称不为空，那么需要对齐进行模糊检索
        if (StringUtils.notBlank(params.getUnitName())) {
            queryWrapper.like(
                Unit::getUnitName, params.getUnitName().trim()
            );
        }

        var page = new Page<Unit>(params.getCurrent(), params.getSize());
        var result = this.baseMapper.page(page, queryWrapper);
        return new PageResp<>(result);
    }

    /**
     * 保存修改
     *
     * @param params 单位信息
     * @param userId 用户id
     * @return boolean
     * @author Clover You
     * @date 2023/7/27 11:33
     */
    @Override
    public boolean updateSave(SaveUnitUpdateRequest params, Long userId) {
        Long unitId = params.getUnitId();

        var record = this.getOne(userId, unitId);
        ThrowUtils.throwIf(record == null, ErrorCode.PARAMS_ERROR, "单位不存在");

        var entity = Unit.builder()
            .unitName(params.getUnitName())
            .unitRemark(params.getUnitRemark())
            .enable(Short.valueOf(params.getEnable()))
            .isDecimal(Short.valueOf(params.getIsDecimal()))
            .sort(params.getSort())
            .build();

        var updateWrapper = new LambdaUpdateWrapper<Unit>();
        updateWrapper.eq(Unit::getUnitId, unitId);
        updateWrapper.eq(Unit::getUserId, userId);

        return this.update(entity, updateWrapper);
    }

    /**
     * 查询单位信息
     *
     * @param userId 用户id
     * @param unitId 单位id
     * @return Unit
     * @author Clover You
     * @date 2023/7/27 11:39
     */
    @Override
    public Unit getOne(Long userId, Long unitId) {
        var queryWrapper = new LambdaQueryWrapper<Unit>();
        queryWrapper.eq(Unit::getUserId, userId);
        queryWrapper.eq(Unit::getUnitId, unitId);

        return this.getOne(queryWrapper);
    }
}

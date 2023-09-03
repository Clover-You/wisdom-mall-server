package top.ctong.wisdom.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.exception.ThrowUtils;
import top.ctong.wisdom.common.model.dto.product.unit.*;
import top.ctong.wisdom.common.model.entity.Unit;
import top.ctong.wisdom.common.utils.PageResp;
import top.ctong.wisdom.common.utils.StringUtils;
import top.ctong.wisdom.product.mapper.UnitMapper;
import top.ctong.wisdom.product.service.UnitService;

import java.util.ArrayList;
import java.util.Date;
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
        var sort = baseMapper.getMaxSortNum(userId) + 1;

        // 检查单位是否存在
        var existsQueryWrapper = new LambdaQueryWrapper<Unit>();
        existsQueryWrapper.eq(Unit::getUserId, userId);
        existsQueryWrapper.eq(Unit::getUnitName, params.getUnitName().trim());
        existsQueryWrapper.eq(Unit::getIsDel, 0);

        var isExists = this.baseMapper.exists(existsQueryWrapper);
        ThrowUtils.throwIf(isExists, ErrorCode.PARAMS_ERROR, "单位已存在，不能再重复新增!");

        // 构造单位表
        var entity = Unit.builder().unitName(params.getUnitName().trim()).unitRemark(params.getUnitRemark()).sort(sort).userId(userId);

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
            queryWrapper.like(Unit::getUnitName, params.getUnitName().trim());
        }

        var page = new Page<Unit>(params.getCurrent(), params.getSize());
        var result = this.baseMapper.page(page, queryWrapper);
        return new PageResp<>(result);
    }

    @Override
    public List<UnitListResponse> list(Long userId) {
        return this.baseMapper.selectListAll(userId);
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

        var entity = Unit.builder().unitName(params.getUnitName()).unitRemark(params.getUnitRemark()).enable(Short.valueOf(params.getEnable())).isDecimal(Short.valueOf(params.getIsDecimal())).sort(params.getSort()).build();

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

    /**
     * 通过单位 id 删除单位信息
     *
     * @param unitId 单位 id
     * @param userId 用户 id
     * @return boolean
     * @author Clover You
     * @date 2023/8/27 00:15
     */
    @Transactional
    @Override
    public boolean removeById(Long userId, Long unitId) {
        var record = getById(unitId);
        // 检查单位是否存在
        ThrowUtils.throwIf(record == null, ErrorCode.PARAMS_ERROR, "单位不存在");

        // 将 del 标识设置为 1 标识删除
        var entity = Unit.builder().userId(userId).unitId(unitId).isDel((short) 1).deleteAt(new Date()).build();

        var result = baseMapper.updateById(entity) == 1;

        if (result && record.getSort() != null) {
            // 假设 当前删除的数据 sort 是 6，那么传递 sort 为上一个 sort 也就是 5，因为在 resetSort 方法中会 +1
            return resetSort(userId, record.getSort());
        }

        // 应该将排序重置
        return result;
    }


    /**
     * 重置单位顺序
     *
     * @param userId 用户id
     * @param begin 开始索引
     * @return boolean
     * @author Clover You
     * @date 2023/9/3 14:48
     */
    @Transactional()
    public boolean resetSort(Long userId, int begin){
        var queryWrapper = new LambdaQueryWrapper<Unit>();
        queryWrapper.eq(Unit::getUserId, userId);
        queryWrapper.eq(Unit::getIsDel, 0);

        if (begin > 0) {
            queryWrapper.ge(Unit::getSort, begin);
        }

        // 取出 sort 字段 >= begin 的数据
        var record = list(queryWrapper);

        if (record.isEmpty()) {
            return true;
        }

        var list = new ArrayList<Unit>(record.size());

        // 将数据 begin + 1 进行排序
        for (int i = 0, y = begin; i < record.size(); i++, y++) {
            var item = record.get(i);
            var result = Unit.builder().userId(userId).sort(y).unitId(item.getUnitId()).build();
            list.add(result);
        }

        return updateBatchById(list);
    }

    /**
     * 检查单位是否存在
     *
     * @param userId 用户 id
     * @param unitId 单位 id
     * @return boolean
     * @author Clover You
     * @date 2023/8/27 00:24
     */
    @Override
    public boolean exists(Long userId, Long unitId) {
        var genWrapperCondition = Unit.builder().userId(userId).unitId(unitId).isDel((short) 0).build();
        var queryWrapper = genQueryWrapper(genWrapperCondition);

        return baseMapper.exists(queryWrapper);
    }

    private Wrapper<Unit> genQueryWrapper(Unit condition) {
        var queryWrapper = new LambdaQueryWrapper<Unit>();

        queryWrapper.eq(condition.getUnitId() != null, Unit::getUnitId, condition.getUnitId());
        queryWrapper.eq(condition.getUserId() != null, Unit::getUserId, condition.getUserId());
        queryWrapper.eq(StringUtils.notBlank(condition.getUnitName()), Unit::getUnitName, condition.getUnitName());
        queryWrapper.eq(StringUtils.notBlank(condition.getUnitRemark()), Unit::getUnitRemark, condition.getUnitRemark());
        queryWrapper.eq(condition.getEnable() != null, Unit::getEnable, condition.getEnable());
        queryWrapper.eq(condition.getIsDecimal() != null, Unit::getIsDecimal, condition.getIsDecimal());
        queryWrapper.eq(condition.getSort() != null, Unit::getSort, condition.getSort());
        queryWrapper.eq(condition.getIsDel() != null, Unit::getIsDel, condition.getIsDel());
        queryWrapper.eq(condition.getCreateAt() != null, Unit::getCreateAt, condition.getCreateAt());
        queryWrapper.eq(condition.getDeleteAt() != null, Unit::getDeleteAt, condition.getDeleteAt());

        return queryWrapper;
    }
}

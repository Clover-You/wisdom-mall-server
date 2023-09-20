package top.ctong.wisdom.product.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.ctong.wisdom.common.model.dto.product.unit.UnitListResponse;
import top.ctong.wisdom.common.model.dto.product.unit.UnitPageResponse;
import top.ctong.wisdom.common.model.entity.Unit;

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
 * 单位数据库交互
 * </p>
 *
 * @author Clover
 * @date 2023-07-26 14:07
 */
@Mapper
public interface UnitMapper extends BaseMapper<Unit> {

    /**
     * 获取单位最大排序序号
     *
     * @return int
     * @author Clover You
     * @date 2023/7/26 14:11
     */
    int getMaxSortNum(@Param("userId") Long userId);

    /**
     * 分页获取单位列表
     *
     * @param page         分页
     * @param queryWrapper 查询条件
     * @return IPage<UnitPageResponse>
     * @author Clover You
     * @date 2023/7/26 16:31
     */
    IPage<UnitPageResponse> page(Page<Unit> page, @Param("ew") Wrapper<Unit> queryWrapper);

    /**
     * 获取用户所有单位信息
     *
     * @param userId 用户id
     * @return List<UnitListResponse>
     * @author Clover You
     * @date 2023/9/3 13:46
     */
    List<UnitListResponse> selectListAll(@Param("userId") Long userId);
}

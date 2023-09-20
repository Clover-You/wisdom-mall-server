package top.ctong.wisdom.product.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.exception.BusinessException;
import top.ctong.wisdom.common.model.dto.product.unit.UnitListResponse;
import top.ctong.wisdom.common.model.entity.Unit;

import java.util.ArrayList;
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
 * 测试 exists 方法是否可用
 * </p>
 *
 * @author Clover
 * @date 2023-08-27 00:43
 */
@SpringBootTest
public class UnitServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UnitServiceTest.class);

    private final UnitService unitService;

    private Unit genTestData() {
        var unitName = System.currentTimeMillis() + "unitName";
        var userId = 10086L;

        return Unit.builder().unitName(unitName).userId(userId).sort(0).build();
    }

    private List<Unit> genList(int maxCount) {
        var list = new ArrayList<Unit>(maxCount);

        for (int i = 0; i < maxCount; i++) {
            var data = genTestData();
            data.setUnitName(data.getUnitName() + i);
            data.setSort(i + 1);

            list.add(data);
        }

        return list;
    }

    @Autowired
    public UnitServiceTest(UnitService unitService) {
        this.unitService = unitService;
    }

    @Test
    @Transactional
    @DisplayName("测试 exists 方法是否能够找到一个已存在的数据")
    void exists() {
        var testData = genTestData();
        var saveResult = unitService.save(testData);

        assert saveResult;

        var exists = unitService.exists(testData.getUserId(), testData.getUnitId());

        assert exists;
    }

    @Test
    @Transactional
    @DisplayName("remove 方法是否能够正确的删除一个已存在的数据")
    void remove() {
        var testData = genTestData();
        unitService.save(testData);

        assert unitService.removeById(testData.getUserId(), testData.getUnitId());
        // 这个测试不应该再存在数据
        assert !unitService.exists(testData.getUserId(), testData.getUnitId());
    }

    @Test
    @Transactional
    @DisplayName("删除一个不存在的单位时应该抛出一个错误")
    void removeNotExistUnitReportError() {
        try {
            unitService.removeById(10082131236L, System.currentTimeMillis());
        } catch (BusinessException e) {
            // 应该返回这些信息 ===>>> ErrorCode.PARAMS_ERROR, "单位不存在"
            var code = e.getErrorCode();

            assert Integer.valueOf(ErrorCode.PARAMS_ERROR.getCode()).equals(code.getCode());
            assert "单位不存在".equals(e.getMessage());
        }
    }

    @Test
    @Transactional
    @DisplayName("删除单位后, 数据库中顺序应该是连续的")
    void checkSortIsContinuousAfterRemove() {
        var testData = genList(10);
        unitService.saveBatch(testData);
        unitService.removeById(testData.get(3).getUserId(), testData.get(3).getUnitId());

        // 获取所有未删除的单位
        var record = unitService.list(testData.get(3).getUserId());

        var correctResult = new ArrayList<>();
        correctResult.add(1);
        correctResult.add(2);
        correctResult.add(3);
        correctResult.add(4);
        correctResult.add(5);
        correctResult.add(6);
        correctResult.add(7);
        correctResult.add(8);
        correctResult.add(9);

        assert record.stream().map(UnitListResponse::getSort).toList().equals(correctResult);
    }

}

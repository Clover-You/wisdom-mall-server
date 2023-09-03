package top.ctong.wisdom.common;

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
 * 集合工具类单元测试
 * </p>
 *
 * @author Clover
 * @date 2023-09-03 13:38
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.ctong.wisdom.common.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.function.Function;

@SpringBootTest
public class CollectionUtilsTest {

    @DisplayName("isContinuous 对于简单的数字类型，是否可以正常使用")
    @Test
    void isContinuous() {
        var integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);

        // 定义映射函数，将整数直接映射为整数
        var toIntegerFunction = Function.<Integer>identity();

        // 检查是否连续
        boolean isContinuous = CollectionUtils.isConsecutive(integerList, toIntegerFunction);

        assert isContinuous;
    }

}

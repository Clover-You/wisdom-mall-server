package top.ctong.wisdom.common.utils;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

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
 * 集合工具类
 * </p>
 *
 * @author Clover
 * @date 2023-09-03 11:22
 */
public class CollectionUtils {

    private CollectionUtils() {}

    /**
     * 在 List 中是否存在重复的元素
     *
     * @param list 数据集合
     * @param func 需要检查的字段
     * @return boolean
     * @author Clover You
     * @date 2023/9/3 13:11
     */
    public static <T> boolean duplicateElementInList(List<T> list, Function<T, ?> func) {
        if (list == null || list.isEmpty()) {
            return false;
        }

        var set = new HashSet<>(list.size());

        for (T target : list) {
            var value = func.apply(target);
            if (set.contains(value)) {
                return true;
            }
            set.add(value);
        }

        return false;
    }

    /**
     * 检查列表中元素是否是连续的？例如[1,2,3]
     *
     * @param list 可能不连续的列表
     * @param func 映射函数，用于取出 sort 字段
     * @return true 为连续，false 不连续
     */
    public static <T> boolean isConsecutive(List<T> list, Function<T, Integer> func) {
        // 如果数组为空, 那么返回 true
        if (list == null || list.isEmpty()) {
            return true;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (T item : list) {
            var num = func.apply(item);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int expectedSum = (max - min + 1) * (min + max) / 2;
        int actualSum = 0;

        for (T item : list) {
            var num = func.apply(item);
            actualSum += num;
        }

        return expectedSum == actualSum;
    }

}

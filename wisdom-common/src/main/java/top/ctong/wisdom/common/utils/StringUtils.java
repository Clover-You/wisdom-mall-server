package top.ctong.wisdom.common.utils;

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
 * 字符串工具类
 * </p>
 *
 * @author Clover
 * @date 2023-06-29 11:08
 */
public class StringUtils {
    private StringUtils() {}

    /**
     * 字符串是否为空
     *
     * @param str 需要检查的字符串
     * @return boolean
     * @author Clover You
     * @date 2023/6/29 11:14
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 字符串是否不是空
     *
     * @param str 需要检查的字符串
     * @return boolean
     * @author Clover You
     * @date 2023/7/26 14:34
     */
    public static boolean notBlank(String str) {
        return !isBlank(str);
    }
}

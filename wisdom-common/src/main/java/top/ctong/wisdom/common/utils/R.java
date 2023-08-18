package top.ctong.wisdom.common.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.SneakyThrows;
import top.ctong.wisdom.common.ErrorCode;

/**
 * <p>
 * 请求响应结构
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 11:51
 */
@Schema(description = "请求响应结构", title = "ResponseResult")
public record R<T>(
    @Schema(description = "业务状态码") int code,
    @Schema(description = "业务状态描述") String message,
    @Schema(
        description = "响应数据",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @JsonProperty(access = JsonProperty.Access.READ_WRITE) T data
) {

    /**
     * 默认成功
     *
     * @return R<T>
     * @author Clover You
     * @date 2023/6/20 11:53
     */
    public static <T> R<T> ok() {
        return new R<>(200, "success", null);
    }

    /**
     * 响应成功状态且携带数据
     *
     * @param data 响应数据体
     * @return R<T>
     * @author Clover You
     * @date 2023/6/21 09:06
     */
    public static <T> R<T> ok(T data) {
        return new R<>(200, "success", data);
    }

    /**
     * 响应失败
     *
     * @return R<T>
     * @author Clover You
     * @date 2023/6/20 13:43
     */
    public static <T> R<T> fail() {
        return R.fail(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 响应失败
     *
     * @param error 错误信息
     * @param data  数据
     * @return R<T>
     * @author Clover You
     * @date 2023/6/20 13:54
     */
    public static <T> R<T> fail(ErrorCode error, T data) {
        return new R<>(error.getCode(), error.getMessage(), data);
    }

    /**
     * 响应失败
     *
     * @param error   错误信息
     * @param message 自定义错误消息
     * @return R<T>
     * @author Clover You
     * @date 2023/6/20 13:54
     */
    public static <T> R<T> fail(ErrorCode error, String message) {
        return new R<>(error.getCode(), message, null);
    }

    /**
     * 响应失败
     *
     * @param error 错误消息
     * @return R<T>
     * @author Clover You
     * @date 2023/6/20 13:59
     */
    public static <T> R<T> fail(ErrorCode error) {
        return new R<>(error.getCode(), error.getMessage(), null);
    }

    public boolean equals(ErrorCode errorCode) {
        return this.code == errorCode.getCode();
    }

    /**
     * result to json
     *
     * @return String
     * @author Clover You
     * @date 2023/6/27 11:04
     */
    @SneakyThrows
    public String json() {
        var om = new ObjectMapper();
        return om.writeValueAsString(this);
    }
}

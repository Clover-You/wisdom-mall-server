package top.ctong.wisdom.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.utils.R;

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
 * 全局异常处理器
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 13:40
 */
@RestControllerAdvice
public class GlobalErrorHandler {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(GlobalErrorHandler.class);

    /**
     * 默认异常处理器
     *
     * @param thr 异常
     * @return R<?>
     * @author Clover You
     * @date 2023/6/20 13:46
     */
    @ExceptionHandler(Throwable.class)
    public R<?> baseHandler(Throwable thr) {
        log.error("GlobalErrorHandler BaseHandler", thr);
        return R.fail();
    }

    /**
     * 参数校验异常
     *
     * @param error 校验信息
     * @return R<?>
     * @author Clover You
     * @date 2023/6/20 14:18
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> validErrorHandler(MethodArgumentNotValidException error) {
        log.info("GlobalErrorHandler ValidErrorHandler", error);
        var fieldError = error.getFieldError();
        if (fieldError == null) return R.fail(ErrorCode.PARAMS_ERROR);

        return R.fail(ErrorCode.PARAMS_ERROR, fieldError.getDefaultMessage());
    }

    /**
     * 业务异常
     *
     * @return R<?>
     * @author Clover You
     * @date 2023/6/29 14:21
     */
    @ExceptionHandler(BusinessException.class)
    public R<?> businessErrorHandler(BusinessException e) {
        log.info("BusinessException ", e);
        return R.fail(e.getErrorCode(), e.getErrorMessage());
    }

}

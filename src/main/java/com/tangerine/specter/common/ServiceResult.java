package com.tangerine.specter.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 响应结果
 *
 * @author 丢失的橘子
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult<T> {
    /**
     * 返回状态
     */
    private boolean success;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 总数
     */
    private Long count;

    private ServiceResult(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    private ServiceResult(boolean success, int code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 页面跳转
     *
     * @param pagePath 跳转路径
     */
    public static ModelAndView jumpPage(String pagePath) {
        return new ModelAndView(pagePath);
    }
}

package com.atguigu.springcloud.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:IceS
 * Date:2020-05-14 11:53:43
 * Description:
 * NONE
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {//T实际返回类型
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
}

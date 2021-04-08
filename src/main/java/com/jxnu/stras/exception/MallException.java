package com.jxnu.stras.exception;

import com.jxnu.stras.enums.ResultEnum;
/**
 * unchecked 不用去处理，交给JVM去处理，继承 RuntimeException
 * checked，需要自己处理，继承 Exception
 */
public class MallException extends RuntimeException {
    //商品无库存是再加购物车会抛出异常
    public MallException(String error) {
        super(error);
    }
    public MallException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}

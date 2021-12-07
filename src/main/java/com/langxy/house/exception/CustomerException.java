package com.langxy.house.exception;

import com.langxy.house.commons.HttpRespEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerException extends RuntimeException {

    private int code;

    public CustomerException(HttpRespEnum eEnum) {
        super(eEnum.getMsg());
        this.code = eEnum.getCode();
    }

    public CustomerException(int code, String msg) {
        super(msg);
        this.code = code;
    }


}

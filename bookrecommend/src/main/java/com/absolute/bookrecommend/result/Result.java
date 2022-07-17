package com.absolute.bookrecommend.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private int code;
    private String message;
    private Object data;
    private Integer id;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(int code, String message, Object data, Integer id) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.id = id;
    }
}

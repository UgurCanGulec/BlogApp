package com.gulecugurcan.util.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class BaseResponse<T> {
    private T data;
    private String errorCode;
    private Map<String, Object> errorDetail;
    private Boolean isSuccess = Boolean.TRUE;

    public BaseResponse(T data) {
        this.data = data;
    }
}

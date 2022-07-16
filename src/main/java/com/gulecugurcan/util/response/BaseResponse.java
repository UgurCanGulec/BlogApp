package com.gulecugurcan.util.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private T data;
    private String errorCode;
    private Map<String, Object> errorDetail;
    private Boolean isSuccess = Boolean.TRUE;

    public BaseResponse(T data) {
        this.data = data;
    }
}

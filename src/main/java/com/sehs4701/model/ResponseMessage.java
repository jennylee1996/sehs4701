package com.sehs4701.model;


import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage<T> {

    private boolean success;
    private String message;
    @Nullable
    private T data;

    public ResponseMessage(boolean success, String message, @Nullable T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public ResponseMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null;
    }

}

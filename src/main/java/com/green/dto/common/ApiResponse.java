package com.green.dto.common;

import com.green.dto.common.pagination.PagedResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import static com.green.constants.Const.AppCode.SUCCESS_CODE;

@Getter
@Setter
public class ApiResponse<T> {
    private String code;
    private String message;
    private T body;

    public ApiResponse() {
        super();
        this.code = SUCCESS_CODE;
        this.message = "SUCCESS";
    }

    public ApiResponse(String code, String message, T body) {
        super();
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public ApiResponse(T body) {
        super();
        this.code = SUCCESS_CODE;
        this.message = "SUCCESS";
        this.body = body;
    }

    public ApiResponse(Page body) {
        super();
        this.code = SUCCESS_CODE;
        this.message = "SUCCESS";
        this.body = (T) PagedResponse.builder().page(body).build();
    }

    public ApiResponse<T> emptyBody() {
        return this;
    }

    protected ApiResponse<T> withCode(String code) {
        this.code = code;
        return this;
    }

    public ApiResponse<T> withMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiResponse<T> withBody(T body) {
        this.body = body;
        return this;
    }
}
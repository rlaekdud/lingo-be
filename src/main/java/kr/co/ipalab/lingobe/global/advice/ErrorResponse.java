package kr.co.ipalab.lingobe.global.advice;

import lombok.Builder;

public record ErrorResponse<T>(int status, ErrorCode code, T message) {

    @Builder
    public ErrorResponse {
    }

}

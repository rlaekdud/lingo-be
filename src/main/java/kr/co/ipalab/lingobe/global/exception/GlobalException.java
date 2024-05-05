package kr.co.ipalab.lingobe.global.exception;

import java.util.HashMap;
import java.util.Map;

import kr.co.ipalab.lingobe.global.advice.ErrorCode;
import lombok.Getter;

@Getter
public abstract class GlobalException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public GlobalException(String message) {
        super(message);
    }

    public abstract ErrorCode code();
}

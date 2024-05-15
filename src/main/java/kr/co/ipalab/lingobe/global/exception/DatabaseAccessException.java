package kr.co.ipalab.lingobe.global.exception;

import kr.co.ipalab.lingobe.global.advice.ErrorCode;
import kr.co.ipalab.lingobe.global.advice.ErrorMessage;

public class DatabaseAccessException extends GlobalException {

    public DatabaseAccessException() {
        super(ErrorMessage.DATABASE_ERROR.getMessage());
    }

    @Override
    public ErrorCode code() {
        return ErrorCode.D02;
    }
}

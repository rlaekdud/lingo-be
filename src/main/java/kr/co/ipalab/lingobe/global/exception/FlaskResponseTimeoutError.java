package kr.co.ipalab.lingobe.global.exception;

import kr.co.ipalab.lingobe.global.advice.ErrorCode;
import kr.co.ipalab.lingobe.global.advice.ErrorMessage;

public class FlaskResponseTimeoutError extends GlobalException {

        public FlaskResponseTimeoutError() {
            super(ErrorMessage.HTTP_NO_RESPONSE.getMessage());
        }

        @Override
        public ErrorCode code() {
            return ErrorCode.R04;
        }

}

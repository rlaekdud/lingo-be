package kr.co.ipalab.lingobe.global.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    P01("파라미터 유효성 검사 실패"),
    T01("토큰 유효성 검증 실패"),
    A01("권한이 없음"),
    A03("페이지 권한이 존재하지 않습니다."),
    D01("중복된 데이터"),
    R01("API 존재하지 않음"),
    R02("Content-type 불일치"),
    R03("미디어 타입 미지원"),
    U01("유효하지 않은 세션"),
    F01("유효하지 않은 파일"),
    D02("데이터베이스 접근 실패"),
    D03("조회 결과 없음"),
    R04("유효하지 않은 요청");

    private final String description;
}

package kr.co.ipalab.lingobe.api.translation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.ipalab.lingobe.api.translation.dto.request.TranslationRequestDto;
import kr.co.ipalab.lingobe.api.translation.service.TranslationService;
import kr.co.ipalab.lingobe.global.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * translation controller class
 * @version 1.0.0
 * @author Kim Dayeong
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/translation")
@Tag(name = "번역 컨트롤러")
public class TranslationController {
    private final TranslationService translationService;

    /**
     * 번역 메소드
     * @param translationRequestDto translationRequest 시 사용하는 dto
     * @return Response<?>
     */
    @GetMapping()
    @Operation(summary = "번역", description = "문장을 번역하고 이에 대한 자체 성능평가 결과와 함께 제공합니다.")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Response.class)))
    public Response<?> translate(@RequestBody TranslationRequestDto translationRequestDto) {
        return Response.ok(translationService.translateText(translationRequestDto), "성공적으로 번역하였습니다.");
    }
}

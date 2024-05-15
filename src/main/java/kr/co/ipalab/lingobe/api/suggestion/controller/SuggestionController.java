package kr.co.ipalab.lingobe.api.suggestion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.ipalab.lingobe.api.suggestion.dto.request.SuggestionRequestDto;
import kr.co.ipalab.lingobe.api.suggestion.service.SuggestionService;
import kr.co.ipalab.lingobe.global.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suggestion")
@Tag(name = "추천 단어 제안 컨트롤러")
public class SuggestionController {

    private final SuggestionService suggestionService;


    @GetMapping()
    @Operation(summary = "추천 단어 제안", description = "단어와 전체 문장을 입력하면 해당 단어에 대한 추천 단어를 제안합니다.")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Response.class)))
    public Response<?> getSuggestion(@RequestBody SuggestionRequestDto suggestionRequestDto) {
        return Response.ok(suggestionService.getSuggestion(suggestionRequestDto), "성공적으로 추천 단어를 제안하였습니다.");
    }
}
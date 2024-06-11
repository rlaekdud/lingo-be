package kr.co.ipalab.lingobe.api.log.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.ipalab.lingobe.api.log.dto.request.LogRequestDto;
import kr.co.ipalab.lingobe.api.log.service.LogService;
import kr.co.ipalab.lingobe.global.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Log Controller
 * @version 1.0.0
 * @author Kim Dayeong
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/lingo-be/api")
@Tag(name = "로그 컨트롤러")
public class LogController {

    private final LogService logService;

    /**
     * save log with request
     * @param logRequestDto log
     * @return success message
     */
    @PostMapping("/log")
    @Operation(summary = "로그 저장", description = "로그를 저장합니다.")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Response.class)))
    public Response<?> saveLog(@RequestBody LogRequestDto logRequestDto) {
        logService.saveLogFromRequest(logRequestDto);
        return Response.ok("로그를 성공적으로 저장하였습니다.");
    }
}

package kr.co.ipalab.lingobe.api.modelList.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.ipalab.lingobe.api.modelList.dto.response.ModelListResponseDto;
import kr.co.ipalab.lingobe.api.modelList.service.ModelListService;
import kr.co.ipalab.lingobe.global.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * model list controller class
 * @version 1.0.0
 * @author Kim Dayeong
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/lingo-be/api")
@Tag(name = "모델 리스트 컨트롤러")
public class ModelListController {

    private final ModelListService modelListService;

    /**
     * get model list
     * @return model list
     */
    @GetMapping("/model")
    @Operation(summary = "모델 리스트를 가져오는 api", description = "Flask 서버로부터 모델 리스트를 가져옵니다.")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ModelListResponseDto.class)))
    public Response<?> getModelList() {
        return Response.ok(modelListService.getModelList(), "모델 리스트를 성공적으로 가져왔습니다.");
    }
}

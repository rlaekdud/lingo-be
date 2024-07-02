package kr.co.ipalab.lingobe.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            this.addResponseBodyWrapperSchemaExample(operation);
            return operation;
        };
    }

    private void addResponseBodyWrapperSchemaExample(Operation operation) {
        final Content content = operation.getResponses().get("200").getContent();
        if (content != null) {
            content.forEach((mediaTypeKey, mediaType) -> {
                Schema<?> originalSchema = mediaType.getSchema();
                Schema<?> wrapperSchema = wrapSchema(originalSchema);
                mediaType.setSchema(wrapperSchema);
            });
        }
    }

    private Schema<?> wrapSchema(Schema<?> originalSchema) {
        Schema<?> wrapperSchema = new Schema<>();
        wrapperSchema.addProperties("status", new Schema<>().type("integer").example(200));
        wrapperSchema.addProperties("message", new Schema<>().type("string"));
        wrapperSchema.addProperties("data", originalSchema);
        return wrapperSchema;
    }

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
            .version("v1.0.0")
            .title("API")
            .description("");

        SecurityScheme auth = new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.COOKIE).name("JSESSIONID");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("sessionAuth"); // 헤더에 토큰 포함

        // When Using JWT, Activate Below Code
//		String jwt = "JWT";
//		SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt); // 헤더에 토큰 포함
//		Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
//			.name(jwt)
//			.type(SecurityScheme.Type.HTTP)
//			.scheme("Bearer")
//			.bearerFormat("JWT")
//		);

        return new OpenAPI()
            .addServersItem(new Server().url("/"))
            .info(info)
            .addSecurityItem(securityRequirement)
            .components(new Components().addSecuritySchemes("sessionAuth", auth))
            ;

    }

}

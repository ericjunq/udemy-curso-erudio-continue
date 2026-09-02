package com.ericjunq.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Find all People",
        description = "Finds all People",
        tags = {"People"},
        responses = {
                @ApiResponse(
                        description = "Success",
                        responseCode = "200"
                ),
                @ApiResponse(description = "No content", responseCode = "204"),
                @ApiResponse(description = "Bad Request", responseCode = "400"),
                @ApiResponse(description = "Unauthorized", responseCode = "401"),
                @ApiResponse(description = "Not Found", responseCode = "404"),
                @ApiResponse(description = "Internal Server Error", responseCode = "500")
        }
)
public @interface SwaggerApiConfiguration {
    @AliasFor(annotation = Operation.class, attribute = "summary")
    String summary() default "Find all People";

    @AliasFor(annotation = Operation.class, attribute = "description")
    String description() default "Find all People";

    @AliasFor(annotation = Operation.class, attribute = "tags")
    String tags() default "People";
}

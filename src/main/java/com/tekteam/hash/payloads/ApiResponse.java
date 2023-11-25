package com.tekteam.hash.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private Object data;
    @JsonIgnore
    private HttpStatus status;

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Object data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }
}

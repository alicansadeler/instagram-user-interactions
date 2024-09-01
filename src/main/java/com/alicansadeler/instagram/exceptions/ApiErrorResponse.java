package com.alicansadeler.instagram.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiErrorResponse {
    private String message;

}

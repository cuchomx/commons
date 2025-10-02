package com.example.commons.dto.find;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFindAllResponseDto {

    private Integer limit;
    private Integer offset;

    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

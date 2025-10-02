package com.example.commons.dto.find;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFindAllRequestDto {

    private String id;

    @Min(0)
    private Integer limit;

    @Min(1)
    private Integer offset;

}

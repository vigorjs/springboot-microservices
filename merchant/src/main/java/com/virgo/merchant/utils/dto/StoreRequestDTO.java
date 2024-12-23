package com.virgo.merchant.utils.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequestDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String merchant_name;

    @NotNull
    @NotBlank
    @NotEmpty
    private String merchant_description;


}

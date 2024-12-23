package com.virgo.product.utils.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotNull
    private Integer merchant_id;

    private String product_name;

    @Nullable
    private String product_description;

    @Min(value = 0, message = "price cant be negative")
    private Long price;

    @Nullable
    @Min(value = 0, message = "Error stock cant be negative")
    private Integer stock;
}
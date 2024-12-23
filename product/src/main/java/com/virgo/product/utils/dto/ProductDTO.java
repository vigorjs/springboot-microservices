package com.virgo.product.utils.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Integer id;
    private Integer merchant_id;
    private String product_name;

    @Nullable
    private String product_description;

    @Min(value = 0, message = "price cant be negative")
    private Long price;

    @Min(value = 0, message = "price cant be negative")
    private Integer stock = 0;

}

package com.virgo.merchant.model.meta;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String product_name;
    private String product_description;
    private Long price;
    private Integer stock = 0;
}

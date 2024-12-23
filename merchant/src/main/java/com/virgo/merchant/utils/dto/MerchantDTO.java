package com.virgo.merchant.utils.dto;

import com.virgo.merchant.model.meta.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDTO {
    private Integer id;
    private String merchant_name;
    private String merchant_description;

    List<Product> products;
}

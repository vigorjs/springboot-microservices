package com.virgo.merchant.client;

import com.virgo.merchant.model.meta.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", url = "${application.config.products-url}")
public interface ProductClient {

    @GetMapping("/merchant/{id}")
    List<Product> getAllProductByMerchantId(@PathVariable("id") Integer id);
}

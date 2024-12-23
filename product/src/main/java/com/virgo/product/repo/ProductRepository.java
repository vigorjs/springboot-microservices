package com.virgo.product.repo;

import com.virgo.product.model.meta.Product;
import com.virgo.product.utils.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    ProductDTO save(Product product);
    Optional<ProductDTO> findById(Integer id);
    List<ProductDTO> findAll();
    List<ProductDTO> findByMerchantId(Integer storeId);
    void update(Product product);
    void deleteById(Integer id);
}
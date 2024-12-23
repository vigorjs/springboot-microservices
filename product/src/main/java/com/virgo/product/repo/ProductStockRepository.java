package com.virgo.product.repo;

import com.virgo.product.model.meta.ProductStock;

import java.util.List;
import java.util.Optional;

public interface ProductStockRepository {
    ProductStock save(ProductStock stock);
    Optional<ProductStock> findById(Integer id);
    Optional<ProductStock> findByProductId(Integer productId);
    List<ProductStock> findAll();
    void update(ProductStock stock);
    void deleteById(Integer id);
}
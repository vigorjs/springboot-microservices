package com.virgo.product.service;

import com.virgo.product.utils.dto.ProductDTO;
import com.virgo.product.utils.dto.ProductRequestDTO;

import java.util.List;

public interface ProductService {
    ProductDTO create(ProductRequestDTO req);
    List<ProductDTO> getAll();
    ProductDTO getById(Integer id);
    List<ProductDTO> getByMerchantId(Integer storeId);
    void delete(Integer id);
    ProductDTO updateById(Integer id, ProductRequestDTO req);
}
package com.virgo.product.repo.impl;

import com.virgo.product.model.meta.Product;
import com.virgo.product.repo.ProductRepository;
import com.virgo.product.utils.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ProductDTO save(Product product) {
        String sql = "INSERT INTO product (merchant_id, product_name, product_description, price) VALUES (?, ?, ?, ?) RETURNING id, merchant_id, product_name, product_description, price";
        return jdbcTemplate.queryForObject(sql, new Object[]{product.getMerchant_id(), product.getProduct_name(), product.getProduct_description(), product.getPrice()}, new ProductRowMapper());
    }

    @Override
    public Optional<ProductDTO> findById(Integer id) {
        String sql = "SELECT product.*, st.quantity as stock FROM product JOIN product_stock st ON product.id=st.products_id WHERE product.id = ?";
        try {
            ProductDTO product = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ProductDTO> findAll() {
        String sql = "SELECT product.*, st.quantity as stock from product JOIN product_stock st ON product.id=st.products_id";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public List<ProductDTO> findByMerchantId(Integer storeId) {
        String sql = "SELECT product.*, st.quantity as stock FROM product JOIN product_stock st ON product.id=st.products_id WHERE product.merchant_id = ?";
        return jdbcTemplate.query(sql, new Object[]{storeId}, new ProductRowMapper());
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET merchant_id = ?, product_name = ?, product_description = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getMerchant_id(), product.getProduct_name(), product.getProduct_description(), product.getPrice(), product.getId());
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ProductRowMapper implements RowMapper<ProductDTO> {
        @Override
        public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer stock = null;
            try {
                stock = rs.getInt("stock");
            } catch (SQLException e) {
                // Jika kolom stock tidak ditemukan, stock tetap 0
            }
            return ProductDTO.builder()
                    .id(rs.getInt("id"))
                    .merchant_id(rs.getInt("merchant_id"))
                    .product_name(rs.getString("product_name"))
                    .product_description(rs.getString("product_description"))
                    .price(rs.getLong("price"))
                    .stock(stock)
                    .build();
        }
    }
}
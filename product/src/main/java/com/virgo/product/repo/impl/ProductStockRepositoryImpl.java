package com.virgo.product.repo.impl;

import com.virgo.product.model.meta.ProductStock;
import com.virgo.product.repo.ProductStockRepository;
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
public class ProductStockRepositoryImpl implements ProductStockRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ProductStock save(ProductStock stock) {
        String sql = "INSERT INTO product_stock (products_id, quantity) VALUES (?, ?) RETURNING id, products_id, quantity";
        return jdbcTemplate.queryForObject(sql, new Object[]{stock.getProducts_id(), stock.getQuantity()}, new StockRowMapper());
    }

    @Override
    public Optional<ProductStock> findById(Integer id) {
        String sql = "SELECT * FROM product_stock WHERE id = ?";
        try {
            ProductStock stock = jdbcTemplate.queryForObject(sql, new Object[]{id}, new StockRowMapper());
            return Optional.ofNullable(stock);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProductStock> findByProductId(Integer productId) {
        String sql = "SELECT * FROM product_stock WHERE products_id = ?";
        try {
            ProductStock stock = jdbcTemplate.queryForObject(sql, new Object[]{productId}, new StockRowMapper());
            return Optional.ofNullable(stock);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ProductStock> findAll() {
        String sql = "SELECT * FROM product_stock";
        return jdbcTemplate.query(sql, new StockRowMapper());
    }

    @Override
    public void update(ProductStock stock) {
        String sql = "UPDATE product_stock SET products_id = ?, quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql, stock.getProducts_id(), stock.getQuantity(), stock.getId());
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM product_stock WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class StockRowMapper implements RowMapper<ProductStock> {
        @Override
        public ProductStock mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ProductStock.builder()
                    .id(rs.getInt("id"))
                    .products_id(rs.getInt("products_id"))
                    .quantity(rs.getInt("quantity"))
                    .build();
        }
    }
}
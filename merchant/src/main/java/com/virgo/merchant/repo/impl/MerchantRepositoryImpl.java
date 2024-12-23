package com.virgo.merchant.repo.impl;

import com.virgo.merchant.model.meta.Merchant;
import com.virgo.merchant.repo.MerchantRepository;
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
public class MerchantRepositoryImpl implements MerchantRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Merchant save(Merchant merchant) {
        String sql = "INSERT INTO merchant (merchant_name, merchant_description) VALUES (?, ?) RETURNING id, merchant_name, merchant_description";
        return jdbcTemplate.queryForObject(sql, new Object[]{merchant.getMerchant_name(), merchant.getMerchant_description()}, new MerchantRowMapper());
    }

    @Override
    public void update(Merchant merchant) {
        String sql = "UPDATE merchant SET merchant_name = ?, merchant_description = ? WHERE id = ? RETURNING id, merchant_name, merchant_description";
        jdbcTemplate.queryForObject(sql, new Object[]{merchant.getMerchant_name(), merchant.getMerchant_description(), merchant.getId()}, new MerchantRowMapper());
    }

    @Override
    public Optional<Merchant> findById(Integer id) {
        String sql = "SELECT * FROM merchant WHERE id = ?";
        try {
            Merchant merchant = jdbcTemplate.queryForObject(sql, new Integer[]{id}, new MerchantRowMapper());
            return Optional.of(merchant);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM merchant WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Merchant> findAll() {
        String sql = "SELECT * FROM merchant";
        return jdbcTemplate.query(sql, new MerchantRowMapper());
    }

    private static class MerchantRowMapper implements RowMapper<Merchant> {

        @Override
        public Merchant mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Merchant.builder()
                    .id(rs.getInt("id"))
                    .merchant_name(rs.getString("merchant_name"))
                    .merchant_description(rs.getString("merchant_description"))
                    .build();
        }
    }
}

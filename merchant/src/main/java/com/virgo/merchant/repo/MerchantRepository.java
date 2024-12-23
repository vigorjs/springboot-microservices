package com.virgo.merchant.repo;


import com.virgo.merchant.model.meta.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantRepository {
    Merchant save(Merchant store);
    Optional<Merchant> findById(Integer id);
    List<Merchant> findAll();
    void update(Merchant user);
    void deleteById(Integer id);
}

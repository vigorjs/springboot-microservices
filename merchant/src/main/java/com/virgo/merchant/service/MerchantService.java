package com.virgo.merchant.service;


import com.virgo.merchant.model.meta.Merchant;
import com.virgo.merchant.utils.dto.MerchantDTO;
import com.virgo.merchant.utils.dto.StoreRequestDTO;

import java.util.List;

public interface MerchantService {
    Merchant create(StoreRequestDTO req);
    List<Merchant> getAll();
    Merchant getById(Integer id);
    void delete(Integer id);
    Merchant updateById(Integer id, StoreRequestDTO req);

    MerchantDTO getAllProductByMerchantId(Integer id);
}

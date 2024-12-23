package com.virgo.merchant.service.impl;

import com.virgo.merchant.client.ProductClient;
import com.virgo.merchant.config.advisers.exception.NotFoundException;
import com.virgo.merchant.model.meta.Merchant;
import com.virgo.merchant.model.meta.Product;
import com.virgo.merchant.repo.MerchantRepository;
import com.virgo.merchant.service.MerchantService;
import com.virgo.merchant.utils.dto.MerchantDTO;
import com.virgo.merchant.utils.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final ProductClient productClient;

    @Override
    public Merchant create(StoreRequestDTO req) {
//        User user = authenticationService.getUserAuthenticated();
        Merchant merchant = Merchant.builder()
                .merchant_name(req.getMerchant_name())
                .merchant_description(req.getMerchant_description())
                .build();
        return merchantRepository.save(merchant);
    }

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant getById(Integer id) {
        return merchantRepository.findById(id).orElseThrow( () -> new NotFoundException("Merchant not Found") );
    }

    @Override
    public void delete(Integer id) {
        if (merchantRepository.findById(id).isPresent()){
            merchantRepository.deleteById(id);
        }else {
            throw new NotFoundException("Category dengan ID " + id + "tidak ditemukan");
        }
    }

    @Override
    public Merchant updateById(Integer id, StoreRequestDTO req) {
        Merchant target = getById(id);
        Merchant storeRequest = updateStoreRequest(target, req);
        merchantRepository.update(storeRequest);

        return target;
    }

    @Override
    public MerchantDTO getAllProductByMerchantId(Integer id) {
        Merchant merchant = getById(id);
        var products = productClient.getAllProductByMerchantId(id);
        return MerchantDTO.builder()
                .id(merchant.getId())
                .merchant_name(merchant.getMerchant_name())
                .merchant_description(merchant.getMerchant_description())
                .products(products)
                .build();
    }


    //helper
    private Merchant updateStoreRequest(Merchant merchant, StoreRequestDTO req) {
        if (req.getMerchant_name() != null && !req.getMerchant_name().isEmpty()) {
            merchant.setMerchant_name(req.getMerchant_name());
        }
        if (req.getMerchant_description() != null && !req.getMerchant_description().isEmpty()) {
            merchant.setMerchant_description(req.getMerchant_description());
        }
        return new Merchant(
                merchant.getId(),
                req.getMerchant_name() != null ? req.getMerchant_name() : merchant.getMerchant_name(),
                req.getMerchant_description() != null ? req.getMerchant_description() : merchant.getMerchant_description()
        );
    }
}

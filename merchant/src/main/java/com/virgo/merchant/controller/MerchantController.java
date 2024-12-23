package com.virgo.merchant.controller;

import com.virgo.merchant.service.MerchantService;
import com.virgo.merchant.utils.dto.StoreRequestDTO;
import com.virgo.merchant.utils.responseWrapper.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchants")
@RequiredArgsConstructor
@RestControllerAdvice
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return Response.renderJSON(merchantService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return Response.renderJSON(merchantService.getById(id));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findAllProductByMerchantId(@PathVariable Integer id) {
        return Response.renderJSON(merchantService.getAllProductByMerchantId(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody StoreRequestDTO req) {
        return Response.renderJSON(
                merchantService.create(req),
                "Merchant berhasil dibuat!",
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody StoreRequestDTO req) {
//        updateById
        return Response.renderJSON(
                merchantService.updateById(id, req),
                "Merchant Updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
//        delete
        merchantService.delete(id);
        return Response.renderJSON(null,"Merchant berhasil dihapus", HttpStatus.OK);
    }
}

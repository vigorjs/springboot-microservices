package com.virgo.product.controller;

import com.virgo.product.service.ProductService;
import com.virgo.product.utils.dto.ProductDTO;
import com.virgo.product.utils.dto.ProductRequestDTO;
import com.virgo.product.utils.responseWrapper.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@RestControllerAdvice
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return Response.renderJSON(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return Response.renderJSON(productService.getById(id));
    }

    @GetMapping("/merchant/{id}")
    public List<ProductDTO> findByMerchantId(@PathVariable Integer id) {
        return productService.getByMerchantId(id);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody ProductRequestDTO req) {
        return Response.renderJSON(
                productService.create(req),
                "Product berhasil dibuat!",
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody ProductRequestDTO req) {
//        updateById
        return Response.renderJSON(
                productService.updateById(id, req),
                "Product Updated",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
//        delete
        productService.delete(id);
        return Response.renderJSON(null,"Product berhasil dihapus", HttpStatus.OK);
    }
}

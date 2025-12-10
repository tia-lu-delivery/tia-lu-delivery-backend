package com.tialu.delivery.controllers;

import com.tialu.delivery.dtos.ErrorResponseDTO;
import com.tialu.delivery.dtos.ProductRequestDTO;
import com.tialu.delivery.dtos.ProductResponseDTO;
import com.tialu.delivery.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
public class ProductController {


    @Autowired
    private ProductService productService;


    @PostMapping("/{idCardapio}/categories/{idCategoria}/products")
    public ResponseEntity<?> createProduct(
            @PathVariable Long idCardapio,
            @PathVariable Long idCategoria,
            @Valid @RequestBody ProductRequestDTO request) {



        try {

            ProductResponseDTO response = productService.createProduct(idCardapio, idCategoria, request);


            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {


            if ("DUPLICATE_NAME".equals(e.getMessage())) {
                ErrorResponseDTO error = new ErrorResponseDTO("DUPLICATE_NAME", "Já existe um produto com o nome '" + request.getNome() + "' nesta categoria.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409 Conflict
            }


            if ("ASSOCIATION_NOT_FOUND".equals(e.getMessage())) {
                ErrorResponseDTO error = new ErrorResponseDTO("ASSOCIATION_NOT_FOUND", "A Categoria ou Cardápio não foi encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // 404 Not Found
            }


            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }
}
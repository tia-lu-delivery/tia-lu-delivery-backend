package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.MenuDTO;
import br.com.fooddelivery.tialudeliveryback.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {


    private final MenuService menuService;

    @GetMapping("/{idCardapio}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable String idCardapio) {
        MenuDTO menu = menuService.getMenuById(idCardapio);
        return ResponseEntity.ok(menu);
    }

}

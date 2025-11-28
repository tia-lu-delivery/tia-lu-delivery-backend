package br.com.fooddelivery.tialudeliveryback.Service;

import org.springframework.stereotype.Service;

import br.com.fooddelivery.tialudeliveryback.Repository.CategoriaRepository;
import br.com.fooddelivery.tialudeliveryback.Entity.Menu;
import br.com.fooddelivery.tialudeliveryback.Entity.MenuRepository;
import br.com.fooddelivery.tialudeliveryback.Entity.Categoria;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemoveCategoryService {
    private final CategoriaRepository categoriaRepository;
    private final MenuRepository menuRepository;

    public void removeCategory(Long id, Long menuId) {
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new RuntimeException("Menu não encontrado"));
    
        Categoria category = categoriaRepository.findByIdAndMenuId(id, menuId)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    
        categoriaRepository.delete(category);
    }
}

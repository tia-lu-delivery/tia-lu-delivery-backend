package br.com.fooddelivery.tialudeliveryback.Service;

import org.springframework.stereotype.Service;

import br.com.fooddelivery.tialudeliveryback.Repository.CategoryRepository;
import br.com.fooddelivery.tialudeliveryback.Repository.MenuRepository;
import br.com.fooddelivery.tialudeliveryback.Entity.Menu;
import br.com.fooddelivery.tialudeliveryback.Entity.Category;
import br.com.fooddelivery.tialudeliveryback.Mapper.CategoryMapper;
import br.com.fooddelivery.tialudeliveryback.DTO.CategoryDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemoveCategoryService {
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDTO removeCategory(Long id, Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("Menu não encontrado"));
        Category category = categoryRepository.findByIdAndMenuId(id, menuId).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        Category deletedCategory = categoryRepository.delete(category);

        return categoryMapper.toDTO(deletedCategory);
    }
}

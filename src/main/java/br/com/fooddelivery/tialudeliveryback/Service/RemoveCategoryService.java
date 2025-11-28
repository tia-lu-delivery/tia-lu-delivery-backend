package br.com.fooddelivery.tialudeliveryback.Service;

import org.springframework.stereotype.Service;

import br.com.fooddelivery.tialudeliveryback.dto.DeleteCategoryResDTO;
import br.com.fooddelivery.tialudeliveryback.Repository.CategoriaRepository;
import br.com.fooddelivery.tialudeliveryback.Repository.MenuRepository;
import br.com.fooddelivery.tialudeliveryback.Mapper.DeleteCategoryResMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemoveCategoryService {
    private final CategoriaRepository categoriaRepository;
    private final MenuRepository menuRepository;

    public DeleteCategoryResDTO deleteCategory(Long idCardapio, Long idCategoria) {
        // Primeiro, verifica se o cardápio existe
        if (!menuRepository.existsById(idCardapio)) {
            return DeleteCategoryResMapper.toCardapioNaoEncontrado(
                    idCategoria.toString(),
                    idCardapio.toString()
            );
        }

        // Depois, verifica se a categoria existe e pertence a esse cardápio
        return categoriaRepository.findByIdAndMenuId(idCategoria, idCardapio)
            .map(categoria -> {
                categoriaRepository.delete(categoria);
                return DeleteCategoryResMapper.toSucesso(
                        idCategoria.toString(),
                        idCardapio.toString()
                );
            })
            .orElseGet(() -> DeleteCategoryResMapper.toCategoriaNaoEncontrada(
                    idCategoria.toString(),
                    idCardapio.toString()
            ));
    }
}

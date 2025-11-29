package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.api.dto.MenuItemSearchResponseDTO;
import br.com.fooddelivery.tialudeliveryback.api.dto.RestaurantSearchResultDTO;
import br.com.fooddelivery.tialudeliveryback.api.dto.SearchResponseDTO;
import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import br.com.fooddelivery.tialudeliveryback.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final MenuItemRepository menuItemRepository;

    public SearchService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Realiza a pesquisa de pratos por nome e retorna uma lista de restaurantes
     * que os vendem, formatada como SearchResponseDTO.
     *
     * @param plateTerm O termo de busca para o nome do prato (Critério CA-01).
     * @return SearchResponseDTO com os resultados (Critério CA-05).
     */
    public SearchResponseDTO searchRestaurantsByPlate(String plateTerm) {
        // Validação básica do termo de pesquisa
        if (plateTerm == null || plateTerm.trim().isEmpty()) {
            // Conforme a especificação de erro, essa validação será melhor tratada
            // no Controller, retornando um 400 Bad Request.
            // Para o Service, lançamos uma exceção que será capturada no Controller.
            throw new IllegalArgumentException("O termo de pesquisa 'plate' é obrigatório.");
        }

        // 1. Pesquisar todos os MenuItem que contenham o termo (Critério CA-03)
        // O método do Repository já garante a busca case-insensitive e por substring.
        List<MenuItem> matchedMenuItems = menuItemRepository.findByNomeContainingIgnoreCase(plateTerm.trim());

        // 2. Agrupar os itens de menu encontrados pelo Restaurante associado
        // A chave do mapa será o objeto Restaurant e o valor será a lista de MenuItem correspondentes.
        Map<Restaurant, List<MenuItem>> groupedByRestaurant = matchedMenuItems.stream()
                .collect(Collectors.groupingBy(MenuItem::getRestaurant));

        // 3. Mapear o resultado agrupado para a lista de DTOs de Resultado
        List<RestaurantSearchResultDTO> results = groupedByRestaurant.entrySet().stream()
                .map(entry -> {
                    Restaurant restaurant = entry.getKey();
                    List<MenuItem> matchedItems = entry.getValue();

                    // Mapeia os MenuItem encontrados para o DTO de resposta do item
                    List<MenuItemSearchResponseDTO> itemDTOs = matchedItems.stream()
                            .map(MenuItemSearchResponseDTO::new)
                            .collect(Collectors.toList());

                    // Cria o DTO do resultado do restaurante (Critério CA-05)
                    return new RestaurantSearchResultDTO(restaurant, itemDTOs);
                })
                // Critério CA-06: Ordenação pode ser implementada aqui se necessário.
                // Por padrão, a ordenação é deixada por relevância implícita (a ordem do agrupamento).
                .collect(Collectors.toList());

        // 4. Constrói e retorna o DTO de resposta final
        // Se nenhum resultado for encontrado (results.isEmpty()), o DTO será
        // construído com uma lista vazia e totalResultados = 0 (Critério CA-07).
        return new SearchResponseDTO(plateTerm, results);
    }
}
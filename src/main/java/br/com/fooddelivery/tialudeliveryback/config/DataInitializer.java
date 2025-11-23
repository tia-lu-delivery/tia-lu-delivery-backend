package br.com.fooddelivery.tialudeliveryback.config;

import br.com.fooddelivery.tialudeliveryback.domain.MenuItem;
import br.com.fooddelivery.tialudeliveryback.domain.Restaurant;
import br.com.fooddelivery.tialudeliveryback.repository.MenuItemRepository;
import br.com.fooddelivery.tialudeliveryback.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Insere dados mockados na base de dados ao inicializar a aplicação,
 * apenas quando a base estiver vazia. Útil para testes manuais da API.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public DataInitializer(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Se já houver dados, não insere novamente
        if (menuItemRepository.count() > 0) {
            return;
        }

        // Restaurante 1
        Restaurant r1 = new Restaurant("R1001", "Burger Mania", 4.8, "30-45 min");
        // Restaurante 2
        Restaurant r2 = new Restaurant("R1002", "Cantina Italiana", 4.1, "40-55 min");

        restaurantRepository.saveAll(Arrays.asList(r1, r2));

        // Pratos do restaurante 1
        MenuItem p1 = new MenuItem(
                "Hamburguer Clássico com Cheddar",
                "Carne suculenta, queijo cheddar, alface e molho especial.",
                BigDecimal.valueOf(32.90),
                r1
        );

        MenuItem p2 = new MenuItem(
                "Hamburguer Vegano",
                "Pão integral, burger de grão-de-bico e maionese vegana.",
                BigDecimal.valueOf(35.50),
                r1
        );

        // Prato do restaurante 2
        MenuItem p3 = new MenuItem(
                "Parmegiana Hamburguês",
                "Prato executivo com molho de tomate, queijo e arroz.",
                BigDecimal.valueOf(59.90),
                r2
        );

        menuItemRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}

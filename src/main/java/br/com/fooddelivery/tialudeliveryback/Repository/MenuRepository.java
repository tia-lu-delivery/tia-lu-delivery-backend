package br.com.fooddelivery.tialudeliveryback.Repository;

import br.com.fooddelivery.tialudeliveryback.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}



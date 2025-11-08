package br.com.fooddelivery.tialudeliveryback.Repository;

import br.com.fooddelivery.tialudeliveryback.Entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}

package br.com.fooddelivery.tialudeliveryback.domain.repository;


import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PaymentMethodRepositoryTest {

    @Autowired
    private PaymentMethodRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("findByIdAndUserId deve retornar o meio de pagamento apenas quando pertencer ao usuário informado")
    void shouldFindByIdAndUserIdOnlyForOwner() {
        // arrange: persiste um meio de pagamento de um usuário específico
        PaymentMethod pm = new PaymentMethod();
        pm.setId("MP001");
        pm.setNumeroCartao("1234567890124321");
        pm.setBandeira("Visa");
        pm.setNomeTitular("JOAO DA SILVA");
        pm.setValidadeMes(12);
        pm.setValidadeAno(2028);
        pm.setTipoCartao("CREDITO");
        pm.setAtivo(true);
        pm.setUserId("user-123"); // campo usado pelo método findByIdAndUserId

        entityManager.persist(pm);
        entityManager.flush();

        // act + assert: deve encontrar quando id e userId batem
        Optional<PaymentMethod> found =
                repository.findByIdAndUserId("MP001", "user-123");

        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo("MP001");
        assertThat(found.get().getUserId()).isEqualTo("user-123");

        // act + assert: não deve encontrar quando o userId é diferente
        Optional<PaymentMethod> notFoundForOtherUser =
                repository.findByIdAndUserId("MP001", "user-999");

        assertThat(notFoundForOtherUser).isEmpty();
    }
}

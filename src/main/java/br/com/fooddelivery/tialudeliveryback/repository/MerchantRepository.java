package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.model.Merchant;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Diz ao Spring que esta interface é um Repositório.
@Repository
// Herda do JpaRepository, ganhando métodos CRUD (save, findById, etc.)
// <Merchant, UUID> -> Gerencia a entidade "Merchant" cuja chave primária (ID) é do tipo "UUID"
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    // Verifica se um registro com este CNPJ já existe no banco.
    // O Spring Data JPA cria a consulta SQL automaticamente pelo nome do método.
    // Retorna 'true' se existir, 'false' se não.
    boolean existsByCnpj(String cnpj);
}

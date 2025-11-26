package br.com.fooddelivery.tialudeliveryback.repositories;

import br.com.fooddelivery.tialudeliveryback.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Busca todos os endereços de um usuário
     *
     * @param userId ID do usuário
     * @return Lista de endereços do usuário
     */
    List<Address> findByUserId(Long userId);

    /**
     * Busca um endereço específico por ID e ID do usuário
     *
     * @param id ID do endereço
     * @param userId ID do usuário
     * @return Optional contendo o endereço se encontrado
     */
    Optional<Address> findByIdAndUserId(Long id, Long userId);

    /**
     * Busca o endereço principal de um usuário
     *
     * @param userId ID do usuário
     * @return Optional contendo o endereço principal se houver
     */
    Optional<Address> findByUserIdAndPadraoEntregaTrue(Long userId);

    /**
     * Define todos os endereços de um usuário como não-principal
     *
     * @param userId ID do usuário
     */
    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.padraoEntrega = false WHERE a.userId = :userId")
    void unsetAllPrincipalAddressesByUserId(@Param("userId") Long userId);

    /**
     * Define um endereço específico como principal
     *
     * @param id ID do endereço
     */
    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.padraoEntrega = true WHERE a.id = :id")
    void setPrincipalAddress(@Param("id") Long id);
}

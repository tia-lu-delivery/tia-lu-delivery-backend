package br.com.fooddelivery.tialudeliveryback.repository;

import br.com.fooddelivery.tialudeliveryback.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
    Optional<Address> findByIdEnderecoAndIdUsuario(Long idEndereco, Long idUsuario);

    boolean existsByIdEnderecoAndIdUsuario(Long idEndereco, Long idUsuario);

    @Modifying
    @Query("UPDATE Address e SET e.isEnderecoPadrao = false WHERE e.idUsuario = :idUsuario")
    void removerEnderecoPadraoDoUsuario(@Param("idUsuario") Long idUsuario);
}


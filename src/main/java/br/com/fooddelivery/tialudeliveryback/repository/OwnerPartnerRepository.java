package br.com.fooddelivery.tialudeliveryback.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fooddelivery.tialudeliveryback.models.OwnerPartner;

@Repository
public interface OwnerPartnerRepository extends JpaRepository <OwnerPartner, UUID> {
    boolean existsByCpf (String cpf);
    Optional <OwnerPartner> findByCpf (String cpf);
    Optional <OwnerPartner> findByIdEstabelecimento (String idEstabelecimento);
}

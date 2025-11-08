package br.com.fooddelivery.tialudeliveryback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fooddelivery.tialudeliveryback.models.OwnerPartner;

@Repository
public interface OwnerPartnerRepository extends JpaRepository <OwnerPartner, String> {
    boolean existsByCpf (String cpf);
    Optional <OwnerPartner> findByCpf (String cpf);
    boolean existsByIdEstablishment (String idEstabelecimento);
    Optional <OwnerPartner> findByEstablishment (String idEstabelecimento);
}
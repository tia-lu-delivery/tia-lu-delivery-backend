package br.com.fooddelivery.tialudeliveryback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner_partner")
class OwnerPartnerEntity {

    @Id
    private String id;
    private String cpf;
    private String nome;
    private String idEstabelecimento;
}

@Repository
public interface OwnerPartnerRepository extends JpaRepository <OwnerPartnerEntity,String> {
    boolean existsByCpf (String cpf);
    Optional <OwnerPartnerEntity> findByCpf (String cpf);
    boolean existsByIdEstablishment (String idEstabelecimento);
    Optional <OwnerPartnerEntity> findByEstablishment (String idEstabelecimento);
}

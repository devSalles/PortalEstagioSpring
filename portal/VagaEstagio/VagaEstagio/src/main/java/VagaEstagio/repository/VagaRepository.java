package VagaEstagio.repository;

import VagaEstagio.model.VagaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<VagaModel,Long> {

    //Verifica se o estagiario esta cadastrado em mais de uma vaga
    boolean existsByEstagiarioModel_Id(Long id);
}

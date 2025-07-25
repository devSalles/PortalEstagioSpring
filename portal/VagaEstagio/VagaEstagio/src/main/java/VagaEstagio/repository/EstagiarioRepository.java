package VagaEstagio.repository;

import VagaEstagio.model.EstagiarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstagiarioRepository extends JpaRepository<EstagiarioModel,Long> {

    List<EstagiarioModel> findByVagaModel_Id(Long id);
}

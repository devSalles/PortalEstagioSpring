package VagaEstagio.repository;

import VagaEstagio.model.VagaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<VagaModel,Long> {
}

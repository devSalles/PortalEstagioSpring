package VagaEstagio.repository;

import VagaEstagio.model.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaModel,Long> {
}

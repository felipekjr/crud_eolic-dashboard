package api.repository;

import api.model.ComplexoEolico;
import api.model.ParqueEolico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ComplexoEolicoRepository extends JpaRepository<ComplexoEolico, Long> {
    Optional<ComplexoEolico> findByNome(String nome);
    Optional<ComplexoEolico> findByParquesEolicos(ParqueEolico parqueEolico);
}

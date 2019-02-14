package api.repository;

import api.model.ComplexoEolico;
import api.model.ParqueEolico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ParqueEolicoRepository extends JpaRepository<ParqueEolico, Long> {
    List<ParqueEolico> findByComplexoEolico(ComplexoEolico complexoEolico);
    Optional<ParqueEolico> findByNome(String nome);
}



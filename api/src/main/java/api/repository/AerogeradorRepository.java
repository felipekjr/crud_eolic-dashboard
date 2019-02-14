package api.repository;

import api.model.Aerogerador;
import api.model.ComplexoEolico;
import api.model.ParqueEolico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AerogeradorRepository extends JpaRepository<Aerogerador, Long> {
    List<Aerogerador> findByParqueEolico(ParqueEolico parqueEolico);
}


package agendaweb.repository;

import agendaweb.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaRepository extends JpaRepository<Contato, Integer> {

    List<Contato> findByContatoContainingIgnoreCase(String suggest);

}

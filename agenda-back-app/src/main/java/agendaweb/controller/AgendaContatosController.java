package agendaweb.controller;

import agendaweb.entity.Contato;
import agendaweb.entity.ContatoDTO;
import agendaweb.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contatos")
@CrossOrigin(origins = "http://localhost:4200")
public class AgendaContatosController {

    @Autowired
    AgendaRepository agendaRepository;

    @PostMapping("/new-contato")
    public ResponseEntity<ContatoDTO> create(@RequestBody ContatoDTO dto) {
        Contato contato = new Contato();
        contato.copy(dto);

        return new ResponseEntity<>(new ContatoDTO(agendaRepository.save(contato)), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ContatoDTO> update(@RequestBody ContatoDTO dto) {
        Contato contato = agendaRepository.findOne(Integer.valueOf(dto.getId()));
        contato.copy(dto);

        return new ResponseEntity<>(new ContatoDTO(agendaRepository.save(contato)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ContatoDTO get(@PathVariable String id) {
        Contato contato = agendaRepository.findOne(Integer.valueOf(id));

        return new ContatoDTO(contato);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id) {
        Contato contato = agendaRepository.findOne(Integer.valueOf(id));
        agendaRepository.delete(contato);
    }

    @GetMapping("/autocomplete/{suggest}")
    public List<ContatoDTO> autoComplete(@PathVariable String suggest) {
        return toDtoList(
           agendaRepository.findByContatoContainingIgnoreCase(suggest)
        );
    }

    @GetMapping("/all")
    public List<ContatoDTO> getAll() {
        return toDtoList(agendaRepository.findAll());
    }

    private List<ContatoDTO> toDtoList(List<Contato>dados) {
        List<ContatoDTO>list = new ArrayList<>();

        for (Contato contato: dados) {
            list.add(new ContatoDTO(contato));
        }
        return list;
    }

}

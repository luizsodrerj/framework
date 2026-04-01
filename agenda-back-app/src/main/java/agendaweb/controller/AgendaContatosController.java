package agendaweb.controller;

import agendaweb.entity.Contato;
import agendaweb.entity.ContatoDTO;
import agendaweb.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @CrossOrigin
    @PostMapping("/new-contato")
    public ResponseEntity<ContatoDTO> create(@RequestBody ContatoDTO dto) {
        Contato contato = new Contato();
        contato.copy(dto);

        return new ResponseEntity<>(new ContatoDTO(agendaRepository.save(contato)), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<ContatoDTO> update(@RequestBody ContatoDTO dto) {
        Contato contato = agendaRepository.findOne(Integer.valueOf(dto.getId()));
        contato.copy(dto);

        return new ResponseEntity<>(new ContatoDTO(agendaRepository.save(contato)), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ContatoDTO get(@PathVariable String id) {
        Contato contato = agendaRepository.findOne(Integer.valueOf(id));

        return new ContatoDTO(contato);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id) {
        Contato contato = agendaRepository.findOne(Integer.valueOf(id));
        agendaRepository.delete(contato);
    }

    @CrossOrigin
    @GetMapping("/autocomplete/{suggest}")
    public List<ContatoDTO> autoComplete(@PathVariable String suggest) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "contato").ignoreCase();
        Sort sort = new Sort(order);

        return  suggest != null && !suggest.trim().equals("") ?
                toDtoList(agendaRepository.findByContatoContainingIgnoreCase(suggest,sort)) :
                getAll();
    }

    @GetMapping("/all")
    public List<ContatoDTO> getAll() {
        return toDtoList(agendaRepository.findAllByOrderByContato());
    }

    private List<ContatoDTO> toDtoList(List<Contato>dados) {
        List<ContatoDTO>list = new ArrayList<>();

        for (Contato contato: dados) {
            list.add(new ContatoDTO(contato));
        }
        return list;
    }

}

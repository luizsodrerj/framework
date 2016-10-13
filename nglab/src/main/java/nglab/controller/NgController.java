package nglab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nglab.entity.Contato;

@RestController
public class NgController {

	@Autowired
	private NgService ngService;
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public List<Contato> getAllContatos() {
		return ngService.findAllContatos();
	}
}

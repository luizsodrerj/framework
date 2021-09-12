package bijus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bijus.entity.Bijuteria;
import bijus.entity.Joia;
import bijus.service.BijusService;

@ManagedBean
@ViewScoped
public class BijusCarouselController {

	private List<Bijuteria>bijus = new ArrayList<Bijuteria>();
	private List<Joia>joias = new ArrayList<Joia>();

	
	public BijusCarouselController() {
		bijus.addAll(
			BijusService.getBijus()	
		);
		joias.addAll(
			BijusService.getJoias()	
		);
	}

	public List<Bijuteria> getBijus() {
		return bijus;
	}
	
	public List<Joia> getJoias() {
		return joias;
	}
}

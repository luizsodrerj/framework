package bijus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import bijus.entity.Bijuteria;
import bijus.entity.Joia;
import bijus.entity.Peca;
import bijus.service.BijusService;
import util.FacesUtil;

@ManagedBean
@ViewScoped
public class BijusCarouselController {

	private List<Bijuteria>bijus = new ArrayList<Bijuteria>();
	private List<Peca>semiJoias  = new ArrayList<Peca>();
	private List<Joia>joias 	 = new ArrayList<Joia>();

	private BijusService bijusService = new BijusService();
	
	
	
	public BijusCarouselController() {
		bijus.addAll(
			bijusService.getBijus()	
		);
		joias.addAll(
			bijusService.getJoias()	
		);
		semiJoias.addAll(
			bijusService.getSemiJoias()	
		);
		populateImageList();
	}

	private void populateImageList() {
		List<Peca>list = new ArrayList<Peca>();
		list.addAll(semiJoias);
		list.addAll(joias);
		list.addAll(bijus);

		HttpSession session = FacesUtil.getSession();
		session.setAttribute("imageList",list);
	}
	
	public List<Bijuteria> getBijus() {
		return bijus;
	}
	
	public List<Joia> getJoias() {
		return joias;
	}
	
	public List<Peca> getSemiJoias() {
		return semiJoias;
	}
}

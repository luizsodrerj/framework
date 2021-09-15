package bijus.service;

import java.util.List;

import bijus.entity.Bijuteria;
import bijus.entity.Joia;
import bijus.entity.Peca;
import framework.persistence.jpa.PersistenceServiceUtil;

public class BijusService {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	
	private List<Bijuteria>bijus;
	private List<Joia>joias;

	

	public void persistPeca(Peca peca) {
		try {
			persistence.beginTransaction();
			persistence.persist(peca);
			persistence.commit();
		} finally {
			persistence.close();
		}
	}
	
	 
	public List<Peca> getPecas() {
		try {
			return persistence.findAll(Peca.class, null);
		} finally {
			persistence.close();
		}
	}
	
	public List<Joia> getJoias() {
		if (joias.isEmpty()) {
			loadJoias();
		}
		return joias;
	}

	public List<Bijuteria> getBijus() {
		if (bijus.isEmpty()) {
			loadBijus();
		}
		return bijus;
	}
	
	private void loadBijus() {
		String[] images = new String[] {
							"18.33.11.jpeg",
							"18.33.12.jpeg",
							"18.33.1211.jpeg",
							"18.33.122.jpeg",
							"18.33.123.jpeg",
							"11.46.24.jpeg"
						  };
		for (String image : images) {
			Bijuteria biju = new Bijuteria();
			biju.setDescricao("Pulseira");
			//biju.setImagem(image);
			biju.setPreco(10D);
			bijus.add(biju);
		}
	}

	private void loadJoias() {
		String[] images = new String[] {
							"11.44.37.jpeg",
							"11.44.38.jpeg",
							"11.44.39.jpeg",
							"11.44.40.jpeg",
							"11.44.401.jpeg",
							"11.44.402.jpeg",
						  };
		for (String image : images) {
			Joia joia = new Joia();
			joia.setDescricao("Colar");
			//joia.setImagem(image);
			joia.setPreco(25D);
			joias.add(joia);
		}
	}
	
}







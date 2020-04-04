package timediff;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ParamsCalcDiff {

	private String dateStart;
	private String dateStop;

	
	public ParamsCalcDiff() {
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateStop() {
		return dateStop;
	}

	public void setDateStop(String dateStop) {
		this.dateStop = dateStop;
	}

}

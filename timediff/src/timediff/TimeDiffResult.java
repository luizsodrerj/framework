package timediff;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TimeDiffResult {

	private String hours;
	private String minutes;
	
	
	public TimeDiffResult() {
	}


	public String getHours() {
		return hours;
	}


	public void setHours(String hours) {
		this.hours = hours;
	}


	public String getMinutes() {
		return minutes;
	}


	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

}

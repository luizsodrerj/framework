/*
 * Created on 17/10/2005
 */
package framework.config;

import java.util.ArrayList;

/**
 * @author Luiz Alberto
 */
public class ApplicationConfig {
	private ArrayList applicationParameters = new ArrayList();
	
	/**
	 * Returna o applicationParameters
	 * @return o applicationParameters.
	 */
	public ArrayList getApplicationParameters() {
		return applicationParameters;
	}
	
	/**
	 * Define applicationParameters 
	 * @param applicationParameters o applicationParameters a definir
	 */
	public void setApplicationParameters(ArrayList applicationParameters) {
		this.applicationParameters = applicationParameters;
	}
	
}

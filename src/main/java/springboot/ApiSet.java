package springboot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApiSet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long API_INDEX;
	
	private String API_INFO;
	
	private String API_NAME;
	
	private String DOMAIN;

	public long getAPI_INDEX() {
		return API_INDEX;
	}

	public void setAPI_INDEX(long aPI_INDEX) {
		API_INDEX = aPI_INDEX;
	}

	public String getAPI_INFO() {
		return API_INFO;
	}

	public void setAPI_INFO(String aPI_INFO) {
		API_INFO = aPI_INFO;
	}

	public String getAPI_NAME() {
		return API_NAME;
	}

	public void setAPI_NAME(String aPI_NAME) {
		API_NAME = aPI_NAME;
	}

	public String getDOMAIN() {
		return DOMAIN;
	}

	public void setDOMAIN(String dOMAIN) {
		DOMAIN = dOMAIN;
	}
	

}

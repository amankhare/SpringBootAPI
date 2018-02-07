package springboot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DataSet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	//@NotBlank
	private String Type;
	/*@NotBlank*/
	private boolean Valid;
	//@NotBlank
	private String Value;
	//@NotBlank
	private String Domain;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public boolean isValid() {
		return Valid;
	}
	public void setValid(boolean valid) {
		Valid = valid;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getDomain() {
		return Domain;
	}
	public void setDomain(String domain) {
		Domain = domain;
	}



}
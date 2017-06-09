package byaj;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Textings {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private long id;
	
	@NotNull
	private String fromNumber;
	
	@NotNull
	private String content;
	
	public long getId(){
		return id;
	}
	public void setId(long val){
		id = val;
	}
	public String getFrom(){
		return fromNumber;
	}
	public void setFrom(String val){
		fromNumber = val;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String val){
		content = val;
	}
}

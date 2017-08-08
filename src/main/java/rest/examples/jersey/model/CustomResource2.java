package rest.examples.jersey.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomResource2 {
	@XmlElement
	CustomResource1 res2;
	@XmlElement
	String item;
	@XmlElement
	Integer g;
	public CustomResource2(){
		
	}
	public CustomResource2(String res1, String item, Integer g){
		res2= new CustomResource1(res1);
		this.item=item;
		this.g=g;
	}
}

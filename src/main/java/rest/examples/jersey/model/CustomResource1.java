package rest.examples.jersey.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomResource1 {
	@XmlElement
	String res;
	public CustomResource1() {	}
	public CustomResource1( String res) {
		res=this.res;
	}
	@Override
	public String toString(){
		return "Custom : "+res;
	}
}

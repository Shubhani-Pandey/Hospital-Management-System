package tables.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Test_Prices {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String tId;
	private String tName;
	private double price;
	
	
	public Test_Prices(String tName, double price) {
		super();
		this.tName = tName;
		this.price = price;
	}
	
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
	@Override
	public String toString() {
		return "Test_Prices [tId=" + tId + ", tName=" + tName + ", price=" + price + "]";
	}
	
	
}

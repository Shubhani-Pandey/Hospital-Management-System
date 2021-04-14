package tables.model;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@NamedQueries(  
	    		{  @NamedQuery(name="Admin.login", query="from Admin A where A.aName=? and A.aPwd=?" ),
	               @NamedQuery(name="Admin.all", query="from Admin"),
	    		   @NamedQuery(name="Admin.delete", query="delete from Admin A where A.aId=?"),
	    		})  
@Table
public class Admin {
	
	/*___________________________Schema for Admin table___________________________
	 * aId                             ---->  primary key,auto-generated,should start with A
	 * aPwd                            ---->  6<length<10  and contain one upper case, one small case, one numeral and one special case charater
	 * aName                           ---->  NOT NULL
	 */
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String aId;
	
	@NotNull @NotEmpty
	private String aName;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{6,10}$")
	@NotNull @NotEmpty
	private String aPwd;
	private boolean hasEditAccess=false;
	
	public static Admin create_admin(Admin a,List<Object> obj) {
		a.setaName((String) obj.get(0));
		a.setaPwd((String)obj.get(1));
		a.setHasEditAccess(false);
		return a;
	}
	
	public static List<Object> admin_info() {
		System.out.println("Enter admin name:");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("Enter admin password");
		String pwd = sc.nextLine();
		return Arrays.asList(name,pwd);
	}
	
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public boolean isHasEditAccess() {
		return hasEditAccess;
	}
	public void setHasEditAccess(boolean hasEditAccess) {
		this.hasEditAccess = hasEditAccess;
	}
	public String getaPwd() {
		return aPwd;
	}
	public void setaPwd(String aPwd) {
		this.aPwd = aPwd;
	}
	
	@Override
	public String toString() {
		return "Admin " + aId + "\t" + aName+"\t\t" + hasEditAccess;
	}
	
	
	

}

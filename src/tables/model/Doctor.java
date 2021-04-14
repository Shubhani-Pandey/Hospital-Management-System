package tables.model;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NamedQueries(  
		{  @NamedQuery(name="Doctor.login", query="from Doctor D where D.dName=? and D.dPassword=?" ),
           @NamedQuery(name="Doctor.onStatus", query="from Doctor D where D.status=?"),
           @NamedQuery(name="Doctor.approveUpdate", query="update Doctor D set D.status=? where D.dId=?"),
           @NamedQuery(name="Doctor.all", query="from Doctor"),
           @NamedQuery(name="Doctor.getDoc", query="from Doctor D where D.dId=?"),
           @NamedQuery(name="Doctor.remove", query="delete from Doctor D where D.dId=?"),
		   @NamedQuery(name="Doctor.field", query="select D.dSpecialist from Doctor D"),
		   @NamedQuery(name="Doctor.accToSpcl", query="from Doctor D where D.dSpecialist=?"),
		   @NamedQuery(name="Doctor.getDocId", query="select D.dId from Doctor D where D.dName=? and D.dPassword=?"),
		   @NamedQuery(name="Doctor.getDocfield", query="select D.dSpecialist from Doctor D where D.dName=? and D.dPassword=?"),
		   //@NamedQuery(name="Doctor.otherDocs", query="from Doctor D where D.status =:stat, D.dId =:id")
		}) 

@Check(constraints = "dGender IN ('F' ,'M')")
@Table
public class Doctor {
	
	/*___________________________Schema for doctor table___________________________
	 * dId                              ---->  primary key,auto-generated,should start with D
	 * dPassword                        ---->  6<length<10  and contain one upper case, one small case, one numeral and one special case charater
	 * dName,dSpecialist,dQual,dGender  ---->  NOT NULL
	 * dAge                             ---->  int
	 * prev_employer                    ---->  Optional(Null by default)
	 * status                           ---->  FALSE by default
	 */
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String dId;
	
	@Column(nullable = false) 
	private String dName, dSpecialist, dQual,dGender;
	
	private int dAge;
	
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{6,10}$")
	private String dPassword;
	
	private String prev_employer=null;
	private boolean status=false;
	
	public static List<Object> doc_login_info() {
		System.out.println("Enter doctor name:");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("Enter doctor password");
		String pwd = sc.nextLine();
		return Arrays.asList(name,pwd);
	}
	
	public static List<Object> new_doc_info() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the following details");
		System.out.println("Name->");
		String name = sc.nextLine();
		System.out.println("Age->");
		int age = sc.nextInt();
		System.out.println("Gender(F|M)->");
		sc = new Scanner(System.in);
		String gender = sc.nextLine();
		System.out.println("Specialist->");
		String splst = sc.nextLine();
		System.out.println("Qualification->");
		String qual = sc.nextLine();
		System.out.println("Previous_Employer->");
		String prevEmp = sc.nextLine();
		System.out.println("Set a passowrd->");
		String pwd = sc.nextLine();
		return Arrays.asList(name,age,gender,splst,qual,prevEmp,pwd);
	}
	
	public static Doctor create_doc(Doctor d,List<Object> obj) {
		d.setdName((String)obj.get(0));
		d.setdAge((int) obj.get(1));
		d.setdGender((String) obj.get(2));
		d.setdSpecialist((String) obj.get(3));
		d.setdQual((String) obj.get(4));
		d.setPrev_employer((String) obj.get(5));
		d.setdPassword((String) obj.get(6));
		return d;
	}
	
	public String getdId() {
		return dId;
	}
	public int getdAge() {
		return dAge;
	}
	public void setdAge(int dAge) {
		this.dAge = dAge;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdSpecialist() {
		return dSpecialist;
	}
	public void setdSpecialist(String dSpecialist) {
		this.dSpecialist = dSpecialist;
	}
	public String getdQual() {
		return dQual;
	}
	public void setdQual(String dQual) {
		this.dQual = dQual;
	}
	public String getdGender() {
		return dGender;
	}
	public void setdGender(String dGender) {
		this.dGender = dGender;
	}
	public String getPrev_employer() {
		return prev_employer;
	}
	public void setPrev_employer(String prev_employer) {
		this.prev_employer = prev_employer;
	}
	public String getdPassword() {
		return dPassword;
	}
	public void setdPassword(String dPassword) {
		this.dPassword = dPassword;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Doctor  "  + dId +"\t"+ dName + "\t"+dAge +"\t" +dGender + "\t" + dSpecialist;
	}
}

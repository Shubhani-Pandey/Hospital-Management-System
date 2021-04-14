package tables.model;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NamedQueries(  
		{  @NamedQuery(name="Patient.login", query="from Patient P where P.pName=? and P.pPwd=?" ),
           @NamedQuery(name="Patient.approved", query="from Patient P where P.approved=?"),
           @NamedQuery(name="Patient.reroute", query="update Patient P set P.re_routed=?, P.dId=? where  P.pId=?"),
           @NamedQuery(name="Patient.approveUpdate", query="update Patient P set P.approved=? where P.pId=? "),
           @NamedQuery(name="Patient.all", query="from Patient"),
           @NamedQuery(name="Patient.remove", query="delete from Patient P where P.pId=?"),
		   @NamedQuery(name="Patient.ofdoc", query="from Patient P where P.dId=? and P.approved=?"),
		   @NamedQuery(name="Patient.assignDoc", query="update Patient P set P.dId=?, P.approved=? where P.pId=?"),
		   @NamedQuery(name="Patient.checkapproved", query="select P.approved from Patient P where P.pName=? and P.pPwd=?"),
		   @NamedQuery(name="Patient.getId", query="select P.pId from Patient P where P.pName=? and P.pPwd=?"),
		   @NamedQuery(name="Patient.returnDocId", query="select P.dId from Patient P where P.pName=? and P.pPwd=?")
		})
@Check(constraints = "pGender IN ('F' ,'M')")
@Table
public class Patient {
	
	/*___________________________Schema for patient table___________________________
	 * dId                             ---->  foreign key
	 * pId                             ---->  primary key,auto-generated,should start with P
	 * pPwd                            ---->  6<length<10  and contain one upper case, one small case, one numeral and one special case charater
	 * pName,pDisease,pGender          ---->  NOT NULL
	 * hasEditAccess,approved,re_routed---->  FALSE by default*/
	
	@ForeignKey(name = "dId")
	private String dId=null;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String pId;
	
	@Column(nullable = false) 
	private String pName, pDisease, pGender;
	
	@Column(nullable = false)
	private int age;
    
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{6,10}$")
	private String pPwd;
	boolean approved=false,re_routed=false;
	
	public static List<Object> patient_login_info() {
		System.out.println("Patient name:");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("Patient password");
		String pwd = sc.nextLine();
		return Arrays.asList(name,pwd);
	}
	
	public static List<Object> new_patient_info() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the following details");
		System.out.println("Name->");
		String name = sc.nextLine();
		System.out.println("Age->");
		int age = sc.nextInt();
		System.out.println("Gender(F|M)->");
		sc = new Scanner(System.in);
		String gender = sc.nextLine();
		System.out.println("Disease->");
		String disease = sc.nextLine();
		System.out.println("Set a passowrd->");
		String pwd = sc.nextLine();
		return Arrays.asList(name,age,gender,disease,pwd);
	}
	
	public static Patient create_patient(Patient p,List<Object> obj) {
		p.setpName((String)obj.get(0));
		p.setAge((int) obj.get(1));
		p.setGender((String) obj.get(2));
		p.setpDisease((String) obj.get(3));
		p.setpPwd((String) obj.get(4));
		return p;
	}
	
	public static String all_patients() {
		String hql = "FROM Patient as p";
		return hql;
	}
	
	/*Getters and Setters*/
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDisease() {
		return pDisease;
	}
	public void setpDisease(String pDisease) {
		this.pDisease = pDisease;
	}
	public String getGender() {
		return pGender;
	}
	public void setGender(String gender) {
		this.pGender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getpGender() {
		return pGender;
	}
	public void setpGender(String pGender) {
		this.pGender = pGender;
	}
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getpPwd() {
		return pPwd;
	}
	public void setpPwd(String pPwd) {
		this.pPwd = pPwd;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isRe_routed() {
		return re_routed;
	}
	public void setRe_routed(boolean re_routed) {
		this.re_routed = re_routed;
	}

	@Override
	public String toString() {
		return "Patient  " + pId + "\t" + pName + "\t" + pDisease + "\t" + pGender + "\t"+ age;
	}
	
	
	

}

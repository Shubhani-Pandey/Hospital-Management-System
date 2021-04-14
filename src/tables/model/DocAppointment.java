package tables.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Future;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;


@Entity

@NamedQueries(  
		{  @NamedQuery(name="DocAppointment.doctorReserved", query="select A.appoint_date from DocAppointment A where A.dId=?" )
		})

@Table
public class DocAppointment {
	
	/*___________________________Schema for DocAppointment table___________________________
	 * dId,pId                         ---->  foreign keys
	 * apoint_date                     ---->  Date of appointment, should be in future
	 * slot                            ---->  1 appointment for each doctor(False by default indicating free slots) */
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String appointId;
	
	@ForeignKey(name = "dId")
	private String dId=null;
	
	@ForeignKey(name = "dId")
	private String pId=null;
	
	@Future
	private Date appoint_date;
	private boolean slot=false;
	
	public static Date new_appointment_info() {
		Scanner sc= new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("________PLEASE ENTER A DATE FOR APPOINTMENT(YYYY-MM-DD) ____________");
		String date = sc.nextLine();
	    Date aDate = null;
		try {
			aDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return aDate;
	}
	
	public String getAppointId() {
		return appointId;
	}
	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}
	public String getadId() {
		return dId;
	}
	public void setadId(String dId) {
		this.dId = dId;
	}
	public String getapId() {
		return pId;
	}
	public void setapId(String pId) {
		this.pId = pId;
	}
	public Date getAppoint_date() {
		return appoint_date;
	}
	public void setAppoint_date(Date appoint_date) {
		this.appoint_date = appoint_date;
	}
	public boolean isSlot() {
		return slot;
	}
	public void setSlot(boolean slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "DocAppointment [appointId=" + appointId + ", dId=" + dId + ", appoint_date=" + appoint_date + "]";
	}
	
	
	
	

}

package mainClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tables.model.Admin;
import tables.model.DocAppointment;
import tables.model.Doctor;
import tables.model.Patient;

public class driver {

	static Scanner sc= new Scanner(System.in);
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) throws BeansException, HibernateException, SQLException, NumberFormatException, IOException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
        Transaction t = ss.beginTransaction();
        
        Query ad_query=ss.getNamedQuery("Admin.all");
		List<Admin> ad_li = (List<Admin>) ad_query.list();
		t.commit();
		if(ad_li.size()==0)
		{
			//_________Default MASTER Admin__________
			Transaction t2 = ss.beginTransaction();
			Admin a1 = (Admin)context.getBean("admin");
			a1.setaName("Shubhani");
			a1.setaPwd("Shubhi123#");
			a1.setHasEditAccess(true);
			ss.save(a1);
			t2.commit();
		}

		
		
		//________________________SCREEN1________________________________
		
		boolean screen1=true;
		while(screen1)
		{
		    System.out.println("\n\n\n");
			System.out.println(".................WELCOME TO E-HOSPITAL MANAGEMENT SYSTEM............................");
			System.out.println("What do you want to login as?");
			System.out.println("1. ADMIN");
			System.out.println("2. DOCTOR");
			System.out.println("3. PATIENT");
			System.out.println("4. EXIT");
			
			System.out.println("Enter your choice(1-4)->");
			sc=new Scanner(System.in);
			int choice = sc.nextInt();
			
			
			switch(choice) {
	
			case 1: boolean admin_login=true;
				    while(admin_login)
				    {
				    	System.out.println("\n\n\n");
						System.out.println(".................ADMIN LOGIN............................\n");
						Transaction t2 = ss.beginTransaction();
						List<Object> params = Admin.admin_info();
						
						Query query2=ss.getNamedQuery("Admin.login");
						query2.setString(0, (String) params.get(0));
						query2.setString(1, (String) params.get(1));
						t2.commit();
			
						List<Admin> rs = (List<Admin>) query2.list();
						
						if(rs.isEmpty())
						{      
							   System.out.println("\n\n\n");
							   System.out.println("LOGIN UNSUCCESSFUL!!");
							   System.out.println("1. Try again to Login");
							   System.out.println("2. Go back to main menu");
							   System.out.println("Enter your choice(1-2)-->");
							   //int choose =Integer.parseInt(r.readLine());
							   int choose = sc.nextInt();
							   if(choose==1)
								   continue;
							   else
							       admin_login=false;
						} 
						else 
						{   
								System.out.println("\n\n\n");
							    System.out.println("_______LOGIN SUCCESSFUL______");
							    
							    boolean screen2=true;
								while(screen2) 
								{
									System.out.println("\n\n\n");
								    System.out.println("Select the agenda->");
								    System.out.println("1. ADD NEW ADMIN");
								    System.out.println("2. REMOVE ADMIN");
								    System.out.println("3. APPROVE DOCTORS");
								    System.out.println("4. VIEW DOCTORS ");
								    System.out.println("5. REMOVE DOCTOR");
								    System.out.println("6. VIEW ALL PATIENTS");
								    System.out.println("7. LOGOUT");
								    System.out.println("Enter the choice(1-7)->");
								   
								    int ch = sc.nextInt();
								    
								    switch(ch)
								    {
										    case 1: Transaction t3 = ss.beginTransaction();
										    		Admin a2 = (Admin)context.getBean("admin");
										    		List<Object> l = Admin.admin_info();
										    		Admin obj = Admin.create_admin(a2, l);
										    		ss.save(obj);
										    		t3.commit();
										    		System.out.println("_____New admin created successfully______");
										    		
										    		System.out.println("\n\n\n");
										    		System.out.println("_________ALL CURRENT ADMINS_________");
										    		System.out.println("\n");
										    		
										    		Transaction t4 = ss.beginTransaction();
										    		Query query3=ss.getNamedQuery("Admin.all");
										    		List<Admin> rs3 = (List<Admin>) query3.list();
										    		t4.commit();
										    		Iterator<Admin> it = rs3.iterator();
										    		
										    		System.out.println("\t\t AdminId"+"\t\t\t"+"AdminName"+"\t"+"Master_Admin");
										    		
										    		while(it.hasNext())  
										    			System.out.println(it.next());
										   
										    		break;
										    		
										    case 2: System.out.println("\n\n\n");
										    		System.out.println("_________ALL CURRENT ADMINS_________");
										    		System.out.println("\n");
										    		
										    		Transaction trans1 = ss.beginTransaction();
										    		Query query=ss.getNamedQuery("Admin.all");
										    		List<Admin> res = (List<Admin>) query.list();
										    		trans1.commit();
										    		Iterator<Admin> e = res.iterator();
										    		
										    		System.out.println("\t\t AdminId"+"\t\t\t"+"AdminName"+"\t"+"Master_Admin");
										    		
										    		while(e.hasNext())  
										    			System.out.println(e.next());
										    		
										    		sc= new Scanner(System.in);
										    		System.out.println("Enter the id of the admin you want to delete-->");
										    		String del_id = sc.nextLine();
										    		Transaction trans = ss.beginTransaction();
										    		Query que=ss.getNamedQuery("Admin.delete");
										    		que.setParameter(0, del_id);
										    		que.executeUpdate();
										    		trans.commit();
										    		
										    		System.out.println("________________Admin deleted successfully__________________");
										    		break;
										    		
										    case 3: boolean doc_approve=true;
										            while(doc_approve)
										            {
												    	    Transaction t5 = ss.beginTransaction();
												    		System.out.println("\n\n");
											    			System.out.println("_______UNAPPROVED DOCTORS WAITING FOR APPROVAL_________");
												    		Query query4=ss.getNamedQuery("Doctor.onStatus");
												    		query4.setParameter(0, false);
												    		List<Admin> rs4 = (List<Admin>) query4.list();
												    		t5.commit();
												    		if(rs4.isEmpty()) 
												    		{
												    			System.out.println("No unapproved doctors");
												    			break;
												    		}
												    		else 
												    		{
													    		Iterator<Admin> it1 = rs4.iterator();
													    		System.out.println("\t\t DoctorId"+"\t\t\t"+"DoctorName"+"\t"+"Age  Gender"+"Specialization");
													    		while(it1.hasNext())
													    			System.out.println(it1.next());
												    		}
												    		
												            System.out.println("\n\n\n");
												    		System.out.println("Enter the id of the doctor for approval->");
												    		sc = new Scanner(System.in);
												    		String id = sc.nextLine();
												    		Transaction t6 = ss.beginTransaction();
												    		Query query5=ss.getNamedQuery("Doctor.approveUpdate");
												    		query5.setParameter(0, true);
												    		query5.setParameter(1, id);
		                                                    query5.executeUpdate();
												    		t6.commit();
												    		System.out.println("Doctor status approved");
												    	    if(rs4.size()>1) 
												    	    {
												    	    	System.out.println("Want to approve more doctors(y/n)");
												    	    	String in = sc.nextLine();
												    	    	if(in.equalsIgnoreCase("y"))
												    	    		break;
												    	    	else
												    	    		continue;
												    	    }		
															
												    }
										            break;
										    		
										    case 4: Transaction t6 = ss.beginTransaction();
										    		System.out.println("______________________APPROVED DOCTORS__________________________");
										    		Query q=ss.getNamedQuery("Doctor.onStatus");
										    		q.setParameter(0, true);
										    		List<Admin> rs6 = (List<Admin>) q.list();
										    		t6.commit();
										    		if(rs6.isEmpty())
										    			System.out.println("No Approved Doctors");
										    		else 
										    		{
										    			Iterator<Admin> it4 = rs6.iterator();
											    		System.out.println("\t\t DoctorId"+"\t\t\t"+"DoctorName"+"\t"+"Age  Gender"+"Specialization");
											    		while(it4.hasNext())
											    			System.out.println(it4.next());
										    		}
										    		
										    		Transaction t7 = ss.beginTransaction();
										    		System.out.println("\n\n");
									    			System.out.println("________________________UNAPPROVED DOCTORS________________________");
										    		Query q2=ss.getNamedQuery("Doctor.onStatus");
										    		q2.setParameter(0, false);
										    		List<Admin> rs7 = (List<Admin>) q2.list();
										    		t7.commit();
										    		if(rs7.isEmpty())
										    			System.out.println("No unapproved doctors");
										    		else
										    		{
											    		Iterator<Admin> it5 = rs7.iterator();
											    		System.out.println("\t\t DoctorId"+"\t\t\t"+"DoctorName"+"\t"+"Age  Gender"+"Specialization");
											    		while(it5.hasNext())  
											    			System.out.println(it5.next());
										    		}
										    		break;
										    		
										    case 5: Transaction t8 = ss.beginTransaction();	
										            System.out.println("________ALL DOCTORS DATA________");
												    Query q3=ss.getNamedQuery("Doctor.onStatus");
										    		q3.setParameter(0, true);
										    		List<Admin> rs8 = (List<Admin>) q3.list();
										    		t8.commit();
										    		if(rs8.isEmpty())
										    			System.out.println("No doctor to remove");
										    		else
										    		{
										    			Iterator<Admin> it6 = rs8.iterator();
											    		System.out.println("\t\t DoctorId"+"\t\t\t"+"DoctorName"+"\t"+"Age  Gender"+"Specialization");
											    		while(it6.hasNext()) 
											    			System.out.println(it6.next());
											    		
											    		Transaction t9 = ss.beginTransaction();
											    		System.out.println("Enter the id of the doctor to be deleted->");
											    	    String doc_id = sc.nextLine();
											    	    Query q4=ss.getNamedQuery("Doctor.remove");
											    		q4.setParameter(0, doc_id);
											    		q4.executeUpdate();
											    		t9.commit();
											    		System.out.println("Doctor deleted successfully");
										    		}
										    		break;
										    	
										    case 6: Transaction t10 = ss.beginTransaction();
											    	System.out.println("__________ALL PATIENTS__________");
											    	Query q5=ss.getNamedQuery("Patient.all");
											    	List<Admin> rs9 = (List<Admin>) q5.list();
										    		t10.commit();
										    		if(rs9.isEmpty())
										    			System.out.println("No patient to display");
										    		else 
										    		{
										    			Iterator<Admin> it7 = rs9.iterator();
											    		System.out.println("\t\t PatientId"+"\t\t\t"+"PatientName"+"\t"+"Disease"+"\t"+"Age  Gender");
											    		while(it7.hasNext())
											    			System.out.println(it7.next());
										    		}
										    		break;
										    		
										    case 7: screen2=false;
										            admin_login=false;
										    		break;
							          }//switch of 5 cases end here
							       }//end of else
							   }//end of case 1 switch
					    } //end of while of admin_login
					    		
				        break;
				       	
			case 2: boolean screen3=true;
			        while(screen3)
			        {
			        	   System.out.println("\n\n\n");
						   System.out.println("__________DOCTOR PORTAL______________");
						   System.out.println("1. New Doctor");
						   System.out.println("2. Already Registered doctor");
						   System.out.println("3. Exit");
						   System.out.println("Enter your choice(1-3)-->");
						   int choose = sc.nextInt();
						   
						   switch(choose)
						   {
						     case 1:System.out.println("\n\n");
						            System.out.println("______WELCOME TO THE HOSPITAL_______");
						     		Transaction d1 = ss.beginTransaction();
						            List<Object> l = Doctor.new_doc_info();
						            Doctor d = (Doctor)context.getBean("doc");
						            d = Doctor.create_doc(d, l);
						            ss.save(d);
						            d1.commit();
						            System.out.println("___________Application sent for approval____________");     
						    	    break;
						    	    
						     case 2: boolean doctor_login=true;
						             while(doctor_login)
						             {
						            	System.out.println("____________DOCTOR LOGIN____________"); 
						            	Transaction d2 = ss.beginTransaction();
						            	List<Object> l2  = Doctor.doc_login_info();
						            	Query qd1=ss.getNamedQuery("Doctor.login");
										qd1.setParameter(0, l2.get(0));
										qd1.setParameter(1, l2.get(1));
										d2.commit();
							
										List<Doctor> rs = (List<Doctor>) qd1.list();
										
										if(rs.isEmpty())
										{      
										   System.out.println("\n\n\n");
										   System.out.println("LOGIN UNSUCCESSFUL!!");
										   System.out.println("1. Try again to Login");
										   System.out.println("2. Go back to doctor portal");
										   System.out.println("Enter your choice(1-2)-->");
										   //int choose =Integer.parseInt(r.readLine());
										   int dch1 = sc.nextInt();
										   if(dch1 ==1)
											   continue;
										   else
										       doctor_login=false;
										} 
										else 
										{   
											System.out.println("\n\n\n");
										    System.out.println("_______LOGIN SUCCESSFUL______");
										    
										    boolean screen4=true;
											while(screen4) 
											{
												System.out.println("\n\n\n");
											    System.out.println("Select the agenda->");
											    System.out.println("1. MY PATIENTS");
											    System.out.println("2. APPROVE/RE-ROUTE PATIENTS");
											    System.out.println("3. VIEW APPOINTMENTS");
											    System.out.println("4. EXIT ");
											    System.out.println("Enter the choice(1-4)->");
											   
											    int ch = sc.nextInt();
											    
											    Transaction d3 = ss.beginTransaction();
								            	Query qd2=ss.getNamedQuery("Doctor.getDocId");
												qd2.setParameter(0, l2.get(0));
												qd2.setParameter(1, l2.get(1));
												d3.commit();
												List rs1 =  qd2.list();
											    
											    switch(ch)
											    {   
											    case 1: Transaction d4 = ss.beginTransaction();
										            	Query qd3=ss.getNamedQuery("Patient.ofdoc");
														qd3.setParameter(0, rs1.get(0));
														qd3.setParameter(1, true);
														d4.commit();
									                      
														List rs2 =  qd3.list();
														System.out.println("MY PATIENTS");
														Iterator itd1 = rs2.iterator();
														while(itd1.hasNext())
															System.out.println(itd1.next());
											    	    break;
											    	    
											    case 2: System.out.println("1. APPROVE PATIENTS");
											            System.out.println("2. RE-ROUTE PATIENTS");
											            System.out.println("Choose one(1-2)-->");
											            sc = new Scanner(System.in);
											            int c = sc.nextInt();
											            
											            Transaction d7 = ss.beginTransaction();
										            	Query qd6=ss.getNamedQuery("Patient.ofdoc");
														qd6.setParameter(0, rs1.get(0));
														qd6.setParameter(1, false);
														d7.commit();
									                      
														List rs4 =  qd6.list();
														System.out.println("PATIENT REQUESTS");
														Iterator itd2 = rs4.iterator();
														while(itd2.hasNext())
															System.out.println(itd2.next());
														
														if(rs4.isEmpty())
														{
															System.out.println("No patient requests!!!!!!!!!!");
															break;
														}
														else
														{  
												            switch(c)
												            {
													            case 1: boolean approve_screen =true;
													                    while(approve_screen)
													                    {
														            	    System.out.println("Enter the id of the patient whom you want to approve-->");
																            sc = new Scanner(System.in);
																            String p_id = sc.nextLine();
																			
																			Transaction d6 = ss.beginTransaction();
															            	Query qd5=ss.getNamedQuery("Patient.approveUpdate");
																			qd5.setParameter(0,true);
																			qd5.setParameter(1, p_id);
																		    qd5.executeUpdate();
																			d6.commit();
																			
																			System.out.println("\n\n");
																			System.out.println("__________Patient Approved_________");
																			System.out.println("\n\n");
																			System.out.println("Want to approve more(y/n):");
																			sc = new Scanner(System.in);
																            String s = sc.nextLine();
																            if(s.equalsIgnoreCase("y"))
																            	continue;
																            else
																            	approve_screen=false;	
													                    }
															            break;
															            
													            case 2: boolean re_route_screen =true;
													                    while(re_route_screen)
													                    {
														            	    System.out.println("Enter the id of the patient whom you want to re-route-->");
																            sc = new Scanner(System.in);
																            String p_id = sc.nextLine();
																			
																            System.out.println("\n\n\n");
																            System.out.println("All available doctors to re-route to");
																            Transaction t8 = ss.beginTransaction();	
															
																            
//																            Criteria criteria = ss.createCriteria(Doctor.class);
//																            criteria.add(Restrictions.ne("id", rs1.get(0)));
//																            criteria.add(Restrictions.eq("stat",true));
//																            List<Doctor> rs8 = criteria.list();
																            
																		    Query q3=ss.getNamedQuery("Doctor.onStatus");
																    		q3.setParameter("status", true);
																    		List<Doctor> rs8 = (List<Doctor>) q3.list();
																    		t8.commit();
																    		if(rs8.isEmpty())
																    			System.out.println("No doctor to re-route to:!!!!");
																    		else
																    		{
																    			Iterator it6 = rs8.iterator();
																	    		System.out.println("\t\t DoctorId"+"\t\t\t"+"DoctorName"+"\t"+"Age  Gender"+"Specialization");
																	    		while(it6.hasNext()) 
																	    			System.out.println(it6.next());
																    		}
																            
																            System.out.println("\n\n\n");
																            System.out.println("Enter the id of the doctor whom you want to re-route to-->");
																            sc = new Scanner(System.in);
																            String d_id = sc.nextLine();
																            
																			Transaction d6 = ss.beginTransaction();
															            	Query qd5=ss.getNamedQuery("Patient.reroute");
																			qd5.setParameter(0,true);
																			qd5.setParameter(1, d_id);
																			qd5.setParameter(2, p_id);
																		    qd5.executeUpdate();
																			d6.commit();
																			
																			System.out.println("\n\n");
																			System.out.println("__________Patient Approved_________");
																			System.out.println("\n\n");
																			System.out.println("Want to approve more(y/n):");
																			sc = new Scanner(System.in);
																            String s = sc.nextLine();
																            if(s.equalsIgnoreCase("y"))
																            	continue;
																            else
																            	re_route_screen=false;	
													                    }
															            break;
												            }     
											            }
											    	    break;
											    	    
											    case 3: System.out.println("______YOUR APPOINTMENTS_________");
											            System.out.println("\n");
											            Transaction p9 = ss.beginTransaction();
										            	Query qp8=ss.getNamedQuery("DocAppointment.doctorReserved");
										            	qp8.setParameter(0, rs1.get(0));
														p9.commit();
													    
														List lp9 = qp8.list();
														
														System.out.println("\n");
														Iterator itp3 = lp9.iterator();
														if(itp3.hasNext())
															System.out.println(itp3.next());
														else
															System.out.println("(YOU DON'T HAVE ANY CURRENT APPOINTMENTS)");
											            
											    	    break;
											   
											    case 5: screen4=false;
											    		doctor_login=false;
											            break;
											    }
											}
										}
						             }
								     break;
								    	 
						     case 3: screen3 = false;
						    	     break;
						   }
				    }
	                break;
		            
			case 3: boolean patient_portal=true;
					while(patient_portal) 
					{  
					   System.out.println("\n\n\n");
					   System.out.println("__________PATIENT PORTAL______________");
					   System.out.println("1. New Patient");
					   System.out.println("2. Already Registered patient");
					   System.out.println("3. Exit");
					   System.out.println("Enter your choice(1-3)-->");
					   int choose = sc.nextInt();
					   
					   switch(choose)
					   {
					     case 1:System.out.println("______WELCOME TO THE HOSPITAL_______");
					     		Transaction p1 = ss.beginTransaction();
					            List<Object> l = Patient.new_patient_info();
					            Patient p = (Patient)context.getBean("patient");
					            p = Patient.create_patient(p, l);
					            
					            System.out.println("Please choose the specialization of doctor according to your interest:");
					            System.out.println("Available doctor have following specializations");
					            Transaction p2 = ss.beginTransaction();
					    	    Query qp1=ss.getNamedQuery("Doctor.field");
					    		List<Object> lp1 = qp1.list();
					    		p2.commit();
					    		
					    		if(lp1.isEmpty()) 
					    			System.out.println("______________No doctors right now..Please tune back after some time____________");
					    		else
					    		{
					    			Iterator<Object> itp1 = lp1.iterator();
					    			System.out.println("\n\n");
						    		System.out.println("Specializations");
						    		while(itp1.hasNext())  
						    			System.out.println(itp1.next());
					    		}
					    		
					    		boolean doc_field=true;
					    		while(doc_field)
					    		{
							    		System.out.println("\n\n");
							    		System.out.println("Enter the specialization to get all related doctors->");
							    		sc= new Scanner(System.in);
							    		String spcl = sc.nextLine();
							    		
							    		System.out.println("We have the following doctors according to the specialization..........");
							    		Transaction p3 = ss.beginTransaction();
							    	    Query qp2=ss.getNamedQuery("Doctor.accToSpcl");
							    	    qp2.setParameter(0, spcl);
							    		List<Object> lp3 = qp2.list();
							    		p3.commit();
							    		if(lp3.isEmpty()) 
							    		{
							    			System.out.println("No doctor according to specialization please choose according to above list");
							    			break;
							    		}
							    		else
							    		{
							    			Iterator<Object> itp2 = lp3.iterator();
								    		System.out.println("\t\t DoctorId"+"\t\t\t"+"DoctorName"+"\t"+"Age  Gender"+"Specialization");
								    		while(itp2.hasNext())
								    			System.out.println(itp2.next());
							    		}
							    		
							    		Transaction p4 = ss.beginTransaction();
							    		System.out.println("Select the doctor according to your interest and enter their id");
							    		String patId = sc.nextLine();
							    		p.setdId(patId);
							            ss.save(p);
							            p4.commit();
							            System.out.println("You are added successfully");
							            System.out.println("Please wait for doctors approval and login again to check further details");
							    	    break;
					    		}
					    		 break;
					    	    
					     case 2: boolean patient_login=true;
					             while(patient_login)
					             {
					            	System.out.println("\n\n");
					            	System.out.println("____________PATIENT LOGIN____________"); 
					            	Transaction p5 = ss.beginTransaction();
					            	List<Object> lp4  = Patient.patient_login_info();
					            	Query qp3=ss.getNamedQuery("Patient.login");
									qp3.setParameter(0, lp4.get(0));
									qp3.setParameter(1, lp4.get(1));
									p5.commit();
						
									List<Patient> lp5 = (List<Patient>) qp3.list();
									
									if(lp5.isEmpty())
									{      
									   System.out.println("\n\n\n");
									   System.out.println("LOGIN UNSUCCESSFUL!!");
									   System.out.println("1. Try again to Login");
									   System.out.println("2. Go back to patient portal");
									   System.out.println("Enter your choice(1-2)-->");
									
									   int pch1 = sc.nextInt();
									   if(pch1 ==1)
										   continue;
									   else
									       patient_login=false;
									} 
									else 
									{   
										System.out.println("\n\n\n");
									    System.out.println("_______LOGIN SUCCESSFUL______");
									    System.out.println("\n\n");
									    
									    Transaction p6 = ss.beginTransaction();
						            	Query qp4=ss.getNamedQuery("Patient.getId");
						            	qp4.setParameter(0, lp4.get(0));
										qp4.setParameter(1, lp4.get(1));
										p6.commit();
										List lp6 = qp3.list();
										
										Transaction p7 = ss.beginTransaction();
						            	Query qp5=ss.getNamedQuery("Patient.checkapproved");
						            	qp5.setParameter(0, lp4.get(0));
						            	qp5.setParameter(1, lp4.get(1));
										p7.commit();
									    
										List<Patient> lp7 = (List<Patient>) qp5.list();
										
										if(lp7.size()>0)
										{
											System.out.println("_________YOU HAVE BEEN ASSIGNED THE DOCTOR__________");
											System.out.println("\n\n");
											System.out.println("__________________DOCTOR DETAILS____________________");
											
											Transaction p8 = ss.beginTransaction();
							            	Query qp7=ss.getNamedQuery("Patient.returnDocId");
							            	qp7.setParameter(0, lp4.get(0));
							            	qp7.setParameter(1, lp4.get(1));
											p8.commit();
											List lp8 = qp7.list();
																					
											Transaction pt = ss.beginTransaction();
							            	Query qp=ss.getNamedQuery("Doctor.getDoc");
							            	qp.setParameter(0, lp8.get(0));
											pt.commit();
											List li = qp.list();
											
											Iterator<Object> itp2 = li.iterator();
								    		System.out.println("\t\t DoctorId"+"\t\t\t"+"DoctorName"+"\t"+"Age  Gender"+"Specialization");
								    		while(itp2.hasNext())
								    			System.out.println(itp2.next());
								    		
								    		System.out.println("\n\n");
								    		System.out.println("_________WANT TO BOOK AN APPOINTMENT(y/n)_________________");
								    		sc= new Scanner(System.in);
								    		String appoint = sc.nextLine();
								    		
								    		if(appoint.equalsIgnoreCase("y"))
								    		{
								    			System.out.println("\n\n");								    
								    			System.out.println("YOUR DOCTOR HAS NO APPOINTMENTS FOR THE FOLLOWING DAYS:");
								    			System.out.println("__BUSY DATES__");
								    			
								    			Transaction p9 = ss.beginTransaction();
								            	Query qp8=ss.getNamedQuery("DocAppointment.doctorReserved");
								            	qp8.setParameter(0, lp8.get(0));
												p9.commit();
											    
												List lp9 = qp8.list();
												
												Iterator itp3 = lp9.iterator();
												if(itp3.hasNext())
													System.out.println(itp3.next());
												else
													System.out.println("(Enter any date)");
												
								                Date d = DocAppointment.new_appointment_info();
								                Transaction p10 = ss.beginTransaction();
									    		DocAppointment a2 = (DocAppointment)context.getBean("appoint");
									    		a2.setAppoint_date(d);
									    		a2.setadId((String)lp8.get(0));
									    		a2.setSlot(true);
									    		ss.save(a2);
									    		p10.commit();
								                System.out.println("\n\n");
								    			System.out.println("___________YOUR APPOINTMENT HAS BEEN BOOKED_________");
								    			System.out.println("\n\n");
								    			System.out.println("Please arrive at the hospital at 3:00pm and follow the COVID guidelines");
								    			break;
								    		}
								    		else
								    		{
								    			System.out.println("_____REDIRECTING YOU BACK_____");
								    			break;
								    		}
											
										}
										else
										{
											System.out.println("_________YOU ARE YET TO BE ASSIGNED A DOCTOR__________");
											System.out.println("_________PLEASE LOGIN AGAIN AFTER SOME TIME___________");
											patient_portal=false;
											break;
										}
									}
					             }
							     break;
					     case 3: patient_portal=false;
					             break;
					   }
					}
					
				break;
				
			case 4: screen1=false;
			        break;
			}//main switch ends here
	     }//screen 1 ends here
		
		 System.out.println("____________GOOD BYE!!! LOGIN AGAIN SOON____________");
		 ss.close();
	     sf.close();
	}

}

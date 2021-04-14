/*
			 * Perform admin login---if_successful--->display next options
			 *                       if_!successful-->display two Options---> try again or go back to main menu
			 * 
			 * how to check successful---> ask username and Password .check in admin db if username and password exists
			 *                        --->if_ yes successful 
			 *                        
			 *                        //!!!!!!!!!!!!!!!!!!SCREEN2!!!!!!!!!!!!!!!!!!! 
			 *                        WHAT AFTER LOGIN:
			 * 											1 CAN ADD NEW ADMIN   ---->ask for username password and insert in admin db then go back to SCREEN2 
			 *                                          2 DOCTOR ADD REQUEST  ----> (DISPLAYS LIST OF ALL DOCTORS WHO HAVE SENT REQUESTS) --->ASK TO APPROVE EITHER ONE OR ALL BY ENTERING dId
			 *                      										  ----> the doctor whose id is mentioned set it's status to true----->go back to SCREEN2 
			 *                                          3) VIEW DOCTORS ----------> display all doctors whose status is true(approved)-->go back to SCREEN2
			 *                                          4 CAN VIEW ALL PATIENTS --> display names all patients along with the name of the doctors tending them.-->go back to SCREEN2
			 *    
			 *                                          5) LOGOUT         --------> display the SCREEN 1*/





 //!!!!!!!!!!!!!!!!SCREEN3!!!!!!!!!!!!!!!!!!!!!!!
//             FIRST DISPLAY
//             1) NEW DOCTOR
//             2) ALREADY REGISTRED DOCTOR
//             3) EXIT(DISPLAYS MAIN MENU)-->SCREEN 1
//
//             1) IF NEW DOCTOR--->ASK FOR NAME ,SPECIALIZATION,QUALIFICATION,PREV_EMPLOYER;
//                             ---> SET THE STATUS AS FALSE BY DEFAULT
//                             ---> ASK FOR PASSWORD
//                             
//             2) ALREDAY REGISTERED DOCTORS---> LOGIN: ASK FOR USERNAME AND PASSWORD AND CHECK IN DOCTOR DB---DISPLAY ACCORDINGLY 
//                                       LOGIN FAILS-->IF WANT TO LOGIN AGAIN-> YES THEN ASK USERNAME PASSWORD AND CHEK 
//                                                                        NO THEN ASK SCREEN 3
//                                       LOGIN SUCCESSFUL--->DISPLAY HIS PROFILE ON TOP
			
//                                                      --->!!!!!!!!!!!SCREEN 4!!!!!!!!!!!!!!!!
//                                                                I)    DISPLAY THE PATIENTS ASSIGNED TO HIM (WITH APPROVE=TRUE AND REROUTE=FALSE)
//	                                                            II)   APPROVE/REROUTE PATIENTS WHO WANT TO TAKE GUIDANCE
//	                                                                  DISPLAY ALL PATIENTS OF DOC WHOSE APPROVED AND RE-ROUTED ARE FALSE.----->DISPLAY SCREEN4
//	                                                                  SCREEN5
//	                                                                  1)APPROVE--->ASK FOR PATIENT ID AND SET APPROVED TO TRUE
//	                                                                  2)REROUTE---> ASK FOR PATIENT ID .DISPLAY LIST OF SPECIALITIES AND ASK TO CHOSE ONE----> ACCORDING TO THE SPECIALITY DISPLAY NAME, dId,Specialization,qual to the doctr
//	                                                                         ----> then ask enter the did to which the patient should be rerouted to AND SET REROUTE = TRUE;
//	                                                                  3) EXIT--->DISPLAY SCREEN 4
//                                                               III)   EDIT PROFILE-----SHOW LIST OF PARAMETERS THAT CAN BE MODIFIED--->ASK FOR PARAM_NAME--->SET(PARAM)---->WANT MORE UPDATIONS
//                                                                                  ----->REPEAT---.ELSE SCREEN 4
//                                                               IV)    EXIT-->DISPLAY SCREEN 3



//!!!!!!!!!!!!!!!!SCREEN5!!!!!!!!!!!!!!!!!!!!!!!     
//			1) NEW PATIENT
//          2) ALREADY REGISTERED PATIENT
//          3) EXIT--->SCREEN1
//
//			1) IF NEW PATIENT--->ASK FOR NAME ,GENDER,AGE,DISEASE;
//							---> SET THE APPROVED AND RE-ROUTED AS FALSE BY DEFAULT
//							---->dislay all specializations and ask for speciaization
//							----->acc. to specialization display doc profile and ask for docId
//							----->EROOROOROROORORO HOW TO ASSIGN PATIENT TO DOCTOR
//							---> ASK FOR PASSWORD
//			2) ALREDAY REGISTERED PATIENT---> LOGIN: ASK FOR USERNAME AND PASSWORD AND CHECK IN PATIENT DB---DISPLAY ACCORDINGLY 
//                          LOGIN FAILS-->IF WANT TO LOGIN AGAIN-> YES THEN ASK USERNAME PASSWORD AND CHEK 
//                                                           NO THEN ASK SCREEN 5 
//                         LOGIN SUCCESSFUL--->DISPLAY HIS PROFILE ON TOP
//
//                                         --->!!!!!!!!!!!SCREEN 6!!!!!!!!!!!!!!!!
//                                            I) CHECK STATUS OF DOCTOR_ALLOTMENT---->IF APPROVED=TRUE   DISPLAYS PROFILE OF DOCTOR(NAME,QUAL,SPECIALIZATION,GENDER,AGE)
//                                                                                    IF RE-ROUTED=TRUE  DISPLAYS MSSG THAT NEW DOCTOER IS ALLOTED
//                                            II)EDIT_PROFILE----SHOW LIST OF PARAMETERS THAT CAN BE MODIFIED--->ASK FOR PARAM_NAME--->SET(PARAM)---->WANT MORE UPDATIONS
//                                                           ----->REPEAT---.ELSE SCREEN 
//                                            III)EXIT--->DISPLAY SCREEN 5






//					patient_login:
//	            {
//		    	    System.out.println(".................PATIENT LOGIN............................\n");
//					Transaction p1 = ss.beginTransaction();
//					List<Object> params = Patient.patient_login_info();
//					
//					Query q1=ss.getNamedQuery("Patient.login");
//					q1.setString(0, (String) params.get(0));
//					q1.setString(1, (String) params.get(1));
//					p1.commit();
//		
//					List<Admin> rs1 = (List<Admin>) q1.list();
//					
//					if(rs1.isEmpty())
//					{      
//						   System.out.println("\n\n\n");
//						   System.out.println("LOGIN UNSUCCESSFUL!!");
//						   System.out.println("1. Try again to Login");
//						   System.out.println("2. Go back to main menu");
//						   System.out.println("Enter your choice(1-2)-->");
//						  
//						   int c = sc.nextInt();
//						   
//						   switch(c)
//						   {
//							   case 1: break patient_login;
//							   case 2: break screen1;
//						   }
//					} 
//					else
//					{
//						System.out.println("\n\n\n");
//					    System.out.println("_______LOGIN SUCCESSFUL______");
//					    
//					    screen2:{
//					    System.out.println("Select the agenda->");
//					    System.out.println("1. ADD NEW ADMIN");
//					    System.out.println("2. DOCTOR REQUESTS FOR APPROVAL");
//					    System.out.println("3. VIEW APPROVED DOCTORS ");
//					    System.out.println("4. VIEW ALL PATIENTS");
//					    System.out.println("5. LOGOUT");
//					    System.out.println("Enter the choice(1-5)->");
//					    }
//					}} 
package View;

import java.io.IOException;
import java.util.*;

import Model.Boat;
import Model.Member;
import Model.RWFile;

public class ConsoleView extends RWFile implements IView {
	
	
    public void mainMenu() throws IOException {
    	
    	super.textToList();
        int menuChoice = 0;
        while (menuChoice != 7) {
            menuChoice = menu();
            switch (menuChoice) {
                case 1:
                    regMember();
                    super.regMemberTxt();
                    
                    break;
                case 2:
                	regBoat();
                	super.regBoatTxt();
                	
                    break;
                case 3:
                    showInfo();
                    
                    break;
                case 4:
                    updateInfo();
                    
                    break;
                case 5:
                    deleteInfo();
                   
                    break;
                case 6:
                	super.clearData();
                    System.out.println("....Data Cleared");
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    
                    break;
            }
        }
    }

    public int menu() {//

        Scanner sc = new Scanner(System.in);
        int menuChoice = 0;
        while (true) {
            try {
                System.out.println("|-----------------------------------|");
                System.out.println("|      Welcome to the Yacht club    |");
                System.out.println("|---------------MENU----------------|");
                System.out.println("| Choose an option from below:      |");
                System.out.println("| 1. Create a Member                |");
                System.out.println("| 2. Register Boat             	    |");
                System.out.println("| 3. Show Information               |");
                System.out.println("| 4. Update Information	 	    |");
                System.out.println("| 5. Delete Information	 	    |");
                System.out.println("| 6. *CLEAR ALL DATA*	      	    |");
                System.out.println("| 7. Exit Programme		    |");
                System.out.println("|-----------------------------------|");
                System.out.print("Enter a number: ");
                menuChoice = sc.nextInt();
            }catch (InputMismatchException ex){
                System.out.println("Error, Please Enter a Valid Number: ");
                sc.next();
            }
            return menuChoice;
        }
    }
    
    public void regMember() {
   		Scanner sc = new Scanner(System.in);
   		System.out.println("\n\t--Create a member--");

   		System.out.print("\nEnter Full Name: ");
   		String name = sc.nextLine();

    	System.out.print("Enter the Personal Number (YYMMDDXXXX): ");
    	String persNum = sc.next();

   		int uniqueId = (int) (System.currentTimeMillis() & 0xffffff);// generate unique id

   		Member temp = new Member(uniqueId, name, persNum);
   		super.memList.add(temp);// Storing Member objects in an ArrayList

   		System.out.println("\nMember " + temp.getName() + " has been created");
   		System.out.println("Your Personal Number (PN): " + temp.getPN());
   		System.out.println("Your Unique ID (UID): " + temp.getUID());
   	}
	 public void regBoat() throws IOException {// Registering boats

	 	 Scanner scan = new Scanner(System.in);
		 System.out.println("\n\t--Register a Boat--");

		 System.out.print("What is your UID: ");
		 int ID;
		 try {
			 ID = scan.nextInt();
		 for (Member m : memList) {
			 if (m.getUID() == ID) {// Checking it with UID in the member list
				 System.out.print("How Many Boats Would Like to Register: ");
				 int boats = scan.nextInt();
				 
				 for (int j = 0; j < boats; j++) {
					 System.out.print("\nChoose a Boat Type (1-4): ");
					 System.out.println("\n1. Sailboat" + "\n2. Motorsailer" + "\n3. Kayak" + "\n4. Other");
					 System.out.print("Enter Number: ");
					 int type = scan.nextInt();
					 System.out.print("Enter Boat Length in Meters: ");
					 double length = scan.nextDouble();
					 if (type == 1) {

						 m.registerBoat(length, "SailBoat");
					 }
					 if (type == 2) {
						 m.registerBoat(length, "MotorSailer");
					 }
					 if (type == 3) {
						 m.registerBoat(length, "Kayak");

					 }
					 if (type == 4) {
						 m.registerBoat(length, "Other");
					 }
				 }
			 }
		 }
		 }catch(InputMismatchException ime) {
			 System.out.println("Error! Enter Vaild Integer.");
			 if (scan.hasNextInt()) {ID = scan.nextInt();}
			// else {sc.hasNext();}
		 }
	 } 
        
      public void showInfo() {
          Scanner sc = new Scanner(System.in);
          System.out.println();
          System.out.println("\t--Show Information--\n");
          System.out.println("Choose an option from below");
          System.out.println("  1. Show Members List");
          System.out.println("  2. Show Members Info");
          System.out.print("\nEnter a number: ");

          int choice3 = sc.nextInt();

          if (choice3 == 1) {
              System.out.println("\nChoose a List");
              System.out.println("1. Compact List");
              System.out.println("2. Verbose List");
              System.out.print("\nEnter a number: ");
              int list = sc.nextInt();

              if (list == 1) {
                  printCompactList();
              }
              if (list == 2) {
                  printVerboseList();
              }
          }
          if (choice3 == 2) {
              specMemInfo();
          }
          return;
      }
      
	  public void printCompactList() {
		  System.out.println("\n||   UID  ||   Name   ||  No. Of Boats  ||");
		  System.out.println("------------------------------------------");
		  for (Member m : super.memList) {
			 System.out.println("||"+
				Integer.toString(m.getUID()) + "||" + m.getName() 
					+ "||" + Integer.toString(m.getNoOfBoats())+"||");
		  }
	  }
	  
	  public void printVerboseList() {
			
		  System.out.println("\n---------------------------------------------");
		  for (Member m : super.memList) {
			  System.out.println("||   UID  ||   Name   ||   Personal Number  ||");
			  System.out.println();
			  System.out.println("||"+Integer.toString(m.getUID()) + "||" + m.getName() 
			  + "||" + m.getPN()+"||");
			  System.out.println("   Boat Length(m)   --   Boat Type   ");
			  for (Boat b : m.getBoatList()) {
				  System.out.println("     	 " +Double.toString(b.getBoatLength())+ "	    ||   "
				  +b.getBoatType()+"      ");
			  }
			  System.out.println("----------------------------------------\n");
		  }
		  System.out.println();
	  }
	  
	  public void specMemInfo() {
		  Scanner sc = new Scanner(System.in);
		  System.out.print("What is your UID : ");
		  int id = sc.nextInt();
		  System.out.println("\n||   UID  ||   Personal Number   ||   Name  ||");
		  for (Member m : super.memList) {
			  if (id == m.getUID()) {
				  System.out.println(m.getUID() + "  " + m.getName() + "  " + m.getPN() + "  " + m.getNoOfBoats());
				  System.out.println("   Boat Length(m)   --   Boat Type   ");
				  for (Boat b : m.getBoatList()) {
					 System.out.println("       "+b.getBoatLength()+"              "
					+b.getBoatType());
				  }
			  }
		  }System.out.println();
	  }
       

      public void deleteInfo() throws IOException {

           Scanner sc = new Scanner(System.in);
           System.out.println("\t--Delete Information--\n");
           System.out.println("1. Delete Member");
           System.out.println("2. Delete Boat");
           System.out.println("Enter Number: ");
           int di = sc.nextInt();
           if (di == 1) {
               deleteMem();
           }
           if (di == 2) {
               deleteBoat();
           }
           return;
       }
  	public void deleteMem() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("What is your UID : ");
		int id = sc.nextInt();
		for (Member m : memList) {
			if (id == m.getUID()) {
				memList.remove(m);
				System.out.println("Member deleted successfully");
				break;
			}
		}
		super.regMemberTxt();
		super.regBoatTxt();
	}
  	
	public void deleteBoat() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("What is your UID : ");
		int id = sc.nextInt();
		for (Member m : memList) {//Checking through the member list
			if (id == m.getUID()) {
				System.out.println("Which boat do you want to remove? ");
				String bt = sc.next();
				for (Boat b : m.getBoatList()) {
					if (bt.equalsIgnoreCase(b.getBoatType()))

					{
						m.deleteBoat(b);
						System.out.println("Boat successfully deleted");
						break;
					}
				}

				break;
			}
		}
		regBoatTxt();
	}

       public void updateInfo() throws IOException {
           Scanner sc = new Scanner(System.in);
           System.out.println();
           System.out.println("\t--update Information--\n");
           System.out.println("Choose an option from below");
           System.out.println("  1. Update Member");
           System.out.println("  2. Update Boat");
           System.out.print("\nEnter a number: ");
           int info = sc.nextInt();

           if (info == 1) {
               updateMemInfo();
           }
           if (info == 2) {
               updateBoat();
           }
       }
       
       public void updateMemInfo() throws IOException {
   		Scanner sc = new Scanner(System.in);
   		System.out.print("What is your UID : ");
   		int id = sc.nextInt();
   		sc.nextLine();
   		for (Member m : memList) {
   			if (id == m.getUID()) {

   				System.out.println("\nEnter Full Name: ");
   				String name = sc.nextLine();
   				m.setName(name);
   				System.out.println("Enter the Personal Number (YYMMDDXXXX): ");
   				String persNum = sc.nextLine();
   				m.setPN(persNum);
   				System.out.println("The member info updated Successfully");
   				break;
   			}
   		}
   		regMemberTxt();
   	}
       
      
   	  public void updateBoat() throws IOException {
   		Scanner sc = new Scanner(System.in);
		System.out.print("What is your UID : ");
		int id = sc.nextInt();
		for (Member m : memList) {//Checking through the member 
			if (id == m.getUID()) {
				System.out.println("Which boat details do you want to update? ");
				String bt = sc.next();
				for (Boat b : m.getBoatList()) {
					if (bt.equalsIgnoreCase(b.getBoatType())){
						System.out.println("Name of the Boat: ");
						String btName = sc.next();
						System.out.println("Length of Boat: ");
						Double btLength = sc.nextDouble();
						m.updateBoat(btLength, btName, b);
						System.out.println("Boat successfully updated");
						break;
					}
				}
	
				break;
			}
		}
		super.regBoatTxt();   	  
   	  }
}


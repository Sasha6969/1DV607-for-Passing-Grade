package View;

import java.io.IOException;

public interface IView {

//Controller of the main menu with feature 
	public void mainMenu(Model.RWFile rwFile) throws IOException;

//printing of main menu with all features
	int menu();

//displaying the registration of members
	void regMember() throws IOException;

//displaying the registration of boats
	void regBoat() throws IOException;

//displaying info for user to choose list or specific member info
	void showInfo() throws NumberFormatException, IOException;

//displays compact list
	void printCompactList();

//displays verbose list
	void printVerboseList();

//displays specific member's info
	void specMemInfo() throws NumberFormatException, IOException;

//displaying deletion of member of boats
	void deleteInfo() throws IOException;

//deletes member and it's boats
	void deleteMem() throws IOException;

//deletes member's boats
	void deleteBoat() throws IOException;

//displays update of member's info or boat
	void updateInfo() throws IOException;

//displays update members info
	void updateMemInfo() throws IOException;

//displays update boat info
	void updateBoat() throws IOException;
//Take uniqueId from user
	public int userEntersUniqueId() throws NumberFormatException, IOException;
//Take Name from  user
	public String userEntersName() throws IOException;
//Take Personal Number from user
	public String userEntersPersNum() throws IOException;

}

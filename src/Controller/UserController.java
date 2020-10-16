package Controller;

import java.io.IOException;

public class UserController {

    public void startProgram(View.ConsoleView console, Model.RWFile rwFile) throws IOException {
        
    	while(!console.exit) {
    		console.mainMenu(rwFile);
        }
    }

}
package game;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;


/**
 * Class for the Gui calculations
 * @author Martin Stuwe 676421
 * @author Zeyi Sun
 * @author Richard Tank
 * @author Fin Niklas Tiedemann
 * group 23
 * it2
 */
public class GuiCalcs {
	
	/**
	 * check if GUI is active
	 */
	static boolean guiActive = false;
	
	/**
	 * String for the path to the save file
	 */
	String saveTe = "save.txt";
	
	
	/**
	 * set-method for guiActive
	 * @param set true if active
	 */
	public static void setGui(boolean set) {
		guiActive = set;
	}
	


    /**
     * method to change the board visual to a real figure picture
     * @param visual the board visual of the figure
     * @return white the white symbol of the figure
     */
    public String checkWhiteSymbols(String visual) {
    	String white = "";
    	if(visual=="P") {
    		white="♙" ;
    	}
    	if(visual=="N") {
    		white="♘" ;
    	}
    	if(visual=="B") {
    		white="♗" ;
    	}
    	if(visual=="R") {
    		white="♖" ;
    	}
    	if(visual=="Q") {
    		white="♕" ;
    	}
    	if(visual=="K") {
    		white="♔" ;
    	}
    	return white;
    }
    
    /**
     * method to change the board visual to a real figure picture
     * @param visual the board visual of the figure
     * @return black the black symbol of the figure
     */
    public String checkBlackSymbols(String visual) {
    	String black = "";
     	if(visual=="p") {
    		black="♟" ;
    	}
    	if(visual=="n") {
    		black="♞" ;
    	}
    	if(visual=="b") {
    		black="♝" ;
    	}
    	if(visual=="r") {
    		black="♜" ;
    	}
    	if(visual=="q") {
    		black="♛" ;
    	}
    	if(visual=="k") {
    		black="♚" ;
    	}
    	return black;
    }
    
    /**
     * method to add beaten figures to a list
     * @param brett board on which the game is on
     * @param screenHeight height of screen
     * @return beaten the list of the beaten figures
     */
    public ListView<String> addBeaten(Board brett, double screenHeight) {
  	  ListView <String>beaten = new ListView<String>();
        beaten.minHeight(screenHeight/4);
        for(int i=0; i<brett.beaten.size();i++) {
      		  beaten.getItems().add(checkWhiteSymbols(brett.beaten.get(i))+checkBlackSymbols(brett.beaten.get(i)));
      	  

        }
        return beaten;

    }
    
    /**
     * method to convert the array number into the real board number
     * for example top left array spot 0,0 would be the 8 of a8
     * @param a number that has to be converted
     * @return b the converted number
     */
    public int numberToNumber(int a) {
    	int b = 0;
    	if(a == 0) {
    		b = 8;
    	}
    	else if (a == 1) {
    		b = 7;
    	}
    	else if (a == 2) {
    		b = 6;
    	}
    	else if (a == 3) {
    		b = 5;
    	}
    	else if (a == 4) {
    		b = 4;
    	}
    	else if (a == 5) {
    		b = 3;
    	}
    	else if (a == 6) {
    		b = 2;
    	}
    	else if (a == 7) {
    		b = 1;
    	}
    	return b;
    }
    
    /**
     * method to convert the array number into the real board letter
     * for example top left array spot 0,0 would be the a of a8
     * @param a number that has to be converted
     * @return b the converted letter
     */
    public String numberToString(int a) {
    	String b ="";
    	if(a == 0) {
    		b = "a";
    	}
    	else if (a == 1) {
    		b = "b";
    	}
    	else if (a == 2) {
    		b = "c";
    	}
    	else if (a == 3) {
    		b = "d";
    	}
    	else if (a == 4) {
    		b = "e";
    	}
    	else if (a == 5) {
    		b = "f";
    	}
    	else if (a == 6) {
    		b = "g";
    	}
    	else if (a == 7) {
    		b = "h";
    	}
    	return b;
    }
    
    /**
     * method to go from Input to the array numbers
     * @param i the char that gets converted
     * @return b the converted char that is now an Integer
     */
    public int backToNumber(char i) {
    	int b = 10;
    	if(i == 'a' || i == '8') {
    		b = 0;
    		return b;
    	}
    	else if(i == 'b' || i == '7') {
    		b = 1;
    		return b;
    	}
    	else if(i == 'c' || i == '6') {
    		b = 2;
    		return b;
    	}
    	else if(i == 'd' || i == '5') {
    		b = 3;
    		return b;
    	}
    	return backToNumber2(i);
    }
    
    /**
     * second method to go from Input to the array numbers
     * @param i the char that gets converted
     * @return b the converted char that is now an Integer
     */
    public int backToNumber2(char i) {
    	int b = 0;
    	if(i == 'e' || i == '4') {
    		b = 4;
    	}
    	else if(i == 'f' || i == '3') {
    		b = 5;
    	}
    	else if(i == 'g' || i == '2') {
    		b = 6;
    	}
    	else if(i == 'h' || i == '1') {
    		b = 7;
    	}
    	return b;
    }
      
    
    
    /**
     * method to make a save in the GUI
     * @param historie ListView of Labels of all moves
     */
    public void makeSaveGui(ListView<Label> historie){
    	File file = new File(saveTe);
    	
    	
    	try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	ArrayList<String> test = new ArrayList<String>();
    	test.add(java.time.LocalDate.now().toString());
    	test.add("---");
    	for(int i = 0; i < historie.getItems().size(); i++) {
    		test.add(historie.getItems().get(i).getText());
    	}
    	
    	
    	try {
			Files.write(Paths.get(saveTe), test, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    }
    
    /**
     * method to load a save file in the GUI
     * @param board Board the save file gets loaded on on
     */
    public void loadGuiSave(Board board) {
    	File test = new File(saveTe);
    	if(test.exists()) {
    		List<String> allLines = new ArrayList<String>();
    		List<String> justMoves = new ArrayList<String>();
    	
    		try {
    			allLines = Files.readAllLines(Paths.get(saveTe), StandardCharsets.UTF_8);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		
    		
    		for (String s: allLines) {
    			justMoves.add(s);
    			if(s.equals("---")) {
    				justMoves.clear();
    			}
    		}
    			
    		
    		for(String s: justMoves) {
    			int from1 = backToNumber(s.charAt(0));
    			int from2 = backToNumber(s.charAt(1));
    			int to1 = backToNumber(s.charAt(3));
    			int to2 = backToNumber(s.charAt(4));
    			String to = "" + to1 + to2;
    			board.getField(from1, from2).move(board, from1, from2, to);
    			
       		}
    		
    	}
    }
 
    /**
     * method to convert input in the historie
     * @param a x axis starting position
     * @param b y axis starting position
     * @param to x and y ending position as a String
     * @return test converted move as a Label
     */
    public Label convertInputToHistorie(int a, int b, String to) {
    	String output ="";
    	Label test = new Label();
    	int to1=Character.getNumericValue(to.charAt(0));
    	int to2=Character.getNumericValue(to.charAt(1));
    	output = numberToString(a) + numberToNumber(b) + "-" + numberToString(to1) + numberToNumber(to2);
		test.setText(output);
		return test;
	}
}
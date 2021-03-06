package figures;

import game.Figures;
import game.Board;

/**
 * Class for the figure: bishop
 * @author Martin Stuwe 676421
 * @author Zeyi Sun
 * @author Richard Tank
 * @author Fin Niklas Tiedemann
 * group 23
 * it1
 */


public class Bishop extends Figures {
	 
	/**
	 *  the String shown on the board in the console
	 */
	private String boardVisual;
	
	/**
	 * the x axis position of the bishop 
	 */
	 public int pos1;
	
	/**
	 * the y axis position of the bishop 
	 */
	 public int pos2;
	
	/**
	 * the color of the bishop
	 */
	private String color;
	
	/**
	 * the color of the bishop
	 */
	private boolean emptySpaces;
	
	/**
	 * array to evaluate the bishop position
	 */
	private static int [] bishopTable = {
            -20,-10,-10,-10,-10,-10,-10,-20,
            -10,  0,  0,  0,  0,  0,  0,-10,
            -10,  0,  5, 10, 10,  5,  0,-10,
            -10,  5,  5, 10, 10,  5,  5,-10,
            -10,  0, 10, 10, 10, 10,  0,-10,
            -10, 10, 10, 10, 10, 10, 10,-10,
            -10,  5,  0,  0,  0,  0,  5,-10,
            -20,-10,-10,-10,-10,-10,-10,-20 };
	
	/**
	 * the constructor creates a new bishop object and needs a x and a y axis position plus a color
	 * @param pos1 x axis position of the bishop
	 * @param pos2 y axis position of the bishop
	 * @param color color of the bishop, valid input: "w" for white, "b" for black
	 */
	public Bishop(int pos1, int pos2, String color) {
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.color = color;
		type = 1;
	}
	
	/**
	 * get-method of boardVisual
	 * if color is "w" boardVisual is in capital letter
	 * if color is "b" boardVisual is in lower case letter
	 * @return boardVisual of the bishop as a String
	 */
	public String getBoardVisual() {
		if(this.color =="w") {
			boardVisual = "B";
			return boardVisual;
		}
		else if(this.color =="b") {
			boardVisual = "b";
			return boardVisual;
		}
		else {
			return boardVisual;
		}
			
	}
	
	/**
	 * set-method of x and y axis position
	 * @param pos1 new x axis position of bishop
	 * @param pos2 new y axis position of bishop
	 */
	public void setPos(int pos1, int pos2) { 
		this.pos1 = pos1;
		this.pos2 = pos2;
	}
	
	/**
	 * get-method of color
	 * @return color of the bishop as a String
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * get method for bishopTable
	 * @return bishopTable
	 */
	public static int[] getTable() {
		int [] bishopTableCopy=bishopTable.clone();
		return bishopTableCopy;
	}
	
	
	/**
	 * Checks if the current move is valid
	 * @param board the board on which the move is tested on
	 * @param x for the x axis position to move to
	 * @param y for the y axis position to move to
	 * @return true for valid move 
	 * @return false for invalid move
	 */
	public boolean validMove(Board board, int x, int y) {
		
		emptySpaces = true;
		
		//check if the field to move to is on the board
		if(x < 0 || x > 7 || y < 0 || y > 7) {
			return false;
		}

	return validMove1(board,x,y);
	}
	
	/**
	 * method to check if move is on top left diagonal
	 * @param board the board on which the move is tested on
	 * @param x the x axis position to move to
	 * @param y the y axis position to move to
	 * @return true if move is valid, false if figures in the way or goes on to validMove2
	 */
	public boolean validMove1(Board board, int x, int y) {
		
		// top left diagonal + check if there are other figures in the way
		if(checkNumb(x,y,-1,-1) ) {
			for(int i=this.pos1+1 , k=this.pos2+1;i<x && k<y  ; i++ ,k++) {
				notNull(board,i,k);
			}
			
			return emptySpaces;
		}
		
		return validMove2(board,x,y);
	}
	
	/**
	 * method to check if move is on bottom right diagonal
	 * @param board the board on which the move is tested on
	 * @param x the x axis position to move to
	 * @param y the y axis position to move to
	 * @return true if move is valid, false if figures in the way or goes on to validMove3
	 */
	public boolean validMove2(Board board, int x, int y) {
		
		// bottom right diagonal + check if there are other figures in the way
		if(checkNumb(x,y,1,1) ) {
			for(int i=this.pos1-1 , k=this.pos2-1;i>x && k>y  ; i-- ,k--) {
				notNull(board,i,k);
			}
			
			return emptySpaces;
		}
		
		return validMove3(board,x,y);
	}
	
	/**
	 * method to check if move is on top right diagonal
	 * @param board the board on which the move is tested on
	 * @param x the x axis position to move to
	 * @param y the y axis position to move to
	 * @return true if move is valid, false if figures in the way or goes on to validMove4
	 */
	public boolean validMove3(Board board, int x, int y) {
		
		// top right diagonal + check if there are other figures in the way
		if(checkNumb(x,y,1,-1) ) {
			for(int i=this.pos1-1 , k=this.pos2+1;i>x && k<y  ; i-- ,k++) {
				notNull(board,i,k);
			}
			
			return emptySpaces;
		}
		return validMove4(board,x,y);
	}
	
	/**
	 * method to check if move is on bottom right diagonal
	 * @param board the board on which the move is tested on
	 * @param x the x axis position to move to
	 * @param y the y axis position to move to
	 * @return true if move is valid
	 * @return false if move is not valid
	 */
	public boolean validMove4(Board board, int x, int y) {
		
		// bottom right diagonal + check if there are other figures in the way
		if(checkNumb(x,y,-1,1) ) {
			for(int i=this.pos1+1 , k=this.pos2-1;i<x && k>y  ; i++ ,k--) {
				notNull(board,i,k);
			}
			return emptySpaces;
		}
		
		else { 
			return false;
		}
	} // for loop ?
	
	/**
	 * method to check if field is empty and to set emptySpaces to false if so
	 * @param board the board on which the move is tested on
	 * @param x the x axis position to move to
	 * @param y the y axis position to move to
	 */
	public void notNull(Board board,int i, int k) {
		if(board.getField(i, k) != null) {
			emptySpaces= false;
		}
	}
	
	/**
	 * checks if field to move to + diagonal distance equals the starting position
	 * @param x the x axis position to move to
	 * @param y the y axis position to move to
	 * @param z1 diagonal distance x axis
	 * @param z2 diagonal distance y axis
	 * @return true if field to move to + diagonal distance equals the starting position
	 */
	public boolean checkSame(int x, int y,int z1, int z2) {
		return this.pos1 == x+z1&& this.pos2 == y+z2;
	}
	
	/**
	 * method to check if move is on diagonal
	 * @param x the x axis starting position
	 * @param y the y axis starting position
	 * @param sign1 x multiplication number
	 * @param sign2 y multiplication number
	 * @return true if move is on diagonal
	 */
	public boolean checkNumb(int x,int y, int sign1, int sign2) {
		return checkSame(x,y,sign1*7,sign2*7) || checkSame(x,y,sign1*6,sign2*6)|| 
				checkSame(x,y,sign1*5,sign2*5) || checkSame(x,y,sign1*4,sign2*4) || 
				checkSame(x,y,sign1*3,sign2*3)|| checkSame(x,y,sign1*2,sign2*2) || 
				checkSame(x,y,sign1*1,sign2*1);
	}
}


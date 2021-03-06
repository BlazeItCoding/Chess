package game;

/**
 * Class for the chess Figures
 * @author Martin Stuwe 676421
 * @author Zeyi Sun
 * @author Richard Tank
 * @author Fin Niklas Tiedemann
 * group 23
 * it1
 */
public class Figures {
	
	/**
	* x axis position
	*/
	public int pos1;
	
	/**
	* y axis position
	*/
	public int pos2;
	
	/**
	* figure's type
	*/
	protected int type;
	
	/**
	* figure's board visualization
	*/
	String boardVisual; 
	
	/**
	* The figure's color
	*/
	String color;
	
	/**
	* The warning for unallowed Move
	*/
	static String unallowed = "!Move not allowed";
	/**
	 * save for starting position
	 */
	static Figures restoreFrom;
	/**
	 * save for ending position
	 */
	static Figures restoreTo;
	 
	/**
	* check if the figure has moved yet
	*/
	private boolean hasMoved;
	
	/**
	 * set-method of x and y axis position
	 * @param pos1 new x axis position 
	 * @param pos2 new y axis position 
	 */
	public void setPos(int Pos1, int Pos2) { 
		this.pos1 = Pos1;
		this.pos2 = Pos2;
	}
	
	/**
	 * get-method of pos1
	 * @return pos1 x axis position
	 */
	public int getPos1() {
		return pos1; 
	}
	
	/**
	 * get-method of pos2
	 * @return y axis position
	 */
	public int getPos2() {
		return pos2; 
	}
	
	/**
	 * get-method of boardVisual
	 * @return boardVisual of the king as a String
	 */
	public String getBoardVisual() {
		return boardVisual;
	}
	/**
	 * get-method of the type
	 * @return type of the figure
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * get-method of color
	 * @return color as a String
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * get-method of hasMoved
	 * @return hasMoved current state depending on if the figure has been moved
	 */
	public boolean isHasMoved() {
		return hasMoved;
	}
	
	/**
	 * set-method of hasMoved
	 * @param hasMoved used after move
	 */
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	
	/**
	 * Checks if the current move is valid
	 * @param board ,the board on which the move is tested on
	 * @param x for the x axis position to move to
	 * @param y for the y axis position to move to
	 * @return false because figure object can't move
	 */
	public boolean validMove(Board board,int x, int y) {
		return false;
	}
	
	/**
	 * method to move the figures on the board
	 * @param board board on which the figure is moved on
	 * @param pos1from x axis position where the figure is moved from
	 * @param pos2from y axis position where the figure is moved from
	 * @param posTo x and y axis position where the figure is moved to
	 * @return true if figure has been moved
	 */
	public Boolean move(Board board, int pos1from, int pos2from,  String posTo) { 
		int pos1to=Character.getNumericValue(posTo.charAt(0));
		int pos2to=Character.getNumericValue(posTo.charAt(1));
		
		// saves starting position of figure
		restoreFrom=board.positionen[pos1from][pos2from];
		// saves ending position of figure
		restoreTo=board.positionen[pos1to][pos2to];
		
		// check if starting position is empty
		if (board.positionen[pos1from][pos2from] == null) {
			System.out.println(unallowed);
			return false;	
		}
		
		// on white's turn, check if figure to be moved is white
		if(board.getCurrentTurn()==0 && board.positionen[pos1from][pos2from].getColor() !="w") {
			System.out.println(unallowed);
			return false;		
		}
		
		// on black's turn, check if figure to be moved is black
		if(board.getCurrentTurn()==1 && board.positionen[pos1from][pos2from].getColor() !="b") {
			System.out.println(unallowed);
			return false;		
		}
		
		// check if figure is allowed to make certain move
		return move2(board, pos1from, pos2from,posTo);
	}
	
	/**
	 * method to check if move is valid
	 * @param board the board the move is on
	 * @param pos1from the x axis position of the starting field
	 * @param pos2from the y axis position of the starting field
	 * @param posTo the x and y axis position of the field to move to
	 * @return false if move not valid, else go on to move22
	 */
	public static boolean move2(Board board, int pos1from, int pos2from, String posTo) { 
		int pos1to=Character.getNumericValue(posTo.charAt(0));
		int pos2to=Character.getNumericValue(posTo.charAt(1));
		// if move not valid
		if(!board.positionen[pos1from][pos2from].validMove(board,pos1to, pos2to)) {
			System.out.println(unallowed);
			return false;
		}
		
		// check if a same color figure is on the ending position
		if(board.positionen[pos1to][pos2to]!= null && board.positionen[pos1from][pos2from].getColor() == board.positionen[pos1to][pos2to].getColor()) {
			System.out.println(unallowed);
			return false;
		}
		
		return move22(board, pos1from, pos2from, posTo);
	}
	
	/**
	 * method to add beaten figures to the beaten list
	 * @param board the board the move is on
	 * @param pos1from the x axis position of the starting field
	 * @param pos2from the y axis position of the starting field
	 * @param posTo the x and y axis position of the field to move to
	 * @return move3
	 */
	public static boolean move22(Board board, int pos1from, int pos2from, String posTo) { 
		int pos1to=Character.getNumericValue(posTo.charAt(0));
		int pos2to=Character.getNumericValue(posTo.charAt(1));
		// color change and adding beaten black figures to the beaten list
		if(board.getCurrentTurn()==0) {
			if(board.positionen[pos1to][pos2to]!= null && board.positionen[pos1to][pos2to].getColor() == "b") {
					board.beaten.add(board.positionen[pos1to][pos2to].getBoardVisual());
			}
			board.setCurrentTurn(1);
		}
		
		// color change and adding beaten white figures to the beaten list
		else if(board.getCurrentTurn()==1) {
				if(board.positionen[pos1to][pos2to]!= null && board.positionen[pos1to][pos2to].getColor() == "w") {
					board.beaten.add(board.positionen[pos1to][pos2to].getBoardVisual());
				}
			board.setCurrentTurn(0);
		}
		return move3(board,pos1from, pos2from,posTo);
	}
	
	/**
	 * method to change the figures position in the array and its position attributes
	 * @param board the board the move is on
	 * @param pos1from the x axis position of the starting field
	 * @param pos2from the y axis position of the starting field
	 * @param posTo the x and y axis position of the field to move to
	 * @return move4
	 */
	public static boolean move3(Board board,int pos1from, int pos2from,String posTo) { 
		int pos1to=Character.getNumericValue(posTo.charAt(0));
		int pos2to=Character.getNumericValue(posTo.charAt(1));
		// moving the figure
		board.positionen[pos1to][pos2to] = board.getField(pos1from,pos2from);
		
		// emptying the starting position
		board.positionen[pos1from][pos2from] = null;
		
		// changing the figures position integers
		board.positionen[pos1to][pos2to].setPos(pos1to, pos2to);
		
		return move4(board,pos1from, pos2from, posTo);
	}
	
	/**
	 * method to check if black is in check after black's move
	 * @param board the board the move is on
	 * @param pos1from the x axis position of the starting field
	 * @param pos2from the y axis position of the starting field
	 * @param posTo the x and y axis position of the field to move to
	 * @return true if no check, false if black in check after the move or go on to check4
	 */
	public static boolean move4(Board board, int pos1from, int pos2from, String posTo) { 
		int pos1to=Character.getNumericValue(posTo.charAt(0));
		int pos2to=Character.getNumericValue(posTo.charAt(1));
		// check if you are in check after the move		
		if(Zug.checkCheck(board)) {
			
			// check if black is in check after the move and restoring the move if so
			if(board.blackCheck && board.getCurrentTurn()==0){
					 board.positionen[pos1to][pos2to] = restoreTo;
					 board.positionen[pos1from][pos2from] = restoreFrom;
					 if(board.positionen[pos1to][pos2to]!= null) {
						 board.positionen[pos1to][pos2to].setPos(pos1to, pos2to);
					 }
					 if(board.positionen[pos1from][pos2from]!= null) {
						 board.positionen[pos1from][pos2from].setPos(pos1from, pos2from);
					 }
					 board.setCurrentTurn(1);
				 
				 System.out.println(unallowed);
				 return false;
				 
			}
			
			else {return check4(board, posTo, pos1from, pos2from);}
		}	 
		
		board.positionen[pos1to][pos2to].setHasMoved(true);
		Zug zug = new Zug(board.getField(pos1to, pos2to),pos1from,pos2from,posTo);  //creates new move and saves in list
		zug.setBoardState(board);
		zug.setTurn(board.getCurrentTurn());
		board.movedList.add(zug);
		return true;
	}
		
	/**
	 * method to check if white is in check after white's move
	 * @param board the board the move is on
	 * @param pos1from the x axis position of the starting field
	 * @param pos2from the y axis position of the starting field
	 * @param posTo the x and y axis position of the field to move to
	 * @return true if move is valid
	 */
	public static boolean check4(Board board, String posTo, int pos1from, int pos2from) {
		 
			int pos1to=Character.getNumericValue(posTo.charAt(0));
			int pos2to=Character.getNumericValue(posTo.charAt(1));
		// check if white is in check after the move and restoring the move if so
		if(board.whiteCheck&&board.getCurrentTurn()==1) {
					board.positionen[pos1to][pos2to] = restoreTo;
					board.positionen[pos1from][pos2from] = restoreFrom;
					if(board.positionen[pos1to][pos2to]!= null) {
						board.positionen[pos1to][pos2to].setPos(pos1to, pos2to);
					}
					if(board.positionen[pos1from][pos2from]!= null) {
						board.positionen[pos1from][pos2from].setPos(pos1from, pos2from);
					}
					board.setCurrentTurn(0);
				
				System.out.println(unallowed);
				return false;
				}
		return true;
	}
	
	/**
	 * method to check if figures on the board have possible moves
	 * @param board board on which the figure is on
	 * @param pos1from x axis position where the figure is moved from
	 * @param pos2from y axis position where the figure is moved from
	 * @param To x and y axis position where the figure is moved to
	 * @return true if figure has at least one possible move
	 */
	public Boolean hasPossibleMove(Board board, int pos1from, int pos2from, String To) {
		int pos1to=Character.getNumericValue(To.charAt(0));
		int pos2to=Character.getNumericValue(To.charAt(1));
		
		// saves starting position of figure
		restoreFrom=board.positionen[pos1from][pos2from];
		
		// saves ending position of figure
		restoreTo=board.positionen[pos1to][pos2to];
		
		// check if starting position is empty
		if (board.positionen[pos1from][pos2from] == null) {
			return false;	
		}
		
		// on white's turn, check if figure to be moved is white
		if(board.getCurrentTurn()==0 && board.positionen[pos1from][pos2from].getColor() !="w") {
			return false;			
		}
		
		// on black's turn, check if figure to be moved is black
		if(board.getCurrentTurn()==1 && board.positionen[pos1from][pos2from].getColor() !="b") {
			return false;		
		}
		
		// check if figure is allowed to make certain move
		if (!board.positionen[pos1from][pos2from].validMove(board,pos1to, pos2to)){
			return false;
		}
		
		// check if a same color figure is on the ending position
		if(board.positionen[pos1to][pos2to]!= null && board.positionen[pos1from][pos2from].getColor() == board.positionen[pos1to][pos2to].getColor()) {
			return false;
		}
		
		
		// moving the figure
		board.positionen[pos1to][pos2to] = board.getField(pos1from,pos2from);
		
		// emptying the starting position
		board.positionen[pos1from][pos2from] = null;
		
		// changing the figures position integers
		board.positionen[pos1to][pos2to].setPos(pos1to, pos2to);
		return hasPossibleMove2(board,pos1from, pos2from, To);
	}
	
	/**
	 * method to check if there is check after the move
	 * @param board the board the move is on
	 * @param pos1from the x axis position of the starting field
	 * @param pos2from the y axis position of the starting field
	 * @param To the x and y axis position of the field to move to
	 * @return true if no check
	 */
	public static Boolean hasPossibleMove2(Board board, int pos1from, int pos2from, String To) { 
		int pos1to=Character.getNumericValue(To.charAt(0));
		int pos2to=Character.getNumericValue(To.charAt(1));
		// check if you are in check after the move	
		Zug.checkCheck(board);
		if(board.getCurrentTurn()==0 && board.whiteCheck ||board.getCurrentTurn()==1 && board.blackCheck ) {
			board.positionen[pos1to][pos2to] = restoreTo;
			board.positionen[pos1from][pos2from] = restoreFrom;
			if(board.positionen[pos1to][pos2to]!= null) {
				board.positionen[pos1to][pos2to].setPos(pos1to, pos2to);
			}
			if(board.positionen[pos1from][pos2from]!= null) {
				board.positionen[pos1from][pos2from].setPos(pos1from, pos2from);
			}
			return false;
		}
		
		// restoring the the ending position
		board.positionen[pos1to][pos2to] = restoreTo;
		
		// restoring the starting position
		board.positionen[pos1from][pos2from] = restoreFrom;
		
		// restoring the integers of the ending position
		if(board.positionen[pos1to][pos2to]!= null) {
			board.positionen[pos1to][pos2to].setPos(pos1to, pos2to);
		}
		
		// restoring the integers of the starting position
		if(board.positionen[pos1from][pos2from]!= null) {
			board.positionen[pos1from][pos2from].setPos(pos1from, pos2from);
		}
	return true;
	}


	
}
	
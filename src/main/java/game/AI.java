package game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import figures.Bishop;
import figures.King;
import figures.Knight;
import figures.Pawn;
import figures.Queen;
import figures.Rook;

/**
 * Class for the AI
 * @author Martin Stuwe 676421
 * @author Zeyi Sun
 * @author Richard Tank
 * @author Fin Niklas Tiedemann
 * group 23
 * it2
 */
public class AI {
	
	/**
	 * the converted turn
	 */
	private String convTurn;

	
	/**
	 * the color of the AI 
	 */
	private int color;
	
	/**
	 * integer of the depth
	 */
	private int desiredDepth;
	
	/**
	 * Zug object to save the best move
	 */
	private Zug bestMove;
	
	/**
	 * check if recently moved
	 */
	private int recently;
	
	/**
	 * ArrayList of possible Moves
	 */
	public List<Zug> possibleMoves = new ArrayList<Zug>();
	
	/**
	 * the AI constructor
	 * @param The color chosen for the AI
	 */
	public AI(int color) {
		this.color = color;
	}
	
	/**
	 * set-method of the color
	 * @param i color of the AI
	 */
	public void setColor(int i) {
		this.color = i;
	}
	
	/**
	 * get-method of the color
	 * @return color color of the AI
	 */
	public int getColor() {
		return color;
	}
	
	
	

	
	/**
	 * method to check and restore possible moves
	 * @param board board the moves should take place on
	 * @param movePos String with each position for the move
	 * @param turn the value for the move turn
	 * @param possibleMoveList the list with each possible move
	 */
	public void checkPossMoves(Board board, String movePos, int turn,List<Zug> possibleMoveList )  {
		
		int i1 = Integer.parseInt(String.valueOf(movePos.charAt(0)));
		int j1 = Integer.parseInt(String.valueOf(movePos.charAt(1)));
		int x1 = Integer.parseInt(String.valueOf(movePos.charAt(2)));
		int y1 = Integer.parseInt(String.valueOf(movePos.charAt(3)));
		
		if (board.getField(i1, j1)!=null&&board.getField(i1, j1).hasPossibleMove(board,i1,j1,Integer.toString(x1)+Integer.toString(y1))) {
		
			Zug zug = new Zug(board.getField(i1, j1),i1,j1,Integer.toString(x1)+Integer.toString(y1));
			if(board.movedList.size()>1&&board.movedList.get(board.movedList.size()-2).getTo1()==i1&&board.movedList.get(board.movedList.size()-2).getTo2()==j1) {
				this.recently = recently +1; 	
				possibleMoveList.add(0,zug);
			}
			else {
				possibleMoveList.add(possibleMoveList.size(),zug);
			}
		}
	
	}
	
	/**
	 * method to find possible randomized Moves for the AI
	 * @param board board the moves should take place on
	 * @param movePos String with each position for the move
	 * @param turn the value for the move turn
	 * @param possibleMoveList the list with each possible move
	 */
	public void findPossMovesRnd(Board board, int turn ) {
		List<Zug> possibleMoveList = new ArrayList<Zug>();
	
		convTurn=convertTurn(turn);
		 
		for (int i22=0;i22<8;i22++) {
			for (int j22=0;j22<8;j22++) {
				for (int x22=0;x22<8;x22++) {
					for (int y22=0;y22<8;y22++) {
						if(board.getField(i22, j22)!=null&&convTurn==board.getField(i22, j22).getColor()){
							
							checkPossMoves(board,Integer.toString(i22)+Integer.toString(j22)+Integer.toString(x22)+Integer.toString(y22),turn, possibleMoveList);
						}
					}
				}
			}
		}
		
			possibleMoves = possibleMoveList;
	
	}


	
	
	/**
	 * method to convert a turn into system variable 0|1
	 * @param turn the turn to convert
	 */
	public String convertTurn(int turn) {
		switch (turn) {
		case 0:
			convTurn = "w";
			break;
		case 1:	
			convTurn = "b";
			break;
		}
		return convTurn;
	}
	
	
	
	/**
	 * method to evaluate the figures for the enemy
	 * @param board board the moves should take place on
	 */
	public int calculateValue(Board board) {
		int value=0;
		for (int x=0;x<8;x++) {
			for (int y=0;y<8;y++) {
				
				value = value+calculateFigure(board.getField(x, y),convertTurn(board.getCurrentTurn()));
					
			}
		}	
		return value;
	}
	
	/**
	 * method to evaluate the figures for the enemy
	 * @param board board the moves should take place on
	 */
	public int calculateValueFor(Board board, int Color) {
		String colorS = convertTurn(Color);
	
		int value=0;
		for (int x=0;x<8;x++) {
			for (int y=0;y<8;y++) {
				
				value = value+calculateFigure(board.getField(x, y),colorS);
				if (board.positionen[x][y]!=null&&board.positionen[x][y].getColor()==colorS) {
				value = value+AICalcs.positionEval(board.getField(x, y),x,y);
		
				}
				
			}
		}
		
		return value;
	}
	

	/**
	 * method to evaluate one single figure
	 * @param figure the figure to evaluate
	 * @param color the color from who's perspective to evaluate for
	 */
	public int calculateFigure(Figures figure, String color) {
		int valuez=0;
		if (figure!=null&&figure.getColor()==color) {
				
					if (figure.getClass()==Bishop.class) {
						
						valuez=valuez+30;
					}
					if (figure.getClass()==King.class) {
						valuez=valuez+5000;
					}
				
					if (figure.getClass()==Knight.class) {
						valuez=valuez+30;
					}
					
					if (figure.getClass()==Pawn.class) {
						
						valuez=valuez+10;
					}
					
					if (figure.getClass()==Queen.class) {
						valuez=valuez+100;
					}
					
					if (figure.getClass()==Rook.class) {
						valuez=valuez+50;
					}
					
		}
				
			
		
		return valuez;
	}
	/**
	 * method to move random
	 * @param board board the moves should take place on
	 */
	public void DoRndMove(Board board) {
		Random rnd= new Random();
	
		Zug rndMove = possibleMoves.get(rnd.nextInt(possibleMoves.size()));	

		
		board.getField(rndMove.from1, rndMove.from2).move(board,rndMove.from1,rndMove.from2,Integer.toString(rndMove.to1)+Integer.toString(rndMove.to2));
		
	}
	
	
	/**
	 * method to find possible Moves for the AI minmax with alpha beta pruning
	 * @param board2 board the moves should be simulated on
	 * @param turn the value for the move turn
	 * @return possibleMoveList the possible Moves
	 */	
	public List<Zug> findPossMovesAI(Board board2, int turn ) {
		

		//System.out.println(copy.positionen.length);
		//copy.setCurrentTurn(board2.getCurrentTurn());
		List<Zug> possibleMoveList = new ArrayList<Zug>();
	
		convTurn=convertTurn(turn);
		 
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				for (int x=0;x<8;x++) {
					for (int y=0;y<8;y++) {
						if(board2.getField(i, j)!=null&&convTurn==board2.getField(i, j).getColor()){
							
							checkPossMoves(board2,Integer.toString(i)+Integer.toString(j)+Integer.toString(x)+Integer.toString(y),turn, possibleMoveList);
						
						}
					}
				}
			}
		}
		List<Zug> moveListRec = new ArrayList<Zug>();
		List<Zug> moveListNotRec = new ArrayList<Zug>();
		for (int iterate = 0 ;iterate< recently-2;iterate++) {
			moveListRec.add(
					new Zug (possibleMoveList.get(iterate).getFigure(),possibleMoveList.get(iterate).getFrom1(),possibleMoveList.get(iterate).getFrom2(),
							Integer.toString(possibleMoveList.get(iterate).getTo1())+Integer.toString(possibleMoveList.get(iterate).getTo2()))
							);
			
			}
		for (int iterate2 = this.recently;iterate2 < possibleMoveList.size()-1;iterate2++) {
			moveListNotRec.add(new Zug (possibleMoveList.get(iterate2).getFigure(),possibleMoveList.get(iterate2).getFrom1(),possibleMoveList.get(iterate2).getFrom2(),
						Integer.toString(possibleMoveList.get(iterate2).getTo1())+Integer.toString(possibleMoveList.get(iterate2).getTo2())));
		}
		Collections.shuffle(moveListNotRec);
		
		
		
		possibleMoveList.removeAll(possibleMoveList);
		possibleMoveList.addAll(moveListRec);
		possibleMoveList.addAll(moveListNotRec);

		return possibleMoveList;
	}
		
	
	
	/**
	 * method to check and restore possible moves
	 * @param board board the moves should take place on
	 * @param movePos String with each position for the move
	 * @param turn the value for the move turn
	 * @param possibleMoveList the list with each possible move
	 */
	public void checkPossMoves2(Board board3, String movePos, int turn,List<Zug> possibleMoveList )  {
		
		int i1 = Character.getNumericValue(movePos.charAt(0));
		int j1 = Character.getNumericValue(movePos.charAt(1));	
		int x1 = Character.getNumericValue(movePos.charAt(2));
		int y1 = Character.getNumericValue(movePos.charAt(3));
		Figures restoreTo2 = board3.getField(x1,y1);
		Figures restoreFrom2=board3.getField(i1, j1);
		if (board3.getField(x1,y1)!=null){
			restoreTo2=board3.getField(x1, y1);
		}
		if (board3.getField(x1,y1)==null) {
			restoreTo2=null;
		}	
		if(turn!=color) {
			board3.setCurrentTurn(turn);
		}
		if (board3.getField(i1, j1).hasPossibleMove(board3,i1,j1,Integer.toString(x1)+Integer.toString(y1))) {
		
			Zug zug = new Zug(board3.getField(i1, j1),i1,j1,Integer.toString(x1)+Integer.toString(y1));
			possibleMoveList.add(zug);
			board3.setField(i1, j1, restoreFrom2);
		if (restoreTo2!=null) {
			board3.setField(x1, y1,restoreTo2);
		}
		if (restoreTo2==null) {
			board3.positionen[x1][y1]=null;
		}
		//board.setCurrentTurn(color);
	}
	}

	
	
	
	/**
	 * Main method for the minmax algorithm with alpha beta pruning
	 * @param board the current board
	 * @param desiredDepth the desired depth
	 * @param color the AI color
	 */
	public void lookAhead(Board board, int desiredDepth, int color) {
		
		this.color=color;
		bestMove= null;
		this.desiredDepth=desiredDepth;
		max(board,desiredDepth,
		                     -100000000, 1000000000);
		if (bestMove == null) {
		    this.recently=0;
		 }
		else {
			board.setCurrentTurn(color);
			this.recently=0;
			board.getField(bestMove.from1, bestMove.from2).move(board, bestMove.from1,bestMove.from2, Integer.toString(bestMove.to1)+Integer.toString(bestMove.to2)); 
		}
	}
	
	
	
	
	/**
	 * Max method for the minmax algorithm with alpha beta pruning
	 * @param board the current board
	 * @param depth the current depth
	 * @param alpha the alpha value
	 * @param beta the current beta value
	 * @return maxWert the current max Value
	 */
	 int max(Board board, int depth, int alpha, int beta) {
		 List<Zug> AImoves;
		 this.recently=0;
		 AImoves=findPossMovesAI(board,color);
	    if (depth == 0 || AImoves.size()==0) {
	       return (calculateValueFor(board,this.color)-calculateValueFor(board,1-this.color))*10;
	    }
	    int maxWert = alpha;
	    for (Zug move: AImoves){
	    	Figures restoreFromEn= board.copy(move.from1, move.from2);
			Figures restoreToEn=null;
			if (board.getField(move.to1, move.to2)!=null) {
				restoreToEn= board.copy(move.to1, move.to2);
			}
			
			board.positionen[move.to1][move.to2]=board.getField(move.from1,move.from2);
			board.positionen[move.from1][move.from2]=null;
			move.getFigure().setPos(move.to1, move.to2);

			int wert = min(board,depth-1,
	                      maxWert, beta);
			move.getFigure().setPos(move.from1, move.from2);
			board.positionen[move.from1][move.from2]=restoreFromEn;
			
			board.positionen[move.to1][move.to2]=restoreToEn;
			
			
	       if (wert > maxWert) {
	          maxWert = wert; 
	          if (depth == desiredDepth) { 
	             bestMove = move;
	          }
	          if (maxWert >= beta) {
	             break; 
	       }}
	    }
	    return maxWert;
	 }
	 
	 
	/**
	* Min method for the minmax algorithm with alpha beta pruning
	* @param board the current board
	* @param depth the current depth
	* @param alpha the alpha value
	* @param beta the current beta value
	* @return minWert the current min Value
	*/
	 int min(Board board,int depth,int alpha, int beta) {
		 List<Zug> PlayerMoves;
		 board.setCurrentTurn(1-this.color);
		this.recently=0;
		 PlayerMoves=findPossMovesAI(board,1-color);
		 
	    if (depth == 0 || PlayerMoves.size()==0) {
	    	return (calculateValueFor(board,this.color)-calculateValueFor(board,1-this.color))*10;
	    }
	    
	    int minWert = beta;
	   board.setCurrentTurn(1-this.color);
	    for  (Zug PlayerMove: PlayerMoves) {
	      	Figures restoreFromPl= board.copy(PlayerMove.from1, PlayerMove.from2);
	    			Figures restoreToPl=null;
	    			if (board.getField(PlayerMove.to1, PlayerMove.to2)!=null) {
	    				restoreToPl= board.copy(PlayerMove.to1, PlayerMove.to2);
	    			}
	    			
	    			board.positionen[PlayerMove.to1][PlayerMove.to2]=board.getField(PlayerMove.from1,PlayerMove.from2);
	    			board.positionen[PlayerMove.from1][PlayerMove.from2]=null;
	    			PlayerMove.getFigure().setPos(PlayerMove.to1, PlayerMove.to2);
	    			board.setCurrentTurn(color);
	       int wert = max(board,depth-1, alpha, minWert);
	       board.setCurrentTurn(color);
	       PlayerMove.getFigure().setPos(PlayerMove.from1, PlayerMove.from2);
	       board.positionen[PlayerMove.from1][PlayerMove.from2]=restoreFromPl;
			
			board.positionen[PlayerMove.to1][PlayerMove.to2]=restoreToPl;
	       if (wert < minWert) {
	          minWert = wert;
	          if (minWert <= alpha) {
	             break;
	       }
	    }}
	    return minWert;
	 }
	 



        
}

	

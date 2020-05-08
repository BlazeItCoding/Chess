package figures;

import game.Board;
import game.Figures;

public class Rook extends Figures {
	 private String boardVisual;
	 private int pos1;
	 private int pos2;
	 private String color;
	 private Boolean hasMoved;
	 
	public Rook(int pos1, int pos2, String color) {
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.color = color;
		this.type = 6;
	}
	public void setPos(int Pos1, int Pos2) { 
		this.pos1 = Pos1;
		this.pos2 = Pos2;
	}
	public String getBoardVisual() {
		if(this.color =="w") {
			this.boardVisual = "R";
			return boardVisual;
		}
		else if(this.color =="b") {
			this.boardVisual = "r";
			return boardVisual;
		}
		else {
			return boardVisual;
		}
			
	}
	
	public String getColor() {
		return color;
	}
	
	// possible move restriction for Rook
	public boolean validMove(Board board,int x, int y) {
		if(x < 0 || x > 7 || y < 0 || y > 7) {
			return false;
		}
		else if(this.pos1 == x && this.pos2 != y) {
			this.setPos(x,y);
			return true;
		}
		else if(this.pos1 != x && this.pos2 == y) {
			this.setPos(x,y);
			return true;
		}
		else return false;
	}
	
	
	
}


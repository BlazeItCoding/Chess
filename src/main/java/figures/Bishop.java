package figures;

import game.Figures;

public class Bishop extends Figures {
	 private String boardVisual;
	 private int pos1;
	 private int pos2;
	 private String color;
	 
	public Bishop(int pos1, int pos2, String color) {
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.color = color;
		this.type = 1;
	}
	
	public String getBoardVisual() {
		if(this.color =="w") {
			this.boardVisual = "B";
			return boardVisual;
		}
		else if(this.color =="b") {
			this.boardVisual = "b";
			return boardVisual;
		}
		else {
			return boardVisual;
		}
			
	}
	public void setPos(int Pos1, int Pos2) { 
		this.pos1 = Pos1;
		this.pos2 = Pos2;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean validMove(int x, int y) {
		
		if(x < 0 || x > 7 || y < 0 || y > 7) {
			return false;
		}
		
		else if(this.pos1 == x-7 && this.pos2 == y-7 || this.pos1 == x-6 && this.pos2 == y-6 || this.pos1 == x-5 && this.pos2 == y-5 || this.pos1 == x-4 && this.pos2 == y-4 || this.pos1 == x-3 && this.pos2 == y-3|| this.pos1 == x-2 && this.pos2 == y-2 || this.pos1 == x-1 && this.pos2 == y-1) {
			this.setPos(x,y);
			return true;
		}
		
		else if(this.pos1 == x+7 && this.pos2 == y+7 || this.pos1 == x+6 && this.pos2 == y+6 || this.pos1 == x+5 && this.pos2 == y+5 || this.pos1 == x+4 && this.pos2 == y+4 || this.pos1 == x+3 && this.pos2 == y+3|| this.pos1 == x+2 && this.pos2 == y+2 || this.pos1 == x+1 && this.pos2 == y+1) {
			this.setPos(x,y);
			return true;
		}
		
		else if(this.pos1 == x+7 && this.pos2 == y-7 || this.pos1 == x+6 && this.pos2 == y-6 || this.pos1 == x+5 && this.pos2 == y-5 || this.pos1 == x+4 && this.pos2 == y-4 || this.pos1 == x+3 && this.pos2 == y-3|| this.pos1 == x+2 && this.pos2 == y-2 || this.pos1 == x+1 && this.pos2 == y-1) {
			this.setPos(x,y);
			return true;
		}
		
		else if(this.pos1 == x-7 && this.pos2 == y+7 || this.pos1 == x-6 && this.pos2 == y+6 || this.pos1 == x-5 && this.pos2 == y+5 || this.pos1 == x-4 && this.pos2 == y+4 || this.pos1 == x-3 && this.pos2 == y+3|| this.pos1 == x-2 && this.pos2 == y+2 || this.pos1 == x-1 && this.pos2 == y+1) {
			this.setPos(x,y);
			return true;
		}
		
		else return false;
	}
	
}


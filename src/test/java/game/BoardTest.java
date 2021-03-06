package game;
import figures.Queen;
import figures.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
* Class for Board tests
* @author Martin Stuwe 676421
* @author Zeyi Sun
* @author Richard Tank
* @author Fin Niklas Tiedemann
* group 23
* it1
*/
public class BoardTest {
	/**
	* Test For Fields
	*/
	@Test
	public void testGetFieldSetField() {
		Board board = new Board();
		Rook R = new Rook(1,1,"b");
		board.setField(1, 1, R);
		assertEquals(R, board.getField(1, 1), "getField und setField funktionieren");
	}
	/**
	* Test For setNull
	*/
	@Test
	public void testSetNull() {
		Board board = new Board();
		Rook R = new Rook(1,1,"b");
		board.setField(1, 1, R);
		assertEquals(R, board.getField(1, 1), "getField und setField funktionieren");
		board.setNull(1, 1);
		assertEquals(null, board.getField(1, 1), "setNull funktioniert");
	}
	/**
	* Test For initializeBoard
	*/
	@Test
	public void testInitializeBoard() { 
		Board board = new Board();
		board.setStart();
		board.initializeBoard();
		for(int i =0; i<8;i++) {
			for(int y =0; y<8;y++) {
				
				if(board.positionen[i][y] == null) {
					board.positionenS[i][y] = " ";
					assertEquals(" ", board.positionenS[i][y], "leerzeichen in Feld");
				}
				
				else {
					assertEquals(board.positionen[i][y].getBoardVisual(), board.positionenS[i][y], "leerzeichen in Feld");
				}
			}
			
		}
	}
	/**
	* Test For move convert1
	*/
		@Test
		public void testConverMoveInput1() {
			Board board = new Board();
			board.setStart();
			assertEquals("07" ,board.ConvertMoveInput(board, 'a', 1) ,"test a1");
			assertEquals("17" ,board.ConvertMoveInput(board, 'b', 1) ,"test b1");
			assertEquals("27" ,board.ConvertMoveInput(board, 'c', 1) ,"test c1");
			assertEquals("37" ,board.ConvertMoveInput(board, 'd', 1) ,"test d1");

}
		/**
		* Test For move convert 2
		*/
		@Test
		public void testConverMoveInput2() {
			Board board = new Board();
			board.setStart();
			assertEquals("47" ,board.ConvertMoveInput(board, 'e', 1) ,"test e1");
			assertEquals("57" ,board.ConvertMoveInput(board, 'f', 1) ,"test f1");
			assertEquals("67" ,board.ConvertMoveInput(board, 'g', 1) ,"test g1");
			assertEquals("77" ,board.ConvertMoveInput(board, 'h', 1) ,"test h1");

}
		/**
		* Test For move convert 3
		*/
		@Test
		public void testConverMoveInput3() {
			Board board = new Board();
			board.setStart();

			assertEquals("420" ,board.ConvertMoveInput(board, 'q', 1) ,"test 420 1");
			assertEquals("420" ,board.ConvertMoveInput(board, 'a', 9) ,"test 420 2");
			assertEquals("420" ,board.ConvertMoveInput(board, 'a', 0) ,"test 420 3");
}
		/**
		* Test For currentTurn
		*/
		@Test
		public void testSetCurrentTurn() {
			Board board = new Board();
			board.setCurrentTurn(2);
			assertEquals(2, board.getCurrentTurn(), "getCurrentTurn und setCurrentTurn funktionieren");
			
		}
		/**
		* Test For checkCheck
		*/
		@Test
		public void testCheckCheck() {
			Board board = new Board();
			board.setStart();
			assertFalse( Zug.checkCheck(board), "checkCheck 1");
			board.setField(4, 1, new Queen(4,1,"w"));
			Zug.checkCheck(board);
			assertTrue( board.blackCheck, "checkCheck 2");
			board.setField(4, 6, new Queen(4,6,"b"));
			board.setNull(4, 1);
			Zug.checkCheck(board);

			assertTrue( board.whiteCheck, "checkCheck 2");
			
		}
		/**
		* Test For checkField
		*/
		@Test
		public void testCheckField() {
			Board board = new Board();
			board.setStart();
			assertFalse( Zug.checkField(board,0,0,"b"), "checkField 1");
			assertTrue( Zug.checkField(board,4,5,"b"), "checkField 2");
			assertFalse( Zug.checkField(board,4,5,"w"), "checkField 3");
			
			
		}
		/**
		* Test For checkPossibleMoves
		*/
		@Test
		public void testCheckPossibleMoves() {
			Board board = new Board();
			board.setStart();
			assertTrue( Zug.checkPossibleMoves(board), "checkField 1");
			board.setCurrentTurn(1);
			assertTrue( Zug.checkPossibleMoves(board), "checkField 2");
			board.setCurrentTurn(0);
			board.getField(4, 6).move(board, 4, 6, "44");
			board.getField(5, 1).move(board, 5, 1, "52");
			board.getField(5, 7).move(board, 5, 7, "46");
			board.getField(6, 1).move(board, 6, 1, "63");
			board.getField(4, 6).move(board, 4, 6, "73");
			assertFalse( Zug.checkPossibleMoves(board), "checkField 3");
			}
		/**
		* Test For draw
		*/
		@Test
		public void testCheckDraw() {
			Board board = new Board();
			board.setStart();
			assertFalse( Zug.checkDraw(board), "checkField 1");
			
			
			
		}
}
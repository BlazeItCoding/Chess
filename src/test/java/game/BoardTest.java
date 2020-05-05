package game;
import figures.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
	@Test
	public void testGetFieldSetField() {
		Board board = new Board();
		Rook R = new Rook(1,1,"b");
		board.setField(1, 1, R);
		assertEquals(R, board.getField(1, 1), "getField und setField funktionieren");
	}
	
}

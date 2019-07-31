import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class boardTest {

	@Test
	void defaultBoardTest() {
		Board newBoard = new Board();
		newBoard.boardMoveMake(2);
		newBoard.boardMoveMake(5);
		assertEquals(2, newBoard.latestPlayer());
		Move lMov = newBoard.latestMove();
		assertEquals(lMov.getPlayer(), 2);
		assertEquals(lMov.getColumn(), 5);
	}
	
	@Test
	void columnFullTest()
	{
			Board newBoard = new Board();
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			boolean retsVal = newBoard.columnIsFull(2);
			assertEquals(retsVal, true);
	}
	
	@Test
	void winnerFullTest()
	{
			Board newBoard = new Board();
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(3);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(3);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(3);
			newBoard.boardMoveMake(2);
			boolean retsVal = newBoard.checkForWinner();
			assertEquals(retsVal, true);
		
	}
	
	@Test
	void arrayListTest()
	{
			Board newBoard = new Board();
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			assertEquals(6, newBoard.getMoveList().size());
	}
	
	@Test
	void gameOn()
	{
			Board newBoard = new Board();
			newBoard.boardMoveMake(5);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(7);
			newBoard.boardMoveMake(1);
			newBoard.boardMoveMake(3);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(4);
			boolean retValue = newBoard.checkForWinner();
			boolean retsVal = newBoard.gameOver();
			assertEquals(retsVal, false);
			assertEquals(retValue, true);
	}
	
	@Test
	void addedToFullTest()
	{
			Board newBoard = new Board();
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(2);
			boolean retValue = newBoard.getAddedToFull();
			assertEquals(retValue, true);
	}
	
	@Test
	void upwardDiagonalTest()
	{
			Board newBoard = new Board();
			newBoard.boardMoveMake(2);
			newBoard.boardMoveMake(3);
			newBoard.boardMoveMake(3);
			newBoard.boardMoveMake(4);
			newBoard.boardMoveMake(4);
			newBoard.boardMoveMake(5);
			newBoard.boardMoveMake(4);
			newBoard.boardMoveMake(5);
			newBoard.boardMoveMake(5);
			boolean retValue = newBoard.checkForWinner();
			assertEquals(retValue, false);
	}
	
	@Test
	void horizontalTest()
	{
		Board newBoard = new Board();
		newBoard.boardMoveMake(2);
		newBoard.boardMoveMake(2);
		newBoard.boardMoveMake(3);
		newBoard.boardMoveMake(3);
		newBoard.boardMoveMake(4);
		newBoard.boardMoveMake(4);
		newBoard.boardMoveMake(5);
		boolean retValue = newBoard.checkForWinner();
		assertEquals(retValue, true);
	}
	
	@Test
	void latestPlayer()
	{
		Board newBoard = new Board();
		newBoard.boardMoveMake(2);
		int latPlayer = newBoard.latestPlayer();
		int listSize = newBoard.getMoveList().size();
		assertEquals(latPlayer, 1);
		assertEquals(listSize, 1);
	}


}

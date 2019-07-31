import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


class moveTest {

	@Test
	void defaultMoveTest() {
		Move a = new Move();
		Move b = new Move(-1, -1, 0);
		assertEquals("Default Test Row", a.getRow(), b.getRow());
		assertEquals("Default Test Column", a.getColumn(), b.getColumn());
		assertEquals("Default Test Player", a.getPlayer(), b.getPlayer());
	
	}
	
	@Test
	void setterTest()
	{
		Move a = new Move();
		Move b = new Move(4, 4, 1);
		a.setColumn(4);
		a.setRow(4);
		a.setPlayer(1);
		a = b;
		assertEquals("Setter Test Row", a.getRow(), b.getRow());
		assertEquals("Setter Test Column", a.getColumn(), b.getColumn());
		assertEquals("Setter Test Player", a.getPlayer(), b.getPlayer());
	}
	
	@Test 
	void getterTest()
	{
		Move a = new Move();
		assertEquals("Getter Test", a.getRow(), -1);
	}

}

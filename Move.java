
public class Move {

	private int rowNum;
	private int colNum;
	private int playerNum;
	
	public Move()
	{
		this.rowNum = -1;
		this.colNum = -1;
		this.playerNum = 0;	
	}
	
	public Move(int row, int col, int player)
	{
		this.rowNum = row;
		this.colNum = col;
		this.playerNum = player;
	}
	
	//Row getter and setter
	public int getRow() {
		return rowNum;
	}
	
	public void setRow(int row) {
		this.rowNum = row;
	}
	
	//Column getter and setter
	public int getColumn() {
		return colNum;
	}
	
	public void setColumn(int column) {
		this.colNum = column;
	}
	
	//Player getter and setter
	public int getPlayer() {
		return playerNum;
	}
	
	public void setPlayer(int player) {
		this.playerNum = player;
	}
	
}

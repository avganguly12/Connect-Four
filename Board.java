import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Board {

	/* Integers used to represent the 
	 * state of a particular spot on the
	 * board, with 0 referring to an empty
	 * spot, 1 referring to player one, 
	 * and two referring to player two
	 */
	public static final int EMPTY = 0;
	public static final int PLAYERONE = 1;
	public static final int PLAYERTWO = 2;

	/* This ArrayList stores the list of moves
	 * made. The parity of the length of the
	 * ArrayList will be used to make sure the
	 * players alternate turns. Moreover, this
	 * data structure supports the functionality of 
	 * the undo feature.
	 */
	private List<Move> moveList;
	
	//This integer represents zero, one, or two referring to the winner.
	private int gameWinner;
	
	
	//This 2-D array represents the board
	private int[][] cBoard;
	
	/*
	 * This boolean is true while the game runs and becomes false when
	 * either a player wins or the board is full
	 */
	private boolean gameOn;
	
	private boolean addedToFull = false;
	
	static JLayeredPane officialGameBoard;
	
	// constructor
	public Board() {
		
		this.moveList = new ArrayList<Move>();
		this.gameWinner = 0;
		this.cBoard = new int[6][7];
		for(int x = 0; x < 6; x++) 
		{
			for(int y = 0; y < 7; y++) 
			{
				cBoard[x][y] = EMPTY;
			}
		}
		this.gameOn = true;
	}
	
	public int getGameWinner() {
		return gameWinner;
	}
	
	
	public void setGameWinner(int winner) {
		this.gameWinner = winner;
	}
	
	public boolean gameOver() {
		return !gameOn;
	}
	
	public void setGameStatus(boolean gameStatus) {
		this.gameOn = gameStatus;
	}

	public ArrayList<Move> getMoveList() {
		ArrayList<Move> movList = (ArrayList<Move>) moveList;
		return movList;
	}
	
	public Move latestMove()
	{
		Move retMove = new Move(-1, -1, 2);
		
		if(getMoveList().size() > 0)
		{
			retMove = getMoveList().get(getMoveList().size()-1);
		}
		return retMove;
	}
	
	
	public boolean getAddedToFull()
	{
		return addedToFull;
	}
	
	public void setAddedToFull(boolean val)
	{
		this.addedToFull = val;
	}

	
	public void dropPiece(int player, int column) {
		try { 
			int row = -1;
				for (int rowNum = 0; rowNum < 6; rowNum++) {
					if (cBoard[rowNum][column] == EMPTY) {
						row = rowNum;
					}
				}
			Move newMove = new Move(row, column, player);
			moveList.add(newMove);
			this.cBoard[row][column] = player;
			checkForWinner();
		} catch (ArrayIndexOutOfBoundsException err) {
			setAddedToFull(true);
		}
	}
	
	public int latestPlayer()
	{
		int retPlayer = 1;
		if(getMoveList() != null)
		{
			retPlayer = latestMove().getPlayer();
		}
		return retPlayer;
	}
	
	// Makes the specified cell in the border empty and removes latest move from list
	public void undoDrop(int row, int col, int letter) {
		this.cBoard[row][col] = 0;
		if(moveList.size() != 0)
		{
			moveList.remove(moveList.size()-1);
		}
	}
	
	public boolean columnIsFull(int col) {
		boolean fullCol = true;
		if (cBoard[0][col] == EMPTY)
			fullCol = false;
		return fullCol;
	}
	
	public int getLastRow() {
		return latestMove().getRow();
	}
	
	public int getLastColumn() {
		return latestMove().getColumn();
	}
	
	public boolean checkForWinner()
	{
		boolean gameWon = false;
		setGameWinner(0);
		
		//Checks for horizontal pattern of four
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 4; y++) {
				if (
		cBoard[x][y] != EMPTY && cBoard[x][y] == cBoard[x][y+1]
		&& cBoard[x][y+1] == cBoard[x][y+2] && cBoard[x][y+2] == cBoard[x][y+3]) 
				{
					setGameWinner(cBoard[x][y]);
					gameWon = true;
				}
			}
		}
		
		//Checks for vertical pattern of four
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 6; y++) {
				if (
			cBoard[x][y] != EMPTY && cBoard[x][y] == cBoard[x+1][y] &&
			cBoard[x+1][y] == cBoard[x+2][y] && cBoard[x+2][y] == cBoard[x+3][y]) 
				{
					setGameWinner(cBoard[x][y]);
					gameWon = true;
				}
			}
		}
		
		//Checks for diagonal going up
		for(int x = 3; x <= 5; x++)
		{
			for(int y = 0; y <= 3; y++)
			{

				if(cBoard[x][y] != EMPTY)
					{
					if (cBoard[x][y] == cBoard[x-1][y+1] && cBoard[x-1][y+1] != EMPTY)
					{
						if(cBoard[x-1][y+1] == cBoard[x-2][y+2] && cBoard[x-2][y+2] != EMPTY)
						{
							if(cBoard[x-2][y+2] == cBoard[x-3][y+3] && cBoard[x-3][y+3] != EMPTY)
							{
								
									setGameWinner(cBoard[x][y]);
									gameWon = true;
							}
						}
					}

					}
			}
		}

		//Checks for diagonal going down
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				if(
		cBoard[x][y] != EMPTY && cBoard[x][y] == cBoard[x+1][y+1]
		&& cBoard[x+1][y+1] == cBoard[x+2][y+2] && cBoard[x+2][y+2] == cBoard[x+3][y+3])
				{
					setGameWinner(cBoard[x][y]);
					gameWon = true;
				}
			}
		}
		
		int count = 0;
		
		for(int i = 0; i < 7; i++)
		{
			if(columnIsFull(i))
			{
				count++;
			}
		}
		
		if(count == 7)
		{
			gameWon = true;
		}
			
				return gameWon;
	}
	
	// the display for the official game board the user sees
	public static JLayeredPane createOfficialGameBoard() {
		officialGameBoard = new JLayeredPane();
		officialGameBoard.setPreferredSize(new Dimension(600, 500));
		ImageIcon connectBoard = new ImageIcon("images/Board.png");
		JLabel newBoardLabel = new JLabel(connectBoard);
		newBoardLabel.setBounds(30, 30, connectBoard.getIconWidth(), connectBoard.getIconHeight());
		officialGameBoard.add(newBoardLabel, 0, 2);
		return officialGameBoard;
	}
	
	public void boardMoveMake(int column) {
		if (latestPlayer() == PLAYERTWO) {
			dropPiece(PLAYERONE, column);
		} else {
			dropPiece(PLAYERTWO, column);
		}
		
		if (getAddedToFull()) {
			getMoveList().remove(getMoveList().size()-1);
		}
	}
	
	public int[][] getBoardStatus()
	{
		int[][] boardCopy = new int[6][7];
		for(int i = 0; i < 6; i++) 
		{
			for(int j = 0; j < 7; j++) 
			{
				boardCopy[i][j] = cBoard[i][j];
			}
		}
		return boardCopy;
	}
	

}

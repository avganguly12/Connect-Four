import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.*;

public class Game implements Runnable {
	
	static Board aveeksGameBoard = new Board();
	static JFrame connectFourGUI;
	static JFrame beginningPopup;
	static JFrame secondPopup;
	static JFrame endingPopup;
	static JPanel buttonPanel;
	static JLayeredPane officialGameBoard;
	public static JLabel pieceVisual = null;

	@Override
	public void run() {
		runGame();
		connectFourGUI.setVisible(false);
		beginningPopup = new JFrame("Instructions");
		JPanel beginGameDisplay = new JPanel(new BorderLayout());
		beginningPopup.setBounds(20, 200, 1000, 80);
		JLabel instructionLabel = new JLabel("Welcome to my Connect Four Game! To play, press the key corresponding to the column of the board you want to drop the object into.");
		beginGameDisplay.add(instructionLabel);
		JButton next = new JButton("next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beginningPopup.setVisible(false);
					secondPopup = new JFrame("Instruction continued");
					JPanel secondGameDisplay = new JPanel(new BorderLayout());
					secondPopup.setBounds(20, 200, 1000, 80);
					JLabel instructionLabelTwo = new JLabel("To win the game, you need four in a row (horizontal or vertical) or four aligned diagonally (ascending or descending). Have fun!");
					secondGameDisplay.add(instructionLabelTwo);
					JButton newNext = new JButton("next");
					newNext.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							secondPopup.setVisible(false);
							runGame();
						}
					});
					secondGameDisplay.add(newNext, BorderLayout.SOUTH);
					secondPopup.getContentPane().add(secondGameDisplay, BorderLayout.CENTER);
					secondPopup.setResizable(true);
					secondPopup.setVisible(true);
			}
		});
		
		beginGameDisplay.add(next, BorderLayout.SOUTH);
		beginningPopup.getContentPane().add(beginGameDisplay, BorderLayout.CENTER);

		beginningPopup.setResizable(true);
		beginningPopup.setVisible(true);
		
	}

	public static void runGame() {
		
		if (connectFourGUI != null) 
			{
			   connectFourGUI.dispose();
			}
	
		
		connectFourGUI = new JFrame("Aveek's Connect Four Game");
		connectFourGUI.getContentPane().add(createPanelComponents(), BorderLayout.CENTER);
	
		connectFourGUI.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent exit) {
			System.exit(1);
			}
		});

		connectFourGUI.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				String userInput = KeyEvent.getKeyText(e.getKeyCode());
				ArrayList<String> dropList = new ArrayList<String>();
				dropList.add("1");
				dropList.add("2");
				dropList.add("3");
				dropList.add("4");
				dropList.add("5");
				dropList.add("6");
				dropList.add("7");
				if (dropList.contains(userInput)) 
				{
					if (!aveeksGameBoard.getAddedToFull()) 
					{
						aveeksGameBoard.boardMoveMake(Integer.parseInt(userInput)-1);
						gameRunner();
					} 
					else 
					{
						aveeksGameBoard.setAddedToFull(false);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		
		connectFourGUI.setFocusable(true);
		connectFourGUI.pack();
		connectFourGUI.setVisible(true);

	}
		
	public static void gameRunner()
	{
		int row = aveeksGameBoard.getLastRow();
		int column = aveeksGameBoard.getLastColumn();
		
		if (aveeksGameBoard.latestPlayer() == 1) {
			ImageIcon pieceCircle = new ImageIcon("images/RED.png");
			pieceVisual = new JLabel(pieceCircle);
			pieceVisual.setBounds(37 + 75 * column, 37 + 75 * row, pieceCircle.getIconWidth(), pieceCircle.getIconHeight());
			officialGameBoard.add(pieceVisual, 2, 2);
			connectFourGUI.paint(connectFourGUI.getGraphics());
		} else if (aveeksGameBoard.latestPlayer() == 2) {
			ImageIcon pieceCircle = new ImageIcon("images/YELLOW.png");
			pieceVisual = new JLabel(pieceCircle);
			pieceVisual.setBounds(37 + 75 * column, 37 + 75 * row, pieceCircle.getIconWidth(), pieceCircle.getIconHeight());
			officialGameBoard.add(pieceVisual, 2, 2);
			connectFourGUI.paint(connectFourGUI.getGraphics());
		} 
		if (aveeksGameBoard.checkForWinner()) {
			aveeksGameBoard.setGameStatus(false);
	        
			endingPopup = new JFrame("GAME OVER!");

			JPanel endGameDisplay = new JPanel(new BorderLayout());

			JLabel finalWinLabel;
			if (aveeksGameBoard.getGameWinner() == Board.PLAYERONE) {
				endingPopup.setBounds(370, 380, 551, 80);
				JLabel redWinLabel = new JLabel("Player Red won the game! To rematch, please run the game again. Thanks for playing! :)");
				finalWinLabel = redWinLabel;
				endGameDisplay.add(finalWinLabel);
			} else if (aveeksGameBoard.getGameWinner() == Board.PLAYERTWO) {
				endingPopup.setBounds(370, 380, 570, 80);
				JLabel yellowWinLabel = new JLabel("Player Yellow won the game! To rematch, please run the game again. Thanks for playing! :)");
				finalWinLabel = yellowWinLabel;
				endGameDisplay.add(finalWinLabel);
			} else {
				endingPopup.setBounds(370, 380, 460, 80);
				JLabel drawLabel = new JLabel("It's a draw! To rematch, please run the game again. Thanks for playing! :)");
				finalWinLabel = drawLabel;
				endGameDisplay.add(finalWinLabel);
			}

			endGameDisplay.add(finalWinLabel, BorderLayout.CENTER);

			endingPopup.getContentPane().add(endGameDisplay, BorderLayout.CENTER);
			endingPopup.setResizable(false);
			endingPopup.setVisible(true);
			
			endingPopup.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent exit) {
				System.exit(1);
				}
			});
		}
	}
	
	
	public static Component createPanelComponents() {
		buttonPanel = new JPanel();
	
		JButton undo_button = new JButton("UNDO");
		undo_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
		        aveeksGameBoard.undoDrop(aveeksGameBoard.getLastRow(), aveeksGameBoard.getLastColumn(), aveeksGameBoard.latestPlayer());
				officialGameBoard.remove(pieceVisual);
				connectFourGUI.paint(connectFourGUI.getGraphics());
				if (!aveeksGameBoard.getAddedToFull()) {
					gameRunner();
				}
				connectFourGUI.requestFocusInWindow();
				}catch(ArrayIndexOutOfBoundsException ERR)
				{
					System.out.println("Cannot undo past one move");
					runGame();
				}
			}
		});
		
		JButton save_button = new JButton("SAVE");
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		write("gameSaver.ase", new int[6][7]);
					}
					catch (IOException ie)
					{
						System.out.println("Input Output Exception");
					}

				connectFourGUI.paint(connectFourGUI.getGraphics());
		        gameRunner();
				connectFourGUI.requestFocusInWindow();

			}
		});
		
		buttonPanel.add(undo_button);
		buttonPanel.add(save_button);

		officialGameBoard = Board.createOfficialGameBoard();
		JPanel panelMain = new JPanel();
		panelMain.add(buttonPanel, BorderLayout.NORTH);
		panelMain.add(officialGameBoard, BorderLayout.CENTER);
		return panelMain;
	}
	
	public static void write (String filename, int[][]theBoard) throws IOException{
		  try(BufferedWriter outputWriter = new BufferedWriter(new FileWriter(filename)))
		  {
			  for (int i = 0; i < 6; i++) {
				  for(int j = 0; j < 7; j++)
				  {
					  outputWriter.write(theBoard[i][j]);
				  }
			  }
			  outputWriter.flush();
			  outputWriter.close();  
		  }
		  catch(IOException e)
		  {
			  e.printStackTrace();
		  }
		}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Game());
	}		
}
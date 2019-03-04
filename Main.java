import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GameHandler {
	JFrame gameFrame;
	JPanel buttonPanel;
	JButton restartBtn;
	static int[][] gameMatrix;
	static int currTurn=0;

	class GameButton extends JButton {
		int rowPlace,colPlace,playerValue;

		public GameButton(int rowPlace,int colPlace) {
			setPreferredSize(new Dimension(70,70));
			this.rowPlace=rowPlace;
			this.colPlace=colPlace;
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					GameHandler.currTurn++;
					String playerSym="X";
					playerValue=1;
					if(GameHandler.currTurn%2==0) {
						playerSym="O";
						playerValue=2;
					}
					setText(playerSym);
					setFont(new Font("Arial",Font.BOLD,35));
					GameHandler.gameMatrix[rowPlace][colPlace]=playerValue;
					if(GameHandler.checkRow(rowPlace) || GameHandler.checkCol(colPlace) || GameHandler.checkDiag()) {
						JOptionPane.showMessageDialog(null,"Player " + playerValue + " has won the game","Victory",JOptionPane.DEFAULT_OPTION);
						GameHandler.this.gameFrame.dispose();
						new GameHandler();
					}
					if(GameHandler.currTurn>=9) {
							JOptionPane.showMessageDialog(null,"Game has tied","Game Over",JOptionPane.DEFAULT_OPTION);
							GameHandler.this.gameFrame.dispose();
							new GameHandler();
					}
					setEnabled(false);
				}
			});
		}
	}

	void initMatrix() {
		gameMatrix=new int[3][];
		for(int i=0;i<3;i++) {
			gameMatrix[i]=new int[3];
			for(int j=0;j<3;j++) {
				gameMatrix[i][j]=0;
			}
		}
	}

	void initFrame() {
		gameFrame=new JFrame("Tic-Tac-Toe");
		gameFrame.setLayout(new BoxLayout(gameFrame.getContentPane(),BoxLayout.Y_AXIS));
		gameFrame.add(new JLabel("Player 1 - X | Player 2 - O"));
		gameFrame.add(buttonPanel);
		gameFrame.add(restartBtn);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
	}

	static boolean checkRow(int row) {
		if(GameHandler.gameMatrix[row][0]==GameHandler.gameMatrix[row][1] && GameHandler.gameMatrix[row][1]==GameHandler.gameMatrix[row][2]) {
			return true;
		}
		return false;
	}

	static boolean checkCol(int col) {
		if(GameHandler.gameMatrix[0][col]==GameHandler.gameMatrix[1][col] && GameHandler.gameMatrix[1][col]==GameHandler.gameMatrix[2][col]) {
			return true;
		}
		return false;
	}

	static boolean checkDiag() {
		if(GameHandler.gameMatrix[0][0]!=0 && GameHandler.gameMatrix[0][0]==GameHandler.gameMatrix[1][1] && GameHandler.gameMatrix[1][1]==GameHandler.gameMatrix[2][2]) {
			return true;
		}
		if(GameHandler.gameMatrix[0][2]!=0 && GameHandler.gameMatrix[0][2]==GameHandler.gameMatrix[1][1] && GameHandler.gameMatrix[1][1]==GameHandler.gameMatrix[2][0]) {
			return true;
		}
		return false;
	}
	public GameHandler() {
		int i,j;
		initMatrix();
		currTurn=0;
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridLayout(3,3));
		GameButton gbtn[]=new GameButton[9];
		int btnCount=0;
		for(i=0;i<3;i++) {
			for(j=0;j<3;j++) {
				gbtn[btnCount]=new GameButton(i,j);
				buttonPanel.add(gbtn[btnCount]);
				btnCount++;
			}
		}
		restartBtn=new JButton("Restart game");
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int confirm=JOptionPane.showConfirmDialog(null,"Sure to quit current game and start over?","Restart?",JOptionPane.YES_NO_OPTION);
				if(confirm==JOptionPane.YES_OPTION) {
					gameFrame.dispose();
					new GameHandler();
				}
				else
					return;
			}
		});
		initFrame();
	}

	public static void main(String args[]) {
		new GameHandler();
	}
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToe extends JFrame implements ActionListener{
	JButton a,b,c,d,e,f,g,h,i,restart;
	JPanel gridPanel;
	int turn=1;
	int[][] gameMatrix;
	void setGrid() {
		a=new JButton("");
		a.setPreferredSize(new Dimension(70,70));
		a.addActionListener(this);
		b=new JButton("");
		b.addActionListener(this);
		c=new JButton("");
		c.addActionListener(this);
		d=new JButton("");
		d.addActionListener(this);
		e=new JButton("");
		e.addActionListener(this);
		f=new JButton("");
		f.addActionListener(this);
		g=new JButton("");
		g.addActionListener(this);
		h=new JButton("");
		h.addActionListener(this);
		i=new JButton("");
		i.addActionListener(this);
	}
	void addGrid() {
		gridPanel=new JPanel();
		gridPanel.setLayout(new GridLayout(3,3));
		gridPanel.add(a);
		gridPanel.add(b);
		gridPanel.add(c);
		gridPanel.add(d);
		gridPanel.add(e);
		gridPanel.add(f);
		gridPanel.add(g);
		gridPanel.add(h);
		gridPanel.add(i);
		add(gridPanel);
	}
	void setMatrix() {
		gameMatrix=new int[3][];
		gameMatrix[0]=new int[3];
		gameMatrix[1]=new int[3];
		gameMatrix[2]=new int[3];
		for(int j=0;j<3;j++) {
			for(int k=0;k<3;k++) {
				gameMatrix[j][k]=0;
			}
		}
	}
	void validateGame() {
		int win=0,j,k;
		for(j=0;j<3;j++) {
			if(gameMatrix[j][0]==gameMatrix[j][1] && gameMatrix[j][0]==gameMatrix[j][2]) {
				win=gameMatrix[j][0];
			}
		}
		for(k=0;k<3;k++) {
			if(gameMatrix[0][k]==gameMatrix[1][k] && gameMatrix[0][k]==gameMatrix[2][k]) {
				win=gameMatrix[k][0];
			}
		}
		if(gameMatrix[0][0]==gameMatrix[1][1] && gameMatrix[0][0]==gameMatrix[2][2]) {
			win=gameMatrix[0][0];
		}
		if(gameMatrix[0][2]==gameMatrix[1][1] && gameMatrix[0][2]==gameMatrix[2][0]) {
			win=gameMatrix[0][2];
		}
		if(win!=0) {
			JOptionPane.showMessageDialog(null,"Player " + win + " has won the game","Game Over",JOptionPane.DEFAULT_OPTION);
			dispose();
			new TicTacToe();
		}
	}
	void turnsOver() {
		int win=0,j,k;
		for(j=0;j<3;j++) {
			if(gameMatrix[j][0]==gameMatrix[j][1] && gameMatrix[j][0]==gameMatrix[j][2]) {
				win=gameMatrix[j][0];
			}
		}
		for(k=0;k<3;k++) {
			if(gameMatrix[0][k]==gameMatrix[1][k] && gameMatrix[0][k]==gameMatrix[2][k]) {
				win=gameMatrix[k][0];
			}
		}
		if(gameMatrix[0][0]==gameMatrix[1][1] && gameMatrix[0][0]==gameMatrix[2][2]) {
			win=gameMatrix[0][0];
		}
		if(gameMatrix[0][2]==gameMatrix[1][1] && gameMatrix[0][2]==gameMatrix[2][0]) {
			win=gameMatrix[0][2];
		}
		if(win!=0) {
			JOptionPane.showMessageDialog(null,"Player " + win + " has won the game","Game Over",JOptionPane.DEFAULT_OPTION);
		}
		else {
			JOptionPane.showMessageDialog(null,"Game has tied","Game Over",JOptionPane.DEFAULT_OPTION);
		}
		dispose();
		new TicTacToe();
	}
	public TicTacToe() {
		setMatrix();
		setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		setGrid();
		add(new JLabel("Player 1 - X | Player 2 - O"));
		addGrid();
		restart=new JButton("Restart game");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int confirm=JOptionPane.showConfirmDialog(null,"Sure to quit current game and start over?","Restart?",JOptionPane.YES_NO_OPTION);
				if(confirm==JOptionPane.YES_OPTION) {
					dispose();
					new TicTacToe();
				}
				else
					return;
			}
		});
		add(restart);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	public void actionPerformed(ActionEvent ae) {
		Object pressed=ae.getSource();
		String turnPrint="X";
		int turnFlag=1;
		if(turn%2==0) {
			turnPrint="O";
			turnFlag=2;
		}
		if(pressed==a && a.getText().isEmpty()) {
			a.setText(turnPrint);
			gameMatrix[0][0]=turnFlag;
			turn++;
		}
		else if(pressed==b && b.getText().isEmpty()) {
			b.setText(turnPrint);
			gameMatrix[0][1]=turnFlag;
			turn++;
		}
		else if(pressed==c && c.getText().isEmpty()) {
			c.setText(turnPrint);
			gameMatrix[0][2]=turnFlag;
			turn++;
		}
		else if(pressed==d && d.getText().isEmpty()) {
			d.setText(turnPrint);
			gameMatrix[1][0]=turnFlag;
			turn++;
		}
		else if(pressed==e && e.getText().isEmpty()) {
			e.setText(turnPrint);
			gameMatrix[1][1]=turnFlag;
			turn++;
		}
		else if(pressed==f && f.getText().isEmpty()) {
			f.setText(turnPrint);
			gameMatrix[1][2]=turnFlag;
			turn++;
		}
		else if(pressed==g && g.getText().isEmpty()) {
			g.setText(turnPrint);
			gameMatrix[2][0]=turnFlag;
			turn++;
		}
		else if(pressed==h && h.getText().isEmpty()) {
			h.setText(turnPrint);
			gameMatrix[2][1]=turnFlag;
			turn++;
		}
		else if(pressed==i && i.getText().isEmpty()) {
			i.setText(turnPrint);
			gameMatrix[2][2]=turnFlag;
			turn++;
		}
		validateGame();
		if(turn==10) {
		turnsOver();
		}
	}
	public static void main(String args[]) {
		new TicTacToe();
	}
}
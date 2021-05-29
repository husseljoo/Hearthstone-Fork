package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class GameOver extends JLayeredPane {

	
	 
	private JPanel gameFinished; 
	private JLabel gameOverSign;
	private JLabel winnerName;
	private JButton playAgainB;
	
	
	public GameOver() {
		setOpaque(true);
		setSize(1920,1080);	
		setBackground(new Color(127,0,255));
		
		
		
		int gameFinishedSignX=((Double)(getWidth()*0.0625)).intValue();
		int gameFinishedSignY=((Double)(getHeight()*0.06)).intValue();
		int gameFinishedSignWidth=((Double)(getWidth()*0.53)).intValue();
		int gameFinishedSignHeight=((Double)(getHeight()*0.264)).intValue();
		
		
		
		
		gameFinished = new JPanel();
		
		gameFinished.setFont(new Font("Tahoma", Font.PLAIN, 35));
		gameFinished.setOpaque(true);
		gameFinished.setBackground(Color.GREEN);
		gameFinished.setLayout(new GridLayout(2,1));
		gameFinished.setBounds(gameFinishedSignX,gameFinishedSignY,gameFinishedSignWidth,gameFinishedSignHeight);
		add(gameFinished);
		
		
		gameOverSign=new JLabel();
		gameOverSign.setFont(new Font("Tahoma", Font.BOLD, 70));
		gameOverSign.setOpaque(true);
		gameOverSign.setBackground(Color.GREEN);
		gameOverSign.setText("GAME OVER");
		gameOverSign.setHorizontalAlignment(SwingConstants.CENTER);
		gameFinished.add(gameOverSign);
		
		winnerName=new JLabel();
		winnerName.setOpaque(true);
		winnerName.setBackground(Color.MAGENTA);
		winnerName.setFont(new Font("Tahoma", Font.BOLD, 50));
		winnerName.setHorizontalAlignment(SwingConstants.CENTER);
		gameFinished.add(winnerName);
		
		
		
		
		
		
		int playAgainBX=((Double)(getWidth()*0.18)).intValue();
		int playAgainBY=((Double)(getHeight()*0.4)).intValue();
		int playAgainBWidth=((Double)(getWidth()*0.27)).intValue();
		int playAgainBHeight=((Double)(getHeight()*0.14)).intValue();
		
		
		
		
		
		
		
		
		
		playAgainB = new JButton("Play Again");
		playAgainB.setOpaque(true);
		playAgainB.setBackground(Color.CYAN);
		playAgainB.setFont(new Font("Tahoma", Font.PLAIN, 40));
		playAgainB.setBounds(playAgainBX, playAgainBY, playAgainBWidth, playAgainBHeight);
		add(playAgainB);
		playAgainB.setActionCommand("playAgainB");
		
		
		
		
		
		
		
		
		
		
		
	}	
	
	
	
	
	public JLabel getGameOverSign() {
		return gameOverSign;
	}


	public void setGameOverSign(JLabel gameOverSign) {
		this.gameOverSign = gameOverSign;
	}


	public JLabel getWinnerName() {
		return winnerName;
	}


	public void setWinnerName(JLabel winnerName) {
		this.winnerName = winnerName;
	}





	public JButton getPlayAgainB() {
		return playAgainB;
	}




	public void setPlayAgainB(JButton playAgainB) {
		this.playAgainB = playAgainB;
	}




	public static void main(String[] args) {
		
		
		JFrame f=new JFrame();
		
		GameOver g=new GameOver();
		f.setContentPane(g);
		
		
		
		f.setVisible(true);
		
		
		
	}
}

	
	
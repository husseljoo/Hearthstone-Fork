package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameView extends JFrame {


	public static void main(String[] args) {
		
	}

	public GameView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		setSize(1920,1080);
		
		
		setTitle("Select your Heroes");
		revalidate();
		repaint();
		setVisible(true);
		
	}

}

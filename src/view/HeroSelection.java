package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.Controller;

public class HeroSelection extends JLayeredPane {

	
	private Controller controller;
	
	private JLabel player1Text;
	private JLabel player2Text;
	private JPanel player1Selection;;
	private JPanel player2Selection;
	
	private JButton warlock;
	private JButton mage;
	private JButton hunter;
	private JButton paladin;
	private JButton priest;
	private JButton warlock2;
	private JButton mage2;
	private JButton hunter2;
	private JButton paladin2;
	private JButton priest2;
	private JButton startGame;

	
	private ArrayList<JButton> player1Buttons = new ArrayList<JButton>();
	private ArrayList<JButton> player2Buttons = new ArrayList<JButton>();
	
	public HeroSelection() {
		setSize(1920,1080);	
		
		
		setOpaque(true);
		setBackground(new Color(255,0,127));
		
		player1Selection = new JPanel();
		player1Selection.setBorder(new LineBorder(Color.BLUE, 5));
		
		int player1X=((Double)(getWidth()*0.07)).intValue();
		int player1Y=((Double)(getHeight()*0.15)).intValue();;
		int player1Width=((Double)(getWidth()*0.21)).intValue();
		int player1Height=((Double)(getHeight()*0.45)).intValue();
		
		player1Selection.setBounds(player1X, player1Y, player1Width,player1Height);
		add(player1Selection);
		player1Selection.setBackground(Color.WHITE);
		player1Selection.setLayout(new GridLayout(5,1));
		
		
	    player2Selection = new JPanel();
		player2Selection.setBorder(new LineBorder(Color.BLUE, 5));
		
		int player2X=((Double)(getWidth()*0.38)).intValue();
		int player2Y=((Double)(getHeight()*0.15)).intValue();;
		int player2Width=((Double)(getWidth()*0.21)).intValue();
		int player2Height=((Double)(getHeight()*0.45)).intValue();
		
		
		player2Selection.setBounds(player2X, player2Y,player2Width,player2Height);
		add(player2Selection);
		player2Selection.setBackground(Color.WHITE);
		player2Selection.setLayout(new GridLayout(5,1));
		
		
		
		
		
		int player1TextX=((Double)(getWidth()*0.07)).intValue();
		int player2TextX=((Double)(getWidth()*0.38)).intValue();
		
		int playerY=((Double)(getHeight()*0.07)).intValue();;
		int playerWidth=((Double)(getWidth()*0.21)).intValue();
		int playerHeight=((Double)(getHeight()*0.06)).intValue();
		
		
		
		
		player1Text = new JLabel();
		player1Text.setFont(new Font("Tahoma", Font.PLAIN, 35));
		player1Text.setOpaque(true);
		player1Text.setBackground(Color.LIGHT_GRAY);
		player1Text.setText("PLAYER 1");
		player1Text.setHorizontalAlignment(SwingConstants.CENTER);
		
		player1Text.setBounds(player1TextX,playerY,playerWidth,playerHeight);
		add(player1Text);
		
		
		player2Text = new JLabel();
		player2Text.setFont(new Font("Tahoma", Font.PLAIN, 35));
		player2Text.setOpaque(true);
		player2Text.setBackground(Color.LIGHT_GRAY);
		player2Text.setText("PLAYER 2");
		player2Text.setHorizontalAlignment(SwingConstants.CENTER);
		
		player2Text.setBounds(player2TextX,playerY,playerWidth,playerHeight);
		add(player2Text);
		
		
		 startGame=new JButton("Start Game");
		 startGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		 
		 int startGameX=((Double)(getWidth()*0.29)).intValue();
		 int startGameY=((Double)(getHeight()*0.32)).intValue();;
		 int startGameWidth=((Double)(getWidth()*0.08)).intValue();
		 int startGameHeight=((Double)(getHeight()*0.08)).intValue();
		 
		 startGame.setBounds(startGameX, startGameY, startGameWidth, startGameHeight);
		 startGame.setBackground(Color.GREEN);
		 
		 add(startGame);
		 
		 
		 addHeroButtons();
		
		 repaint();
		 revalidate(); 
		
		 setVisible(true);
	}

	
	public void addHeroButtons() {
		
		 warlock = new JButton("Warlock");
		 warlock.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 mage = new JButton("Mage");
		 mage.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 hunter = new JButton("Hunter");
		 hunter.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 paladin = new JButton("Paladin");
		 paladin.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 priest = new JButton("Priest");
		 priest.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		
		 warlock2 = new JButton("Warlock");
		 warlock2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 mage2 = new JButton("Mage");
		 mage2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 hunter2 = new JButton("Hunter");
		 hunter2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 paladin2 = new JButton("Paladin");
		 paladin2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 priest2 = new JButton("Priest");
		 priest2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		 
		
		
	
		warlock.addActionListener(controller);
		mage.addActionListener(controller);
		hunter.addActionListener(controller);
		paladin.addActionListener(controller);
		priest.addActionListener(controller);
		
		warlock2.addActionListener(controller);
		mage2.addActionListener(controller);
		hunter2.addActionListener(controller);
		paladin2.addActionListener(controller);
		priest2.addActionListener(controller);
		
		startGame.addActionListener(controller);


		player1Selection.add(warlock);
		player1Selection.add(mage);
		player1Selection.add(hunter);
		player1Selection.add(paladin);
		player1Selection.add(priest);
		
		player2Selection.add(warlock2);
		player2Selection.add(mage2);
		player2Selection.add(hunter2);
		player2Selection.add(paladin2);
		player2Selection.add(priest2);
		
		player1Buttons.add(warlock);
		player1Buttons.add(mage);
		player1Buttons.add(hunter);
		player1Buttons.add(paladin);
		player1Buttons.add(priest);
		
		player2Buttons.add(warlock2);
		player2Buttons.add(mage2);
		player2Buttons.add(hunter2);
		player2Buttons.add(paladin2);
		player2Buttons.add(priest2);
		
		
		}




	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}


	public JButton getWarlock() {
		return warlock;
	}


	public void setWarlock(JButton warlock) {
		this.warlock = warlock;
	}


	public JButton getMage() {
		return mage;
	}


	public void setMage(JButton mage) {
		this.mage = mage;
	}


	public JButton getHunter() {
		return hunter;
	}


	public void setHunter(JButton hunter) {
		this.hunter = hunter;
	}


	public JButton getPaladin() {
		return paladin;
	}


	public void setPaladin(JButton paladin) {
		this.paladin = paladin;
	}


	public JButton getPriest() {
		return priest;
	}


	public void setPriest(JButton priest) {
		this.priest = priest;
	}


	public JButton getWarlock2() {
		return warlock2;
	}


	public void setWarlock2(JButton warlock2) {
		this.warlock2 = warlock2;
	}


	public JButton getMage2() {
		return mage2;
	}


	public void setMage2(JButton mage2) {
		this.mage2 = mage2;
	}


	public JButton getHunter2() {
		return hunter2;
	}


	public void setHunter2(JButton hunter2) {
		this.hunter2 = hunter2;
	}


	public JButton getPaladin2() {
		return paladin2;
	}


	public void setPaladin2(JButton paladin2) {
		this.paladin2 = paladin2;
	}


	public JButton getPriest2() {
		return priest2;
	}


	public void setPriest2(JButton priest2) {
		this.priest2 = priest2;
	}


	public JButton getStartGame() {
		return startGame;
	}


	public void setStartGame(JButton startGame) {
		this.startGame = startGame;
	}


	public ArrayList<JButton> getPlayer1Buttons() {
		return player1Buttons;
	}


	public void setPlayer1Buttons(ArrayList<JButton> player1Buttons) {
		this.player1Buttons = player1Buttons;
	}


	public ArrayList<JButton> getPlayer2Buttons() {
		return player2Buttons;
	}


	public void setPlayer2Buttons(ArrayList<JButton> player2Buttons) {
		this.player2Buttons = player2Buttons;
	}


	public void setPlayer1Selection(JPanel player1Selection) {
		this.player1Selection = player1Selection;
	}


	public void setPlayer2Selection(JPanel player2Selection) {
		this.player2Selection = player2Selection;
	}


	public JPanel getPlayer1Selection() {
		return player1Selection;
	}





	public JPanel getPlayer2Selection() {
		return player2Selection;
	}




	public static void main(String[] args) {
		HeroSelection b=new HeroSelection();
	}
}

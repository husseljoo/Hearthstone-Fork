package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.Controller;




public class Brett extends JLayeredPane {

	private Controller controller;
	
	private JLayeredPane opposingHand;
	private JPanel opposingField;
	private JPanel currentPlayerHand;
	private JPanel currentPlayerField;
	
	private JPanel sidePart;
	
	
	private JLabel opponentHeroDeck;
	private JLabel opponentCards;
	private JLabel opponentHP;
	private JLabel opponentMana;
	private JLabel selectTargetPopUp;
	
	
	private JButton endTurnB;
	private JButton useHeroPowerB;
	
	
	private JButton currentHeroNameB;
	private JButton opponentHeroNameB;
	
	private JLabel currentHeroHPLabel;
	private JLabel currentHeroManaLabel;
	private JLabel currentHeroDeckLabel;
	
	private JPanel burnedCard;
	
	public static final Color LIGHT_BROWN = new Color(153,76,0);

	public Brett() {
		
		
		setSize(1920,1080);
		
		setOpaque(true);
		setBackground(new Color(102,51,0));
		
		sidePart = new JPanel();
		sidePart.setBorder(new LineBorder(Color.DARK_GRAY, 3));
		sidePart.setLayout(new GridLayout(7,1));
		
		
		int sidePartX=((Double)(getWidth()*0.55)).intValue();
		int sidePartY=((Double)(getHeight()*0.017)).intValue();;
		int sidePartWidth=((Double)(getWidth()*0.11)).intValue();
		int sidePartHeight=((Double)(getHeight()*0.40)).intValue();
		
		sidePart.setBounds(sidePartX, sidePartY, sidePartWidth, sidePartHeight);
		add(sidePart);
		
		
		
		int burnedCardX=((Double)(getWidth()*0.208)).intValue();
		int burnedCardXburnedCardX=((Double)(getHeight()*0.124)).intValue();;
		int burnedCardWidth=((Double)(getWidth()*0.14)).intValue();
		int burnedCardHeight=((Double)(getHeight()*0.37)).intValue();
		

		burnedCard = new JPanel();
		burnedCard.setLayout(new GridLayout());
		burnedCard.setOpaque(false);
		burnedCard.setFont(new Font("Rockwell Condensed", Font.PLAIN, 40));
		burnedCard.setBackground(Color.ORANGE);
		burnedCard.setBounds(burnedCardX,134,burnedCardWidth,burnedCardHeight);
		add(burnedCard);
		
		
		
		
		
		
		
		opposingHand = new JLayeredPane();
		opposingHand.setBorder(new LineBorder(Color.BLACK, 3));
		opposingHand.setBackground(Color.YELLOW);
		opposingHand.setBounds(73, -1, 732, 88);
		opposingHand.setLayout(new GridLayout(1,7)); //MAX 7 MINIONS
		add(opposingHand);
		
		opponentHeroDeck = new JLabel();
		opponentHeroDeck.setBorder(new LineBorder(Color.BLACK, 3));
		opponentHeroDeck.setBackground(Color.LIGHT_GRAY);
		opponentHeroDeck.setText("opponent Deck");
		opponentHeroDeck.setHorizontalAlignment(SwingConstants.CENTER);
		opponentHeroDeck.setOpaque(true);
		opposingHand.add(opponentHeroDeck);
		
		opponentCards = new JLabel();
		opponentCards.setBorder(new LineBorder(Color.BLACK, 3));
		opponentCards.setBackground(Color.MAGENTA);
		opponentCards.setText("opponent Hand");
		opponentCards.setHorizontalAlignment(SwingConstants.CENTER);
		opponentCards.setOpaque(true);
		opposingHand.add(opponentCards);
		
		opponentHP = new JLabel();
		opponentHP.setBorder(new LineBorder(Color.BLACK, 3));
		opponentHP.setBackground(Color.GREEN);
		opponentHP.setText("opponent HP");
		opponentHP.setHorizontalAlignment(SwingConstants.CENTER);
		opponentHP.setOpaque(true);
		opposingHand.add(opponentHP);
		
		opponentMana = new JLabel();
		opponentMana.setBorder(new LineBorder(Color.BLACK, 3));
		opponentMana.setBackground(Color.CYAN);
		opponentMana.setText("opponentMana");
		opponentMana.setHorizontalAlignment(SwingConstants.CENTER);
		opponentMana.setOpaque(true);
		opposingHand.add(opponentMana);
		
		
		int opposingFieldY=((Double)(getHeight()*0.08)).intValue();
		int currentPlayerFieldY=((Double)(getHeight()*0.25)).intValue();;
		int FieldWidth=((Double)(getWidth()*0.54)).intValue();
		int FieldHeight=((Double)(getHeight()*0.17)).intValue();
		
		
		opposingField = new JPanel();
		opposingField.setOpaque(true);
		opposingField.setBackground(LIGHT_BROWN);
		opposingField.setBorder(new LineBorder(Color.RED, 4));
		opposingField.setBounds(0, opposingFieldY, FieldWidth, FieldHeight);
		add(opposingField);
		
		currentPlayerField = new JPanel();
		currentPlayerField.setOpaque(true);
		currentPlayerField.setBackground(LIGHT_BROWN);
		currentPlayerField.setBorder(new LineBorder(Color.RED, 4));
		currentPlayerField.setBounds(0, opposingFieldY+FieldHeight, FieldWidth, FieldHeight); //or y=currentPlayerFieldY
		currentPlayerField.setLayout(new FlowLayout());
		add(currentPlayerField);
		
		
		
		int currentPlayerHandY=((Double)(getHeight()*0.425)).intValue();;
		int Hand2Width=((Double)(getWidth()*0.66)).intValue();
		
		currentPlayerHand= new JPanel();
		currentPlayerHand.setOpaque(true);
		currentPlayerHand.setBackground(LIGHT_BROWN);
		currentPlayerHand.setBorder(new LineBorder(Color.BLUE, 4));
		currentPlayerHand.setBounds(0, currentPlayerFieldY+FieldHeight, Hand2Width, FieldHeight); //or y=currentPlayerHandY
		currentPlayerHand.setLayout(new FlowLayout());
		add(currentPlayerHand);
		
		
		
		int ButtonsX=((Double)(getHeight()*0.08)).intValue();
		
		int endTurnY=((Double)(getHeight()*0.25)).intValue();
		int useHeroPowerY=((Double)(getHeight()*0.25)).intValue();
		int selectTargetY=((Double)(getHeight()*0.25)).intValue();
		
		int ButtonsWidth=((Double)(getWidth()*0.54)).intValue();
		int ButtonsHeight=((Double)(getHeight()*0.17)).intValue();
		

		
		
		
		
		
		
		
		
		
		opponentHeroNameB=new JButton();
		opponentHeroNameB.setBackground(Color.MAGENTA);
		opponentHeroNameB.setText("Paladin wtf");
		opponentHeroNameB.setActionCommand("Hero");
		opponentHeroNameB.setHorizontalAlignment(SwingConstants.CENTER);
		opponentHeroNameB.setOpaque(true);
		sidePart.add(opponentHeroNameB);
		
		endTurnB=new JButton("end Turn");
		endTurnB.setBorder(new LineBorder(Color.BLACK, 2));
		endTurnB.setBackground(Color.RED);
		sidePart.add(endTurnB);
		
		useHeroPowerB=new JButton("UseHeroPower");
		useHeroPowerB.setBorder(new LineBorder(Color.BLACK, 2));
		useHeroPowerB.setBackground(Color.ORANGE);
		sidePart.add(useHeroPowerB);
		
		
		//selectTargetB=new JButton("select Target");
		//selectTargetB.setBorder(new LineBorder(Color.BLACK, 2));
		//selectTargetB.setBackground(Color.YELLOW);
		//sidePart.add(selectTargetB);
		
		
		currentHeroHPLabel=new JLabel();
		currentHeroHPLabel.setBackground(Color.GREEN);
		currentHeroHPLabel.setText("ay rakam");
		currentHeroHPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentHeroHPLabel.setOpaque(true);
		sidePart.add(currentHeroHPLabel);
		
		currentHeroManaLabel=new JLabel();
		currentHeroManaLabel.setBackground(Color.CYAN);
		currentHeroManaLabel.setText("any number");
		currentHeroManaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentHeroManaLabel.setOpaque(true);
		sidePart.add(currentHeroManaLabel);
		
		
		currentHeroDeckLabel=new JLabel();
		currentHeroDeckLabel.setBackground(Color.LIGHT_GRAY);
		currentHeroDeckLabel.setText("Paladin");
		currentHeroDeckLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentHeroDeckLabel.setOpaque(true);
		sidePart.add(currentHeroDeckLabel);
		
		
		currentHeroNameB=new JButton();         
		currentHeroNameB.setBackground(Color.MAGENTA);
		currentHeroNameB.setText("Rexxar lol");
		currentHeroNameB.setActionCommand("Hero");
		currentHeroNameB.setHorizontalAlignment(SwingConstants.CENTER);
		currentHeroNameB.setOpaque(true);
		sidePart.add(currentHeroNameB);
		
		
		setControllerActionListener(endTurnB);
		setControllerActionListener(useHeroPowerB);
		
		selectTargetPopUp = new JLabel("Select Target");
		selectTargetPopUp.setBorder(new LineBorder(Color.BLACK, 3));
		selectTargetPopUp.setOpaque(true);
		selectTargetPopUp.setFont(new Font("Rockwell Condensed", Font.PLAIN, 40));
		selectTargetPopUp.setBackground(Color.YELLOW);
		selectTargetPopUp.setHorizontalAlignment(SwingConstants.CENTER);
		selectTargetPopUp.setBounds(835, -1, 195, 76);
		add(selectTargetPopUp);
		
		
		
		
		
		
		
		
		
		
		
		repaint();
		revalidate(); 
		setVisible(true);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public JPanel getBurnedCard() {
		return burnedCard;
	}















	public void setBurnedCard(JPanel burnedCard) {
		this.burnedCard = burnedCard;
	}















	public void setControllerActionListener(JButton b) {
		b.addActionListener(controller);
	}
	
	
	
	
	



	public JLabel getSelectTargetPopUp() {
		return selectTargetPopUp;
	}







	public void setSelectTargetPopUp(JLabel selectTargetPopUp) {
		this.selectTargetPopUp = selectTargetPopUp;
	}







	public void setController(Controller controller) {
		this.controller = controller;
	}












	

	public JPanel getSidePart() {
		return sidePart;
	}







	public void setSidePart(JPanel sidePart) {
		this.sidePart = sidePart;
	}







	public Controller getController() {
		return controller;
	}







	public JButton getEndTurnB() {
		return endTurnB;
	}






	public void setEndTurnB(JButton endTurnB) {
		this.endTurnB = endTurnB;
	}






	public JLabel getCurrentHeroHPLabel() {
		return currentHeroHPLabel;
	}






	public void setCurrentHeroHPLabel(JLabel currentHeroHPLabel) {
		this.currentHeroHPLabel = currentHeroHPLabel;
	}






	public JLabel getCurrentHeroManaLabel() {
		return currentHeroManaLabel;
	}






	public void setCurrentHeroManaLabel(JLabel currentHeroManaLabel) {
		this.currentHeroManaLabel = currentHeroManaLabel;
	}






	public JLabel getCurrentHeroDeckLabel() {
		return currentHeroDeckLabel;
	}






	public void setCurrentHeroDeckLabel(JLabel currentHeroDeckLabel) {
		this.currentHeroDeckLabel = currentHeroDeckLabel;
	}






	public JButton getUseHeroPowerB() {
		return useHeroPowerB;
	}






	public void setUseHeroPowerB(JButton useHeroPowerB) {
		this.useHeroPowerB = useHeroPowerB;
	}






	//public JButton getSelectTargetB() {
	//	return selectTargetB;}






	//	public void setSelectTargetB(JButton selectTargetB) {
	//	this.selectTargetB = selectTargetB;}







	public JLabel getOpponentHeroDeck() {
		return opponentHeroDeck;
	}






	public void setOpponentHeroDeck(JLabel opponentHeroDeck) {
		this.opponentHeroDeck = opponentHeroDeck;
	}











	public JLabel getOpponentCards() {
		return opponentCards;
	}






	public JButton getCurrentHeroNameB() {
		return currentHeroNameB;
	}






	public void setCurrentHeroNameB(JButton currentHeroNameB) {
		this.currentHeroNameB = currentHeroNameB;
	}






	public void setOpponentCards(JLabel opponentCards) {
		this.opponentCards = opponentCards;
	}






	public JLabel getOpponentHP() {
		return opponentHP;
	}






	public void setOpponentHP(JLabel opponentHP) {
		this.opponentHP = opponentHP;
	}






	public JLabel getOpponentMana() {
		return opponentMana;
	}






	public void setOpponentMana(JLabel opponentMana) {
		this.opponentMana = opponentMana;
	}


	




	public JButton getOpponentHeroNameB() {
		return opponentHeroNameB;
	}






	public void setOpponentHeroNameB(JButton opponentHeroNameB) {
		this.opponentHeroNameB = opponentHeroNameB;
	}








	
	public JLayeredPane getOpposingHand() {
		return opposingHand;
	}


	public void setOpposingHand(JLayeredPane opposingHand) {
		this.opposingHand = opposingHand;
	}


	public JPanel getOpposingField() {
		return opposingField;
	}


	public void setOpposingField(JPanel opposingField) {
		this.opposingField = opposingField;
	}


	public JPanel getCurrentPlayerHand() {
		return currentPlayerHand;
	}


	public void setCurrentPlayerHand(JPanel currentPlayerHand) {
		this.currentPlayerHand = currentPlayerHand;
	}


	public JPanel getCurrentPlayerField() {
		return currentPlayerField;
	}


	public void setCurrentPlayerField(JPanel currentPlayerField) {
		this.currentPlayerField = currentPlayerField;
	}




	public static void main(String[] args) {
		Brett b=new Brett();
	}
}

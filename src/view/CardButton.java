package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Spell;




public class CardButton extends JButton {
	
	private Controller controller;
	
	private String name;
	private int manaCost;
	private String rarity;
	private int attack;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private char type;
	
	
	private Card card;
	
	
	public CardButton(Card c) {
		
		
		card=c;
		
		addActionListener(controller);
		setText(c.getName());
	
		int width=((new Brett()).getCurrentPlayerField().getWidth())/9;
		int height=((new Brett())).getCurrentPlayerField().getHeight();
		
		setSize(width,height);
	    setPreferredSize(new Dimension(width-10,(height*3)/4));
		
		setBounds(0,0,width-10,(height*3)/4);
		
		setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		setLayout(null);
		
		
		/*
		ImageIcon photoIcon=new ImageIcon("Images/Sunwalker.png");
		Image photo=photoIcon.getImage();
		Image modifiedPhoto=photo.getScaledInstance(getWidth(), getHeight(),java.awt.Image.SCALE_SMOOTH);
		photoIcon=new ImageIcon(modifiedPhoto);
		
		setBorder(BorderFactory.createEmptyBorder());
		setContentAreaFilled(false);
		
		setIcon(photoIcon);
		*/
		
		if(c instanceof Minion){
			name=c.getName(); 
			manaCost=c.getManaCost();
			rarity=rarityToString(c.getRarity());
			attack=((Minion) c).getAttack();
			currentHP=((Minion) c).getCurrentHP();
			taunt=((Minion) c).isTaunt();
			divine=((Minion) c).isDivine();
			sleeping=((Minion) c).isSleeping();
			attacked=((Minion) c).isAttacked();
			
			type='M';
			
			
			setActionCommand("Minion");
		
		JPanel sleepingPanel=new JPanel();
		sleepingPanel.setBackground(Color.GREEN);
		sleepingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		sleepingPanel.setBackground(Color.RED);
		sleepingPanel.setPreferredSize(new Dimension(getWidth(),getHeight()/8));
		sleepingPanel.setLayout(new GridLayout(1,3));
		
		sleepingPanel.setBounds(0,0,getWidth(),getHeight()/8);
		
		
		JPanel bodyOfPic=new JPanel();
		bodyOfPic.setBackground(Color.CYAN);
		bodyOfPic.setPreferredSize(new Dimension(getWidth(),(getHeight()*6)/8));
		bodyOfPic.setName("Name of Card");
		bodyOfPic.setLayout(new GridLayout(1,1));;
		
		bodyOfPic.setBounds(0,sleepingPanel.getHeight(),getWidth(),(getHeight()*6)/8);
		
		JPanel attributesPanel=new JPanel();
		attributesPanel.setBackground(Color.MAGENTA);
		attributesPanel.setPreferredSize(new Dimension(getWidth(),getHeight()/8));
		attributesPanel.setLayout(new GridLayout(1,3));
		
		
		
		attributesPanel.setBounds(0,bodyOfPic.getHeight(),getWidth(),(getHeight()*6)/8);
		
		
		
		
		JLabel nameOfCardLabel=new JLabel();
		nameOfCardLabel.setBackground(Color.BLUE);
		nameOfCardLabel.setText(name);
		
		JLabel manaCostLabel=new JLabel();
		manaCostLabel.setBackground(Color.CYAN);
		manaCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		manaCostLabel.setText(manaCost+"");
		manaCostLabel.setBounds(0,0,getWidth()/4,getHeight()/8);
		manaCostLabel.setOpaque(true);
		
		JLabel HPLabel=new JLabel();
		HPLabel.setBackground(Color.GREEN);
		HPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HPLabel.setText(currentHP+"");
		HPLabel.setBounds((getWidth()*3)/4,(getHeight()*6)/8+getHeight()/8,getWidth()/4,getHeight()/8);
		HPLabel.setOpaque(true);
		
		
		JLabel attackLabel=new JLabel();
		attackLabel.setBackground(Color.YELLOW);
		attackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		attackLabel.setText(attack+"");
		attackLabel.setBounds(0,(getHeight()*6)/8+getHeight()/8,getWidth()/4,getHeight()/8);
		attackLabel.setOpaque(true);
		
		
		JLabel sleepingLabel=new JLabel();
		sleepingLabel.setBackground(Color.MAGENTA);
		sleepingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sleepingLabel.setText("Sleeping");
		sleepingLabel.setBounds(getWidth()/4,(getHeight()*6)/8+getHeight()/8,(getWidth()*2)/4,getHeight()/8);
		sleepingLabel.setOpaque(true);
		
		
		JLabel tauntLabel=new JLabel();
		tauntLabel.setBackground(Color.LIGHT_GRAY);
		tauntLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tauntLabel.setText("Taunt");
		tauntLabel.setBounds(getWidth()/4,(getHeight()*6)/8,(getWidth()*2)/4,getHeight()/8);
		tauntLabel.setOpaque(true);
		
		JLabel divineLabel=new JLabel();
		divineLabel.setBackground(Color.YELLOW);
		divineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		divineLabel.setText("Divine");
		divineLabel.setBounds(getWidth()/4,(getHeight()*6)/8-getHeight()/8,(getWidth()*2)/4,getHeight()/8);
		divineLabel.setOpaque(true);
		
		
		JLabel rarityLabel=new JLabel();
		rarityLabel.setBackground(Color.GRAY);
		rarityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rarityLabel.setText(rarity);
		rarityLabel.setOpaque(true);
		rarityLabel.setBounds(getWidth()/3,0,(getWidth()*2)/3,getHeight()/8); //do different color and placement
		add(rarityLabel);
		
		
	add(manaCostLabel);
	add(HPLabel);
	add(attackLabel);
	
	
	if(sleeping)
		add(sleepingLabel);
	if(taunt)
		add(tauntLabel);
	if(divine)
		add(divineLabel);
	
	
		}
		
		
		if(c instanceof Spell){
			name=c.getName(); 
			manaCost=c.getManaCost();
			rarity=rarityToString(c.getRarity());
			
			setActionCommand("Spell");
			
			JLabel manaCostLabel=new JLabel();
			manaCostLabel.setBackground(Color.CYAN);
			manaCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
			manaCostLabel.setText(manaCost+"");
			manaCostLabel.setBounds(0,0,getWidth()/4,getHeight()/8);
			manaCostLabel.setOpaque(true);
		
			
			
			JLabel rarityLabel=new JLabel();
			rarityLabel.setBackground(Color.GRAY);
			rarityLabel.setHorizontalAlignment(SwingConstants.CENTER);
			rarityLabel.setText(rarity);
			rarityLabel.setOpaque(true);
			rarityLabel.setBounds(getWidth()/3,0,(getWidth()*2)/3,getHeight()/8); //do different color and placement
			add(rarityLabel);
			
			
			setBackground(Color.LIGHT_GRAY);
			
			add(manaCostLabel);
		
		
		}
		
		
		
		
		
		
		

}

	
	
	
	
	
	public String rarityToString(Rarity r) {
		
		if(r.equals(Rarity.BASIC))
			return "Basic";
		else if(r.equals(Rarity.COMMON))
			return "Common";
		else if(r.equals(Rarity.RARE))
			return "Rare";
		else if(r.equals(Rarity.EPIC ))
			return "Epic";
		else if(r.equals(Rarity.LEGENDARY))
			return "Legendary";
		else
			return "";
		
	}
	
	
	
	
	




	public Card getCard() {
		return card;
	}








	public void setCard(Card card) {
		this.card = card;
	}
















	public void setController(Controller controller) {
		this.controller = controller;
	}





	public char getType() {
		return type;
	}





	


	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

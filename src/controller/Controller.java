package controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import view.Brett;
import view.CardButton;
import view.GameOver;
import view.GameView;
import view.HeroSelection;

public class Controller implements GameListener,ActionListener{
	private Game model;
	
	private Hero hero1;
	private Hero hero2;
	
	
	private GameView view;
	private HeroSelection heroMenu;
	private Brett brett;
	
	
	private JPanel errorPanel;
	
	private Minion selectedMinionAttacker=null;
	private boolean useHeroPowerClicked=false;
	private Spell selectedSpell=null;
	
	private JButton burnedButton;
	
	

	
	public Controller() throws IOException,CloneNotSupportedException,FullHandException{
	
	 view=new GameView();
	 heroMenu=new HeroSelection();
	 view.setContentPane(new HeroSelection());
	
	 addHeroButtons();
	 while(hero1==null||hero2==null) {}
	
	
	 model.setListener(this);
	 
		
	 view.revalidate();
	 view.repaint();
	
	}
	
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) (e.getSource());
		
		if(b.getActionCommand().equals("playAgainB")) {
			
			view.setContentPane(new HeroSelection());
			model=null;
			addHeroButtons();
			
			
			view.revalidate();
			view.repaint();
		}
		
	
		else if(b.getActionCommand().equals("Burned")) {
			((Brett)view.getContentPane()).getBurnedCard().removeAll();
			view.repaint();
			view.revalidate();}
		
		
		
		
		else if(view.getContentPane() instanceof HeroSelection) { 
			try {
			playersSelectingStartGame(b);}
			catch(IOException|CloneNotSupportedException|FullHandException ex) {
				displayError(ex);}
			}
		
		
		
		else if(view.getContentPane() instanceof Brett) {
			
			if(b.getActionCommand().equals("end Turn")) {
				try {
					model.getCurrentHero().endTurn();
					
					selectedMinionAttacker=null;
					
					useHeroPowerClicked=false;
					selectedSpell=null;
					updateWholeView();
					view.repaint();
					view.revalidate();
				
						}
				catch(CloneNotSupportedException|FullHandException ex) {
					
					selectedMinionAttacker=null;					
					useHeroPowerClicked=false;
					selectedSpell=null;
					
					if(ex instanceof FullHandException) {
						
						((Brett)view.getContentPane()).getBurnedCard().removeAll();
						
						
						Card burnedCard=((FullHandException)ex).getBurned();
						
						updateWholeView();
						view.repaint();
						view.revalidate();
						
						burnedButton=displayBurned(burnedCard,((Brett)view.getContentPane()).getBurnedCard());
						
						if(burnedButton!=null) {
							burnedButton.addActionListener(this);
							burnedButton.setActionCommand("Burned");
							
						((Brett)view.getContentPane()).getBurnedCard().add(burnedButton);
						
						((Brett)view.getContentPane()).getCurrentHeroDeckLabel().setText("My Deck Cards left: "+model.getCurrentHero().getDeck().size());
						view.repaint();
						view.revalidate();}
						
						
						
						
				}
				
			
			
			}}
			
		
			
			
			else if(b.getActionCommand().equals("Hero") && (selectedMinionAttacker!=null||selectedSpell!=null||useHeroPowerClicked ) ) {
				

				Hero targetHero=null;
			if( ((Brett)view.getContentPane()).getCurrentHeroNameB()==b)
				 targetHero=model.getCurrentHero();
			else 
				 targetHero=model.getOpponent();
		
				try {
				
				if(selectedMinionAttacker!=null) {
					
					model.getCurrentHero().attackWithMinion(selectedMinionAttacker,targetHero);
					
					
					
				}
				
				else if(useHeroPowerClicked) {
					
					
					
					if(model.getCurrentHero() instanceof Mage)
						((Mage)model.getCurrentHero()).useHeroPower(targetHero);
					else if(model.getCurrentHero() instanceof Priest)
						((Priest)model.getCurrentHero()).useHeroPower(targetHero);
				}
				else if(selectedSpell!=null && selectedSpell instanceof HeroTargetSpell) {
					
					model.getCurrentHero().castSpell((HeroTargetSpell)selectedSpell,targetHero);
					
				}
				
				
				
				
				
				
				
				
				if(model.getCurrentHero().getCurrentHP()!=0 && model.getOpponent().getCurrentHP()!=0) {
					selectedMinionAttacker=null;
					useHeroPowerClicked=false;
					selectedSpell=null;
					
					updateWholeView();
					view.revalidate();
					view.repaint();}
				
				
				}
				catch(NotYourTurnException|InvalidTargetException|NotSummonedException|TauntBypassException
						|CannotAttackException|FullFieldException|FullHandException|NotEnoughManaException
						|HeroPowerAlreadyUsedException|CloneNotSupportedException ex) {
					
					selectedMinionAttacker=null;
					useHeroPowerClicked=false;
					selectedSpell=null;
					((Brett)view.getContentPane()).getSelectTargetPopUp().setVisible((false));
					displayError(ex);}
				
				selectedMinionAttacker=null;
				//((Brett)view.getContentPane()).getSelectTargetPopUp().setVisible(false);
				selectedSpell=null;
				useHeroPowerClicked=false;
				
				
				
			}
			
			else if(b.getActionCommand().equals("UseHeroPower")){
				
				Hero h=model.getCurrentHero();
				if(h instanceof Mage ||h instanceof Priest) {
					useHeroPowerClicked=true;
				
					}
				else { 
					
				try {	
					model.validateUsingHeroPower(model.getCurrentHero());
				if(h instanceof Hunter)
					((Hunter)h).useHeroPower();
				else if(h instanceof Warlock)
					((Warlock)h).useHeroPower();
				else if(h instanceof Paladin)
					((Paladin)h).useHeroPower();
				
				
			
				selectedMinionAttacker=null;
				useHeroPowerClicked=false;
				selectedSpell=null;
				
				updateWholeView();
				view.revalidate();
				view.repaint();
				
				}
				catch(NotYourTurnException|FullFieldException|FullHandException|NotEnoughManaException
						|HeroPowerAlreadyUsedException|CloneNotSupportedException ex) {
					
					selectedMinionAttacker=null;
					useHeroPowerClicked=false;
					selectedSpell=null;
					((Brett)view.getContentPane()).getSelectTargetPopUp().setVisible((false));
					displayError(ex);
				}
				
				
				}
				updateWholeView();
				view.revalidate();
				view.repaint();
			}
			
			
		
			else if( b instanceof CardButton && ((CardButton)b).getCard() instanceof Minion) {
				try{
				 
					Minion m=(Minion) ((CardButton)b).getCard();
					
				if(selectedMinionAttacker!=null) {
					
					
					model.getCurrentHero().attackWithMinion(selectedMinionAttacker,m);
					
					selectedMinionAttacker=null;
						}
				else if( useHeroPowerClicked) {
						if(model.getCurrentHero() instanceof Mage)
							((Mage)model.getCurrentHero()).useHeroPower(m);
						else if(model.getCurrentHero() instanceof Priest)
							((Priest)model.getCurrentHero()).useHeroPower(m);
						
						selectedSpell=null;
						selectedMinionAttacker=null;
						useHeroPowerClicked=false;
						
						}
				else if(selectedSpell!=null ) {
					
					
					if(selectedSpell instanceof LeechingSpell)
						model.getCurrentHero().castSpell((LeechingSpell)selectedSpell,m);
					
					else if(selectedSpell instanceof MinionTargetSpell)
						model.getCurrentHero().castSpell((MinionTargetSpell)selectedSpell,m);
					
					selectedSpell=null;
					
				}
					
					
					
					
				else if( model.getCurrentHero().getHand().contains(m)) {   
					
					useHeroPowerClicked=false;
					selectedSpell=null;
					selectedMinionAttacker=null;
					model.getCurrentHero().playMinion(m);
					
					
					}
				
				
				else if(selectedMinionAttacker==null && model.getCurrentHero().getField().contains(m)) {
					
					selectedMinionAttacker=m;
					
					useHeroPowerClicked=false;
					selectedSpell=null;
						}
				
				
				}
				catch(NotYourTurnException|NotEnoughManaException|FullFieldException|InvalidTargetException
						|TauntBypassException|CannotAttackException|NotSummonedException|HeroPowerAlreadyUsedException
						|CloneNotSupportedException|FullHandException ex) {
					
					selectedMinionAttacker=null;
					useHeroPowerClicked=false;
					selectedSpell=null;
					((Brett)view.getContentPane()).getSelectTargetPopUp().setVisible((false));
					displayError(ex);}
					
				
				updateWholeView();
				view.revalidate();
				view.repaint();
			
			
			}
				
				else if(b instanceof CardButton && (((CardButton)b).getCard() instanceof Spell)) {
					Spell spell=(Spell) ((CardButton)b).getCard();
					
					if( spell instanceof FieldSpell || spell instanceof AOESpell ) {
					
					try {
					
						
						if( spell instanceof FieldSpell ) {
							
							FieldSpell s=(FieldSpell) spell;
							model.getCurrentHero().castSpell(s);
							selectedSpell=null;
							selectedMinionAttacker=null;
							useHeroPowerClicked=false;
						}
					
						else if( spell instanceof AOESpell ) {
							
							AOESpell s=(AOESpell) spell;
							model.getCurrentHero().castSpell(s,model.getOpponent().getField());
							selectedSpell=null;
							selectedMinionAttacker=null;
							useHeroPowerClicked=false;
							
						}}

						catch(NotYourTurnException|NotEnoughManaException ex) {
							
							selectedMinionAttacker=null;
							useHeroPowerClicked=false;
							selectedSpell=null;
							displayError(ex);
								}}
							
								
								
						
				else if( spell instanceof HeroTargetSpell ||spell instanceof LeechingSpell||spell instanceof MinionTargetSpell  ) {
							
							selectedSpell=spell;
							}
					
						
						selectedMinionAttacker=null;
						useHeroPowerClicked=false;
						
						updateWholeView();
						view.revalidate();
						view.repaint();
					
					
				
				
				
				
				}
			
				
				}
			
			
		
		
		}
			
			
	
			
			
		
		
	
	
	
	
	
	
	
	public void displayError(Exception ex) {
	errorPanel = new JPanel();
	JOptionPane.showMessageDialog(errorPanel, ex.getMessage(), "Move is not valid", JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	
	public void playersSelectingStartGame(JButton b ) throws IOException,CloneNotSupportedException,FullHandException {
		
			
		if(((HeroSelection)view.getContentPane()).getPlayer1Buttons().contains(b) && hero1==null) {
			hero1=createHeroInst(b.getActionCommand());
			hero1.setListener(model);
			b.setBackground(Color.ORANGE);
			}
		else if(((HeroSelection)view.getContentPane()).getPlayer2Buttons().contains(b) && hero2==null) {
			hero2=createHeroInst(b.getActionCommand());
			hero2.setListener(model);
			b.setBackground(Color.ORANGE);
				
			}
		else if(b.getActionCommand().equals("Start Game") && hero1!=null && hero2!=null) {
			model=new Game(hero1,hero2);
			model.setListener(this);
			
			updateWholeView();
			view.revalidate();
			view.repaint();
		}
		
		}
		
		
		
		
	
	
	
	public void updateWholeView() {
		view.setContentPane(new Brett());
		
		if( !(selectedMinionAttacker!=null || useHeroPowerClicked || selectedSpell!=null) ) {
			((Brett)view.getContentPane()).getSelectTargetPopUp().setVisible((false));}
		
		
		
		(((Brett)view.getContentPane()).getEndTurnB()).addActionListener(this);
		(((Brett)view.getContentPane()).getUseHeroPowerB()).addActionListener(this);
		(((Brett)view.getContentPane()).getCurrentHeroNameB()).addActionListener(this);
		(((Brett)view.getContentPane()).getOpponentHeroNameB()).addActionListener(this);
		
		
		
		
		
	if(hero1==model.getCurrentHero()) 
		view.setTitle("PLAYER 1 TURN");
	else
		view.setTitle("PLAYER 2 TURN");
		
		
		
		((Brett)view.getContentPane()).getCurrentHeroNameB().setText(model.getCurrentHero().getName());
		((Brett)view.getContentPane()).getOpponentHeroNameB().setText(model.getOpponent().getName());
		
		
		for(int i=0;i<model.getCurrentHero().getHand().size();i++) {
			CardButton minionCard=new CardButton( model.getCurrentHero().getHand().get(i)  );
			minionCard.addActionListener(this);
			((Brett)view.getContentPane()).getCurrentPlayerHand().add(minionCard);   
			minionCard.setActionCommand("Hand Card");}
		
		for(int i=0;i<model.getCurrentHero().getField().size();i++) {
			CardButton minionCard=new CardButton( model.getCurrentHero().getField().get(i)  );
			minionCard.addActionListener(this);
			((Brett)view.getContentPane()).getCurrentPlayerField().add(minionCard);  
			minionCard.setActionCommand("Field Card");}
		
		for(int i=0;i<model.getOpponent().getField().size();i++) {
			CardButton minionCard=new CardButton( model.getOpponent().getField().get(i)  );
			minionCard.addActionListener(this);
			((Brett)view.getContentPane()).getOpposingField().add(minionCard); 
			minionCard.setActionCommand("Field Card");}
		
		
		
	updateHeroesAttributes();
	
				}
	
	
	
	
	public void updateHeroesAttributes() {
		
		
		((Brett)view.getContentPane()).getCurrentHeroHPLabel().setText("My HP:"+model.getCurrentHero().getCurrentHP());
		((Brett)view.getContentPane()).getCurrentHeroManaLabel().setText("MyTotalMana:"+model.getCurrentHero().getTotalManaCrystals()+"| MyCurrentMana:"+model.getCurrentHero().getCurrentManaCrystals());
		((Brett)view.getContentPane()).getCurrentHeroDeckLabel().setText("My Deck Cards left: "+model.getCurrentHero().getDeck().size());
		
		((Brett)view.getContentPane()).getOpponentHP().setText("Opponent HP:"+model.getOpponent().getCurrentHP());
		((Brett)view.getContentPane()).getOpponentMana().setText("Total Mana:"+model.getOpponent().getTotalManaCrystals()+"| Current Mana:"+model.getOpponent().getCurrentManaCrystals());
		((Brett)view.getContentPane()).getOpponentHeroDeck().setText("Opponent Deck Cards left: "+model.getOpponent().getDeck().size());
		((Brett)view.getContentPane()).getOpponentCards().setText("Opponent Hand Cards left: "+model.getOpponent().getHand().size());
		
		
	}
	
	public void onGameOver() {
		view.getContentPane().removeAll();
		view.setContentPane(new GameOver());
		
		
		int w;
		if(model.getFirstHero().getCurrentHP()==0)
			w=1;
		else
			w=2;
		String winnerText="Player "+w+" wins!";
		((GameOver)view.getContentPane()).getWinnerName().setText(winnerText);
		((GameOver)view.getContentPane()).getPlayAgainB().addActionListener(this);
		
		
		hero1=null;
		hero2=null;
		view.revalidate();
		view.repaint();
		
	}
	
	
	
	
	public GameView getView() {
		return view;
	}


	public void setView(GameView view) {
		this.view = view;
	}


	public Game getModel() {
		return model;
	}


	public HeroSelection getHeroMenu() {
		return heroMenu;
	}


	public Brett getBrett() {
		return brett;
	}


	public Hero createHeroInst(String s)  throws IOException,CloneNotSupportedException {
		
		switch(s) 
        { 
            case "Warlock": 
                return new Warlock(); 
            case "Mage": 
            	return new Mage(); 
            case "Hunter": 
            	return new Hunter();
            case "Paladin": 
            	return new Paladin();
            case "Priest": 
            	return new Priest();
            default: 
                return null; 
        } 
	}
	
	
	
	
	
	public Hero getHero1() {
		return hero1;
	}
	
	
	
	
	
	public Hero getHero2() {
		return hero2;
	}


	


	
	
	
	
	
	
	public void addHeroButtons() {
		
	
		(((HeroSelection)view.getContentPane()).getWarlock()).addActionListener(this);
		((HeroSelection)view.getContentPane()).getMage().addActionListener(this);
		((HeroSelection)view.getContentPane()).getHunter().addActionListener(this);
		((HeroSelection)view.getContentPane()).getPaladin().addActionListener(this);
		((HeroSelection)view.getContentPane()).getPriest().addActionListener(this);
		
		((HeroSelection)view.getContentPane()).getWarlock2().addActionListener(this);
		((HeroSelection)view.getContentPane()).getMage2().addActionListener(this);
		((HeroSelection)view.getContentPane()).getHunter2().addActionListener(this);
		((HeroSelection)view.getContentPane()).getPaladin2().addActionListener(this);
		((HeroSelection)view.getContentPane()).getPriest2().addActionListener(this);
		
		((HeroSelection)view.getContentPane()).getStartGame().addActionListener(this);
		
		
	}
	
	public JButton displayBurned(Card c,JPanel p) {
		
		
		
		if(c==null)
			return null;
		else {
		JButton b=new JButton();
		b.addActionListener(this);
		b.setActionCommand("Burned");
		
		b.setText(c.getName());
	
		int width=p.getWidth();
		int height=p.getHeight();
		
		b.setSize(width,height);
	   
		
		b.setBackground(Color.ORANGE);
		b.setLayout(new BorderLayout());
		b.setLayout(null);
		

		 String name;
		 int manaCost;
		 String rarity;
		 int attack;
		 int currentHP;
		 boolean taunt;
		 boolean divine;
		 boolean sleeping;
		 boolean attacked;
		
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
			
			
			
			
		b.setActionCommand("Minion");
		
		JPanel sleepingPanel=new JPanel();
		sleepingPanel.setBackground(Color.GREEN);
		sleepingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		sleepingPanel.setBackground(Color.RED);
		sleepingPanel.setPreferredSize(new Dimension(p.getWidth(),p.getHeight()/8));
		sleepingPanel.setLayout(new GridLayout(1,3));
		
		sleepingPanel.setBounds(0,0,p.getWidth(),p.getHeight()/8);
		
		
		JPanel bodyOfPic=new JPanel();
		bodyOfPic.setBackground(Color.CYAN);
		bodyOfPic.setPreferredSize(new Dimension(p.getWidth(),(p.getHeight()*6)/8));
		bodyOfPic.setName("Name of Card");
		bodyOfPic.setLayout(new GridLayout(1,1));;
		
		bodyOfPic.setBounds(0,sleepingPanel.getHeight(),p.getWidth(),(p.getHeight()*6)/8);
		
		JPanel attributesPanel=new JPanel();
		attributesPanel.setBackground(Color.MAGENTA);
		attributesPanel.setPreferredSize(new Dimension(p.getWidth(),p.getHeight()/8));
		attributesPanel.setLayout(new GridLayout(1,3));
		
		
		
		attributesPanel.setBounds(0,bodyOfPic.getHeight(),p.getWidth(),(p.getHeight()*6)/8);
		
		
		
		
		JLabel nameOfCardLabel=new JLabel();
		nameOfCardLabel.setBackground(Color.BLUE);
		nameOfCardLabel.setText(name);
		
		JLabel manaCostLabel=new JLabel();
		manaCostLabel.setBackground(Color.CYAN);
		manaCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		manaCostLabel.setText(manaCost+"");
		manaCostLabel.setBounds(0,0,p.getWidth()/4,p.getHeight()/8);
		manaCostLabel.setOpaque(true);
		
		JLabel HPLabel=new JLabel();
		HPLabel.setBackground(Color.GREEN);
		HPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HPLabel.setText(currentHP+"");
		HPLabel.setBounds((p.getWidth()*3)/4,(p.getHeight()*6)/8+p.getHeight()/8,p.getWidth()/4,p.getHeight()/8);
		HPLabel.setOpaque(true);
		
		
		JLabel attackLabel=new JLabel();
		attackLabel.setBackground(Color.YELLOW);
		attackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		attackLabel.setText(attack+"");
		attackLabel.setBounds(0,(p.getHeight()*6)/8+p.getHeight()/8,p.getWidth()/4,p.getHeight()/8);
		attackLabel.setOpaque(true);
		
		
		JLabel BurnedLabel=new JLabel();
		BurnedLabel.setBackground(Color.RED);
		BurnedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BurnedLabel.setText("BURNED!!");
		BurnedLabel.setBounds(p.getWidth()/4,(p.getHeight()*6)/8+p.getHeight()/8,(p.getWidth()*2)/4,p.getHeight()/8);
		BurnedLabel.setOpaque(true);
		
		
		JLabel tauntLabel=new JLabel();
		tauntLabel.setBackground(Color.LIGHT_GRAY);
		tauntLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tauntLabel.setText("Taunt");
		tauntLabel.setBounds(p.getWidth()/4,(p.getHeight()*6)/8,(p.getWidth()*2)/4,p.getHeight()/8);
		tauntLabel.setOpaque(true);
		
		JLabel divineLabel=new JLabel();
		divineLabel.setBackground(Color.YELLOW);
		divineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		divineLabel.setText("Divine");
		divineLabel.setBounds(p.getWidth()/4,(p.getHeight()*6)/8-p.getHeight()/8,(p.getWidth()*2)/4,p.getHeight()/8);
		divineLabel.setOpaque(true);
		
		
		JLabel rarityLabel=new JLabel();
		rarityLabel.setBackground(Color.GRAY);
		rarityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rarityLabel.setText(rarity);
		rarityLabel.setOpaque(true);
		rarityLabel.setBounds(p.getWidth()/3,0,(p.getWidth()*2)/3,p.getHeight()/8); //do different color and placement
		b.add(rarityLabel);
		
		
	b.add(manaCostLabel);
	b.add(HPLabel);
	b.add(attackLabel);
	
	
	
	b.add(BurnedLabel);
	if(taunt)
		b.add(tauntLabel);
	if(divine)
		b.add(divineLabel);
	
	
		}
		
		
		if(c instanceof Spell){
			name=c.getName(); 
			manaCost=c.getManaCost();
			rarity=rarityToString(c.getRarity());
			
			b.setActionCommand("Spell");
			
			JLabel manaCostLabel=new JLabel();
			manaCostLabel.setBackground(Color.CYAN);
			manaCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
			manaCostLabel.setText(manaCost+"");
			manaCostLabel.setBounds(0,0,p.getWidth()/4,p.getHeight()/8);
			manaCostLabel.setOpaque(true);
		
			
			
			JLabel rarityLabel=new JLabel();
			rarityLabel.setBackground(Color.GRAY);
			rarityLabel.setHorizontalAlignment(SwingConstants.CENTER);
			rarityLabel.setText(rarity);
			rarityLabel.setOpaque(true);
			rarityLabel.setBounds(p.getWidth()/3,0,(p.getWidth()*2)/3,p.getHeight()/8); //do different color and placement
			b.add(rarityLabel);
			
			
			JLabel BurnedLabel=new JLabel();
			BurnedLabel.setBackground(Color.RED);
			BurnedLabel.setHorizontalAlignment(SwingConstants.CENTER);
			BurnedLabel.setText("BURNED!!");
			BurnedLabel.setBounds(p.getWidth()/4,(p.getHeight()*6)/8+p.getHeight()/8,(p.getWidth()*2)/4,p.getHeight()/8);
			BurnedLabel.setOpaque(true);
			
			b.setBackground(Color.LIGHT_GRAY);
			
			b.add(BurnedLabel);
			b.add(manaCostLabel);
		
		
		}
		
		
		return b;}
		
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
	
	public static void main(String[] args) throws IOException,CloneNotSupportedException,FullHandException{

		Controller c=new Controller();
	
	}
 
	
	

}

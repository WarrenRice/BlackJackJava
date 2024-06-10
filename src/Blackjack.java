import java.awt.BorderLayout;											//import the library
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Blackjack extends JDialog {								//create main application
	
	private final JPanel contentPanel = new JPanel();					//define contentPanel as JPanel object for a main panel
	private JLabel lblDealerMoney;										//define lblDealerMoney as a JLabel object to display dealer's money
	private JLabel lblPlayerMoney;										//define lblPlayerMoney as a JLabel object to display player's money
	private JLabel lblPrizeMoney;										//define lblPrizeMoney as a JLabel object to display prize pot
	private JLabel lblPlayerHandValue;									//define lblPlayerHandValue as a JLabel to display player's hand value
	private JLabel lblDealerHandValue;									//define lblDealerHandValue as a JLabel to display dealer's hand value
	private JLabel lblResult;											//define lblResult as a JLabel to display round result
	private JLabel lblGameOver;											//define lblGameOver as a JLabel to display when the game is over
	private JLabel lblResultState;										//define lblResultState as a JLabel to display the result status
	
	private JLabel[] playerCardLbls, dealerCardLbls;					//define playerCardLbls and dealerCardLbls arrays to store player's and dealer's card objects
	
	private JButton btnGo = new JButton();								//define btnGo as a JButton object
	private JButton btnBet = new JButton();								//define btnBet as a JButton object
	private JButton btnHit = new JButton();								//define btnHit as a JButton object
	private JButton btnStand = new JButton();							//define btnStand as a JButton object
	
	private Player player = new Player();								//define player as a Player object
	private Player dealer = new Player();								//define dealer as a Player object
	
	private Deck deck;													//define deck as a Deck object
	
	private final String PLAYER = "PLAYER";								//define constant PLAYER as a string
	private final String DEALER = "DEALER";								//define constant DEALER as a string
	private final String TIE = "TIE";									//define constant TIE as a string
	private final String NONE = "NONE";									//define constant NONE as a string
	
	private int prize;													//define prize as an integer to store prize pot
	private String winner = "NONE";										//define winner as a string to store the round result

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {							//main method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	void main(String[] args)
		*
		* Method parameters		:	args - the method permits String command line parameters to be entered
		*
		* Method return			:	void
		*
		* Synopsis				:	This is a main method of Blackjack game application. Player are playing against
		*							a computer dealer. Both the player and the dealer start with $1000. The player 
		*							start a round by pressing 'BET' button to add the prize pot. The player can begin  
		*							drawing by pressing 'GO' button and betting will end. Then, the player can only
		*							choose 'HIT' button to draw a new card or 'STAND' button if the player is satisfied 
		*							with the hand. After the 'STAND' button is pressed, the player's turn end and the 
		*							dealer's turn begins.
		*
		*							Whoever has a hand value nearest to 21 without goes over 21 will win the round and
		*							get the money from the prize pot.
		*
		* References			:   Wikipedia. (2023). Blackjack. Retrieved March 30, 2023, 
		* 								from https://en.wikipedia.org/wiki/Blackjack
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*							2023-03-29		W. Poomarin				Change dealer stop drawing until dealer's hand >= 17 (Wikipedia, 2023.)
		*							2023-03-30		W. Poomarin				Fix bugs with some rare scenario when the game announces a wrong winner
		*							2023-03-31		W. Poomarin				Correct some errors and comments
		*							2023-03-31		W. Poomarin				Change back to dealer draw until the dealer's hand > player's hand
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		try {
			Blackjack game = new Blackjack();							//create game object as Blackjack object to run the game
			game.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	//setup JDialog 
			game.setVisible(true);										//display the game
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Blackjack() {												//create Blackjack class

		initialize();													//call initialize() to set up labels and buttons
		
		btnBet= new JButton("BET ($50)");								//setup btnBet button which is labeled "BET ($50)"
		btnBet.setEnabled(true);										//enable the btnBet button
		btnBet.setBounds(196, 227, 89, 23);								//setup it's position and size
		btnBet.addActionListener(new ActionListener() {					//call bet() method when it is clicked
			public void actionPerformed(ActionEvent e) {
				bet();
			}
		});			
		contentPanel.setBackground(new Color(0, 206, 103));				//set Background color
		contentPanel.add(btnBet);										//add it to the panel
		
	
		btnGo = new JButton("GO");										//setup btnGo button which is labeled "GO"
		btnGo.setEnabled(false);										//disable the button
		btnGo.setBounds(329, 227, 89, 23);								//setup it's position and size
		btnGo.addActionListener(new ActionListener() {					//call go() method when it is clicked
			public void actionPerformed(ActionEvent e) {
				go();
			}
		});			
		contentPanel.add(btnGo);										//add it to the panel
		
		
		btnHit= new JButton("HIT");										//setup btnHit button which is labeled "HIT"
		btnHit.setEnabled(false);										//disable the btnHit button
		btnHit.setBounds(464, 227, 89, 23);								//setup it's position and size
		btnHit.addActionListener(new ActionListener() {					//call hit() method when it is clicked
			public void actionPerformed(ActionEvent e) {
				hit();
			}
		});
		contentPanel.add(btnHit);										//add it to the panel
		

		btnStand= new JButton("STAND");									//setup btnStand button which is labeled "STAND"
		btnStand.setEnabled(false);										//disable the btnStand button
		btnStand.setBounds(599, 227, 89, 23);							//setup it's position and size
		btnStand.addActionListener(new ActionListener() {				//call stand() method when it is clicked
			public void actionPerformed(ActionEvent e) {
				stand();
			}
		});		
		contentPanel.add(btnStand);										//add it to the panel
		
	}

	public void initialize() {											//create initialize() method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	void initialize
		*
		* Method parameters		:	none
		*
		* Method return			:	void
		*
		* Synopsis				:	This method will initialize buttons, labels and card images layout. 
		* 							It's also setup some initial variables that are essential of the game.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		prize = 0;														//set prize = 0
		deck = new Deck();												//create new Deck object
		
		setBounds(100, 100, 900, 500);									//setup window
		getContentPane().setLayout(new BorderLayout());					
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		playerCardLbls = new JLabel[5];									//create playerCardLbls array to store 5 JLabel objects
		dealerCardLbls = new JLabel[5];									//create dealerCardLbls array to store 5 JLabel objects

		lblDealerMoney = new JLabel("1000");							//set lblDealerMoney label = "1000"
		lblPlayerMoney = new JLabel("1000");							//set lblPlayerMoney label = "1000"
		lblPrizeMoney = new JLabel("0");								//set lblPrizeMoney label = "0"
		lblPlayerHandValue = new JLabel("0");							//set lblPlayerHandValue label = "0"
		lblDealerHandValue = new JLabel("0");							//set lblDealerHandValue label = "0"
		lblResult = new JLabel("(Playing)");							//set lblResult label = "(Playing)"
		lblGameOver = new JLabel("");									//set lblGameOver label = ""
		lblGameOver.setForeground(Color.RED);							//set lblGameOver color
		lblResultState = new JLabel("");								//set lblResultState label = ""
		
		JLabel lblDealerCards = new JLabel("Dealer's Cards");			//create lblDealerCards text label "Dealer's Cards"
		lblDealerCards.setHorizontalAlignment(SwingConstants.CENTER);	//place it at the center of text box
		lblDealerCards.setFont(new Font("Tahoma", Font.BOLD, 14));		//set it's font and size
		lblDealerCards.setBounds(360, 22, 167, 14);						//set it's position
		contentPanel.add(lblDealerCards);								//add it to the panel
		
		JLabel lblPlayerCards = new JLabel("Player's Cards");			//create lblPlayerCards text label "Player's Cards"
		lblPlayerCards.setHorizontalAlignment(SwingConstants.CENTER);	//place it at the center of text box
		lblPlayerCards.setFont(new Font("Tahoma", Font.BOLD, 14));		//set it's font and size
		lblPlayerCards.setBounds(360, 424, 154, 14);					//set it's position
		contentPanel.add(lblPlayerCards);								//add it to the panel
		
		JLabel lblDealerMoneyTxt = new JLabel("Dealer's Money");		//create lblDealerMoneyTxt text label "Dealer's Money"
		lblDealerMoneyTxt.setHorizontalAlignment(SwingConstants.CENTER);	//place it at the center of text box
		lblDealerMoneyTxt.setFont(new Font("Tahoma", Font.BOLD, 14));	//set it's font and size
		lblDealerMoneyTxt.setBounds(16, 22, 152, 14);					//set it's position
		contentPanel.add(lblDealerMoneyTxt);							//add it to the panel
		
		JLabel lblPlayerMoneyTxt = new JLabel("Player's Money");		//create lblPlayerMoneyTxt text label "Player's Money"
		lblPlayerMoneyTxt.setHorizontalAlignment(SwingConstants.CENTER);	//place it at the center of text box
		lblPlayerMoneyTxt.setFont(new Font("Tahoma", Font.BOLD, 14));	//set it's font and size
		lblPlayerMoneyTxt.setBounds(16, 313, 152, 14);					//set it's position
		contentPanel.add(lblPlayerMoneyTxt);							//add it to the panel
		
		JLabel lblDealerHand = new JLabel("Dealer's Hand");				//create lblDealerHand text label "Dealer's Hand"
		lblDealerHand.setHorizontalAlignment(SwingConstants.CENTER);	//place it at the center of text box
		lblDealerHand.setFont(new Font("Tahoma", Font.BOLD, 14));		//set it's font and size
		lblDealerHand.setBounds(722, 22, 152, 14);						//set it's position
		contentPanel.add(lblDealerHand);								//add it to the panel
		
		JLabel lblPlayerHand = new JLabel("Player's Hand");				//create lblPlayerHand text label "Player's Hand"
		lblPlayerHand.setHorizontalAlignment(SwingConstants.CENTER);	//place it at the center of text box
		lblPlayerHand.setFont(new Font("Tahoma", Font.BOLD, 14));		//set it's font and size
		lblPlayerHand.setBounds(722, 317, 152, 14);						//set it's position
		contentPanel.add(lblPlayerHand);								//add it to the panel
		
																		//lblDealerMoney set up
		lblDealerMoney.setHorizontalAlignment(SwingConstants.CENTER);	//place lblDealerMoney label at the center of text box
		lblDealerMoney.setFont(new Font("Tahoma", Font.BOLD, 16));		//set it's font and size
		lblDealerMoney.setBounds(76, 59, 46, 14);						//set it's position
		contentPanel.add(lblDealerMoney);								//add it to the panel
		
																		//lblPlayerMoney set up
		lblPlayerMoney.setHorizontalAlignment(SwingConstants.CENTER);	//place lblPlayerMoney label at the center of text box
		lblPlayerMoney.setFont(new Font("Tahoma", Font.BOLD, 16));		//set it's font and size
		lblPlayerMoney.setBounds(76, 354, 46, 14);						//set it's position
		contentPanel.add(lblPlayerMoney);								//add it to the panel
		
																		//lblDealerHandValue set up
		lblDealerHandValue.setHorizontalAlignment(SwingConstants.CENTER);	//place lblDealerHandValue label at the center of text box
		lblDealerHandValue.setFont(new Font("Tahoma", Font.BOLD, 16));	//set it's font and size
		lblDealerHandValue.setBounds(774, 61, 46, 14);					//set it's position
		contentPanel.add(lblDealerHandValue);							//add it to the panel
		
																		//lblPlayerHandValue set up
		lblPlayerHandValue.setHorizontalAlignment(SwingConstants.CENTER);	//place lblPlayerHandValue label at the center of text box
		lblPlayerHandValue.setFont(new Font("Tahoma", Font.BOLD, 16));	//set it's font and size
		lblPlayerHandValue.setBounds(774, 354, 46, 14);					//set it's position
		contentPanel.add(lblPlayerHandValue);							//add it to the panel
		
		JLabel lblPrize = new JLabel("Prize Pot");						//create lblPrize text label "Prize Pot"
		lblPrize.setHorizontalAlignment(SwingConstants.CENTER);			//place lblPrize at the center of text box
		lblPrize.setFont(new Font("Tahoma", Font.BOLD, 14));			//set it's font and size
		lblPrize.setBounds(16, 190, 152, 14);							//set it's position
		contentPanel.add(lblPrize);										//add it to the panel
		
																		//lblPrizeMoney set up
		lblPrizeMoney.setHorizontalAlignment(SwingConstants.CENTER);	//place lblPrizeMoney at the center of text box
		lblPrizeMoney.setFont(new Font("Tahoma", Font.BOLD, 16));		//set it's font and size
		lblPrizeMoney.setBounds(60, 229, 62, 14);						//set it's position
		contentPanel.add(lblPrizeMoney);								//add it to the panel
		
		JLabel lblResultTxt = new JLabel("Round Winner");				//create lblResultTxt text label "Round Winner"
		lblResultTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultTxt.setFont(new Font("Tahoma", Font.BOLD, 14));		//set it's font and size
		lblResultTxt.setBounds(712, 190, 162, 14);						//set it's position
		contentPanel.add(lblResultTxt);									//add it to the panel
		
																		//lblResult set up
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 14));			//place lblResult at the center of text box
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);		//set it's font and size
		lblResult.setBounds(722, 215, 152, 14);							//set it's position
		contentPanel.add(lblResult);									//add it to the panel
		
																		//lblResultState set up
		lblResultState.setHorizontalAlignment(SwingConstants.CENTER);	//place lblResultState at the center of text box
		lblResultState.setFont(new Font("Tahoma", Font.BOLD, 12));		//set it's font and size
		lblResultState.setBounds(712, 235, 162, 23);					//set it's position
		contentPanel.add(lblResultState);								//add it to the panel
		
																		//lblGameOver set up
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);		//place lblGameOver at the center of text box
		lblGameOver.setFont(new Font("Tahoma", Font.BOLD, 18));			//set it's font and size
		lblGameOver.setBounds(308, 190, 272, 26);						//set it's position
		contentPanel.add(lblGameOver);									//add it to the panel
		
		playerCardLbls[0] = new JLabel(deck.noCard());					//create playerCardLbls[0] as a JLabel object and display the back of the card
		playerCardLbls[0].setBounds(174, 286, 89, 123);					//set it's position and size
		contentPanel.add(playerCardLbls[0]);							//add it to the panel
		
		playerCardLbls[1] = new JLabel(deck.noCard());					//create playerCardLbls[1] as a JLabel object and display the back of the card
		playerCardLbls[1].setBounds(286, 286, 89, 123);					//set it's position and size
		contentPanel.add(playerCardLbls[1]);							//add it to the panel
		
		playerCardLbls[2] = new JLabel(deck.noCard());					//create playerCardLbls[2] as a JLabel object and display the back of the card
		playerCardLbls[2].setBounds(397, 286, 89, 123);					//set it's position and size
		contentPanel.add(playerCardLbls[2]);							//add it to the panel
		
		playerCardLbls[3] = new JLabel(deck.noCard());					//create playerCardLbls[3] as a JLabel object and display the back of the card
		playerCardLbls[3].setBounds(510, 286, 89, 123);					//set it's position and size
		contentPanel.add(playerCardLbls[3]);							//add it to the panel
		
		playerCardLbls[4] = new JLabel(deck.noCard());					//create playerCardLbls[4] as a JLabel object and display the back of the card
		playerCardLbls[4].setBounds(624, 286, 89, 123);					//set it's position and size
		contentPanel.add(playerCardLbls[4]);							//add it to the panel
		
		dealerCardLbls[0]= new JLabel(deck.noCard());					//create dealerCardLbls[0] as a JLabel object and display the back of the card					
		dealerCardLbls[0].setBounds(174, 61, 89, 123);					//set it's position and size
		contentPanel.add(dealerCardLbls[0]);							//add it to the panel
		
		dealerCardLbls[1] = new JLabel(deck.noCard());					//create dealerCardLbls[1] as a JLabel object and display the back of the card
		dealerCardLbls[1].setBounds(286, 61, 89, 123);					//set it's position and size
		contentPanel.add(dealerCardLbls[1]);							//add it to the panel
		
		dealerCardLbls[2] = new JLabel(deck.noCard());					//create dealerCardLbls[2] as a JLabel object and display the back of the card
		dealerCardLbls[2].setBounds(393, 61, 89, 123);					//set it's position and size
		contentPanel.add(dealerCardLbls[2]);							//add it to the panel
		
		dealerCardLbls[3] = new JLabel(deck.noCard());					//create dealerCardLbls[3] as a JLabel object and display the back of the card
		dealerCardLbls[3].setBounds(510, 61, 89, 123);					//set it's position and size
		contentPanel.add(dealerCardLbls[3]);							//add it to the panel
		
		dealerCardLbls[4] = new JLabel(deck.noCard());					//create dealerCardLbls[4] as a JLabel object and display the back of the card
		dealerCardLbls[4].setBounds(624, 61, 89, 123);					//set it's position and size
		contentPanel.add(dealerCardLbls[4]);							//add it to the panel
	}
	
	public void bet() {													//create bet() method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	void bet
		*
		* Method parameters		:	none
		*
		* Method return			:	void
		*
		* Synopsis				:	This method will be called when the player press "BET" button.
		* 							It will reduce $50 from the player's and the dealer's money and add
		* 							$100 to the prize pot. It will also update the player's and the dealer's 
		* 							money display 
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		lblResult.setText("");											//set lblResult label = ""
		lblResultState.setText("");										//set lblResultState label = ""
		
		if (prize == 0) {
			for (int i = 0; i < 5; i++) {								//hide every cards on the table
				playerCardLbls[i].setIcon(deck.noCard());				//display the card's back image
				dealerCardLbls[i].setIcon(deck.noCard());				//display the card's back image
			}
		}
		
		if (player.getMoney() >49 && dealer.getMoney() >49) {			//check if player's and dealer's money is more than 49
			
			player.setMoney(player.getMoney()-50);						//reduce player's money by $50
			dealer.setMoney(dealer.getMoney()-50);						//reduce dealer's money by $50
			prize = prize + 100;										//add money of $100 to the prize pot
		} 
		
		if (player.getMoney() <50 || dealer.getMoney() <50) {			//check if player's or dealer's money is less than 50
			btnGo.setEnabled(true);										//enable the btnBet button
			btnBet.setEnabled(false);									//disable the btnGo button
			btnHit.setEnabled(false);									//disable the btnHit button
			btnStand.setEnabled(false);									//disable the btnStand button
		}
		
		if (prize > 0 ) {
			btnGo.setEnabled(true);										//enable the btnBet button
		} 
		
		lblPlayerMoney.setText(player.getMoneyString());				//set lblPlayerMoney label to the current money in the pocket
		lblDealerMoney.setText(dealer.getMoneyString());				//set lblDealerMoney label to the current money in the pocket
		lblPrizeMoney.setText(String.valueOf(prize));					//set lblPrizeMoney label to the current money in prize pot
	}
	
	public void go() {													//create go() method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	void go
		*
		* Method parameters		:	none
		*
		* Method return			:	void
		*
		* Synopsis				:	This method will be called when the player press "GO" button.
		* 							The player's and the dealer's hand will be reseted. The new deck will be created and 
		* 							shuffled. The Player and the dealer will draw 2 cards. The player will shows 2 cards 
		* 							and the dealer will show only one card. It will update the player's and the dealer's 
		* 							hand value. It also will display "BET" button and enable "HIT" and "STAND" buttons.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		clearCards(deck, player, dealer);									//reset deck, player's and dealer's hands
		
		lblResult.setText("");												//set lblResult label = ""
		lblResultState.setText("");											//set lblResultState label = ""
		
		int playerScore;													//define playerScore as integer to store player's hand value
		int dealerScore;													//define dealerScore as integer to store dealer's hand value
		
		deck = new Deck();													//create a new deck as Deck object
		deck.shuffle();														//call deck.shuffle() method to shuffle the cards
		
		player.setScore(0);													//set player's hand value = 0
		dealer.setScore(0);													//set dealer's hand value = 0
		player.setAces(0);													//set player's aces = 0
		dealer.setAces(0);													//set dealer's aces = 0
		
		playerScore = player.getScore();									//set playerScore = player's hand value by calling player.getScore()
		dealerScore = dealer.getScore();									//set dealerScore = dealer's hand value by calling dealer.getScore()

		player.draw(deck.drawCard());										//call player.draw(deck.drawCard()) method to draw a card from deck's cards array to player's cards array
		player.setHits(player.getHits()+1);									//set player's hit number = player's hit number + 1 

		dealer.draw(deck.drawCard());										//call dealer.draw(deck.drawCard()) method to draw a card from deck's cards array to dealer's cards array
		dealer.setHits(dealer.getHits()+1);									//set dealer's hit number = dealer's hit number + 1 
		
		player.draw(deck.drawCard());										//call player.draw(deck.drawCard()) method to draw a card from deck's cards array to player's cards array
		player.setHits(player.getHits()+1);									//set player's hit number = player's hit number + 1 

		dealer.draw(deck.drawCard());										//call dealer.draw(deck.drawCard()) method to draw a card from deck's cards array to dealer's cards array
		dealer.setHits(dealer.getHits()+1);									//set dealer's hit number = dealer's hit number + 1 
			
		playerCardLbls[0].setIcon(player.getCard(0).showCard());			//display the player's first card
		playerScore = getCardValue(player.getCard(0).getRank(),player);		//set playerScore = the player's first card value
		player.setScore(playerScore);										//set player's hand value = playerScore
		
		playerCardLbls[1].setIcon(player.getCard(1).showCard());			//display the player's second card
		playerScore = playerScore + getCardValue(player.getCard(1).getRank(),player);	//set playerScore = playerScore + the second card value
		player.setScore(playerScore);										//set player hand value = playerScore
		
		lblPlayerHandValue.setText(player.getScoreString());				//set lblPlayerHandValue label = player's hand value by calling player.getScoreString()
		
		dealerCardLbls[0].setIcon(dealer.getCard(0).showCard());			//display the dealer's first card
		dealerScore = getCardValue(dealer.getCard(0).getRank(),dealer);		//set dealerScore = the dealer's first card value
		dealer.setScore(dealerScore);										//set dealer's hand value = dealerScore
		
		dealerCardLbls[1].setIcon(dealer.getCard(1).hideCard());			//hide the dealer's second card
		
		lblDealerHandValue.setText(dealer.getScoreString());				//set lblDealerHandValue label = dealer's hand value by calling dealer.getScoreString()
		
		winner = checkBlackjack(playerScore, (dealerScore + 
				getCardValue(dealer.getCard(1).getRank(),dealer))); 		//set winner = return of checkBlackjack() method to see if the player has a blackjack
				
		if (winner == NONE) {												//if player does not have a blackjack		
			btnBet.setEnabled(false);										//disable btnBet button
			btnGo.setEnabled(false);										//disable btnGo button
			btnHit.setEnabled(true);										//enable btnHit button
			btnStand.setEnabled(true);										//enable btnStand button
		} else {															//if someone have blackjack (player or both)

			dealerCardLbls[1].setIcon(dealer.getCard(1).showCard());						//display the dealer's second card
			dealerScore = dealerScore + getCardValue(dealer.getCard(1).getRank(),dealer);	//set dealerScore = dealerScore + the second's value
						
			dealer.setScore(dealerScore);								//set dealer's hand value by  method to set value of the first cards in dealer's hand to dealer's score
			
			lblDealerHandValue.setText(dealer.getScoreString());		//set lblDealerHandValue label = dealerScore by calling dealer.getScoreString()
			if (winner == TIE){
				lblResultState.setText("(Split the prize)");			//set lblResultState label = "(Split the prize)"
			}else {
				lblResultState.setText("(Blackjack)");					//set lblResultState label = "(Blackjack)"
			}
			
			getPrize(winner);											//call getPrize() method to give money to the winner
			updateUI();													//call updateUI() method to update player's and dealer's money
			
			btnBet.setEnabled(true);									//enable the btnBet button
			btnGo.setEnabled(false);									//disable the btnGo button
			btnHit.setEnabled(false);									//disable the btnHit button
			btnStand.setEnabled(false);									//disable the btnStand button
			
			checkGameOver();											//call checkGameOver() method to check if game is over or not
		}
	}

	public void hit() {													//create hit() method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	hit go
		*
		* Method parameters		:	none
		*
		* Method return			:	void
		*
		* Synopsis				:	This method will be called when the player press "HIT" button.
		* 							The player will draw a new card and the card value will be added to the player's hand value.
		* 							If the player's hand value is over 21, the player busts and the dealer wins.
		* 							If the player has 5 cards in the hand, the player will win when the dealer doesn't had Blackjack.
		* 							Otherwise, it will be a tie.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		int playerScore = player.getScore();							//define and set playerScore to store player's hand value by calling player.getScore()
		int hitNumber = player.getHits();								//define and set hitNumber to store player's hit number by calling player.getHits()
		int dealerScore = dealer.getScore();							//define and set playerScore to store player's hand value by calling dealer.getScore()
		int ace = player.getAces();										//define and set ace to store player's aces value by calling player.getAces()
			
		player.draw(deck.drawCard());									//call player.draw(deck.drawCard()) method to draw a card from deck's cards array to player's cards array 
																		
		
		playerCardLbls[hitNumber].setIcon(player.getCard(hitNumber).showCard());				//display player's new card	
		playerScore = playerScore + getCardValue(player.getCard(hitNumber).getRank(),player);	//set playerScore = playerScore + value of the new card 
		
		if (playerScore > 21 && ace > 0){								//check if playerScore > 21 and player has aces
			playerScore = playerScore - 10;								//set playerScore = playerScore - 10
			player.setAces(0);											//set player's ace is used
		}
		
		player.setHits(hitNumber+1);									//set player's hit number = hitNumber+1
		player.setScore(playerScore);									//update player's hand value by calling player.setScore(playerScore)
		lblPlayerHandValue.setText(player.getScoreString());			//update lblPlayerHandValue label = player's hand value
		
		if (playerScore > 21) {											//check if player's hand is busted
			winner = DEALER;											//set winner = "DEALER"
			lblResultState.setText("(Player busts)");					//set lblResultState label = "(Player busts)"
			
			getPrize(winner);											//call getPrize(winner) method to give the prize to the winner
			updateUI();													//call updateUI() method to update player's and dealer's money
				
			btnBet.setEnabled(true);									//enable the btnGo button
			btnGo.setEnabled(false);									//disable the btnGo button
			btnHit.setEnabled(false);									//disable the btnHit button
			btnStand.setEnabled(false);									//disable the btnStand button
			
			checkGameOver();											//call checkGameOver() method to check if the game is over
				
		} else if (player.getHits() == 5) {								//check if the player has draw 5 cards
			int ifDealerBlackJack;										//define ifDealerBlackJack as an integer
			ifDealerBlackJack = dealerScore + 
					getCardValue(dealer.getCard(1).getRank(),dealer);	//set ifDealerBlackJack = dealerScore + the dealer's second card
						
			if (ifDealerBlackJack == 21) {								//check if the dealer has Blackjack
				
				dealerCardLbls[1].setIcon(dealer.getCard(1).showCard());		//display dealer's card
				dealer.setScore(ifDealerBlackJack);								//update dealer's hand value = dealerScore by calling dealer.setScore(dealerScore) method
				lblDealerHandValue.setText(dealer.getScoreString());			//update lblDealerHandValue label = dealer's hand value
				
				winner = TIE;													//if the dealer has a Blackjack,  winner = "TIE"
				lblResultState.setText("(Split the prize)");					//set lblResultState label = "(Split the prize)"
				
				getPrize(winner);										//call getPrize(winner) method to give the prize to the winner
				updateUI();												//call updateUI() method to update player's and dealer's money
					
			} else {													//if the dealer does not have a Blackjack
				winner = PLAYER;										//winner = "PLAYER"
				lblResultState.setText("(5-Cards Monty)");				//set lblResultState label = "(5-Cards Monty)"
				
				getPrize(winner);										//call getPrize(winner) method to give the prize to the winner
				updateUI();												//call updateUI() method to update player's and dealer's money
			}
			btnBet.setEnabled(true);									//enable btnBet button
			btnGo.setEnabled(false);									//disable the btnGo button			
			btnHit.setEnabled(false);									//disable btnHit button
			btnStand.setEnabled(false);									//disable btnStand button
			
			checkGameOver();											//call checkGameOver() method to check if the game is over
		}
	}
	
	public void stand() {
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	stand go
		*
		* Method parameters		:	none
		*
		* Method return			:	void
		*
		* Synopsis				:	This method will be called when the player press "STAND" button.
		* 							The player's turn will ends and the dealer's turn begin. The dealer will try to win
		* 							by drawing until the dealer's hand value greater or equal to 17, or busts. (Bicyclecards, 2022.)
		* 
		* 							Whoever has a hand value nearest to 21 without goes over 21 will win the round and
		*							get the money from the prize pot.
		*
		* References			:   Wikipedia. (2023). Blackjack. Retrieved March 30, 2023, 
		* 								from https://en.wikipedia.org/wiki/Blackjack
		*
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*							2023-03-29		W. Poomarin				Change dealer stop drawing until dealer's hand >= 17 (Wikipedia, 2023.)
		*							2023-03-30		W. Poomarin				Fix bugs with some rare scenario when the game announces a wrong winner
		*							2023-03-31		W. Poomarin				Change back to dealer draw until the dealer's hand > player's hand
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		int playerScore = player.getScore();							//define and set playerScore to store player's hand value by calling player.getScore()
		int dealerScore = dealer.getScore();							//define and set playerScore to store dealer's hand value by calling dealer.getScore()
		int hitNumber = dealer.getHits();								//define and set hitNumber to store dealer's hit number by calling dealer.getHits()
		int ace= dealer.getAces();										//define and set hitNumber to store dealer's ace card by calling dealer.getAces()
		
		dealerCardLbls[1].setIcon(dealer.getCard(1).showCard());		//display dealer's card
		dealerScore = dealerScore + getCardValue(dealer.getCard(1).getRank(),dealer);	//set dealerScore = dealerScore + the second card's value

		dealer.setScore(dealerScore);									//update dealer's hand value = dealerScore by calling dealer.setScore(dealerScore) method
		lblDealerHandValue.setText(dealer.getScoreString());			//update lblDealerHandValue label = dealer's hand value
		
		ace = dealer.getAces();											//set ace = dealer's ace card
		
		while (dealerScore <= playerScore && hitNumber < 5 && dealerScore < 21){	//while dealer's hand value is less than player's hand
						
			dealer.draw(deck.drawCard());								//call dealer.draw(deck.drawCard()) method to draw a card from deck's cards array to dealer's cards array 							
			hitNumber = dealer.getHits();								//set hitNumber = dealer's hit number
			
			dealerCardLbls[hitNumber].setIcon(dealer.getCard(hitNumber).showCard());				//display dealer's new card
			dealerScore = dealerScore + getCardValue(dealer.getCard(hitNumber).getRank(),dealer);	//set dealerScore = dealerScore + the new card's value
			
			if (dealerScore > 21 && ace > 0){							//check if dealerScore > 21 and dealer has aces
				dealerScore = dealerScore - 10;							//set dealerScore = dealerScore - 10
				dealer.setAces(0);										//set dealer's ace is used
			}

			dealer.setScore(dealerScore);								//update dealer's hand value = dealerScore by calling dealer.setScore(dealerScore) method
			lblDealerHandValue.setText(dealer.getScoreString());		//update lblDealerHandValue label = dealer's hand value
			
			dealer.setHits(hitNumber+1);								//set dealer's hit number = hitNumber + 1
			hitNumber = dealer.getHits();								//set hitNumber = dealer's hit number
			ace = dealer.getAces();										//set ace = dealer's remaining ace card

		}
				
		if (dealerScore <= 21 && hitNumber == 5) {						//if dealerScore doesn't busts and have drown 5 cards
			if (playerScore == 21) {
				winner = TIE;											//then winner = "TIE"
				lblResultState.setText("(Split the prize)");			//set lblResultState label = "(Split the prize)"
			} else {
				winner = DEALER;										//then winner = "DEALER"
				lblResultState.setText("(5-Cards Monty)");				//set lblResultState label = "(5-Cards Monty)"
			}
		} else if (dealerScore > 21) {									//check if dealer's hand is busted
			winner = PLAYER;											//then winner = "PLAYER"
			lblResultState.setText("(Dealer busts)");					//set lblResultState label = "(Dealer busts)"
		} else if (dealerScore == playerScore) {						//if dealer and player have same score
			winner = TIE;												//then winner = "TIE"
			lblResultState.setText("(Split the prize)");				//set lblResultState label = "(Split the prize)"
		} else if (dealerScore < playerScore) {							//if player's hand value is greater than dealer's hand value
			winner = PLAYER;											//then winner = "PLAYER"
			lblResultState.setText("(Player gets the prize)");			//set lblResultState label = "(Player gets the prize)"
		} else if (dealerScore > playerScore) {							//if player's hand value is lesser than dealer's hand value
			winner = DEALER;											//then winner = "DEALER"
			if (dealerScore == 21 && hitNumber == 2) {					
				lblResultState.setText("(Blackjack)");					//set lblResultState label = "(Blackjack)"
			} else {
				lblResultState.setText("(Dealer gets the prize)");		//set lblResultState label = "(Dealer gets the prize)"
			}
		}

		getPrize(winner);												//call getPrize(winner) method to give the prize to the winner
		updateUI();														//call updateUI() method to update player's and dealer's money

		btnGo.setEnabled(false);										//disable btnGo button
		btnBet.setEnabled(true);										//enable btnBet button										
		btnHit.setEnabled(false);										//disable btnHit button	
		btnStand.setEnabled(false);										//disable btnStand button
		
		checkGameOver();												//call checkGameOver() method to check if the game is over
	}
	
	public String checkBlackjack(int playerScore, int dealerScore) {	//create checkBlackjack method input parameter: player's hand value and dealer's hand value
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	String checkBlackjack
		*
		* Method parameters		:	playerScore - the method permits an integer parameters to be entered
		* 						:	dealerScore - the method permits an integer parameters to be entered
		*
		* Method return			:	String
		*
		* Synopsis				:	This method will determine if the player or the dealer have blackjacks in their hands.
		* 							This method will return "TIE" if both the player or the dealer have blackjacks.
		* 							This method will return "PLAYER" if only the player has blackjacks.
		* 							This method will return "NONE" if none the player or the dealer has blackjack.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		if (playerScore == 21) {										//if player's hand value == 21
			if (dealerScore == 21) {									//if dealer's hand value == 21
				return TIE;												//return "TIE"
			} else {													//else
				return PLAYER;											//return "PLAYER"
			}
		} else {														//none has hand value == 21
			return NONE;												//return "NONE"
		}
	}
	
	public void getPrize(String winner) {								//create getPrize method input parameter: String 
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	void getPrize
		*
		* Method parameters		:	winner - the method permits a string parameters to be entered
		*
		* Method return			:	String
		*
		* Synopsis				:	This method will give the winner money from the prize pot if there is a winner.
		* 							If it is a "TIE", the money from the prize pot will be split equally. This method
		* 							also reset prize pot to 0 and winner to "NONE".
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		if (winner == PLAYER) {											//if winner == "PLAYER"
			player.setMoney(prize + player.getMoney());					//set player's money = player's money + prize
		} else if (winner == DEALER){									//if winner == "DEALER"
			dealer.setMoney(prize + dealer.getMoney());					//set dealer's money = dealer's money + prize
		} else if (winner == TIE){										//if winner == "TIE", spit the prize
			player.setMoney(prize/2 + player.getMoney());				//set player's money = player's money + prize/2
			dealer.setMoney(prize/2 + dealer.getMoney());				//set dealer's money = dealer's money + prize/2
		}
		prize = 0;														//reset prize = 0					
		winner = NONE;													//reset winner = "NONE"
	}	
	
	public void checkGameOver() {										//create checkGameOver method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	void checkGameOver
		*
		* Method parameters		:	none
		*
		* Method return			:	void
		*
		* Synopsis				:	This method will checked if the player's or the dealer's is equal 0.
		* 							The game over texts will be displayed and every buttons will be disabled.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		if (dealer.getMoney() == 0) {									//if dealer's money == 0
			lblGameOver.setText("GAME OVER - YOU WIN");					//set and display lblGameOver label "GAME OVER - YOU WIN"
			
			btnGo.setEnabled(false);									//disable btnGo button
			btnBet.setEnabled(false);									//disable btnBet button										
			btnHit.setEnabled(false);									//disable btnHit button	
			btnStand.setEnabled(false);									//disable btnStand button
		}
		
		if (player.getMoney() == 0) {									//if player's money == 0
			lblGameOver.setText("GAME OVER - YOU LOSE");				//set and display lblGameOver label "GAME OVER - YOU LOSE"
			
			btnGo.setEnabled(false);									//disable btnGo button
			btnBet.setEnabled(false);									//disable btnBet button										
			btnHit.setEnabled(false);									//disable btnHit button	
			btnStand.setEnabled(false);									//disable btnStand button
		}
	}
	
	public void updateUI() {											//create updateUI method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	void updateUI
		*
		* Method parameters		:	none
		*
		* Method return			:	void
		*
		* Synopsis				:	This method will update the player's, the dealer's, and the prize pot labels.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		lblPlayerMoney.setText(player.getMoneyString());				//set and display lblPlayerMoney label = player's money
		lblDealerMoney.setText(dealer.getMoneyString());				//set and display lblDealerMoney label = dealer's money
		lblPrizeMoney.setText(String.valueOf(prize));					//set and display lblPrizeMoney label = prize value
		lblResult.setText(winner);										//set and display lblResult label = winner
	}
	
	public void clearCards(Deck deck, Player player, Player dealer){	//create clearCards method input parameter: Deck object, Player object and Player object
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	void clearCards
		*
		* Method parameters		:	deck - the method permits a Deck object parameters to be entered
		* 						:	player - the method permits a Player object parameters to be entered
		* 						:	dealer - the method permits a Player object parameters to be entered
		*
		* Method return			:	void
		*
		* Synopsis				:	This method will reset card deck, player's cards, player's hand value, dealer's cards,
		* 							and dealer's hand value.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		for (int i = 0; i < player.getHits(); i++) {					//remove every elements in the player's cards array
			try {
				player.removeCard(0);
			} catch (Exception e) {
			}
			
		}
		for (int i = 0; i < dealer.getHits(); i++) {					//remove every elements in the dealer's cards array
			try {
				dealer.removeCard(0);
			} catch (Exception e) {
			}
		}
		
		player.setHits(0);												//reset player's hit number = 0
		dealer.setHits(0);												//reset dealer's hit number = 0
		
		deck = null;													//set desk object to null

	}
	
	public int getCardValue(int cardRank, Player unit) {				//create getCardValue 
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	int getCardValue
		*
		* Method parameters		:	cardRank - the method permits an integer parameters to be entered
		* 						:	player - the method permits a Player object parameters to be entered
		*
		* Method return			:	int
		*
		* Synopsis				:	This method return a card value and also record if the ace card return 11.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				Fix bugs when player money = 0 but still can play
		*							2023-03-28		W. Poomarin				Fix bugs with ace card add commends
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		int handValue = unit.getScore();								//define handValue as an integer to store player's or dealer's hand value

		if (cardRank > 10) {											//if it is a face card
			return 10;													//set card's value = 10
		} else if (cardRank == 1 && handValue < 11 ) {					//if it is an ace card and hand value less than 11
			unit.setAces(1);											//set ace = 1
			return 11;													//set card's value = 11
		} else if (cardRank == 1 && handValue > 10) {					//if it is an ace card and hand value more than 10
			return 1;													//set card's value = 1
		} else {														
			return cardRank;											//set card's value = cardRank
		}
	}
}

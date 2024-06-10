import java.util.ArrayList;										//import the library

public class Player {											//create Player class

	  private int money;										//define money as an integer to store Player's money data member
	  private int score;										//define score as an integer to store Player's hand value data member
	  private int hits;											//define hits as an integer to store Player's hits number data member
	  private int aces;											//define aces as an integer to store Player's aces number data member
	  
	  private ArrayList<Card> cards = new ArrayList<Card>(5);	//define cards as array to store Card objects

	  public Player() {											//create Player constructor method
		  
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	Player class constructor
			*
			* Method parameters		:	none
			*
			* Method return			:	Player
			*
			* Synopsis				:	This method create Player object with initial data members.
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  
		  this.money = 1000;									//set money = 1000
		  this.score = 0;										//set score = 0
		  this.hits = 0;										//set hits = 0
		  this.aces = 0;										//set aces = 0
	  }

	  public int getMoney() {									//create getMoney method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	int getMoney
			*
			* Method parameters		:	none
			*
			* Method return			:	int
			*
			* Synopsis				:	This method return an integer data member: money
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  return money;											//return money data as an integer
	  }
	  
	  public String getMoneyString() {							//create getMoneyString method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	String getMoney
			*
			* Method parameters		:	none
			*
			* Method return			:	String
			*
			* Synopsis				:	This method return a data member: money as a string
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  return String.valueOf(money);							//return money data as a string
	  }
	  
	  public int getScore() {									//create getScore method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	int getScore
			*
			* Method parameters		:	none
			*
			* Method return			:	int
			*
			* Synopsis				:	This method return the hand value as an integer data member: score
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  return score;											//return score data as an integer
	  }
	  
	  public int getHits() {									//create getHits method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	int getHits
			*
			* Method parameters		:	none
			*
			* Method return			:	int
			*
			* Synopsis				:	This method return the card count as an integer data member: hits
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  return hits;											//return hits data as an integer
	  }
	  
	  public int getAces() {									//create getAces method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	int getAces
			*
			* Method parameters		:	none
			*
			* Method return			:	int
			*
			* Synopsis				:	This method return the ace card count that value = 11 as an integer data member: aces
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  return aces;											//return aces data as an integer
	  }
	  
	  public String getScoreString() {							//create getScoreString method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	String getScoreString
			*
			* Method parameters		:	none
			*
			* Method return			:	String
			*
			* Synopsis				:	This method return the hand value as a string data member: score
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  return String.valueOf(score);							//return score data as a string
	  }	  
	  
	  public void setHits(int number) {							//create setHits method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	void setHits
			*
			* Method parameters		:	number - the method permits an integer parameters to be entered
			*
			* Method return			:	void
			*
			* Synopsis				:	This method set the data member: hits = number
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  this.hits = number;									//set hits = input integer
	  }
	  
	  public void setMoney(int input) {							//create setMoney method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	void setMoney
			*
			* Method parameters		:	input - the method permits an integer parameters to be entered
			*
			* Method return			:	void
			*
			* Synopsis				:	This method set the data member: money = input
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  this.money = input;									//set money = input integer
	  }
	  
	  public void setScore(int input) {							//create setScore method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	void setScore
			*
			* Method parameters		:	input - the method permits an integer parameters to be entered
			*
			* Method return			:	void
			*
			* Synopsis				:	This method set the data member: score = input
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  this.score = input;									//set score = input integer
	  }
	  
	  public void setAces(int input) {							//create setAces method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	void setAces
			*
			* Method parameters		:	input - the method permits an integer parameters to be entered
			*
			* Method return			:	void
			*
			* Synopsis				:	This method set the data member: aces = input
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  this.aces = input;									//set aces = input integer
	  }
	  
	  public void draw(Card newCard) {							//create draw method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	void draw
			*
			* Method parameters		:	newCard - the method permits a Card object parameters to be entered
			*
			* Method return			:	void
			*
			* Synopsis				:	This method will add a new card object to Player's cards array.
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  cards.add(newCard);									//add a Card object to the cards array
	  }
	  
	  public Card getCard(int index) {							//create getCard method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	Card getCard
			*
			* Method parameters		:	index - the method permits an integer parameters to be entered
			*
			* Method return			:	Card
			*
			* Synopsis				:	This method will return a card object to Player's cards array at input index.
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*							2023-03-29		W. Poomarin				add removeCard method
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  return this.cards.get(index);								
	  }
	  
	  public void removeCard(int index) {						//create removeCard method
			/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			* Method				:	void removeCard
			*
			* Method parameters		:	index - the method permits an integer parameters to be entered
			*
			* Method return			:	void
			*
			* Synopsis				:	This method will remove an element in Player's cards array at input index.
			* 
			* Modifications			:
			*							Date			Developer				Notes
			*							----			---------				-----
			*							2023-03-18		W. Poomarin				Build UI layout
			*							2023-03-25		W. Poomarin				Finish first programming
			*							2023-03-29		W. Poomarin				add removeCard method
			*
			** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		  this.cards.remove(index);								
	  }
}
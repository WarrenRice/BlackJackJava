import java.util.ArrayList;												//import the library
import java.util.Random;

import javax.swing.ImageIcon;

public class Deck {														//create Deck class
	private String[] suitLists = {"S", "H", "D", "C"};					//define and set  suitLists as string array to store all the suit strings
	private String[] rankLists = {"A", "2", "3", "4", "5", "6", "7", 	//define and set rankLists as string array to store all the rank strings
			"8", "9", "10", "J", "Q", "K"};
	private ArrayList<Card> cards;										//define cards array to store Card object
	private ImageIcon noCard = new ImageIcon("Images/nocard.png");		//define and set noCard as ImageIcon to store the nocards's image
	
	
	public Deck() {											//create Deck constructor method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	Deck class constructor
		*
		* Method parameters		:	none
		*
		* Method return			:	Deck
		*
		* Synopsis				:	This method create Deck object with Card array which contain every cards.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*							2023-03-27		W. Poomarin				add suitLists and rankLists arrays
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
	    cards = new ArrayList<Card>();						//create cards array
	    for(int i=0; i<4; i++)								//for from 0 to 3 to identify suit of the card
	    {
	        for(int j=1; j<=13; j++)						//for from 1 to 13 to identify rank of the card
	        {
	        	cards.add(new Card(i,j,suitLists[i],rankLists[j-1])); 	//create and add Card object to the cards array
	        }
	    }
	}
	
	public void shuffle() {									//create shuffle method
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	int shuffle
		*
		* Method parameters		:	none
		*
		* Method return			:	int
		*
		* Synopsis				:	This method will shuffle cards' indexes.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		Random random = new Random();						//create random as Random object
		Card temp;											//create temp as Card object in order to swap position between two cards
	    for(int i=0; i<200; i++)							//swap position 200 times
	    {
	        int index1 = random.nextInt(cards.size()-1);	//set index1 = the random index of the first card
	        int index2 = random.nextInt(cards.size()-1);	//set index2 = the random index of the second card
	        temp = cards.get(index2);						//set temp = cards[index2]
	        cards.set(index2, cards.get(index1));			//set cards[index2] = cards[index1]
	        cards.set(index1, temp);						//set cards[index1] = temp
	    }		
	}
	
	public Card drawCard() {								//create drawCard method
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	Card shuffle
		*
		* Method parameters		:	none
		*
		* Method return			:	Card
		*
		* Synopsis				:	This method will return first element from cards array and remove it from the deck.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
	    return cards.remove(0);								//remove cards[0] from cards array and return card[0] as Card object
	}
	
	public ImageIcon noCard() {
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	ImageIcon cardBack
		*
		* Method parameters		:	none
		*
		* Method return			:	ImageIcon
		*
		* Synopsis				:	This method return back image of the Card.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		return noCard;										//return noCard ImageIcon object
	}
}

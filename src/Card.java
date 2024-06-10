import javax.swing.ImageIcon;											//import the library							

public class Card {														//create Cars class
	private int suit;													//define suit as an integer
	private int rank;													//define rank as an integer
	private String suitCode;											//define suitCode as a string to store the suit string of this object
	private String rankCode;											//define rankCode as a string to store the rank string of this object
	private ImageIcon imageBack = new ImageIcon("Images/back.png");		//define and set imageBack as ImageIcon to store the card's back image
	private ImageIcon imageFront = new ImageIcon("");					//define and set imageFront as ImageIcon to store the card's front image
	
	public Card(int suit, int rank, String suitcode, String rankcode){	//create Card constructor method
		
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	Card class constructor
		*
		* Method parameters		:	none
		*
		* Method return			:	Card
		*
		* Synopsis				:	This method create Card object with initial data members.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		
		this.rank = rank;												//set rank = integer rank input parameter
		this.suit = suit;												//set suit = integer suit input parameter
		this.rankCode = rankcode;										//set rankCode = string rankcode input parameter
		this.suitCode = suitcode;										//set suitCode = string suitcode input parameter
		this.imageFront = new ImageIcon("Images/"+ this.rankCode + 		//set imageFront = the image file that is match rankCode and suitCode
				this.suitCode +".png");
	}
	
	public int getRank() {												//create getRank method
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	int getRank
		*
		* Method parameters		:	none
		*
		* Method return			:	int
		*
		* Synopsis				:	This method return a rank of the card as an integer data member: rank
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		return rank;													//return rank data member as an integer
	}
	
	public int getSuit() {												//create getSuit method
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	int getSuit
		*
		* Method parameters		:	none
		*
		* Method return			:	int
		*
		* Synopsis				:	This method return a suit of the card as an integer data member: suit
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		return suit;													//return suit data member as an integer
	}
	
	public ImageIcon showCard() {										//create showCard method
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	ImageIcon getSuit
		*
		* Method parameters		:	none
		*
		* Method return			:	ImageIcon
		*
		* Synopsis				:	This method return front image of the Card.
		* 
		* Modifications			:
		*							Date			Developer				Notes
		*							----			---------				-----
		*							2023-03-18		W. Poomarin				Build UI layout
		*							2023-03-25		W. Poomarin				Finish first programming
		*
		** =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= */
		return imageFront;												//return imageFront object to show the front of the card
	}
	
	public ImageIcon hideCard() {										//create hideCard method
		/* =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		* Method				:	ImageIcon hideCard
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
		return imageBack;												//return imageBack object to show the back of the card
	}
}

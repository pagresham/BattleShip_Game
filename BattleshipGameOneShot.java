import java.util.Scanner;


/**
 * @author piercegresham
 */
public class BattleshipGameOneShot {
	/**
	 * Main method for Battleship game
	 * Contains logic to script the gameplay
	 * Contains logic to prompt and record inmput/output
	 * @param args
	 */
	public static void main(String[] args) {
		// Begin Game
		Scanner in = new Scanner(System.in);
		String lastShot = "";
		boolean playAgain = true;

		// Begin round of play
		while(playAgain) {
			// Create Ocean and place Ships 
			Ocean o = new Ocean();
			o.placeAllShipsRandomly();
			System.out.println("Welcome to Battleship!");
			System.out.println("You get to make 5 shots at a time until you sink the fleet.");
			System.out.println("Use Format: 0,0; 1,1; 2,2; 3,3; 4,4;");

			//	    testShotToAllSquares(o);

			//	    testShotToMiddle(o);

			// While all ships are not sunk
			while(!o.isGameOver()) {
				o.print();
				System.out.println(lastShot);
				int row = 0;
				int col = 0;

				System.out.println("List you shots: 1,1; 2,2; ...");



				System.out.println("Row: ");
				if(in.hasNextInt()) {
					row = in.nextInt();
					System.out.println("Column: ");
					if(in.hasNextInt()) {
						col = in.nextInt();

						if(checkInputRange(row) && checkInputRange(col)) {

							System.out.println();
							System.out.println();

							System.out.println("Firing at "+row+" : "+col);
							System.out.println();

							if(o.shootAt(row, col)) { // I hit a live ship
								if(o.getShipArray()[row][col].isSunk()) { // if i sank the ship
									lastShot = "You just sank a "+o.getShipArray()[row][col].getShipType();
									//			    System.out.println("You just sank a "+o.getShipArray()[row][col].getShipType());
								}
								else // if I just wounded the ship
									lastShot = "hit";
								System.out.println("Hit");
							}
							else lastShot = "miss";
							System.out.println("Miss");

						}

						else {
							System.out.println("Inputs from 0 to 19 only.");
							in.next();
						}


					}
					else {
						System.out.println("Only ints 0 - 19");
						in.next();
					}
				}
				else {
					System.out.println("Only ints 0 - 19");
					in.next();
				}




			}
			System.out.println("Congratulations, You've sank the fleet!");
			System.out.println("Shots Fired = "+o.getShotsFired());
			System.out.println("Sould you like to play again? (y/n)");
			String again = in.next();
			playAgain = (again.equals("y")) ? true : false;
		}




		System.out.println("Thanks for playing!");
		in.close();
	}


	// =======  Helper Methods  ==========  //

	public static void testShotToAllSquares(Ocean o) {
		// Test Shots across the middle
		for(int i = 0; i<20;i++) { // Shoots at all spaces on board
			for(int j = 0; j < 20; j++){
				o.shootAt(i, j);
			}
		}
	}

	public static void testShotToMiddle(Ocean o) {
		for(int i = 0; i<20;i++) { // shoots at a diagonal row of spaces
			o.shootAt(i, i);
		}
	}

	public static boolean checkInputRange(int i) {
		if(i >= 0 && i < 20) {
			return true;
		}
		return false;
	}








}

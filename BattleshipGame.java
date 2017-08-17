import java.util.Scanner;

/**
 * @author piercegresham
 */
public class BattleshipGame {
	/**
	 * Main method for Battleship game
	 * Contains logic to script the gameplay
	 * Contains logic to prompt and record inmput/output
	 * @param args
	 */
	public static void main(String[] args) {
		// Begin Game
		Scanner in = new Scanner(System.in);
		boolean playAgain = true;

		// Begin round of play
		while(playAgain) {
			// Create Ocean and place Ships 
			Ocean o = new Ocean();
			o.placeAllShipsRandomly();
			System.out.println("Welcome to Battleship!");
			System.out.println();
			System.out.println("You get to make 5 shots at a time until you sink the fleet.");
			System.out.println("Use Format: 0,0; 1,1; 2,2; 3,3; 4,4;");
			System.out.println();

			// Test shots below //

			// testShotToAllSquares(o);
			o.print();
			// testShotToMiddle(o);

			// While all ships are not sunk
			while(!o.isGameOver()) {

				o.print();
				System.out.println("List you shots: 1,1; 2,2; ...");
				// Take string line as input
				String shots = (in.nextLine());


				// Remove spaces, and convert semicolons to commas
				shots = shots.replaceAll(" ", "");
				// Get array of values from split of string
				String[] shotsArr = shots.split(",|;");
				int[] shotInts = new int[10];

				// Check user input
				if (validateInputString(shotsArr)) {
					shotInts = convertToIntArr(shotsArr);

					for (int i = 0; i < shotInts.length; i+=2) {
						// shoot with coords of i and i+1

						System.out.println();
						// Check if hit was made
						if(o.shootAt(shotInts[i], shotInts[i + 1])) {
							// Check to see if a ship at that space is sunk
							if(o.getShipArray()[shotInts[i]][shotInts[i + 1]].isSunk()) { // if i sank the ship
								System.out.print("Firing at "+shotInts[i]+" : "+shotInts[i + 1]);
								System.out.print("    You just sank a "+o.getShipArray()[shotInts[i]][shotInts[i + 1]].getShipType());
								System.out.println();
							}
							else { // Report hitting a ship
								System.out.print("Firing at "+shotInts[i]+" : "+shotInts[i + 1]);
								System.out.print("    Hit");
								System.out.println();
							}

						}
						else {
							// report miss
							System.out.print("Firing at "+shotInts[i]+" : "+shotInts[i + 1]);
							System.out.print("    Miss");
							System.out.println();
						}
					}
				}
				else { // shot array was not valid
					System.out.println("INVALID");
					System.out.println("Enter coords for your next 5 shots.");
					System.out.println("Coords must be positive integers from 0 to 19");
					System.out.println("ex: 1,1; 2,2; 3,3; 4,4; 5,5");
				}

			}
			// Display final results, and check to play again
			System.out.println("Congratulations, You've sank the fleet!");
			System.out.println("Shots Fired = "+o.getShotsFired());
			System.out.println("Sould you like to play again? (y/n)");
			String again = in.next();

			playAgain = (again.equals("y")) ? true : false;
		}

		// EndGame
		System.out.println("Thanks for playing!");
		in.close();
	}


	// =======  Helper Methods  ==========  //

	/**
	 * Fires a shot to all squares for testing 
	 * @param o - Ocean to be fired upon
	 */
	public static void testShotToAllSquares(Ocean o) {
		// Test Shots across the middle
		for(int i = 0; i<20;i++) { // Shoots at all spaces on board
			for(int j = 0; j < 20; j++){
				o.shootAt(i, j);
			}
		}
	}

	/**
	 * Fires a shot to every space in a diagonal line across board
	 * @param o - The ocean to be fired upon
	 */
	public static void testShotToMiddle(Ocean o) {
		for(int i = 0; i<20;i++) { // shoots at a diagonal row of spaces
			o.shootAt(i, i);
		}
		for(int i = 0; i<20;i++) { // shoots at a diagonal row of spaces
			int col = Math.abs(i - 19);
			o.shootAt(i, col);
		}
	}

	/**
	 * Fact checks if input is within pre-defined range
	 */
	public static boolean checkInputRange(int i) {
		if(i >= 0 && i < 20) {
			return true;
		}
		return false;
	}



	/**
	 * Calls BattleshipGame.isInteger() with passed string, and radix 10
	 */
	public static boolean isInteger(String s) {
		return isInteger(s,10);
	}
	/**
	 * Passes a String, and returns whether is an integer
	 * @param String s
	 * @param radix - Used for Java API call to Character.digit()
	 */
	public static boolean isInteger(String s, int radix) {

		if(s.isEmpty() || s == null) 
			return false;

		for(int i = 0; i < s.length(); i++) {
			if(i == 0 && s.charAt(i) == '-') {
				if(s.length() == 1) return false;
				else continue;
			}
			if(Character.digit(s.charAt(i),radix) < 0) return false;
		}
		return true;
	}

	/** 
	 * For each entry in array, check if it is a valid integer
	 * @param arr
	 * @return
	 */
	public static boolean validateInputString(String[] arr) {
		if (arr.length != 10){
			return false;
		}
		for(String s : arr){
			if(!isInteger(s)) {
				return false;
			}
			else { // is an integer
				if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > 19) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Converts a string array to an int array
	 * @return - int[] 
	 */
	public static int[] convertToIntArr(String[] arr) {
		int[] ints = new int[10];
		for (int i = 0; i < arr.length; i++) {
			ints[i] = Integer.parseInt(arr[i]);
		}
		return ints;
	}

} 

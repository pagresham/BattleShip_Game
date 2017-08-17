/**
 * Ocean Class is the central Class in the game, analogous to the game board
 * @author piercegresham
 */
public class Ocean {


	private Ship[][] ships; // Used to quickly determine which ship is in any given location.
	private int shotsFired; // The total number of shots fired by the user.
	private int hitCount; // The number of times a shot hit a ship. If the user shoots the same part of a ship more than once, every hit is counted, even though the additional â€�hitsâ€� donâ€™t do the user any good.
	private int shipsSunk; // The number of ships sunk. Remember that you have a total of 13 ships. 

	/**
	 * Constructor. 
	 * Creates an empty ocean (fills the ships array with a bunch of EmptySea instances). 
	 * Also initializes any game variables, such as how many shots have been fired.
	 */
	public Ocean() {
		ships = new Ship[20][20];
		for (int i = 0;i < 20; i++) { // Fill ocean with empty sea
			for (int j = 0;j < 20; j++ ) {
				ships[i][j] = new EmptySea();
				// I may not need to set these values, but it makes sense now.
				ships[i][j].setBowRow(i);
				ships[i][j].setBowColumn(j);
			}
		}
		// set start values
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}

	/**
	 * OK ?!!
	 * Place all ships randomly on the (initially empty) ocean. 
	 * Place larger ships before smaller ones,  
	 * Use the Random class in the java.util package
	 * @return
	 */
	public void placeAllShipsRandomly() {
		// Initialize all ships
		BattleShip b = new BattleShip();
		BattleCruiser bc = new BattleCruiser();
		Cruiser c1 = new Cruiser();
		Cruiser c2 = new Cruiser();
		LightCruiser lc1 = new LightCruiser();
		LightCruiser lc2 = new LightCruiser();
		Destroyer d1 = new Destroyer();
		Destroyer d2 = new Destroyer();
		Destroyer d3 = new Destroyer();
		Submarine s1 = new Submarine();
		Submarine s2 = new Submarine();
		Submarine s3 = new Submarine();
		Submarine s4 = new Submarine();
		Ship[] fleet= new Ship[] {b, bc, c1, c2, lc1, lc2, d1, d2, d3, s1, s2, s3, s4};
		//	int shipsPlaced = 0;
		for(Ship i : fleet) {
			//	    System.out.println(i.getShipType());
			boolean placed = false;

			while(!placed) {
				int positionInt = (int)(Math.random()*2);
				boolean pos = (positionInt % 2 == 0) ? true : false; // if positionInt is 0 = True = horizontal
				int row = (int) (Math.random()* 20);
				int col = (int)(Math.random()* 20);
				//		System.out.println(row+" : "+col+" : "+pos);
				if(i.okToPlaceShipAt(row, col, pos, this)) {
					i.placeShipAt(row, col, pos, this);
					//		    shipsPlaced++;
					placed = true;
					//		    System.out.println(i.getShipType()+ " placed");
					//		    System.out.println("ships placed =========== "+ shipsPlaced);
				}
			}
		}
	}




	/**
	 * Returns true if the given location contains a real ship, still afloat, 
	 * (not an EmptySea), false if it does not. 
	 * Updates the number of shots that have been fired, and the number of hits. 
	 * Note: If a location contains a real ship, 
	 * shootAt should return true every time the user shoots at that same location. 
	 * Once a ship has been sunk, additional shots at its location should return false.
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean shootAt(int row, int column) {
		shotsFired++;
		//	System.out.println(ships[row][column].toString());
		if(ships[row][column].getShipType() != "empty") { // you hit a ship
			if(!ships[row][column].isSunk()) { // the ship is unSunk()
				hitCount++;

				// update ships info
				ships[row][column].shootAt(row, column) ;
				//		System.out.println(ships[row][column].isSunk());
				if(ships[row][column].isSunk()) { shipsSunk++; }
				return true;
			}
			else return false; // because the ship is sunk
		}
		else { // you did hit empty sea
			ships[row][column].shootAt(row, column); // empty sea shoot at is called
			return false;
		}
	}

	/**
	 * Prints the ocean
	 */
	public void print() {
		System.out.print(" Shots Fired: " + getShotsFired() + "    ");
		System.out.print("Hits Landed: " + getHitCount() + "    ");
		System.out.print("Ships Sunk: " + getShipsSunk() + "    ");
		System.out.println();
		System.out.println();
		for(int i = -1; i < 20; i++) {
			for(int j = -1; j < 20; j++) {
				if(i == -1) { // top row - display a blank, and then the column numbers
					if(j == -1) System.out.print("  ");
					else System.out.printf("%3d", j);
				}
				else {
					if (j == -1) System.out.printf("%2d ", i);
					else { // Inside of game board

						if(ships[i][j].getShipType().equals("empty")) { // This space contains only empty sea
							if(!ships[i][j].isShotAt()) { // this empty sea has not been shot at
								System.out.printf(" %s ",ships[i][j]);
							}
							else System.out.printf(" %s ", ships[i][j]);
						}
						else { // This space is a ship
							if(ships[i][j].checkHitArray(i, j)) { // if this space has been hit 
								System.out.print(" " + this.getShipArray()[i][j] + " ");
							}
							else {
								System.out.printf(" . ");
							}   
						}
					}
				}
			}
			System.out.println();
		}
	}

	// OK !!?
	/**
	 * Returns true if the given location contains a ship, false if it does not. 
	 */
	public boolean isOccupied(int row, int column) {
		// TODO Complete this method
		return (ships[row][column].getShipType().equals("empty")) ? false : true;
	}


	//May need to rework this to check to see if every non EmptySea space is hit
	/**
	 * Returns true if all ships have been sunk, otherwise false.
	 * @return boolean
	 */
	public boolean isGameOver() {
		//	return (getShipsSunk() >= 13) ? true : false;
		for(int i = 0; i < 20; i++) {
			for ( int j = 0; j < 20; j++) {
				if(!ships[i][j].getShipType().equals("empty")) { // hit a ship
					if(!ships[i][j].isSunk()) { // if ship is not sunk

						return false;
					}
				}
			}
		}
		return true;
	}


	// Getter Setter Methods
	public Ship[][] getShipArray() {
		return ships;
	}
	public void setShips(Ship[][] ships) {
		this.ships = ships;
	}
	public int getShotsFired() {
		return shotsFired;
	}
	public void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public int getShipsSunk() {
		return shipsSunk;
	}
	public void setShipsSunk(int shipsSunk) {
		this.shipsSunk = shipsSunk;
	}
}

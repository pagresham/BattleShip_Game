/**
 * Abstract class Ship, all other types of ships inherit from Ship, including emptyOcean 
 */

/**
 * SuperClass to all ships
 * @author piercegresham
 */
public abstract class Ship {

	// instance variables

	private int bowRow; // the row (0 to 19) which contains the bow (front) of the ship.
	private int bowColumn; // the column which contains the bow (front) of the ship. 
	private int length; // the number of squares occupied by the ship. An â€�empty seaâ€� location has length 1. 
	private boolean horizontal; // true if the ship occupies a single row, false otherwise. Ships will either be placed vertically or horizontally in the ocean.
	public boolean[] hit = new boolean[8]; // this is a boolean array of size 8 that record hits. Only battleships use all the locations. The others will use fewer.
	private boolean shotAt;

	// Auto generated getters - setters
	public int getBowRow() {
		return bowRow;
	}
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	public int getBowColumn() {
		return bowColumn;
	}
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	public boolean[] getHit() {
		return hit;
	}
	public boolean isShotAt() {
		return shotAt;
	}
	public void setShotAt(boolean shotAt) {
		this.shotAt = shotAt;
	}
	public void setHit(boolean[] hit) {
		this.hit = hit;
	}

	// All OK
	abstract String getShipType();

	// non abstract methods


	// OK !!
	/**
	 * Returns true if it is okay to put a ship of this length with its bow in this location, 
	 * with the given orientation, and returns false otherwise. The ship must not overlap another ship, 
	 * or touch another ship (vertically, horizontally, or diagonally), and it must not stick out 
	 * beyond the array. 
	 * @param row - int 
	 * @param column - int
	 * @param horizontal - bool
	 * @param ocean -Ocean
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// Attempt to place ships given horizontal placement
		if(horizontal) {
			// Check if space is out of bounds
			for(int i = column; i < column + this.getLength(); i++) {
				if(i > 19) return false;
			}
			
			for(int i = row -1; i <= row +1; i++) {
				for(int j = column -1; j <= column + this.getLength(); j++) {
					//		    System.out.println(i + "  :  "+j);
					if (i < 20 && i >= 0 && j < 20 && j >= 0) {
						//			System.out.println("Value being considered");
						//			System.out.println(ocean.getShipArray()[i][j].getShipType());
						if(!ocean.getShipArray()[i][j].getShipType().equals("empty")) {
							//			    System.out.println("Unable to place "+this.getShipType()+". Conflict at "+i+" : "+j);
							return false;
						}
					}
				}
			}
		}
		// Attempt to place ships given vertical placement
		if(!horizontal) {
			
			for(int i = row; i < row + this.getLength(); i++) {
				if(i > 19) return false;
			}
			// Check one space perimeter around proposed location, including the location itself.
			// Must all be empty sea to return true
			for(int i = row -1; i <= row + this.getLength(); i++) {
				for(int j = column -1; j <= column + 1; j++) {
					//		    System.out.println(i + "  :  "+j);
					if (i < 20 && i >= 0 && j < 20 && j >= 0) {
						//			System.out.println("Value being considered");
						//			System.out.println(ocean.getShipArray()[i][j].getShipType());
						if(!ocean.getShipArray()[i][j].getShipType().equals("empty")) {
							//			    System.out.println("Unable to place "+this.getShipType()+". Conflict at "+i+" : "+j);
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	// OK 
	/**
	 * Puts the ship in the ocean. This involves giving values to the bowRow, bowColumn, 
	 * and horizontal instance variables in the ship, and it also involves putting a reference to 
	 * the ship in each of 1 or more locations (up to 8) in the ships array in the Ocean object. 
	 * (Note: This will be as many as eight identical references; you can't refer to a part of a ship, 
	 * only to the whole ship.)
	 * @param row - int
	 * @param column - int 
	 * @param horizontal - bool
	 * @param ocean - Ocean
	 */
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		if(horizontal) {
			//	    String s = this.getShipType();
			for(int i = column; i < column + this.getLength(); i++) {
				ocean.getShipArray()[row][i] = this; // Sets location in sea to refer to the same ship as being placed. Will this work?!?
			}
		}
		else {
			for(int i = row; i < row + this.getLength(); i++) {
				//		System.out.println("i equals: "+i);
				ocean.getShipArray()[i][column] = this; // Sets location in sea to refer to the same ship as being placed. Will this work?!?
			}
		}
	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship hasnt been sunk, 
	 * mark that part of the ship as 'hit' (in the hit array, 0 indicates the bow) 
	 * and return true, otherwise return false.
	 * Updates the hit array
	 * @param row - int
	 * @param column - int
	 */
	
	/**
	 * Fires on a given space, and returns whether a hit occurs
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean shootAt( int row, int column) {
		//	System.out.println(row+"  "+column);
		//	System.out.println(this.getShipType());
		
		// Error checking
		if(!this.isValidShot(row, column))  return false; 
		//	if (!checkInputRange(row) || !checkInputRange(column)) return false;
		if(!this.isSunk()) {

			if(this.horizontal) {
				hit[column - bowColumn] = true;
			}
			else {
				hit[row - bowRow] = true;
			}
			return true;
		}

		return false;
	}

	/**
	 * Return true if every part of the ship has been hit, false otherwise. 
	 * @return
	 */
	public boolean isSunk() {
		// if any piece of the hip is un hit, return false
		for(int i = 0; i < this.getLength(); i++) { // go through hit array only to length of the ship in question
			if(hit[i] == false) {
				return false;
			}
		}
		return true;
	}




	// OK 
	/*
	 * Returns a single-character String to use in the Oceans print method (see below)
	 * This method should return 'x' if the ship has been sunk, 'S' if it has not been sunk. 
	 * This method can be used to print out locations in the ocean that have been shot at; 
	 * it should not be used to print locations that have not been shot at.
	 */
	@Override
	public String toString() {
		return (isSunk()) ? "x" : "S";
	}


	/**
	 * Checks all spaces in ships hit array to assess if it is sunk
	 */
	public boolean checkHitArray(int row, int col) {
		if(horizontal) {
			if(getHit()[col - getBowColumn()] == true) {
				return true;
			}
		}
		else {
			if (getHit()[row - getBowRow()] == true) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if shot fired is with range
	 */
	public static boolean checkInputRange(int i) {
		if(i >= 0 && i < 20) {
			return true;
		}
		return false;
	}

	public  boolean isValidShot(int shotRow, int shotCol) {
		int row = getBowRow();
		int col = getBowColumn();
		if(horizontal) {

			if (!(shotRow == row)) {
				return false;
			}
			else {
				if(shotCol >= getBowColumn() && shotCol <= getBowColumn() + this.length -1)  {
					return true;
				}
			}
		}
		else {
			if (!(shotCol == col)) {
				return false;
			}
			else if  (shotRow >= getBowRow()  && shotRow <= getBowRow() + this.length -1){
				return true;
			}

		}
		return false;
	}
}













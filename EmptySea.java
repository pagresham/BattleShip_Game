
public class EmptySea extends Ship {

	/**
	 * Constructor
	 */
	public EmptySea() {
		this.setShotAt(false);
		this.setLength(1); // default length of an empty patch of ocean
	}

	// OK
	/* (non-Javadoc)
	 * @see Ship#getShipType()
	 */
	@Override
	String getShipType() {
		return "empty";
	}

	// OK
	/**
	 * This method overrides shootAt(int row, int column) that is inherited from Ship, 
	 * and always returns false to indicate that nothing was hit.
	 */
	@Override
	public boolean shootAt(int row, int column) {
		this.setShotAt(true);
		return false;
	}

	// OK 
	/*
	 * This method overrides isSunk() that is inherited from Ship, 
	 * and always returns false to indicate that you didn’t sink anything. 
	 */
	@Override 
	public boolean isSunk() {
		return false;
	}

	// OK
	/**
	 * Returns a single-character String to use in the Oceans print method. 
	 * Since this is the empty sea, you could choose to have an unoccupied sea in many ways
	 */
	@Override
	public String toString() {
		return (isShotAt()) ? "-" : ".";
	}




}












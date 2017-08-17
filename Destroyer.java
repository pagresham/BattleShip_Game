
public class Destroyer extends Ship {
	/**
	 * Constructor
	 */
	public Destroyer() {
		this.setShotAt(false);
		this.setLength(4); // Set the inherited length variable to the correct value, 
	}

	/* (non-Javadoc)
	 * @see Ship#getShipType()
	 */
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "destroyer";
	}

}

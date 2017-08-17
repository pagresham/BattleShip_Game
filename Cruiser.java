
public class Cruiser extends Ship {
	/**
	 * Constructor
	 */
	public Cruiser() {
		this.setShotAt(false);
		this.setLength(6); // Set the inherited length variable to the correct value, 
	}

	/* 
	 * @see Ship#getShipType()
	 */
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "cruiser";
	}

}

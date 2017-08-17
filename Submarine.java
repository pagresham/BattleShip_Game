
public class Submarine extends Ship {
	/**
	 * Constructor 
	 */
	public Submarine() {
		this.setShotAt(false);
		this.setLength(3); // Set the inherited length variable to the correct value, 
	}

	/* 
	 * @see Ship#getShipType()
	 */
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "submarine";
	}

}

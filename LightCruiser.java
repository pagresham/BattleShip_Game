
public class LightCruiser extends Ship {
	/**
	 * Constructor 
	 */
	public LightCruiser() {
		this.setShotAt(false);
		this.setLength(5); // Set the inherited length variable to the correct value, 
	}

	/* (non-Javadoc)
	 * @see Ship#getShipType()
	 */
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "lightcruiser";
	}

}

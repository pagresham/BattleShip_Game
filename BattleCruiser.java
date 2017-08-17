
public class BattleCruiser extends Ship {

	public BattleCruiser() {
		this.setShotAt(false);
		this.setLength(7); // Set the inherited length variable to the correct value, 
	}

	/* 
	 * @see Ship#getShipType()
	 */
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "battlecruiser";
	}

}

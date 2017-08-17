
public class BattleShip extends Ship {

	public BattleShip() {
		this.setShotAt(false);
		this.setLength(8); // Set the inherited length variable to the correct value, 
	}

	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "battleship";
	}

}

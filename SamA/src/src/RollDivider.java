package src;
import java.util.*;
public class RollDivider {
	public static Vector<Roll> getRolls(Vector<Order> ords){
		Vector<Roll> rolls = new Vector<Roll>();
		for(Order ord : ords) {
			boolean added =false;
			for(Roll roll : rolls) {
				if(roll.isGroup(ord)) {
					roll.add(ord);
					added = true;
					break;
				}
			}
			if(added == false) {
				rolls.add(new Roll(ord));
			}
		}	
		return rolls;
	}
}

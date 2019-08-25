package src;
import java.util.*;
public class RollDivider {
	/**
	 * Divide Orders into Roll(오더를 Roll로 정리)
	 * @param ords 오더들
	 * @return	Vector<Roll> 롤
	 */
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

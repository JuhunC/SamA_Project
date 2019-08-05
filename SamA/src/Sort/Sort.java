package Sort;
import java.util.*;
import src.*;
public class Sort {
	
	public static Vector<Order> getWeekOrder(Vector<Order> ori_ords, int idx){
		Vector<Order> orders = new Vector<Order>();
		for(Order ord : ori_ords) {  
			if(ord.weightByWeek.elementAt(idx)>0.0) {
				orders.add(ord);
			}
		}
		return orders;
	}
}

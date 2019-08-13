package Sort;
import java.util.*;
import src.*;
public class Sort {

	public static Vector<Order> sortByBreadth(Vector<Order> ords){
		Collections.sort(ords);
		for(Order ord : ords) {
			System.out.println(ord.order_breadth);
		}
		return ords;
	}
	public static Vector<Order> getWeekOrder(Vector<Order> ori_ords, int idx){
		Vector<Order> orders = new Vector<Order>();
		for(Order ord : ori_ords) {  
			//System.out.println(ord.weightByWeek.size());
			if(ord.weightByWeek.elementAt(idx)>0.0) {
				orders.add(ord);
			}
		}
		return orders;
	}
}

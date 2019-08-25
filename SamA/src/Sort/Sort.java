package Sort;
import java.util.*;
import src.*;
public class Sort {
	/**
	 * Sort Orders by Breadth(폭 크기로 정리)
	 * @param ords
	 * @return
	 */
	public static Vector<Order> sortByBreadth(Vector<Order> ords){
		Collections.sort(ords);
		for(Order ord : ords) {
			System.out.println(ord.order_breadth);
		}
		return ords;
	}
	/**
	 * Get orders from specific Week(특정 주차의 오더 구분)
	 * @param ori_ords 오더들
	 * @param idx	주차
	 * @return	Vector<Order> 주차 오더들
	 */
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

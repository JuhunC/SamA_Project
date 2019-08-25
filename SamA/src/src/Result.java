package src;
import java.util.*;
public class Result {
	Material mat;
	int mat_index;
	String ROLL;
	String STRIP_LOT;
	int mat_weight;
	Vector<Order> ords;
	Vector<Integer> order_num;	// 개수
	int order_thickness;		// 두께
	int order_length;			// 길이
	int repeat;					// 횟수	
	String core_type;			// 코아
	int core_bore;				// 내경
	
	/**
	 * Check (Order thickness/length/core_type/core_bore)
	 * @param ord	오더
	 * @return	boolean 결과에 같이 사용될 수 있는지 판단
	 */
	boolean isSameResult(Order ord) {
		if(ord.order_thickness == this.order_thickness
				&& ord.order_length == this.order_length
				&& ord.core_type.equals(this.core_type)
				&& ord.core_bore == this.core_bore) {
			return true;
		}
		return false;
	}
	/**
	 * Add an Order x Num
	 * @param ord	오더
	 * @param num	오더개수
	 */
	boolean addOrder(Order ord, int num) {
		if(isSameResult(ord)== true) {
			ords.add(ord);
			order_num.add(num);	
			return true;
		}
		return false;
	}
	
	/**
	 * Return Number of Orders:오더의 개수 반환
	 * @return int 오더의 개수
	 */
	int getOrderSize() {
		return ords.size();
	}
}

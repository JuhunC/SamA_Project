package src;
import java.util.*;
public class Result {
	Material mat;
	int mat_index;
	String ROLL;
	String STRIP_LOT;
	int mat_weight;
	Vector<Order> ords;
	Vector<Integer> order_num;	// ����
	int order_thickness;		// �β�
	int order_length;			// ����
	int repeat;					// Ƚ��	
	String core_type;			// �ھ�
	int core_bore;				// ����
	
	/**
	 * Check (Order thickness/length/core_type/core_bore)
	 * @param ord	����
	 * @return	boolean ����� ���� ���� �� �ִ��� �Ǵ�
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
	 * @param ord	����
	 * @param num	��������
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
	 * Return Number of Orders:������ ���� ��ȯ
	 * @return int ������ ����
	 */
	int getOrderSize() {
		return ords.size();
	}
}

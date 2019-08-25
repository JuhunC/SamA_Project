package src;
import java.util.*;
public class Section {
	public int order_length;			// ����
	public int core_bore;				// ����
	public String core_type;			// �ھ�
	Vector<Order> ords = new Vector<Order>();
	
	/**
	 * Section Constructor(���� ������)
	 * @param ord	����
	 */
	Section(Order ord){
		this.order_length = ord.order_length;
		this.core_bore = ord.core_bore;
		this.core_type = ord.core_type;
	}
	/**
	 * add Order to the Section
	 * @param ord ����
	 */
	void addOrder(Order ord) {
		ords.add(ord);
	}
	/**
	 * Decide Order can be used in this Section(������ ���԰������� �Ǵ� ����)
	 * @param ord ����
	 * @return boolean	���԰��ɿ���
	 */
	boolean isSection(Order ord) {
		if(this.order_length == ord.order_length
				&& this.core_bore == ord.core_bore
				&& this.core_type.equals(ord.core_type)) {
			return true;
		}else
			return false;
	}
	/**
	 * Basic Print Method
	 */
	void print() {
		System.out.print("Section:"
				+ords.size()+"��\t"
				+order_length +"\t"
				+core_bore+"\t"
				+core_type+"\t");
		for(Order ord: ords) {
			System.out.print(ord.order_code+"\t");
		}System.out.println();
	}
}

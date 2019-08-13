package src;
import java.util.*;
public class Section {
	public int order_length;			// ����
	public int core_bore;				// ����
	public String core_type;			// �ھ�
	Vector<Order> ords = new Vector<Order>();
	
	void print() {
		System.out.print("Section:"
				+order_length +"\t"
				+core_bore+"\t"
				+core_type+"\t");
		for(Order ord: ords) {
			System.out.print(ord.order_code+"\t");
		}System.out.println();
	}
	Section(Order ord){
		this.order_length = ord.order_length;
		this.core_bore = ord.core_bore;
		this.core_type = ord.core_type;
	}
	void addOrder(Order ord) {
		ords.add(ord);
	}
	boolean isSection(Order ord) {
		if(this.order_length == ord.order_length
				&& this.core_bore == ord.core_bore
				&& this.core_type.equals(ord.core_type)) {
			return true;
		}else
			return false;
	}
}

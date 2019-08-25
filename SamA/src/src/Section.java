package src;
import java.util.*;
public class Section {
	public int order_length;			// 길이
	public int core_bore;				// 내경
	public String core_type;			// 코아
	Vector<Order> ords = new Vector<Order>();
	
	/**
	 * Section Constructor(섹션 생성자)
	 * @param ord	오더
	 */
	Section(Order ord){
		this.order_length = ord.order_length;
		this.core_bore = ord.core_bore;
		this.core_type = ord.core_type;
	}
	/**
	 * add Order to the Section
	 * @param ord 오더
	 */
	void addOrder(Order ord) {
		ords.add(ord);
	}
	/**
	 * Decide Order can be used in this Section(오더가 포함가능한지 판단 여부)
	 * @param ord 오더
	 * @return boolean	포함가능여부
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
				+ords.size()+"개\t"
				+order_length +"\t"
				+core_bore+"\t"
				+core_type+"\t");
		for(Order ord: ords) {
			System.out.print(ord.order_code+"\t");
		}System.out.println();
	}
}

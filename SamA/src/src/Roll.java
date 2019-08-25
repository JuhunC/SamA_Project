package src;
import java.util.*;

public class Roll {
	float order_thickness;
	String order_temper;
	String alloy_code;
	char doubling;
	Vector<Order> ords = new Vector<Order>();
	Vector<Section> sects = new Vector<Section>();
	
	// 원자재 구분
	String material_m;	// 원자재_M (제품생산구분)
	String material_temper;		// 원자재_T (원자재 TEMPER)
	
	// Trimming 미미 구분
	String order_type;
	String specific_order_type;
	/**
	 * Divide into Section.class
	 */
	void dividedIntoSections() {
		for(Order ord : ords) {
			boolean added =false;
			for(Section sect : sects) {
				if(sect.isSection(ord)==true) {
					sect.addOrder(ord);
					added= true;
					break;
				}
			}
			if(added == false) {
				Section sect = new Section(ord);
				sect.addOrder(ord);
				sects.add(sect);
			}
		}
	}
	/**
	 * Roll Constructor(롤 생성자-오더)
	 * @param ord	오더
	 */
	Roll(Order ord){
		this.order_thickness = ord.order_thickness;
		this.order_temper = ord.order_temper;
		this.alloy_code = ord.alloy_code.elementAt(0);
		this.doubling = ord.doubling;
		this.material_m = ord.material_m.elementAt(0);
		this.material_temper = ord.material_temper;
		this.order_type = ord.order_type;
		this.specific_order_type = ord.specific_order_type;
		ords.add(ord);
	}
	/**
	 * Roll Constructor(롤 생성자-param)
	 * @param order_thickness 오더두께
	 * @param order_temper	 원자재_T (원자재 TEMPER)
	 * @param alloy_code	(AB,AC,...) format
	 * @param doubling	권취
	 */
	Roll(float order_thickness, String order_temper,
			String alloy_code, char doubling){
		this.order_thickness = order_thickness;
		this.order_temper = order_temper;
		this.alloy_code = alloy_code;
		this.doubling = doubling;
	}
	/**
	 * Add order to Roll(오더 추가)
	 * @param ord	오더
	 */
	void add(Order ord) {
		ords.add(ord);
	}
	/**
	 * can be within the Group(그룹에 추가 가능한지 여부)
	 * @param ord 오더
	 * @return	boolean 오더 추가가능 여부
	 */
	boolean isGroup(Order ord) {
		if(order_thickness==ord.order_thickness
				&& ord.order_temper.equals(this.order_temper)
				&& ord.doubling == this.doubling) {
			for(int i=0;i<ord.alloy_code.size();i++) {
				if(ord.alloy_code.elementAt(i).equals(this.alloy_code)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Basic Print Method
	 */
	void print() {
		System.out.print(
				this.order_thickness+"\t"
				+this.order_temper +"\t"
				+this.alloy_code+"\t"
				+this.doubling+"\t"
				+this.ords.size()+"개\t:\t");
		for(Order ord:ords) {
			System.out.print(ord.order_code+"\t");
		}System.out.println();
		for(Section sect : sects) {
			sect.print();
		}
	}
}

package src;
import java.util.*;

public class Roll {
	float order_thickness;
	String order_temper;
	String alloy_code;
	char doubling;
	Vector<Order> ords = new Vector<Order>();
	Vector<Section> sects = new Vector<Section>();
	
	// ������ ����
	String material_m;	// ������_M (��ǰ���걸��)
	String material_temper;		// ������_T (������ TEMPER)
	
	// Trimming �̹� ����
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
	 * Roll Constructor(�� ������-����)
	 * @param ord	����
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
	 * Roll Constructor(�� ������-param)
	 * @param order_thickness �����β�
	 * @param order_temper	 ������_T (������ TEMPER)
	 * @param alloy_code	(AB,AC,...) format
	 * @param doubling	����
	 */
	Roll(float order_thickness, String order_temper,
			String alloy_code, char doubling){
		this.order_thickness = order_thickness;
		this.order_temper = order_temper;
		this.alloy_code = alloy_code;
		this.doubling = doubling;
	}
	/**
	 * Add order to Roll(���� �߰�)
	 * @param ord	����
	 */
	void add(Order ord) {
		ords.add(ord);
	}
	/**
	 * can be within the Group(�׷쿡 �߰� �������� ����)
	 * @param ord ����
	 * @return	boolean ���� �߰����� ����
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
				+this.ords.size()+"��\t:\t");
		for(Order ord:ords) {
			System.out.print(ord.order_code+"\t");
		}System.out.println();
		for(Section sect : sects) {
			sect.print();
		}
	}
}

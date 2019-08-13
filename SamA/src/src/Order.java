package src;
import java.util.*;
public class Order implements Comparable<Order>{
	public int team_num;				// 팀
	public String customer;				// 고객명
	public String order_type;			// 품목
	public String specific_order_type;	// 세부품목
	public boolean consider = false;	// 폭조합
	public String order_code;			// 제품코드
	public float order_thickness;		// 두께
	public float order_breadth;			// 폭
	public int order_length;			// 길이
	public float order_length_min;		// 최소길이
	public float order_length_max;		// 최대길이
	public Vector<String> alloy_code = new Vector<String>();	// (AB,AC,...) format
	public Vector<String> alloy = new Vector<String>();		// (A***) format
	public String order_temper;			// TEMPER
	public char doubling;				// 권취
	public int core_bore;				// 내경
	public String core_type;			// 코아
	public Vector<String> material_m = new Vector<String>();	// 원자재_M (제품생산구분)
	public String material_temper;		// 원자재_T (원자재 TEMPER)
	public Vector<Float> weightByWeek;	// 매주 원자재 투입량
	public void print() {
		System.out.println(
				team_num +"\t"
				+ customer + "\t"
				+ order_type + "\t"
				+ specific_order_type+"\t"
				+ consider + "\t"
				+ order_code + "      \t"
				+ order_thickness + "\t"
				+ order_breadth + "\t"
				+ order_length + "\t"
				+ order_length_min + "\t"
				+ order_length_max + "\t"
				+ alloy_code + "\t"
				+ alloy + "\t"
				+ order_temper + "\t"
				+ doubling + "\t"
				+ core_bore + "\t"
				+ core_type + "\t"
				+ material_m + "\t"
				+ material_temper + "\t"
				+ weightByWeek+"\t"
				);
	}
	Order(int team_num, 
			String customer,
			String order_type,
			String specific_order_type,
			boolean consider,
			String order_code,
			float order_thickness,
			float order_breadth,
			int order_length,
			float order_length_min,
			float order_length_max,
			String alloy_code,
			String order_temper,
			char doubling,
			int core_bore,
			String core_type,
			String material_m,
			String material_temper,
			Vector<Float> weightByWeek
			){
		this.team_num = team_num;
		this.customer = customer;
		this.order_type = order_type;
		this.specific_order_type = specific_order_type;
		this.consider = consider;
		this.order_code = order_code;
		this.order_thickness = order_thickness;
		this.order_breadth = order_breadth;
		this.order_length = order_length;
		this.order_length_min = order_length_min;
		this.order_length_max = order_length_max;
		String arr[] = alloy_code.split(",");
		for(String str:arr) {
			this.alloy_code.add(str);
			this.alloy.add(Format.getAlloy(str));
		}

		this.order_temper = order_temper;
		this.doubling = doubling;
		this.core_bore = core_bore;
		this.core_type = core_type;
		String arr1[] = material_m.split(",");
		for(String str:arr1) {
			this.material_m.add(str);
		}
		this.material_temper = material_temper;
		this.weightByWeek = weightByWeek;
	}
	@Override
	public int compareTo(Order ord) {
		return (int)(ord.order_breadth - this.order_breadth);
	}
}

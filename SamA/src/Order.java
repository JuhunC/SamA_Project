import java.util.*;
public class Order {
	public int team_num;				// ��
	public String customer;				// ����
	public String order_type;			// ǰ��
	public boolean consider = false;	// ������
	public String order_code;			// ��ǰ�ڵ�
	public float order_thickness;		// �β�
	public float order_breadth;			// ��
	public int order_length;			// ����
	public float order_length_min;		// �ּұ���
	public float order_length_max;		// �ִ����
	public Vector<String> alloy_code = new Vector<String>();	// (AB,AC,...) format
	public Vector<String> alloy = new Vector<String>();		// (A***) format
	public String order_temper;			// TEMPER
	public char doubling;				// ����
	public int core_bore;				// ����
	public String core_type;			// �ھ�
	public char material_m;				// ������_M (��ǰ���걸��)
	public String material_temper;		// ������_T (������ TEMPER)
	void print() {
		System.out.println(
				team_num +"\t"
				//+ customer + "\t"
				+ order_type + "\t"
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
				
				);
	}
	Order(int team_num, 
			String customer,
			String order_type,
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
			char material_m,
			String material_temper
			){
		this.team_num = team_num;
		this.customer = customer;
		this.order_type = order_type;
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
		this.material_m = material_m;
		this.material_temper = material_temper;
	}
}

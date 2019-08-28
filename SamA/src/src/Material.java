package src;
import java.util.*;

import Sort.Calculate;
import Standard.Format;
import Standard.Trim;

import java.text.*;
public class Material {
	public String material_code;	// ��ǰ�ڵ�
	public String ROLL;				// ROLL
	public String STRIP_LOT;		// STRIP LOT
	public int material_thickness;	// �ǵβ�
	public int material_breadth;	// ����
	public String alloy_code;		// (AB,AC,...) format
	public String alloy;			// (A***) format
	public String material_temper;	// TEMPER
	public double weight;				// �����߷�
	public Date produced_date;		// ��������
	
	// Grouping Method
	public int max_len;						// ������ �ִ����(�β��� order_thickness�� ��)
	public boolean isInitialized = false;	// ���� ���̺� �ʱ�ȭ ����
	public float order_thickness;			//
	public String order_alloy_code;
	public String order_alloy;
	public String order_temper;
	public char doubling;
	public int core_bore;
	public String core_type;
	
	// Order Table
	public static int ROW = 100;
	public static int COL = 10;
	public float t_breadth[][] = new float[ROW][COL];
	public String t_order_code[][] = new String[ROW][COL];
	public float t_thickness[]= new float [ROW];
	public int t_breadth_total[] = new int[ROW];
	public int t_length[] = new int[ROW];
	public int t_length_sum = 0;
	public double t_weight_t[] = new double[ROW];
	public double t_weight[][] = new double[ROW][COL];
	public double t_sum_weight = 0;
	public double t_pure_weight = 0;

	
	public int t_core_bore[] = new int[ROW];
	public String t_core_type[] = new String[ROW];
	/**
	 * get Loss Weight(ton)
	 * @return
	 */
	double getLoss() {
		update();
		return this.weight - this.t_sum_weight;
	}
	/**
	 * Print Order Table(if used)
	 */
	void printTable() {
		if(isInitialized == false)
			return;
		System.out.println();
		System.out.println(this.material_code+"\t"+this.material_breadth+"\t"+this.getLoss()+"/"+this.weight
				+"\t"+(this.weight-this.t_pure_weight) +"/"+this.weight);
		System.out.println(doubling+"\t"+order_temper+"\t"+this.alloy);
		for(int r=0;r<10;r++) {
			System.out.print(this.t_thickness[r]+"\t|\t");
			for(int c=0;c<5;c++) {
				System.out.print(this.t_breadth[r][c]+"\t");
			}
			System.out.print(this.t_length[r]+"����\t|\t"
					+this.t_breadth_total[r]+"mm\t|\t");
			System.out.print(this.t_weight_t[r]+"��\t|\t"+this.t_core_bore[r]
					+"\t|\t"+this.t_core_type[r]+"\n");
		}
		for(int r=0;r<10;r++) {
			System.out.print("\t\t");
			for(int c=0;c<5;c++) {
				if(this.t_order_code[r][c] != null)
					System.out.print(this.t_order_code[r][c]+"\t");
				else System.out.print("\t\t\t");
			}System.out.println();
		}
		System.out.println();
	}
	/**
	 * Update Order Table
	 */
	void update() {
		this.t_pure_weight = 0;
		for(int r= 0; r< ROW;r++) {
			int breadth_sum = 0;
			int cnt =0;
			for(int c=0;c<COL;c++) {
				breadth_sum += this.t_breadth[r][c];
				if(this.t_breadth[r][c]>0) {
					cnt++;
				}
			}
			this.t_breadth_total[r] = breadth_sum;
			this.t_weight_t[r] = Calculate.getWeight(this.t_thickness[r], this.t_length[r], this.material_breadth);
			for(int c=0;c<COL;c++) {
				this.t_weight[r][c] = this.t_thickness[r]*this.t_breadth[r][c]
						*this.t_length[r]*2.71*0.000001/1000;
				this.t_pure_weight += this.t_weight[r][c];
			}
		}
		t_sum_weight = 0;
		t_length_sum =0;
		for(int r= 0;r<ROW;r++) {
			t_sum_weight+= this.t_weight_t[r];
			t_length_sum += this.t_length[r];
		}
	}
	/**
	 * Add Order(if possible - columns first, then rows)
	 * @param ord ����
	 * @return	boolean
	 */
	boolean addOrder(Order ord) {
		// weight of one Order product
		double ord_weight = Calculate.getWeight(ord.order_thickness, ord.order_length, ord.order_breadth);
		
		if(ord_weight + this.t_sum_weight < this.weight) { // Check OverWeight(���� �ʰ� Ȯ��)
			for(int r=0;r<ROW;r++) {
				if(this.t_thickness[r] == 0) {// New Input(row) ���� ù ���� �߰�
					// �� ���� �ʰ�(���� ��) && ���� �ʰ� Ȯ��(�̹� ����)
					if(this.t_length_sum+ord.order_length < this.max_len
							&& this.t_sum_weight + Calculate.getWeight(ord.order_thickness, ord.order_length, this.material_breadth) < weight
							&& ord.order_breadth + Trim.getTrimRate(ord.order_type, ord.specific_order_type, 1)< this.material_breadth) {
							//&& ord_weight+Trim.getTrimRate(ord.order_type, ord.specific_order_type, 1) + t_sum_weight < weight) {
						this.t_thickness[r] = ord.order_thickness;
						this.t_breadth[r][0] = ord.order_breadth;
						this.t_order_code[r][0] = ord.order_code;
						this.t_length[r] = ord.order_length;
						this.t_core_bore[r] = ord.core_bore;
						this.t_core_type[r] = ord.core_type;
						return true;
					}
					return false; // ���� �ʰ� Ȥ�� ���� �ʰ�(�̹�����)
				}else {
					// �����ϴ� ���� �߰�
					if(this.t_thickness[r] == ord.order_thickness
							&& this.t_core_bore[r] == ord.core_bore
							&& this.t_core_type[r].equals(ord.core_type)) { // �β� ��ġ
						int cnt = 0;
						float sum =0;
						for(int c=0;c<COL;c++) {
							if(this.t_breadth[r][c]>0) {
								sum += t_breadth[r][c];
								cnt++;
							}
						}
						if(cnt<4
							&& sum + ord.order_breadth + Trim.getTrimRate(ord.order_type, ord.specific_order_type, cnt+1)<=this.material_breadth
							&& ord_weight+ this.t_sum_weight < weight) {
							t_breadth[r][cnt] = ord.order_breadth;
							t_order_code[r][cnt] = ord.order_code;
							return true;
						}
					}
				}
			}// end of for(r)
		}
		return false;
	}
	/**
	 * Add Order if Possible(�߰� ������ ����(1��)�� �߰��ϱ�)
	 * @param ord ����
	 * @return boolean �߰�����
	 */
	boolean addIfPossible(Order ord) {
		update();
		if(isInitialized == false
				&& ord.order_breadth + Trim.getTrimRate(ord.order_type, ord.specific_order_type, 1) < this.material_breadth
				&& ord.material_m.elementAt(0).equals(this.getMaterial_M())
				&& ord.material_temper.equals(this.material_temper)
				&& ord.alloy_code.elementAt(0).equals(this.alloy_code)) {
			// Initialize Order Table for this material
			isInitialized = true;
			// Initialize Grouping Method Variable
			this.order_thickness = ord.order_thickness;
			this.order_alloy = ord.alloy.elementAt(0);
			this.order_alloy_code = ord.alloy_code.elementAt(0);
			this.order_temper = ord.order_temper;
			this.doubling = ord.doubling;
//			this.core_bore = ord.core_bore;
//			this.core_type = ord.core_type;
			this.max_len = Calculate.calculateOrderLength((double)this.order_thickness, (double)this.material_breadth, (int)this.weight*1000);
			
			// Add order(���� �߰�)
			this.addOrder(ord);
			update();
			
			return true;
		}else if(isInitialized == true
				&& this.order_thickness == ord.order_thickness
				//&& this.order_alloy.equals(ord.alloy.elementAt(0))
				&& this.order_alloy_code.equals(ord.alloy_code.elementAt(0))
				&& this.order_temper.equals(ord.order_temper)
				//&& this.material_temper.equals(ord.material_temper)
				&& this.material_temper.equals(ord.material_temper)
				&& this.getMaterial_M().equals(ord.material_m.elementAt(0))
				&& this.doubling == ord.doubling
				&& this.alloy_code.equals(ord.alloy_code.elementAt(0))
//				&& this.alloy.equals(ord.alloy.elementAt(0))
//				&& this.core_bore == ord.core_bore
//				&& this.core_type.equals(ord.core_type)
				){
			// Add Order(������ �߰�)
			return this.addOrder(ord);
		}else
			return false;
	}
	/**
	 * Material Constructor
	 * @param material_code	��ǰ�ڵ�
	 * @param ROLL ROLL
	 * @param STRIP_LOT	STRIP LOT
	 * @param material_thickness	�ǵβ�
	 * @param material_breadth	����
	 * @param alloy	(AB,AC,...) format
	 * @param material_temper	(A***) format
	 * @param weight	�����߷�
	 * @param Date_str	��������
	 */
	Material(String material_code,
			String ROLL,
			String STRIP_LOT,
			int material_thickness,
			int material_breadth,
			String alloy,
			String material_temper,
			int weight,
			String Date_str
			){
		this.material_code = material_code;
		this.ROLL = ROLL;
		this.STRIP_LOT = STRIP_LOT;
		this.material_thickness = material_thickness;
		this.material_breadth = material_breadth;
		this.alloy =alloy;
		this.alloy_code = Format.getAlloyCode(alloy);
		// TODO : calculate alloy_code
		this.material_temper = material_temper;
		this.weight = weight/1000;
		try {
			this.produced_date = new SimpleDateFormat("mm/dd/yy").parse(Date_str);
			this.produced_date.setYear(this.produced_date.getYear()+2000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Wrong Date Format");
		}		
	}
	/**
	 * Get Material Maker (������ ����ȸ�� ���� getter)
	 * @return material_m
	 */
	String getMaterial_M() {
		return ""+ material_code.charAt(material_code.length()-1);
	}
	/**
	 * Basic Print Method
	 */
	void print() {
		System.out.println(
				material_code+"\t"
				+ROLL+"\t"
				+STRIP_LOT+"\t"
				+material_thickness+"\t"
				+material_breadth+"\t"
				+alloy_code+"\t"
				+alloy+"\t"
				+material_temper+"\t"
				+weight+"\t"
				+produced_date
				);
		
	}
}

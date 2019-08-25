package src;
import java.util.*;

import Sort.Calculate;
import Standard.Format;
import Standard.Trim;

import java.text.*;
public class Material {
	public String material_code;	// 제품코드
	public String ROLL;				// ROLL
	public String STRIP_LOT;		// STRIP LOT
	public int material_thickness;	// 실두께
	public int material_breadth;	// 실폭
	public String alloy_code;		// (AB,AC,...) format
	public String alloy;			// (A***) format
	public String material_temper;	// TEMPER
	public double weight;				// 포장중량
	public Date produced_date;		// 생산일자
	
	public int max_len;
	public boolean isInitialized = false;
	public float order_thickness;
	public String order_alloy_code;
	public String order_alloy;
	public String order_temper;
	public char doubling;
	public int core_bore;
	public String core_type;
	
	public static int ROW = 100;
	public static int COL = 10;
	public float t_breadth[][] = new float[ROW][COL];
	public float t_thickness[]= new float [ROW];
	public int t_breadth_total[] = new int[ROW];
	public int t_length[] = new int[ROW];
	public int t_length_sum = 0;
	public double t_weight_t[] = new double[ROW];
	public double t_wegiht[][] = new double[ROW][COL];
	public double t_sum_weight = 0;
	double getLoss() {
		update();
		return this.weight - this.t_sum_weight;
	}
	void printTable() {
		if(isInitialized == false)
			return;
		System.out.println();
		System.out.println(this.material_code+":"+this.material_breadth+":"+this.getLoss());
		for(int r=0;r<10;r++) {
			System.out.print(this.t_thickness[r]+"\t|\t");
			for(int c=0;c<10;c++) {
				System.out.print(this.t_breadth[r][c]+"\t");
			}
			System.out.print("|"+this.t_thickness[r]+"|");
			System.out.print(this.t_length[r]+"|");
			System.out.print(this.t_weight_t[r]+"\n");
		}
		System.out.println();
	}
	void update() {
		for(int r= 0; r< ROW;r++) {
			int breadth_sum = 0;
			for(int c=0;c<COL;c++) {
				breadth_sum += this.t_breadth[r][c];
			}
			this.t_breadth_total[r] = breadth_sum;
			this.t_weight_t[r] = this.t_thickness[r]*this.t_breadth_total[r]
					*this.t_length[r]*2.71*0.000001/1000;
			for(int c=0;c<COL;c++) {
				this.t_wegiht[r][c] = this.t_thickness[r]*this.t_breadth[r][c]
						*this.t_length[r]*2.71*0.000001/1000;
			}
		}
		t_sum_weight = 0;
		t_length_sum =0;
		for(int r= 0;r<ROW;r++) {
			t_sum_weight+= this.t_weight_t[r];
			t_length_sum += this.t_length[r];
		}
	}
	boolean addOrder(Order ord) {
		double ord_weight = Calculate.getWeight(ord.order_thickness, ord.order_length, ord.order_breadth);
		//System.out.println(this.getLoss()+"\t:\t"+ord_weight+"\t:\t"+t_sum_weight);
		if(ord_weight + this.t_sum_weight < this.weight) {
			for(int r=0;r<ROW;r++) {
				if(this.t_thickness[r] == 0) {// 새로운 줄을 추가할 떄
					if(this.t_length_sum+ord.order_length < this.max_len
							&& ord_weight+Trim.getTrimRate(ord.order_type, ord.specific_order_type, 1) + t_sum_weight < weight) {
						this.t_thickness[r] = ord.order_thickness;
						this.t_breadth[r][0] = ord.order_breadth;
						this.t_length[r] = ord.order_length;
						return true;
					}
					return false; // 너무 길어짐
				}else {
					if(this.t_thickness[r] == ord.order_thickness) { // 두께 일치
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
							&& ord_weight+Trim.getTrimRate(ord.order_type, ord.specific_order_type, cnt+1)< weight) {
							t_breadth[r][cnt] = ord.order_breadth;
							return true;
						}
					}
				}
			}// end of for(r)
		}
		return false;
		// weight over
		// length over
		// breadth over
	}
	boolean addIfPossible(Order ord) {
		update();
		if(isInitialized == false
				&& ord.order_breadth + Trim.getTrimRate(ord.order_type, ord.specific_order_type, 1) < this.material_breadth
				&& ord.material_m.elementAt(0).equals(this.getMaterial_M())
				&& ord.material_temper.equals(this.material_temper)) {
			isInitialized = true;
			this.order_thickness = ord.order_thickness;
			this.order_alloy = ord.alloy.elementAt(0);
			this.order_alloy_code = ord.alloy_code.elementAt(0);
			this.order_temper = ord.order_temper;
			this.doubling = ord.doubling;
			this.core_bore = ord.core_bore;
			this.core_type = ord.core_type;
			this.max_len = Calculate.calculateOrderLength((double)this.order_thickness, (double)this.material_breadth, (int)this.weight*1000);
			this.addOrder(ord);
			update();
			
			//TODO: add first order to the material;
			return true;
		}else if(isInitialized == true
				&& this.order_thickness == ord.order_thickness
				//&& this.order_alloy.equals(ord.alloy.elementAt(0))
				&& this.order_alloy_code.equals(ord.alloy_code.elementAt(0))
				&& this.order_temper.equals(ord.order_temper)
				&& this.material_temper.equals(ord.material_temper)
				&& this.getMaterial_M().equals(ord.material_m.elementAt(0))
				&& this.doubling == ord.doubling
				&& this.core_bore == ord.core_bore
				&& this.core_type.equals(ord.core_type)){
			return this.addOrder(ord);
			//return false;
			//	  vs
			//return true;
			//return true;
		}else 
			return false;
	}
	/**
	 * Material Constructor
	 * @param material_code	제품코드
	 * @param ROLL ROLL
	 * @param STRIP_LOT	STRIP LOT
	 * @param material_thickness	실두께
	 * @param material_breadth	실폭
	 * @param alloy	(AB,AC,...) format
	 * @param material_temper	(A***) format
	 * @param weight	포장중량
	 * @param Date_str	생산일자
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
	 * Get Material Maker (원자제 제작회사 약자 getter)
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

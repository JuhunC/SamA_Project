import java.util.*;
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
	public int weight;				// 포장중량
	public Date produced_date;		// 생산일자
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
		this.weight = weight;
		try {
			this.produced_date = new SimpleDateFormat("mm/dd/yy").parse(Date_str);
			this.produced_date.setYear(this.produced_date.getYear()+2000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Wrong Date Format");
		}		
	}
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

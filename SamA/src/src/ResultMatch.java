package src;
import java.util.*;
public class ResultMatch {
	public Material mat; // contains 원자재, ROLL, STRIP_LOT
	
	// 오더 별 매칭
	public float result_thickness;	//두께
	public int result_length;		//길이
	public int times; 				//횟수
	public String result_core_type;	//코아
	public int result_core_bore;	//내경
	
	// 생산 오더별
	public Vector<Order> ords; 			// 각각의 폭 정의
	public Vector<Integer> num; 		// 개수
	public Vector<Float> ord_weight;	//생산량
	
	public void print() {
		if(Src.verbose == true) {
			System.out.print(
					mat.material_code +"\t"
					+ mat.ROLL + "\t"
					+ mat.STRIP_LOT+"\t"
					+ mat.weight+"\t");
			for(Order ord: ords) {
				System.out.print(ord.order_code+"\t");
			}
			System.out.print(result_thickness+"\t");
			for(int i=0;i<ords.size();i++) {
				System.out.print(ords.elementAt(i).order_breadth+":"
						+ num.elementAt(i)+"\t");
			}
			System.out.print(result_length+"\t"
					+times+"\t"
					+result_core_type+"\t"
					+result_core_bore+"\t");
			for(Float f : ord_weight) {
				System.out.print(f+"\t");
			}
			System.out.println();
		}
	}
}

package src;
import java.util.*;
public class ResultMatch {
	public Material mat; // contains ������, ROLL, STRIP_LOT
	
	// ���� �� ��Ī
	public float result_thickness;	//�β�
	public int result_length;		//����
	public int times; 				//Ƚ��
	public String result_core_type;	//�ھ�
	public int result_core_bore;	//����
	
	// ���� ������
	public Vector<Order> ords; 			// ������ �� ����
	public Vector<Integer> num; 		// ����
	public Vector<Float> ord_weight;	//���귮
	
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

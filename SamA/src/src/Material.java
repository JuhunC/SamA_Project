package src;
import java.util.*;

import Standard.Format;

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

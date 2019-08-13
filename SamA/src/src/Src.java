package src;
import java.util.*;
import Sort.*;
public class Src {
	public static boolean verbose = false;
	public static String order_plan_dir="./생산계획_작성중.xls";
	public static String material_dir="./원자재내역.xls";
	public static String format_dir = "./폭조합용 세부자료_V1_190726.xls";
	public static void main(String args[]) {
		Format f = new Format(format_dir);
		Vector<Order> orders = OrderReader.Read(format_dir);
		Vector<Material> materials = MaterialReader.Read(material_dir);

		
		
		for(int i =0;i<13;i++) {
			Vector<Order> weekOrder = Sort.getWeekOrder(orders, i);
			System.out.println(i+" week : "+weekOrder.size()+" orders");
		}
		Vector<Order> week1_ords = new Vector<Order>();
		for(Order ord : orders) {
			if(ord.weightByWeek.elementAt(0)!=0) {
				week1_ords.add(ord);
			}
		}
		
		
		
		Vector<Roll> rolls = RollDivider.getRolls(week1_ords);
		
		for(Roll roll : rolls) {
			
			roll.dividedIntoSections();
			roll.print();
		}
		
		
		
//		System.out.println(Calculate.getTimes(9,24000,1238,4.3));
//		System.out.println(Calculate.getTimes(9,24000,750,2.6));
		
	}
}
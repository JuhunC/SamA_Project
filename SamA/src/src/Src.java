package src;
import java.util.*;
import Sort.*;
import Standard.Format;
import Standard.Trim;
public class Src {
	public static boolean verbose = false;
	public static String order_plan_dir="./생산계획_작성중.xls";
	public static String material_dir="./원자재내역.xls";
	public static String format_dir = "./폭조합용 세부자료_V1_190726.xls";
	public static void main(String args[]) {
		Format.set_file_path(format_dir);
		Trim.set_file_path(format_dir);
		
		Vector<Order> orders = OrderReader.Read(format_dir);
		Vector<Material> materials = MaterialReader.Read(material_dir);

		for(int i =0;i<13;i++) {
			Vector<Order> weekOrder = Sort.getWeekOrder(orders, i);
			System.out.println(i+" week : "+weekOrder.size()+" orders");
		}
		for(Order ord : orders)
			ord.setWeek(1, 4);
		
		for(Order ord: orders) {
			boolean changed = true;
			while(true) {
				if(changed == false || ord.times == 0)
					break;
				changed = false;
				for(Material mat: materials) {
					if(mat.addIfPossible(ord)) {
						ord.times--;
						changed = true;
						break;
					}
				}
				
			}
		}
		
		for(Material mat : materials) {
			//if(mat.getLoss()<1.0)
			if(mat.getLoss() != mat.weight)
			//mat.print();
			mat.printTable();
			//break;
		}
//		Vector<Order> week1_ords = new Vector<Order>();
//		for(Order ord : orders) {
//			if(ord.weightByWeek.elementAt(0)!=0
//					|| ord.weightByWeek.elementAt(1)!=0
//					|| ord.weightByWeek.elementAt(2)!=0
//					|| ord.weightByWeek.elementAt(3)!=0) {
//				week1_ords.add(ord);
//			}
//		}
//		
//		
//		Vector<Roll> rolls = RollDivider.getRolls(week1_ords);
//		for(Roll roll : rolls) {
//			System.out.println();
//			roll.dividedIntoSections();
//			roll.print();
//		}
//		
//		System.out.println("***************************************************************");
//		Vector<Boolean> used = new Vector<Boolean>();
//		for(int i=0;i<materials.size();i++) used.add(false);
//		for(Roll roll : rolls) {
//			System.out.println();
//			System.out.print("ROLL : "+ roll.order_thickness+"\t"+roll.ords.size()+"개\n");
//			
//			// find material
//			Vector<Material> mat_list = new Vector<Material>();
//			for(int i =0;i<materials.size();i++) {
//				Material mat = materials.elementAt(i);
//				if(mat.material_temper.equals(roll.material_temper)
//					&& mat.getMaterial_M().equals(roll.material_m)){
//					mat_list.add(mat);
//				}
//			}
//			System.out.print("MatList:"+mat_list.size()+"\t:");
//			for(Material mat : mat_list) {
//				System.out.print(mat.material_breadth+"\t");
//			}System.out.println();
//			// start 폭조합
//			int min_breadth_diff = Integer.MAX_VALUE;
//			int min_mat_idx = -1;
//			for(int mat_idx = 0; mat_idx < mat_list.size();mat_idx++) {
//				Material match_mat = mat_list.elementAt(mat_idx);
//				for(Section sect : roll.sects) {
//					Collections.sort(sect.ords);
//					int breadth = match_mat.material_breadth;
//					Stack<Order> cur_ord = new Stack<Order>();
//					int cur_breadth = 0;
//					int order_cnt =0;
//					for(int idx = 0; idx<sect.ords.size();idx++) {
//						Order ord = sect.ords.elementAt(idx);
//						if(ord.weightByWeek.elementAt(0)!=0
//								|| ord.weightByWeek.elementAt(1)!=0
//								|| ord.weightByWeek.elementAt(2)!=0
//								|| ord.weightByWeek.elementAt(3)!=0) {
//							cur_ord.add(ord);
//							cur_breadth += ord.order_breadth;
//							order_cnt++;
//							int trim_rate = Trim.getTrimRate(roll.order_type, roll.specific_order_type, order_cnt);
//							if(trim_rate >= 0
//								&& cur_breadth + trim_rate < breadth
//								&& order_cnt < 4) {
//								idx--;
//								continue;
//							}else {
//								cur_ord.pop();
//								cur_breadth-=ord.order_breadth;
//								order_cnt--;
//								break;
//							}
//						}
//					}
//					double mat_weight = match_mat.weight;
//					double cal_weight = Calculate.getWeight(roll.order_thickness, sect.order_length, cur_breadth);
//					if(cur_ord.size() > 0 
//							&& match_mat.material_breadth-cur_breadth < min_breadth_diff) {
//						min_breadth_diff = match_mat.material_breadth - cur_breadth;
//						min_mat_idx = mat_idx;
//					}
//					
//					
////					//System.out.println();
////					System.out.print("Matched Material : "+match_mat.material_code+"\t:"+match_mat.material_breadth);
////					if(cur_ord.size()>0) {
////						System.out.println((cur_breadth+Trim.getTrimRate(roll.order_type, roll.specific_order_type, cur_ord.size()))+"/"+match_mat.material_breadth);
////						for(int i =0;i<cur_ord.size();i++) {
////							System.out.print(cur_ord.elementAt(i).order_breadth+"\t");
////							//cur_ord.elementAt(i).print();
////						}
////					}
////					System.out.println();
//				}
//			}
//			System.out.println(min_mat_idx);
//			if(min_mat_idx != -1) {
//				Material match_mat = mat_list.elementAt(min_mat_idx);
//				for(Section sect : roll.sects) {
////					for(Order ordd : sect.ords) {
////						System.out.print(ordd.order_breadth+"\t");
////					}System.out.println();
//					Collections.sort(sect.ords);
//					int breadth = match_mat.material_breadth;
//					Stack<Order> cur_ord = new Stack<Order>();
//					int cur_breadth = 0;
//					int order_cnt =0;
//					for(int idx = 0; idx<sect.ords.size();idx++) {
//						Order ord = sect.ords.elementAt(idx);
//						if(ord.weightByWeek.elementAt(0)!=0
//								|| ord.weightByWeek.elementAt(1)!=0
//								|| ord.weightByWeek.elementAt(2)!=0
//								|| ord.weightByWeek.elementAt(3)!=0) {
//							cur_ord.add(ord);
//							cur_breadth += ord.order_breadth;
//							order_cnt++;
//							int trim_rate = Trim.getTrimRate(roll.order_type, roll.specific_order_type, order_cnt);
//							System.out.println(trim_rate);
//							if(trim_rate >= 0
//								&& cur_breadth + trim_rate < breadth
//								&& order_cnt < 4) {
//								idx--;
//								continue;
//							}else {
//								cur_ord.pop();
//								cur_breadth-=ord.order_breadth;
//								order_cnt--;
//								break;
//							}
//						}
//					}
//					double mat_weight = match_mat.weight;
//					double cal_weight = Calculate.getWeight(roll.order_thickness, sect.order_length, cur_breadth);
//					
//					
//					//System.out.println();
//					System.out.println("Matched Material : "+match_mat.material_code+"\t:"+match_mat.material_breadth);
//					if(cur_ord.size()>0) {
//						System.out.println((cur_breadth+Trim.getTrimRate(roll.order_type, roll.specific_order_type, cur_ord.size()))+"/"+match_mat.material_breadth);
//						for(int i =0;i<cur_ord.size();i++) {
//							System.out.print(cur_ord.elementAt(i).order_breadth+"\t");
//							//cur_ord.elementAt(i).print();
//						}
//					}
//					System.out.println();
//				}
//			}
//		}
//		
		
//		System.out.println(Calculate.getTimes(9,24000,1238,4.3));
//		System.out.println(Calculate.getTimes(9,24000,750,2.6));
		
	}
}
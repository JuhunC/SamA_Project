import java.util.*;
public class Src {
	public static String order_plan_dir="./�����ȹ_�ۼ���.xls";
	public static String material_dir="./�����系��.xls";
	public static String format_dir = "./�����տ� �����ڷ�_V1_190726.xls";
	public static void main(String args[]) {
		Format f = new Format(format_dir);
		Vector<Order> orders = OrderReader.Read(format_dir);
		Vector<Material> materials = MaterialReader.Read(material_dir);
	}
}

package Sort;

public class Calculate {
	
	/**
	 * ���� ���
	 * @param thickness �β�
	 * @param length	����
	 * @param breadth	�ʺ�
	 * @return ����(ton)
	 */
	public static double getWeight(double thickness, double length, double breadth) {
		return thickness * breadth * length * 2.71 * 0.000001 * 2 / 1000;
	}
	/**
	 * Ư�� ���Է� ��� ��� �������� Ƚ�� ���
	 * @param thickness	�β�
	 * @param length	����
	 * @param breadth	�ʺ�
	 * @param weight	����
	 * @return	Ƚ����ȯ
	 */
	public static int getTimes(double thickness, double length, double breadth, double weight) {
		double res = weight/getWeight(thickness, length, breadth);
		if(res%1 > 0) {
			res++;
		}
		int result = (int)res;
		return result;
	}
	//public static int getTrimmingRate(String order_type, )
}
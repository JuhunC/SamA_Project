package Sort;

public class Calculate {
	private static float transference_rate = 0.99f;	//����
	
	
	
	
	/**
	 * �β� ���(�е� 2.71)
	 * @param order_thickness	��ǰ ��(��)
	 * @param order_length		��ǰ����(m)
	 * @param weight			�߷�(��)
	 * @return	calculated_thickness 	���ġ�β�(��)
	 */
	double calculateThickness(double order_thickness, int order_length, int weight) {
		return (weight*1000000)/(order_thickness*order_length*2.71);
	}
	/**
	 * ���� ���(�е�: 2.71)
	 * @param order_thickness	��ǰ�β�(��)
	 * @param order_breadth		��ǰ ��(��)
	 * @param weight			�߷�(��)
	 * @return length			��ǰ ����(m)
	 */
	int calculateOrderLength(double order_thickness, double order_breadth, int weight) {
		return (int)(weight/order_thickness/order_breadth/2.71/0.000001);
	}
	/**
	 * STRIP -> ��ǰ ����� ���� ���� ���
	 * @param order_thickness	��ǰ�β�(��)
	 * @param STRIP_breadth		STRIP ��(��)
	 * @param STRIP_weight		STRIP�߷�(��)
	 * @return	order_length	��ǰ ����(m)
	 */
	int calculateOrderLength_onProduction(double order_thickness, double STRIP_breadth, int STRIP_weight) {
		return (int)((STRIP_weight*Calculate.transference_rate)/order_thickness/STRIP_breadth/2.71/0.000001);
	}
	/**
	 * ���� ���
	 * @param thickness �β�
	 * @param length	����
	 * @param breadth	�ʺ�
	 * @return weight 	����(ton)
	 */
	public static double getWeight(double thickness, double length, double breadth) {
		return thickness * breadth * length * 2.71 * 0.000001 * 2 / 1000;
	}
	/**
	 * Ư�� ���Է� ��� ��� �������� Ƚ�� ���
	 * @param thickness	�β�
	 * @param length	����
	 * @param breadth	�ʺ�
	 * @param weight	����(ton)
	 * @return	times	Ƚ����ȯ(repeat)
	 */
	public static int getTimes(double thickness, double length, double breadth, double weight) {
		double res = weight/getWeight(thickness, length, breadth);
		if(res%1 > 0) {
			res++;
		}
		int result = (int)res;
		return result;
	}
}
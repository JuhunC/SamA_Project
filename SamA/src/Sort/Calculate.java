package Sort;

public class Calculate {
	private static float transference_rate = 0.99f;	//수율
	
	
	
	
	/**
	 * 두께 계산(밀도 2.71)
	 * @param order_thickness	제품 폭(㎜)
	 * @param order_length		제품길이(m)
	 * @param weight			중량(㎏)
	 * @return	calculated_thickness 	계산치두께(㎛)
	 */
	double calculateThickness(double order_thickness, int order_length, int weight) {
		return (weight*1000000)/(order_thickness*order_length*2.71);
	}
	/**
	 * 길이 계산(밀도: 2.71)
	 * @param order_thickness	제품두께(㎛)
	 * @param order_breadth		제품 폭(㎜)
	 * @param weight			중량(㎏)
	 * @return length			제품 길이(m)
	 */
	int calculateOrderLength(double order_thickness, double order_breadth, int weight) {
		return (int)(weight/order_thickness/order_breadth/2.71/0.000001);
	}
	/**
	 * STRIP -> 제품 생산시 예상 길이 계산
	 * @param order_thickness	제품두께(㎛)
	 * @param STRIP_breadth		STRIP 폭(㎜)
	 * @param STRIP_weight		STRIP중량(㎏)
	 * @return	order_length	제품 길이(m)
	 */
	int calculateOrderLength_onProduction(double order_thickness, double STRIP_breadth, int STRIP_weight) {
		return (int)((STRIP_weight*Calculate.transference_rate)/order_thickness/STRIP_breadth/2.71/0.000001);
	}
	/**
	 * 무게 계산
	 * @param thickness 두께
	 * @param length	길이
	 * @param breadth	너비
	 * @return weight 	무게(ton)
	 */
	public static double getWeight(double thickness, double length, double breadth) {
		return thickness * breadth * length * 2.71 * 0.000001 * 2 / 1000;
	}
	/**
	 * 특정 무게로 몇번 출력 가능한지 횟수 계산
	 * @param thickness	두께
	 * @param length	길이
	 * @param breadth	너비
	 * @param weight	무게(ton)
	 * @return	times	횟수반환(repeat)
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
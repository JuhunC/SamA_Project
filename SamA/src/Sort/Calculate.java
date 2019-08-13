package Sort;

public class Calculate {
	
	/**
	 * 무게 계산
	 * @param thickness 두께
	 * @param length	길이
	 * @param breadth	너비
	 * @return 무게(ton)
	 */
	public static double getWeight(double thickness, double length, double breadth) {
		return thickness * breadth * length * 2.71 * 0.000001 * 2 / 1000;
	}
	/**
	 * 특정 무게로 몇번 출력 가능한지 횟수 계산
	 * @param thickness	두께
	 * @param length	길이
	 * @param breadth	너비
	 * @param weight	무게
	 * @return	횟수반환
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
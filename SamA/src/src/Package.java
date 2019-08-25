package src;
import java.util.*;
public class Package {
	public float order_thickness;
	public String core_type;
	public int core_bore;
	public String order_temper;
	
	public String alloy_code;
	public String alloy;
	
	public Vector<Order>ords = new Vector<Order>();
	public Vector<Material>mats = new Vector<Material>();

	/**
	 * Package Constructor(오더를 기본으로 선택)
	 * @param ord
	 */
	Package(Order ord){
		this.addOrder(ord);
		this.order_thickness = ord.order_thickness;
		this.core_type = ord.core_type;
		this.core_bore = ord.core_bore;
		this.order_temper = ord.order_temper;
		this.alloy_code = ord.alloy_code.elementAt(0);
		this.alloy = ord.alloy.elementAt(0);
	}
	
	
	/**
	 * Is Material(가능한 원자재인지 판단)
	 * @param mat	원자재
	 * @return boolean 사용 가능 여부
	 */
	boolean isMaterial(Material mat) {
		if(mat.alloy_code.equals(this.alloy_code))
				return true;
		else return false;
	}
	/**
	 * Is Order(포함 가능한 오더인지 판단)
	 * @param ord	오더
	 * @return	boolean	포함 여부
	 */
	boolean isOrder(Order ord) {
		if(ord.order_thickness == this.order_thickness
				&& ord.core_bore == this.core_bore
				&& ord.core_type.equals(this.core_bore)
				&& ord.order_temper.equals(order_temper))
			return true;
		else return false;
	}
	/**
	 * Add Order to Package
	 * @param ord	오더
	 */
	public void addOrder(Order ord) {
		for(Order or: ords) {
			if(or.order_code.equals(ord.order_code))
				return;
		}
		ords.add(ord);
	}
	/**
	 * Add material to Package
	 * @param mat	원자재
	 */
	public void addMaterial(Material mat) {
		for(Material ma: mats) {
			if(ma.material_code.equals(mat.material_code))
				return;
		}
		mats.add(mat);
	}
}

package src;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.*;
public class OrderReader {
	private static int error = -1;
	private static DataFormatter df = new DataFormatter();
	/**
	 * Read Order from File(파일에서 오더 읽기)
	 * @param file_path	파일위치
	 * @return	Vector<Order> ords 오더목록
	 */
	public static Vector<Order> Read(String file_path) {
		Vector<Order> orders = new Vector<Order>();
		
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(new File(file_path));
			
			// Print Sheet Information
			System.out.print("File("+file_path+") has " + workbook.getNumberOfSheets() + " Sheets : ");
			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
	        while (sheetIterator.hasNext()) {
	            Sheet sheet = sheetIterator.next();
	            System.out.print(sheet.getSheetName()+"\t");
	        }System.out.println();
				        
	        
	        // Read one sheet
	        Sheet orderSheet = workbook.getSheetAt(1);
	        boolean skip_1st_row =false;
	        for(Row row: orderSheet) {
	        	if(skip_1st_row == false) {
	        		skip_1st_row = true;
	        	}else {
	        		String tmp;
	        		int team_num;				// 팀
	        		String customer;			// 고객명
	        		String order_type;			// 품목
	        		String specific_order_type;	// 세부품목
	        		boolean consider = false;	// 폭조합
	        		String order_code;			// 제품코드
	        		float order_thickness;		// 두께
	        		float order_breadth;		// 폭
	        		int order_length;			// 길이
	        		float order_length_min;		// 최소길이
	        		float order_length_max;		// 최대길이
	        		String alloy_code;			// (AB,AC,...) format
	        		String order_temper;		// TEMPER
	        		char doubling;				// 권취
	        		int core_bore;				// 내경
	        		String core_type;			// 코아
	        		String material_m;			// 원자재_M (제품생산구분)
	        		String material_temper;		// 원자재_T (원자재 TEMPER)
	        		Vector<Float> weightByWeek = new Vector<Float>();	// 매주 투입량
	        		// 팀
	        		tmp = df.formatCellValue(row.getCell(0));
	        		if(tmp == "") {
	        			team_num = error; continue;
	        		}
	        		else team_num = Integer.parseInt(tmp);
	        		
	        		// 고객명
	        		customer = df.formatCellValue(row.getCell(1));
	        		
	        		// 품목
	        		order_type = df.formatCellValue(row.getCell(3));
	        		
	        		specific_order_type= df.formatCellValue(row.getCell(4));
	        		
	        		// 폭조합
	        		if(df.formatCellValue(row.getCell(6))=="O") {
	        			consider = true;
	        		}else consider = false;
	        		
	        		// 제품코드
	        		order_code = df.formatCellValue(row.getCell(5));
	        		
	        		// 두께
	        		tmp =df.formatCellValue(row.getCell(7));
	        		if(tmp=="")
	        			order_thickness = error;
	        		else order_thickness = Float.parseFloat(tmp);
	        		
	        		// 폭
	        		tmp  =df.formatCellValue(row.getCell(8));
	        		if(tmp=="")
	        			order_breadth = error;
	        		else order_breadth = Float.parseFloat(tmp);
	        		
	        		// 길이
	        		tmp = df.formatCellValue(row.getCell(9));
	        		if(tmp=="")
	        			order_length = error;
	        		else order_length = Integer.parseInt(tmp);
	        		
	        		// 최소길이
	        		tmp = df.formatCellValue(row.getCell(10));
	        		if(tmp=="")
	        			order_length_min = error;
	        		else order_length_min = Float.parseFloat(tmp);
	        		
	        		// 최대길이
	        		tmp = df.formatCellValue(row.getCell(11));
	        		if(tmp =="")
	        			order_length_max = error;
	        		else order_length_max = Float.parseFloat(tmp);
	        		
	        		// (AB,AC,...) format
	        		alloy_code = df.formatCellValue(row.getCell(12));
	        		
	        		// TEMPER
	        		order_temper = df.formatCellValue(row.getCell(13));
	        		
	        		// 권취
	        		tmp  =df.formatCellValue(row.getCell(14));
	        		if(tmp=="")
	        			doubling = (char)error;
	        		else doubling = tmp.charAt(0);
	        		
	        		// 내경
	        		tmp  =df.formatCellValue(row.getCell(15));
	        		if(tmp=="")
	        			core_bore = error;
	        		else core_bore = Integer.parseInt(tmp);
	        		
	        		//코아
	        		core_type = df.formatCellValue(row.getCell(16));
	        		
	        		// 원자재_M
	        		tmp = df.formatCellValue(row.getCell(17));
	        		if(tmp == "")
	        			material_m = "";
	        		else material_m = tmp;
	        		
	        		material_temper = df.formatCellValue(row.getCell(18));
	        		
	        		for(int i =0;i<13;i++) {
	        			tmp = df.formatCellValue(row.getCell(22+i));
	        		
	        			try {
	        				weightByWeek.add(Float.parseFloat(tmp));
	        			}catch(NumberFormatException e) {
	        				weightByWeek.add(0.0f);
	        			}
//	        			if(tmp.length()>0) {
//	        				System.out.println(tmp);
//	        				weightByWeek.add(Float.parseFloat(tmp));
//	        			}
//	        			else {
//	        				weightByWeek.add(0.0f);
//	        			}
	        		}
	        		
	        		// TODO: add order weight by month(check excel file)
	        		
	        		if(order_code != ""
	        				&& order_code != "합계"
	        				&& order_thickness != error
	        				&& order_breadth != error
	        				&& order_length != error
	        				&& doubling != error
	        				&& core_bore != error
	        				&& core_type != ""
	        				&& material_m != ""
	        				) {
		        		orders.add(new Order(
		        				team_num,
		        				customer,
		        				order_type,
		        				specific_order_type,
		        				consider,
		        				order_code,
		        				order_thickness,
		        				order_breadth,
		        				order_length,
		        				order_length_min,
		        				order_length_max,
		        				alloy_code,
		        				order_temper,
		        				doubling,
		        				core_bore,
		        				core_type,
		        				material_m,
		        				material_temper,
		        				weightByWeek
		        				));
	        		}
	        	}
	        }
	        if(Src.verbose==true) {
		        for(Order ord : orders) {
		        	ord.print();
		        }System.out.println("Number of Order : " + orders.size());
	        }
				        
		} catch (EncryptedDocumentException e) {
			System.err.println("Wrong Format File : " + file_path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File : "+file_path+" not found!");
			e.printStackTrace();
		}
		return orders;
	}
}

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.*;
public class OrderReader {
	private static int error = -1;
	private static DataFormatter df = new DataFormatter();
	
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
	        		int team_num;				// ��
	        		String customer;			// ����
	        		String order_type;			// ǰ��
	        		boolean consider = false;	// ������
	        		String order_code;			// ��ǰ�ڵ�
	        		float order_thickness;		// �β�
	        		float order_breadth;		// ��
	        		int order_length;			// ����
	        		float order_length_min;		// �ּұ���
	        		float order_length_max;		// �ִ����
	        		String alloy_code;			// (AB,AC,...) format
	        		String order_temper;		// TEMPER
	        		char doubling;				// ����
	        		int core_bore;				// ����
	        		String core_type;			// �ھ�
	        		char material_m;			// ������_M (��ǰ���걸��)
	        		String material_temper;		// ������_T (������ TEMPER)
	        		
	        		// ��
	        		tmp = df.formatCellValue(row.getCell(0));
	        		if(tmp == "")
	        			team_num = error;
	        		else team_num = Integer.parseInt(tmp);
	        		
	        		// ����
	        		customer = df.formatCellValue(row.getCell(1));
	        		
	        		// ǰ��
	        		order_type = df.formatCellValue(row.getCell(3));
	        		
	        		// ������
	        		if(df.formatCellValue(row.getCell(6))=="O") {
	        			consider = true;
	        		}else consider = false;
	        		
	        		// ��ǰ�ڵ�
	        		order_code = df.formatCellValue(row.getCell(5));
	        		
	        		// �β�
	        		tmp =df.formatCellValue(row.getCell(7));
	        		if(tmp=="")
	        			order_thickness = error;
	        		else order_thickness = Float.parseFloat(tmp);
	        		
	        		// ��
	        		tmp  =df.formatCellValue(row.getCell(8));
	        		if(tmp=="")
	        			order_breadth = error;
	        		else order_breadth = Float.parseFloat(tmp);
	        		
	        		// ����
	        		tmp = df.formatCellValue(row.getCell(9));
	        		if(tmp=="")
	        			order_length = error;
	        		else order_length = Integer.parseInt(tmp);
	        		
	        		// �ּұ���
	        		tmp = df.formatCellValue(row.getCell(10));
	        		if(tmp=="")
	        			order_length_min = error;
	        		else order_length_min = Float.parseFloat(tmp);
	        		
	        		// �ִ����
	        		tmp = df.formatCellValue(row.getCell(11));
	        		if(tmp =="")
	        			order_length_max = error;
	        		else order_length_max = Float.parseFloat(tmp);
	        		
	        		// (AB,AC,...) format
	        		alloy_code = df.formatCellValue(row.getCell(12));
	        		
	        		// TEMPER
	        		order_temper = df.formatCellValue(row.getCell(13));
	        		
	        		// ����
	        		tmp  =df.formatCellValue(row.getCell(14));
	        		if(tmp=="")
	        			doubling = (char)error;
	        		else doubling = tmp.charAt(0);
	        		
	        		// ����
	        		tmp  =df.formatCellValue(row.getCell(15));
	        		if(tmp=="")
	        			core_bore = error;
	        		else core_bore = Integer.parseInt(tmp);
	        		
	        		//�ھ�
	        		core_type = df.formatCellValue(row.getCell(16));
	        		
	        		// ������_M
	        		tmp = df.formatCellValue(row.getCell(17));
	        		if(tmp == "")
	        			material_m = (char)error;
	        		else material_m = tmp.charAt(0);
	        		
	        		material_temper = df.formatCellValue(row.getCell(18));
	        		
	        		// TODO: add order weight by month(check excel file)
	        		
	        		if(order_code != ""
	        				&& order_code != "�հ�"
	        				&& order_thickness != error
	        				&& order_breadth != error
	        				&& order_length != error
	        				&& doubling != error
	        				&& core_bore != error
	        				&& core_type != ""
	        				&& material_m != error
	        				) {
		        		orders.add(new Order(
		        				team_num,
		        				customer,
		        				order_type,
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
		        				material_temper      				
		        				));
	        		}
	        	}
	        }
	        for(Order ord : orders) {
	        	ord.print();
	        }System.out.println("Number of Order : " + orders.size());
				        
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

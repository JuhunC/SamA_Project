package src;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.*;
public class MaterialReader {
	private static int error = -1;
	private static DataFormatter df = new DataFormatter();
	/**
	 * Read Materials from file(���Ͽ��� ������ �б�)
	 * @param file_path	������ġ
	 * @return Vector<Material> mats	������ ���� ���
	 */
	public static Vector<Material> Read(String file_path){
		Vector<Material> materials = new Vector<Material>();
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
	        
	        // Read one Sheet
	        Sheet materialSheet= workbook.getSheetAt(0);
	        boolean skip_1st_row =false;
	        for(Row row: materialSheet) {
	        	if(skip_1st_row == false) {
	        		skip_1st_row = true;
	        	}else {
	        		String material_code; 	// ��ǰ�ڵ�
	        		String ROLL; 			// ROLL
	        		String STRIP_LOT; 		// STRIP_LOT
	        		int material_thickness; // �ǵβ�
	        		int material_breadth; 	// ����
	        		String alloy;	 		// ALLOY
	        		String material_temper;	// TEMPER
	        		int weight;				// �����߷�
	        		String produced_date;	// ��������
	        		
	        		// ��ǰ�ڵ�
	        		material_code = df.formatCellValue(row.getCell(0));
	        		if(material_code.length() <=0)
	        			continue;
	        		// ROLL
	        		ROLL = df.formatCellValue(row.getCell(1));
	        		// STRIP LOT
	        		STRIP_LOT = df.formatCellValue(row.getCell(2));
	        		// �ǵβ�
	        		String tmp =df.formatCellValue(row.getCell(3));
	        		if(tmp.length()>0)
	        			material_thickness = Integer.parseInt(tmp);
	        		else material_thickness = error;
	        		// ����
	        		tmp =df.formatCellValue(row.getCell(4)).replace(",", "");
	        		if(tmp.length()>0)
	        			material_breadth = Integer.parseInt(tmp);
	        		else material_breadth = error;
	        		// ALLOY
	        		alloy = df.formatCellValue(row.getCell(5));
	        		// TEMPER
	        		material_temper = df.formatCellValue(row.getCell(6));
	        		// �����߷�
	        		tmp =df.formatCellValue(row.getCell(7)).replace(",","");
	        		if(tmp.length()>0)
	        			weight = Integer.parseInt(tmp);
	        		else weight = error;
	        		// ��������
	        		produced_date = df.formatCellValue(row.getCell(8));
	        		
	        		if(material_code.length() >0
	        				&& ROLL.length() >0
	        				&& STRIP_LOT.length() >0
	        				&& material_thickness !=error
	        				&& material_breadth != error
	        				&& alloy.length() >0
	        				&& material_temper.length() >0
	        				&& weight != error
	        				&& produced_date.length() >0) {
	        			
	        			materials.add(new Material(
	        					material_code,
	        					ROLL,
	        					STRIP_LOT,
	        					material_thickness,
	        					material_breadth,
	        					alloy,
	        					material_temper,
	        					weight,
	        					produced_date));
	        		}
	        	}
	        }
	        System.out.println(materials.size() + " materials are read!");
		} catch (EncryptedDocumentException e) {
			System.err.println("Wrong Format File : " + file_path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File : "+file_path+" not found!");
			e.printStackTrace();
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(Src.verbose == true) {
			for(Material mat: materials) {
				mat.print();
			}System.out.println("Number of Materials : "+ materials.size());
		}
		
		return materials;
	}
	
}

package Standard;
import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.EncryptedDocumentException;
public class Format {
	private static DataFormatter df = new DataFormatter();
	public static Map<String,String> Alloy = new HashMap<String, String>();		// CODE(AB,AC,..) : ALLOY(A****)
	/**
	 * Set Format File
	 * @param file_path	파일위치
	 */
	public static void set_file_path(String file_path){
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
			
	        // Read ALLOY code match
	        Sheet format = workbook.getSheetAt(0);
	        for(int i=16;i<=24;i++) {
	        	Row r = format.getRow(i);
        		Alloy.put(
        				df.formatCellValue(r.getCell(2)).toString(),
        				df.formatCellValue(r.getCell(3)).toString()
        				);
	        }
	        workbook.close();
		} catch (EncryptedDocumentException e) {
			System.err.println("Wrong Format File : " + file_path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File : "+file_path+" not found!");
			e.printStackTrace();
		}	
		
	}
	/**
	 * Get Alloy_code using Alloy
	 * @param alloy	(A***)
	 * @return alloy_code(AB,AC...)
	 */
	public static String getAlloyCode(String alloy) {
		if(Alloy.size() <=0 ) {
			System.err.println("Set File directory for Format.class");
			return null;
		}
		
		for(Map.Entry<String, String> entry : Alloy.entrySet()) {
			if(alloy.equals(entry.getValue())){
				return entry.getKey();
			}
		}
		return "";
	}
	/**
	 * Get Alloy using Alloy_code
	 * @param alloy_code(AB,AC...)
	 * @return alloy(A***)
	 */
	public static String getAlloy(String alloy_code) {
		if(Alloy.size() <=0 ) {
			System.err.println("Set File directory for Format.class");
			return null;
		}
		return Alloy.get(alloy_code);
	}
}

import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.EncryptedDocumentException;
public class Format {
	private static DataFormatter df = new DataFormatter();
	public static Map<String,String> Alloy = new HashMap<String, String>();		// CODE(AB,AC,..) : ALLOY(A****)
	Format(String file_path){
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
	        
		} catch (EncryptedDocumentException e) {
			System.err.println("Wrong Format File : " + file_path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File : "+file_path+" not found!");
			e.printStackTrace();
		}	
	}
	public static String getAlloyCode(String alloy) {
		for(Map.Entry<String, String> entry : Alloy.entrySet()) {
			if(alloy.equals(entry.getValue())){
				return entry.getKey();
			}
		}
		return "";
	}
	public static String getAlloy(String alloy_code) {
		return Alloy.get(alloy_code);
	}
}

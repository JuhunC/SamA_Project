package Standard;
import java.util.Iterator;
import java.util.Vector;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.EncryptedDocumentException;
public class Trim {
	private static DataFormatter df = new DataFormatter();
	static Vector<String> order_type = new Vector<String>();			// 품목(D열)
	static Vector<String> specific_order_type = new Vector<String>();	// 세부품목(E열)
	static Vector<Vector<Integer>> cut = new Vector<Vector<Integer>>();	// 4컷
	/**
	 * Static Constructor - for Trim.cut
	 */
	static {
		for(int i=0;i<4;i++) {
			cut.add(new Vector<Integer>());
		}
	}
	/**
	 * get Trimming Rate(미미 폭 내역 조회)
	 * @param order_type 품목(D열)
	 * @param specific_order_type	세부품목(E열)
	 * @param cut	컷(1,2,3,4)
	 * @return	int 미미 폭
	 */
	public static int getTrimRate(String order_type, String specific_order_type, int cut) {
		for(int i =0;i<Trim.order_type.size();i++) {
			if(Trim.order_type.elementAt(i).equals(order_type)
				&& Trim.specific_order_type.elementAt(i).equals(specific_order_type)
					) {
				return Trim.cut.elementAt(cut).elementAt(i);
			}
		}
		return -1;
	}
	
	/**
	 * Read Trimming Rate from file
	 * @param file_path	파일위치
	 */
	public static void set_file_path(String file_path) {
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
			
	        // Read Trimming rate(미미 폭 내역 읽기)
	        Sheet format = workbook.getSheetAt(0);
	        for(int i=52;i<=66;i++) {
	        	Row r = format.getRow(i);
	        	Trim.order_type.add(df.formatCellValue(r.getCell(1)));
	        	Trim.specific_order_type.add(df.formatCellValue(r.getCell(2)).toString());
	        	int t=-1;
	        	for(int j=0;j<4;j++) {
		        	try {
		        		t = Integer.parseInt(df.formatCellValue(r.getCell(3+j)));
		        	}catch(NumberFormatException e) {
		        		t = -1;
		        	}finally {
		        		Trim.cut.elementAt(j).add(t);
		        	}
	        	}
	        }
		} catch (EncryptedDocumentException e) {
			System.err.println("Wrong Format File : " + file_path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File : "+file_path+" not found!");
			e.printStackTrace();
		}	
	}
}

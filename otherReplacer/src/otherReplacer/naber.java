package otherReplacer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class naber {
	@SuppressWarnings("deprecation")
	
	public static void main(String[] args) throws IOException {
			Set<String> namesMain = new HashSet<String>();
			namesMain.add("$/BOA/");
			namesMain.add("$/BOA.Kernel/");
			namesMain.add("$/BOA.BusinessModules/");
			namesMain.add("$/BOA.DWHModules/");
			namesMain.add("$/BOA.Modules/");
			namesMain.add("$/BOA.Loans/");
			for (String names : namesMain) {
				Set<String> columnA = new HashSet<String>();
				Set<String> columnB = new HashSet<String>();
				FileInputStream fis = new FileInputStream(new File("C:\\Users\\PC\\Desktop\\asdas.xlsx"));
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet sheet1 = wb.getSheetAt(1);
				Iterator<Row> rowIterator1 = sheet1.iterator();
				String mainText = names+"Main/";
				String testText = names+"Test/";
				String gecici = "";
				while (rowIterator1.hasNext()) {	
									
					gecici = rowIterator1.next().getCell(0).toString().trim();
						
						if(gecici.startsWith(mainText)){	
							
							columnA.add(gecici.substring(mainText.length()).toString().split(" ")[0]);
																					
						}
						if (gecici.startsWith(testText)) {
							
							columnB.add(gecici.substring(testText.length()).toString().split(" ")[0]);
								
						}
						gecici = "";
						
				}
				System.out.println("||||||||||||||||||"+names+"||||||||||||||||||");
				System.out.println("\n\n**********************"+names+"  MAIN*********************\n"+columnA.size());
				
				for (String rowA : columnA) {
					System.out.println(rowA);
				}
				System.out.println("\n\n**********************"+names+"  TEST**********************\n"+columnB.size());
				for (String rowB : columnB) {
					System.out.println(rowB);
				}
			}
			}
			
		
		
	}




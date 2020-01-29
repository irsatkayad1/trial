package Edit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EditJenk {

	public static void main(String[] args) throws IOException {
		String gitPrefix = "git.exe clone ";
		String sshStartsWith = "https://tfs/tfs/VK/";
		String projectName = "";
		String underGit = "/_git/";
		String folderName = "";
		
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\PC\\Desktop\\newfolder\\repos.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		XSSFSheet sheet2 = wb.getSheetAt(1);
		XSSFSheet sheet3 = wb.getSheetAt(2);
		XSSFSheet sheet4 = wb.getSheetAt(3);
		XSSFSheet sheet5 = wb.getSheetAt(4);
		XSSFSheet sheet6 = wb.getSheetAt(5);
		ArrayList<XSSFSheet> sheets = new ArrayList<XSSFSheet>();
		sheets.addAll(Arrays.asList(sheet1,sheet2,sheet3,sheet4,sheet5,sheet6));
		Iterator<Row> rowIterator;
		
		for (XSSFSheet xssfSheet : sheets) {
			rowIterator = xssfSheet.iterator();
			
			while (rowIterator.hasNext()) {
				projectName = xssfSheet.getSheetName();
				folderName = rowIterator.next().getCell(0).toString().trim();
				String sshUrl = sshStartsWith+projectName+underGit+folderName;
				BufferedWriter writer;
				System.out.println(gitPrefix+sshUrl);
				try {
					writer = new BufferedWriter(new FileWriter("C:\\Users\\PC\\Desktop\\newfolder\\batFolder\\"+folderName.replace(".","-")+".bat"));
					writer.write(gitPrefix+sshUrl);
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		
		
		
		
		
		
		
		
		

	}

}

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.ObjectInputStream.GetField;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> branchs = new ArrayList<String>();
		branchs.addAll(Arrays.asList("master","dev","Release","QA"));
		
		// programin calisacagi ana lokasyonu ve replace yapilacak ifadeyi al
		// ilgili degiskenlere ata
		String mainFilePath = args[0];
		String oldName = args[1];
		String newName = args[2];
		String dosAdi = args[3];
		String projectName = args[4];
		String directoryName = args[5];		
		CommandCreator cmdCreate = new CommandCreator();
		
		System.out.println(mainFilePath);
		System.out.println(oldName);
		System.out.println(newName);
		System.out.println(dosAdi);
		System.out.println(projectName);
		System.out.println(directoryName);
		
		//git clone yapiliyor
		cmdCreate.cloneCreator(projectName, directoryName);
		// temel fonksiyonlari iceren sinif elemani uretilir
		Replacer r = new Replacer(dosAdi);

		System.out.println("//////////////////////////////");
		// ana klasoru icerisinde arama yapabilmek icin tanimla
		File root = new File(mainFilePath+"\\"+directoryName);		
		//
		ArrayList<File> DirList = new ArrayList<File>();
		//
		if(root != null)
		{
			// eger gecerli bir dosya yolu parametre olarak verildiyse 
			// ana dosya yolundan baslayarak taramaya basla
			// sadece ana dosya yolundaki csproj dosyalari rekursiflik disi kaliyor
			// bunun icin main path uzerindeki gezinmeyi manuel yap
			boolean basariliMi = false;
			File[] f1 = r.getCsprojFiles(root);
			if(f1 != null)
			{
				if(f1.length > 0)
				{
					for(int i = 0; i < f1.length; i++)
					{
						Path path1 = f1[i].getAbsoluteFile().toPath();
						basariliMi = r.replaceRegs(path1, oldName, newName);
						if(basariliMi)
						{
							System.out.println(f1[i].getAbsolutePath() + " replacement success");
						}
						else
						{
							System.out.println(f1[i].getAbsolutePath() + " replacement failed");
						}
						basariliMi = false;
					}
				}
			}

			DirList = r.getDirectories(root);
			for(int i = 0; i < DirList.size(); i++)
			{
				// ana dosya icerisindeki dosyalari klasor bazli kontrol et
				// eger icerisinde csproj uzantili dosya varsa
				// burada replace islemini gerceklestir
				File[] f = r.getCsprojFiles(DirList.get(i));
				if(f != null)
				{
					if(f.length > 0)
					{
						for(int j = 0; j< f.length; j++)
						{
							Path path = f[j].getAbsoluteFile().toPath();
							basariliMi = r.replaceRegs(path, oldName, newName);
							if(basariliMi)
							{
								System.out.println(f[j].getAbsolutePath() + " replacement success");
							}
							else
							{
								System.out.println(f[j].getAbsolutePath() + " replacement failed");
							}
							basariliMi = false;
						}
					}
				}
			}			
		}		
		for (String branchName : branchs) {
			try {
				System.out.println(branchName+" e geçiliyor");
				Process y = Runtime.getRuntime().exec("git checkout "+branchName,null,new File(mainFilePath+"\\"+directoryName));
				y.waitFor();
				System.out.println(y.getOutputStream());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}		
			
			try {
				System.out.println("add : "+branchName);
				Process u = Runtime.getRuntime().exec("git add . ",null,new File(mainFilePath+"\\"+directoryName));
				u.waitFor();
				System.out.println(u.getOutputStream());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}		
			try {
				System.out.println("commit : "+branchName +"   git commit -m \"jenkinsFile Updated by script\"");
				Process q = Runtime.getRuntime().exec("git commit -m \"jenkinsFile Updated by script\"",null,new File(mainFilePath+"\\"+directoryName));
				q.waitFor();
				System.out.println(q.getOutputStream());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}		
			try {
				System.out.println("push : "+branchName);
				Process w = Runtime.getRuntime().exec("git push",null,new File(mainFilePath+"\\"+directoryName));
				w.waitFor();
				System.out.println(w.getOutputStream());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}		
		}
	}
}

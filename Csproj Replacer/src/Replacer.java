import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;


public class Replacer {

	public String dosyaAdi;
	
	public Replacer(String dAdi){
		dosyaAdi=dAdi;
	}
	
	public boolean replaceRegs(Path filePath, String oldReg, String newReg)
	{
		try
		{
			System.out.println("*-*-*-*-*-*-*-*-*-*-*-");
			BufferedReader file = new BufferedReader(new FileReader(filePath.toString()));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;
	        while ((line = file.readLine()) != null) {
	            inputBuffer.append(line);
	            inputBuffer.append('\n');
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();
	        inputStr = inputStr.replaceAll(oldReg, newReg);
	        FileOutputStream fileOut = new FileOutputStream(filePath.toString());
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();
 			return true;
		}
		catch(Exception er)
		{
			System.out.print(er.getMessage());
			return false;
		}
	}

	public File[] getCsprojFiles(File root)
	{
		try
		{
			File[] subDirFiles = root.listFiles(new FilenameFilter() {
	            public boolean accept(File dir, String name) {
	                return name.endsWith(dosyaAdi);
	            }
	        });
			//
			/*if(subDirFiles.length > 0)
			{
				// tum dosyalari gez ve path'leriyle birlikte listeye kaydet
				for(int i = 0; i < subDirFiles.length; i++)
				{
					Path path = subDirFiles[i].toPath();
					boolean a = replaceRegs(path,oldName,newName);
					if(a == true)
					{
						System.out.println(subDirFiles[i].getName() + " yazma islemi basarili!");
					}
					else
					{
						System.out.println(subDirFiles[i].getName() + " yazma islemi basarisiz!");
					}
				}
			}*/
			return subDirFiles;
		}
		catch(Exception er)
		{
			System.out.println(er.getLocalizedMessage());
			return null;
		}
	}

	public ArrayList<File> getDirectories(File root)
	{
		try
		{
			ArrayList<File> directories = new ArrayList<File>();
			// gelen dosya yolundaki tum dosya ve klasorleri diziye at
			File[] liste = root.listFiles();
			// eger dosya veya klasor varsa
			if(liste != null)
			{
				if(liste.length > 0)
				{
					// tum diziyi gez
					for(int i = 0; i < liste.length; i++)
					{
						// eger dizide klasor varsa bunu klasor listesine ekle
						// klasor icinde de herhangi bir klasor varsa bulmak icin
						// fonksiyonu tekrar cagir
						if(liste[i].isDirectory())
						{
							// bulunan dosyayi diziye ekle
							System.out.println(liste[i].getAbsolutePath());
							directories.add(liste[i]);
							directories.addAll(getDirectories(liste[i]));
						}
					}
				}
			}
			return directories;
		}
		catch(Exception er)
		{
			System.out.println(er.getLocalizedMessage());
			return null;
		}
	}
}

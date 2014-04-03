package temperature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		File folder = new File("D:/GIS/Malaria/Climate");	

		int i = 0;
		File[] listOfFiles = folder.listFiles();
		int j = listOfFiles.length;
		
		String[] fileName = new String[j];

		for (File file : listOfFiles) {
			if (file.isFile()) {
				fileName[i] = file.getName();
				i++;
			}
		}

		DataReader dr = new DataReader(folder, fileName);
		
		File folder2 = new File("D:/GIS/Malaria/KNMI/Temp_nonAdjusted");

		i = 0;
		File[] listOfFiles2 = folder2.listFiles();
		j = listOfFiles2.length;
		
		String[] fileName2 = new String[j];

		for (File file : listOfFiles2) {
			if (file.isFile()) {
				fileName2[i] = file.getName();
				i++;
			}
		}
		DataReader2 dr2 = new DataReader2(folder2, fileName2, "air_temp");
		
		File folder3 = new File("D:/GIS/Malaria/KNMI/Prec_nonAdjusted");

		i = 0;
		File[] listOfFiles3 = folder3.listFiles();
		j = listOfFiles3.length;
		
		String[] fileName3 = new String[j];

		for (File file : listOfFiles3) {
			if (file.isFile()) {
				fileName3[i] = file.getName();
				i++;
			}
		}
		DataReader2 dr3 = new DataReader2(folder3, fileName3, "prec");

	}
}

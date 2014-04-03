package temperature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		File folder = new File("D:/GIS/Malaria/Climate");	
		//   /Users/ludovicdupuis/Documents/workspace_Java/Malaria/ClimateDataFetcher/src/essai
		int i = 0;
		File[] listOfFiles = folder.listFiles();
		int j = listOfFiles.length;
			System.out.println("length: " + j);
		
		String[] fileName = new String[j];

		for (File file : listOfFiles) {
			if (file.isFile()) {
				fileName[i] = file.getName();
				System.out.println(file.getName());
				i++;
			}
		}

		//DataReader dr = new DataReader(folder, fileName);
		
		File folder2 = new File("D:/GIS/Malaria/KNMI/Temp");
		//  D:/GIS/Malaria/KNMI/Prec
		//  /Users/ludovicdupuis/Documents/workspace_Java/Malaria/ClimateDataFetcher/src/data2
		i = 0;
		File[] listOfFiles2 = folder2.listFiles();
		j = listOfFiles2.length;
			System.out.println("length: " + j);
		
		String[] fileName2 = new String[j];

		for (File file : listOfFiles2) {
			if (file.isFile()) {
				fileName2[i] = file.getName();
				System.out.println(file.getName());
				i++;
			}
		}
		DataReader2 dr2 = new DataReader2(folder2, fileName2, "air_temp");

	}
}

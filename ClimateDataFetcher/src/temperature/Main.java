package temperature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		File folder = new File("D:/GIS/Malaria/Climate");	//D:\GIS\Malaria\Climate
		
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

		DataReader dr = new DataReader(folder, fileName);

	}
}

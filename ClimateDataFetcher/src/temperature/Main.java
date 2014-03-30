package temperature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		File folder = new File("D:/GIS/Malaria/essai");
		String[] file = {"air_temp.1985","air_temp.1986","precip.1986"};
		DataReader dr = new DataReader(folder, file);
		/*for(int i = 0 ; i<file.length;i++){
			int idx = file[i].indexOf(".");
			String type = file[i].substring(0, idx);
			int year = Integer.parseInt(file[i].substring(idx+1));
			DataReader dr = new DataReader(folder + "/"+ file[i], type, year);
		}*/
	}
}

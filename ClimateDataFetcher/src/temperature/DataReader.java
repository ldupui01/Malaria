package temperature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataReader {
	private List<Station> listS;
	private Station firstStation;
	
	public DataReader(File folder, String[] file) throws FileNotFoundException, IOException{
		listS = new ArrayList<Station>();
		
		for(int i = 0 ; i<file.length;i++){
			int idx = file[i].indexOf(".");
			String type = file[i].substring(0, idx);
			int year = Integer.parseInt(file[i].substring(idx+1));
			String filepath = folder + "/"+ file[i];
			if (year>1984){
				readFile(filepath, year, type);	
				printData(year, type);
			}
		}
	}

	private void printData(int year, String type) {
		Iterator<Station> it = listS.iterator();
		System.out.println(year + type);
		while(it.hasNext()){
		    Station obj = it.next();
		    String print = obj.toString(year, type);
		    /*create new file here*/
		    System.out.println(print);
		}
		
	}

	private void readFile(String file, int year, String type) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		try{
			String line;
			while ((line = br.readLine()) != null) {
				deCryptString(line+" ", year, type);

			}
			br.close();
		}
		finally{
			br.close();
		}
	}

	private void deCryptString(String s, int year, String type) {
		double[] data = new double[12];
		double[] coord = new double[2];
		int i = 0;
		int idx = 0;
		for(int j=0; j<s.length();j++){
			if(Character.isWhitespace(s.charAt(j))){
				if(j>i && s.substring(i+1, j).length()>1){
					if (idx>1){
						data[idx-2] = Double.parseDouble(s.substring(i+1, j));
						idx++;
					}else{
						coord[idx] = Double.parseDouble(s.substring(i+1, j));
						idx++;
					}
					i = j;
				}else{
					i = j;
				}
			}
		}
		
		if (coord[0]>-20 && coord[0]<52){
			if (coord[1]>-35 && coord[1]<25){
				CreateStation(coord, data, year, type);
			}
		}
		
	}
	
	private void CreateStation(double[] coord, double[] data, int year, String type){
		Station st = new Station(coord[0], coord[1]);
		boolean check = false;
		Iterator<Station> it = listS.iterator();
		while(it.hasNext()){
		    Station obj = it.next();
		    if (obj.equals(st)){
		    	if (type.equalsIgnoreCase("air_temp")){
					obj.setTemp(year, data);
				}else{
					obj.setPrec(year, data);
				}
		    	check = true;
		    }
		}
		
		if (!check){
			if (type.equalsIgnoreCase("air_temp")){
				st.setTemp(year, data);
			}else{
				st.setPrec(year, data);
			}
	    	listS.add(st);

		}
	}
}

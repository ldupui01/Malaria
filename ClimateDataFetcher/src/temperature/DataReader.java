package temperature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
	private List<Station> listS;
	private Station firstStation;
	
	public DataReader(File folder, String[] file) throws FileNotFoundException, IOException{
		listS = new ArrayList<Station>();
		
		for(int i = 0 ; i<file.length;i++){
			int idx = file[i].indexOf(".");
			String type = file[i].substring(0, idx);
			System.out.println(type);
			int year = Integer.parseInt(file[i].substring(idx+1));
			String filepath = folder + "/"+ file[i];
			readFile(filepath, year, type);	
		}
	}

	private void readFile(String file, int year, String type) throws FileNotFoundException, IOException {
		int i = 0;
		BufferedReader br = new BufferedReader(new FileReader(file));
		try{
			String line;
			while ((line = br.readLine()) != null) {
				if(i==1){						//TODO Condition i==1 to be deleted
					System.out.println(line);	
					//TODO change create station to a simple create station then update station with rest of line
					//TODO if station exist (to be tested -> write the eqauls.object in Station) then update
					createStation(line+" ", year, type);
				}								//TODO Condition i==1 to be deleted here too
				i++;
			}
			br.close();
		}
		finally{
			br.close();
		}
		System.out.println("final " +i); //TODO to be deleted
	}

	private void createStation(String s, int year, String type) {
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
		Station st = new Station(coord[0], coord[1]);
		
		if (type == "air_temp"){
			
		}
		//Check from here
		for (int k=0; k<data.length;k++){
			switch (k){
			case 0:
				st.setCoord(data[0], data[1]);
				break;
			case 1:
			default:
				st.setTemp(k, data[k]);
			}
		}
		listS.add(st);	
	}
	
	private void updateStation(String line, int year) {
		// TODO Auto-generated method stub
		
	}
}

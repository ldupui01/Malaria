package temperature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DataReader2 {
	private List<Station> listS;
	private List<Integer>listYear;
	private Station firstStation;
	
	public DataReader2(File folder, String[] file, String type) throws FileNotFoundException, IOException{
		listS = new ArrayList<Station>();
		listYear = new LinkedList<Integer>();
		
		for(int i = 0 ; i<file.length;i++){
			//int idx = file[i].indexOf(".");
			//String type = file[i].substring(0, idx);
			//int year = Integer.parseInt(file[i].substring(idx+1));
			String filepath = folder + "/"+ file[i];
			//if (year>1984){
				readFile(filepath, type); //, year, type);	
				//listYear.add(year);
			//}
		}
		printData();
		//System.out.println(listS.size());
	}

	private void printData() {
		 try{
		    Writer output = null;
		    File logFile = new File("results_02042015.txt");
		     
		    output = new BufferedWriter(new FileWriter(logFile));

			Iterator<Station> it = listS.iterator();
			while(it.hasNext()){
				Station obj = it.next();
				String print = "";
				for (Integer year : listYear){   //(int year = 1985; year<2011;year++){
					for (int i = 0; i<2; i++){
						switch (i){
						case 0:
							print = obj.toString(year, "air_temp");
							break;
						case 1:
							print = obj.toString(year, "prec");
							break;
						}
						//System.out.println(print + "\r");
						output.write(print + "\r");
					}
				}
			}
			output.close();
			System.out.println(logFile.getCanonicalPath());
	        System.out.println("File has been written");
	
	    }catch(Exception e){
	        System.out.println("Could not create file");
	    }
	}

	private void readFile(String file, String type) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		try{
			String line;
			double [] coordinates = new double[2];
			while ((line = br.readLine()) != null) {
				if (line.substring(0, 15).equalsIgnoreCase("# coordinates: ")){
					System.out.println("found coordinate");
					coordinates = decryptCoord(line);
				}
				//System.out.println(line.substring(0,1));
				if(!line.substring(0,1).equalsIgnoreCase("#")){
					//System.out.println(line);
					deCryptString(line+" ", type, coordinates);
				}
			}
			br.close();
		}
		finally{
			br.close();
		}
	}

	private double[] decryptCoord(String line) {
		// TODO Auto-generated method stub
		double[] coord = new double[2];
		int i = 15;
		int idx = 0;
		for(int j=15; j<line.length();j++){
			if(Character.isWhitespace(line.charAt(j))){
				if(j>i && line.substring(i+1, j).length()>1){
					if (idx == 0){
						coord[idx] = Double.parseDouble(line.substring(i+1, j-2));
						System.out.println(line.substring(i, j-2));
						idx++;
					}else if(idx == 1){
						coord[idx] = Double.parseDouble(line.substring(i+1, j-2));
						System.out.println(line.substring(i,j-2));
						idx++;
					}
					i = j;
				}else{
					i = j;
				}
			}}
		
		return coord;
	}

	private void deCryptString(String s, String type, double[] coord) {
		//System.out.println(s.substring(0,14));
		double[] data = new double[12];
		int year = -999;
		int i = 0;
		int idx = 0;
		for(int j=0; j<s.length();j++){
			if(Character.isWhitespace(s.charAt(j))){
				if(j>i && s.substring(i+1, j).length()>1){
					if (idx != 0){
						data[idx-1] = Double.parseDouble(s.substring(i+1, j));
						//System.out.println(s.substring(i, j));
						idx++;
					}else{
						//System.out.println(s.substring(i,j));
						year = Integer.parseInt(s.substring(i+1, j));
						listYear.add(year);
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


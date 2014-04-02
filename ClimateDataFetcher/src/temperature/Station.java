package temperature;

import java.util.ArrayList;
import java.util.Iterator;

public class Station {
	private double longitude;
	private double latitude;
	private ArrayList<Data> dataAL;
	
	public Station(double lgt, double lat){
		setCoord(lgt, lat);
		dataAL = new ArrayList<Data>();
		hashCode();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	
	public void setTemp (int year, double[] t){
		double[] p = null;
		Data d = new Data(year, t, p);
		if(dataAL.contains(d)){
			Iterator<Data> it = dataAL.iterator();
			while(it.hasNext())
			{
			    Data elem = it.next();
			    if (elem.equals(d)){
			    	elem.setTemp(t);
			    }
			}
		}else{
			dataAL.add(d);
		}
	}
	
	public void setPrec(int year, double[] p){
		double[] t = null;
		Data d = new Data(year, t, p);
		if(dataAL.contains(d)){
			Iterator<Data> it = dataAL.iterator();
			while(it.hasNext())
			{
			    Data elem = it.next();
			    if (elem.equals(d)){
			    	elem.setPrec(p);
			    }
			}
		}else{
			dataAL.add(d);
		}
	}
	
	public void setCoord (double lg, double lat){
		longitude = lg;
		latitude = lat;
	}
	
	public double[] getCoord(){
		double[] coord = new double[2];
		coord[0] = longitude;
		coord[1] = latitude;
		return coord;
	}
	
	public double[] getTemp(int year){
		double[] donnee = new double[12];
		Data d = new Data(year);
		Iterator<Data> it = dataAL.iterator();
		while(it.hasNext())
		{
		    Data elem = it.next();
		    if (elem.equals(d)){
		    	donnee = elem.getTemp();
		    }
		}
		return donnee;
	}
	
	public double[] getPrec(int year){
		double[] donnee = new double[12];
		Data d = new Data(year);
		Iterator<Data> it = dataAL.iterator();
		while(it.hasNext())
		{
		    Data elem = it.next();
		    if (elem.equals(d)){
		    	donnee = elem.getPrec();
		    }
		}
		return donnee;
	}
	
	//@Override
	public String toString(int year, String type){
	
		String result = longitude +","+latitude+","+year;
		
		double[] data;
		if(type.equalsIgnoreCase("air_temp")){
			data = getTemp(year);
			for(int i = 0;i<data.length;i++){
				result += "," + data[i];
			}
		}else{
			data = getPrec(year);
			for(int i = 0;i<data.length;i++){
				result += "," + data[i];
			}
		}
		return result;
	}
	
}

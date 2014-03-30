package temperature;

public class Station {
	private int sid;
	private int year;
	private double[] coord; // long = 0 ; lat = 1
	private double[] temp;
	private double[] prec;
	private Station nextSt = null;
	private Station prevSt = null;
	
	public Station(){
		this.sid = sid;
		year = 0;
		coord = new double[2];
		initCoord();
		temp = new double[12];
		prec = new double[12];
		initData();
		boolean valid = true;
	}
	
	public void initCoord(){
		coord[0] = 1000;
		coord[1] = 100;
	}
	
	public void initData(){
		for(int i=0;i<temp.length;i++){
			temp[i] = -999;
			prec[i] = -999;
		}
	}
	
	public void setYear(int y){
		year = y;
	}
	
	public void setTemp (int idx, double t){
		if (idx<12){
			temp[idx] = t;
		}
	}
	
	public void setPrec(int idx, double p){
		if (idx<12){
			prec[idx] = p;
		}
	}
	
	public void setCoord (double lg, double lat){
		coord[0] = lg;
		coord[1] = lat;
	}
	
	public int getSid(){
		return sid;
	}
	public int getYear (){
		return year;
	}
	public double[] getCoord(){
		return coord;
	}
	public double[] getTemp(){
		return temp;
	}
	public double[] getPrec(){
		return prec;
	}
	public double getTemp(int idx){
		return temp[idx];
	}
	public double getPrec(int idx){
		return prec[idx];
	}
}

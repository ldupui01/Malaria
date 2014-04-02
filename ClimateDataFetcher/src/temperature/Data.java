package temperature;

public class Data {
	private int year;
	private double[] temp;
	private double[] prec;
	
	public Data(int y){
		setYear(y);
		temp = new double[12];
		prec = new double[12];
	}
	
	public Data(int y, double[] t, double[] p){
		setYear(y);
		setTemp(t);
		setPrec(p);
		hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
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
		Data other = (Data) obj;
		if (year != other.year)
			return false;
		return true;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double[] getTemp() {
		return temp;
	}

	public void setTemp(double[] temp) {
		this.temp = temp;
	}

	public double[] getPrec() {
		return prec;
	}

	public void setPrec(double[] prec) {
		this.prec = prec;
	}
}

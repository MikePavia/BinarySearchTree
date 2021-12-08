package assignment;
/**
 * cps 231
 * 11/18/2021
 * @author mikep
 *Implementation of the NameInterface class
 */
public class NameInfo implements NameInterface {
	private String name;
	private int rank;
	private int frequency;
	private double proportion;
	private double cumulativeProportion;


	public NameInfo(String name) {
		this.name = name;
	}



	public NameInfo() {	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}else {
			return false;
		}


	}





	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public int getRank() {

		return rank;
	}

	@Override
	public void setRank(int rank) {
		this.rank = rank;

	}

	@Override
	public int getFrequency() {

		return frequency;
	}

	@Override
	public void setFrequency(int frequency) {
		this.frequency = frequency;

	}

	@Override
	public double getProportion() {

		return proportion;
	}

	@Override
	public void setProportion(double proportion) {
		this.proportion = proportion;

	}

	@Override
	public double getCumulativeProportion() {

		return cumulativeProportion;
	}

	@Override
	public void setCumulativeProportion(double cumulativeProportion) {
		this.cumulativeProportion = cumulativeProportion;

	}


	
	@Override
	public int compareTo(NameInterface other) {
	int result = 0;
	
	//NameInterface name = (NameInterface) other;
	
	if (this.name.compareTo(other.getName()) < 0) {		
		result = -1;
	}else if (this.name.compareTo(other.getName()) > 0) {
		result = 1;
	}else {
		if(this.name.equals(other.getName())) {
			result = 0;
		}
	}
		
	return result;
	}
	
	



}

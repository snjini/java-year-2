
//Sasha Njini
//260783102

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;
	
	public Building(OneBuilding data){
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}
	
	public String toString(){
		String result = this.data.toString() + "\n";
		if (this.older != null){
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null){
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
		}
		if (this.younger != null){
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}
	
	public Building addBuilding (OneBuilding b){
		// ADD YOUR CODE HERE	
	Building building = new Building(data);	
	
	if(b.yearOfConstruction < this.data.yearOfConstruction) {
		if(this.older !=null) {
			this.older.addBuilding(b);
			
		} else {
			this.older = new Building(b);
		}
	}	
	
	else if(b.yearOfConstruction == this.data.yearOfConstruction && b.name != this.data.name)	{
		
		if(b.height > this.data.height) {
			this.data = b;
			
			if(this.same !=null) {
				this.same.addBuilding(building.data);
				
			} else {
				this.same = building;
			}
		} else {
			if(this.same != null) {
				this.same.addBuilding(b);	
				
			} else {
				this.same = new Building(b);
			}
		}
	}
	
	else if(b.yearOfConstruction > this.data.yearOfConstruction) {
		
		if(this.younger != null) {
			this.younger.addBuilding(b);
		} else {
			this.younger = new Building(b);	
		}
	}
		return this; 
		// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public Building addBuildings (Building b){
		// ADD YOUR CODE HERE
	if(b==null) {
		return this;
	}
	
	if(b.data !=null) {
		addBuilding(b.data);
	}
	
	if(b.older !=null) {
		addBuildings(b.older);	
	}
	
	if(b.same !=null) {
		addBuildings(b.same);
	}
	
	if(b.younger !=null) {
		addBuildings(b.younger);
	}
	
		return this; 
		// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public Building removeBuilding(OneBuilding b) {
		// ADD YOUR CODE HERE
	if (this.data.name == b.name && this.data.yearOfConstruction == b.yearOfConstruction
	&& this.data.height == b.height && this.data.yearForRepair == b.yearForRepair
	&& this.data.costForRepair == b.costForRepair) {

		if (this.same != null) {
		Building tempBuilding1 = this.older;
		Building tempBuilding2 = this.younger;
		Building tempBuilding3 = this.same;
		this.data = tempBuilding3.data;
		this.same = tempBuilding3.same;
		this.older = tempBuilding3.older;
		this.younger = tempBuilding3.younger;

			if (tempBuilding1 != null) {
				this.addBuildings(tempBuilding1);
			}

			if (tempBuilding2 != null) {
				this.addBuildings(tempBuilding2);
			}
		return this;
		}
		
		else if (this.older != null) {
		Building tempBuilding1 = this.same;
		Building tempBuilding2 = this.younger;
		Building tempBuilding3 = this.older;
		this.data = tempBuilding2.data;
		this.same = tempBuilding3.same;
		this.older = tempBuilding3.older;
		this.younger = tempBuilding3.younger;

			if (tempBuilding1 != null) {
				this.addBuildings(tempBuilding1);
			}

			if (tempBuilding2 != null) {
				this.addBuildings(tempBuilding2);
			}
		return this;
		}

		else if (this.younger != null) {
		Building tempBuilding1 = this.older;
		Building tempBuilding2 = this.same;
		Building tempBuilding3 = this.younger;
		this.data = tempBuilding3.data;
		this.same = tempBuilding3.same;
		this.older = tempBuilding3.older;
		this.younger = tempBuilding3.younger;

			if (tempBuilding1 != null) {
				this.addBuildings(tempBuilding1);
			}

			if (tempBuilding2 != null) {
				this.addBuildings(tempBuilding2);
			}
		return this;
		}

		else {
			
		this.data = null;
		this.same = null;
		this.older = null;
		this.younger = null;

		return this;
		}
	}

		if (this.older != null) {
		this.older.removeBuilding(b);
		}
		
		if (this.same != null) {
		this.same.removeBuilding(b);
		}
		
		if (this.younger != null) {
		this.younger.removeBuilding(b);
		}

		return this; 
		// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public int oldest(){
		// ADD YOUR CODE HERE
	if (this.older == null) {
		return this.data.yearOfConstruction;
	}
		return this.older.oldest();
	    // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
		
	public int highest(){
		// ADD YOUR CODE HERE
	int currentHeight = this.data.height;
		
	if(this.younger!=null && this.older!=null) {
		int youngerHeight = this.younger.highest();
		int olderHeight = this.older.highest();
		
		if (currentHeight >= youngerHeight && currentHeight >= olderHeight) {
			return currentHeight;
		}
		if (currentHeight < youngerHeight && currentHeight < olderHeight) {
			return youngerHeight;
		}
		if (youngerHeight <= olderHeight && currentHeight <= olderHeight ) {
			return olderHeight;
		}
		if (youngerHeight > olderHeight && youngerHeight > currentHeight) {
			return youngerHeight;
		}
	}
	
	if (this.younger!=null && this.older == null) {
		int youngerHeight = this.younger.highest();
		
		if (currentHeight >= youngerHeight) {
			return currentHeight;
		}
		if (currentHeight < youngerHeight) {
			return youngerHeight;
		}
	}
	
	if (this.younger==null && this.older != null) {
		int olderHeight = this.older.highest();
			
		if (currentHeight >= olderHeight) {
			return currentHeight;
		}
		if (currentHeight < olderHeight) {
			return olderHeight;
		}
	}
		
		return currentHeight;
		// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public OneBuilding highestFromYear (int year){
		// ADD YOUR CODE HERE
	if(year == this.data.yearOfConstruction) {
			return this.data;
	}
	else if(year > this.data.yearOfConstruction) {
		if(this.younger!=null) {
			return this.younger.highestFromYear(year);
		}
			
	}
	else if (year < this.data.yearOfConstruction) {
		if(this.older!=null) {
			return this.older.highestFromYear(year);
		}		
	}
		return null;
		// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	//helper method
	public int numOfSame(OneBuilding b) {
		
	if(same == null) {
		return 0;
	}
	
	int num = same.numOfSame(b);
		return num + 1;
	}
	
	//helper
	public int numInYear(int year) {
		
	int counter = 0;
		
	if(year == data.yearOfConstruction) {
		if(same == null) {
			counter = 1;
		}else {
			counter = 1+ numOfSame(data);
		}
	}
	else if(year > data.yearOfConstruction) {
		if(younger!=null) {
			return younger.numInYear(year);
		}
	}
	else if(year < data.yearOfConstruction) {
		if(older!=null) {		
			return older.numInYear(year);
		}
	}
		return counter;
	
	}
	
	public int numberFromYears (int yearMin, int yearMax){
		// ADD YOUR CODE HERE
	int counter = 0;
	
	if(yearMin > yearMax) {
			return 0;
	} else {
		while(yearMin<=yearMax) {
			numInYear(yearMin);
			counter = counter + numInYear(yearMin);
			yearMin++;
		}
	}
		return counter; 
		// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public int costOfYear (int year) {
	
	int counter = 0;
	
	if(year == this.data.yearForRepair) {
		counter =this.data.costForRepair;		
	}
	
	if(same!=null) {
		counter += same.costOfYear(year);
	}
	
	if(older!=null) {
		counter +=older.costOfYear(year);
	}
	if(younger!=null) {
		counter +=younger.costOfYear(year);
	}
		return counter;	
		
	}
	public int[] costPlanning (int nbYears){
		// ADD YOUR CODE HERE
		
	int[] cost = new int[nbYears];

	for(int i = 0; i < cost.length; i++) {
		int year = 2018+i;
		int num = costOfYear(year);
		cost[i] = num;
			
	}
		return cost; 
		// DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
}

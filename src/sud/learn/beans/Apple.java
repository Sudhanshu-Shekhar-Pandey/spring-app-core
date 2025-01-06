package sud.learn.beans;

public class Apple {
	
	public String fruitName;
	public int fruitId;
	public boolean withSeeds;
	
	// Setters - Required for Setter-based Dependency Injection
	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public void setFruitId(int fruitId) {
		this.fruitId = fruitId;
	}

	public void setWithSeeds(boolean withSeeds) {
		this.withSeeds = withSeeds;
	}

	public void printFruitDetails() {
		System.out.println(" Fruit Details : \n Namae: " + this.fruitName +"  , ID : "+ this.fruitId +"  , Seed : "+ this.withSeeds );
	}
	

}

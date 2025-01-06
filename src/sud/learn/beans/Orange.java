package sud.learn.beans;

public class Orange implements FruitInterface{
	
	public String fruitName;
	public int fruitId;
	public boolean withSeeds;
	
	
	// Required for Constructor-based Dependency Injection
	public Orange(String fruitName, int fruitId, boolean withSeeds) {
		this.fruitName = fruitName;
		this.fruitId = fruitId;
		this.withSeeds = withSeeds;
		System.out.println("Orange constructor called ... ");
	}

	@Override
	public String getFruitName() {
		return "Orange Fruit";
	}
	
	@Override
	public int getFruitId() {
		return 101;
	}
	
	@Override
	public void printFruitDetails() {
		System.out.println(" Fruit Details : \n Namae: " + this.fruitName +"  , ID : "+ this.fruitId +"  , Seed : "+ this.withSeeds );
	}
	

}

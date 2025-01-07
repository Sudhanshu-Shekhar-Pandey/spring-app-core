package sud.learn.beans;

import sud.learn.service.FruitService;

public class Orange implements FruitInterface{
	
	public String fruitName;
	public int fruitId;
	public boolean withSeeds;
	private FruitService fruitService;
	
	
	// Required for Constructor-based Dependency Injection with reference class obj 
	public Orange(String fruitName, int fruitId, boolean withSeeds, FruitService fruitService) {
		this.fruitName = fruitName;
		this.fruitId = fruitId;
		this.withSeeds = withSeeds;
		this.fruitService = fruitService;
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
	
	public void getFruitPrice() {
		System.out.println(" Calling via reference object ...");
		fruitService.getFruitPrice();
	}
	
}

package sud.learn.beans;

public class Orange implements FruitInterface{
	
	public String fruitName;
	public int fruitId;
	public boolean withSeeds;
	
	@Override
	public String getFruitName() {
		return "Orange Fruit";
	}
	
	@Override
	public int getFruitId() {
		return 101;
	}
	
	

}

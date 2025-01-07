package sud.learn.service;

public class FruitServiceImpl implements FruitService{
	
	@Override
	public void getFruitDetails() {
		System.out.println(" Fruit Details Fetched...");
	}
	
	@Override
	public void getFruitPrice() {
		System.out.println(" Fruit Price Fetched ... Rs 500 / kg ");
	}
	
	
}

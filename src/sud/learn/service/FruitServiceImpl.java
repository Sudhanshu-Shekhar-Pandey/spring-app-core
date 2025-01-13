package sud.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FruitServiceImpl implements FruitService{
	
	@Autowired
	PriceService priceService;
	
	// constructor needed only for 'autowire=constructor'
//	public FruitServiceImpl(PriceService priceService) {
//		this.priceService = priceService;
//	}

//	public void setPriceService(PriceService priceService) {
//		this.priceService = priceService;
//	}

	@Override
	public void getFruitDetails() {
		System.out.println(" Fruit Details Fetched...");
	}
	
	@Override
	public void getFruitPrice() {
//		System.out.println(" Fruit Price Fetched ... Rs 500 / kg ");
		priceService.getCostPrice();
	}
	
	public void test() {
		System.out.println("Testing Success. . .");
	}
	
	
	
	
}

package sud.learn.service;

import org.springframework.stereotype.Component;

@Component
public class PriceService {
	
	public void getCostPrice() {
		System.out.println("  Cost Price : XYZ.XY " );
	}
	
	public void getSellingPrice() {
		System.out.println("  Sellling Price : XXX " );
	}

}

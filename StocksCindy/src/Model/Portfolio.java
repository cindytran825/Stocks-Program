package Model;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {

  private final Map<String, Integer> inventory = new HashMap<>();


  public void addToPortfolio(String companyName, Integer quantity) {
    inventory.put(companyName, quantity);
  }
//
//  public double
}

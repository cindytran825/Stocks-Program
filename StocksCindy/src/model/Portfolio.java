package model;

import java.util.Map;

public interface Portfolio {

  Map<String, Integer> getListInventories();

  Map<String, Integer> editPortfolio(
          String companyName,
          int share
  ) throws IllegalArgumentException;

  double getValue(String date, String pathToStock);

  String getPortfolioName();

}

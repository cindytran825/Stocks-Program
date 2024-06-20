package view;

import java.awt.event.ActionListener;

import javax.swing.*;

public interface IGUIViewMain extends GUIView {
  void msgBox();
  void checkComponent();
  void invalidInput();
  void buildBuyBox(ActionListener listener, String[] tickers);
  void buildComponentBox(ActionListener listener);
  void buildCreatePortfolio(ActionListener listener);
  void setCompositionText(String result);
  void setComboboxDisplay(String portfolioName);
  JComboBox<String> getCombobox();
  void updatePortfolio(String newPortfolioName);
  String getCreateName();
  void createSuccess();
}

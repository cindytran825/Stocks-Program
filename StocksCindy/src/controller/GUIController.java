package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Model;
import view.GUIViewImpl;

public class GUIController implements ActionListener, Controller {
  private final Model model;
  private final GUIViewImpl guiView; // TODO CHANGE TYPE
  private final String stockDirectory;
  private final String portfolioDirectory;
  private String portName;
  private String ticker;
  private String share;
  private String date;

  public GUIController(Model model, GUIViewImpl guiView) {
    this.model = model;
    this.guiView = guiView;
    this.stockDirectory = "StocksCindy/CSVFiles";
    this.portfolioDirectory = "StocksCindy/UserPortfolio";
  }

  @Override
  public void goControl() {
    guiView.setListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    guiView.checkComponent();
    portName = guiView.getSelectedPortfolioName();

    switch (e.getActionCommand()) {
      case "Buy/Sell":
        buildBuySell();
        break;
      case "Composition/Value":
        buildCompVal();
        //call class
        break;

      case "Portfolio Selection":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          guiView.setComboboxDisplay((String) box.getSelectedItem());
        }
        break;

      case "Create portfolio":
        System.out.println("Create portfolio");
        guiView.buildCreatePortfolio(this);
        break;

      case "Create":
        System.out.println("Created");
        String newName = guiView.getCreateName();
        if (!newName.isEmpty()) {
          model.createPortfolio(newName);
          guiView.updatePortfolio(newName);
          guiView.createSuccess();
        }
        break;


      case "buy button":
        ticker = guiView.getPurchaseTicker();
        share = guiView.getPurchaseShares();
        date = guiView.getPurchaseDate();
        try {
          model.buyStock(portName, ticker, share, date);
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        buildBuySell();
        break;

      case "sell button":
        ticker = guiView.getPurchaseTicker();
        share = guiView.getPurchaseShares();
        date = guiView.getPurchaseDate();
        try {
          model.sellStock(portName, ticker, share, date);
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        buildBuySell();
        break;

      case "comp button":
        System.out.println("composition");
        date = guiView.getAnalysisDate();
        try {
          System.out.println(portName);
          System.out.println(date);
          String valueText = model.getPortfolioComposition(portName, date);
          System.out.println(valueText);
          guiView.setCompositionText(valueText);
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        buildCompVal();
        break;

      case "value button":
        date = guiView.getAnalysisDate();
        try {
          String valueText = model.getPortfolioDistribution(portName, date)
                  + "\n\nTotal Value: "
                  + model.evaluatePortfolio(portName, date);
          guiView.setCompositionText(valueText);
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        buildCompVal();
        break;
    }
  }

  private void buildBuySell() {
    if (guiView.getCombobox().getSelectedIndex() == 0) {
      guiView.msgBox();
    } else {
      guiView.buildBuyBox(this, model.getStockNames().split("\\n"));
    }
  }

  private void buildCompVal() {
    if (guiView.getCombobox().getSelectedIndex() == 0) {
      guiView.msgBox();
    } else {
      guiView.buildComponentBox(this);
    }
  }


}

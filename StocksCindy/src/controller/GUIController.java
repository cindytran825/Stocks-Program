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

  public GUIController(Model model, GUIViewImpl guiView) {
      this.model = model;
      this.guiView = guiView;
      this.stockDirectory = "StocksCindy/CSVFiles";
      this.portfolioDirectory = "StocksCindy/UserPortfolio";
  }

  @Override
  public void goControl() {
    System.out.println(guiView);
    guiView.setListener(this);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    guiView.checkComponent();

      switch (e.getActionCommand()) {
        case "Buy/Sell":
          if (guiView.getCombobox().getSelectedIndex() == 0) {
            guiView.msgBox();
          } else {
            guiView.buildBuyBox(this, model.getStockNames().split("\\n"));
          }

          break;
        case "Composition/Value":
          if (guiView.getCombobox().getSelectedIndex() == 0) {
            guiView.msgBox();
          } else {
            guiView.buildComponentBox(this);
          }
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
          break;

        case "buy button":
          System.out.println("buy stock");
          break;

        case "sell button":
          System.out.println("sell stock");
          break;
      }


  }
}

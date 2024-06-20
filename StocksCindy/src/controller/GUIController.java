package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

import model.Model;
import model.StocksModel;
import view.GUIBuy;
import view.GUIComposition;
import view.GUICreatePortfolio;
import view.GUIGetValue;
import view.GUINoPortfolio;
import view.GUIView;
import view.GUIViewImpl;
import view.View;

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

  public void processCommand(String command) {
    StringBuilder output = new StringBuilder();
    Scanner s = new Scanner(command);

    while(s.hasNextLine()) {
      String in = s.next();
      switch(in) {
        case "Buy":
          String name = s.nextLine();
          String ticker = s.nextLine();
          String shares = s.nextLine();
          String date = s.nextLine();
          model.buyStock(name, ticker, shares, date);

      }

    }

  }




  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("going through");

    guiView.checkComponent();

    // if no portfolio has been selected
//    String portfolioName;
//    if (e.getSource() instanceof JComboBox) {
//      JComboBox<String> box = (JComboBox<String>) e.getSource();
//      portfolioName = (String) box.getSelectedItem();
//      if (portfolioName.equals("Select Portfolio")) {
//        noPort = new GUINoPortfolio();
//        noPort.setBounds(0, 200, 500, 100);
//        mainPanel.add(noPort);
//        guiView.add(mainPanel);
//        guiView.pack();
//        return;
//      }
//    }

    switch (e.getActionCommand()) {
      case "Buy":
          guiView.buildBuyBox();

//        guiView.createBuyBox();
//        --> "submit"
//                --> model.buy....


//        /**
//         * calls the GUIBuy panel that displays a new panel.
//         */
//        radioDisplay.setText("Buy was selected");
//        buyPanel = new GUIBuy();
//        buyPanel.setBounds(0, 200, 500, 100);
//        mainPanel.add(buyPanel);
//        guiView.add(mainPanel);
//        guiView.pack();
//        //call the portcreate class
        break;
      case "Sell":
        guiView.buildSellBox();
        break;
      case "Composition":
        guiView.buildComponentBox();
        //call class
        break;
      case "Value":
        guiView.buildValueBox();
        //call class
        break;

      case "Portfolio Selection":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          guiView.setComboboxDisplay((String) box.getSelectedItem());
        }
        break;

      case "Create portfolio":
        guiView.buildCreatePortfolio();
        break;

//      case "Open file": {
//        final JFileChooser fchooser = new JFileChooser(".");
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                "JPG & GIF Images", "jpg", "gif");
//        fchooser.setFileFilter(filter);
//        int retvalue = fchooser.showOpenDialog(SwingFeaturesFrame.this);
//        if (retvalue == JFileChooser.APPROVE_OPTION) {
//          File f = fchooser.getSelectedFile();
//          fileOpenDisplay.setText(f.getAbsolutePath());
//        }
//      }
//      break;
//      case "Save file": {
//        final JFileChooser fchooser = new JFileChooser(".");
//        int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
//        if (retvalue == JFileChooser.APPROVE_OPTION) {
//          File f = fchooser.getSelectedFile();
//          fileSaveDisplay.setText(f.getAbsolutePath());
//        }
//      }
//      break;
    }
  }
}

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

public class GUIController implements Controller, ActionListener {
  private Model model;
  private View view;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JLabel radioDisplay;
  private JLabel comboboxDisplay;
  private GUIBuy buyPanel;
  private GUIComposition composition;
  private GUIGetValue getTypeValue;
  private GUICreatePortfolio create;
  private GUINoPortfolio noPort;
  private final Scanner scan;
  private final GUIViewImpl guiView;
  private final String stockDirectory;
  private final String portfolioDirectory;

  public GUIController(Model model, View view, Readable readable, GUIViewImpl guiView) {
      this.view = view;
      this.model = model;
      this.scan = new Scanner(readable);
      this.guiView = guiView;
      this.stockDirectory = "StocksCindy/CSVFiles";
      this.portfolioDirectory = "StocksCindy/UserPortfolio";
      guiView.setListener(this);
  }


  @Override
  public void goControl() {
//    this.view.addActionListener(this);
//    this.view.makeVisible();
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
    if (mainPanel.getComponentCount() > 3) {
      mainPanel.remove(mainPanel.getComponentCount() - 1);
    }

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
        radioDisplay.setText("Sell was selected");
        buyPanel = new GUIBuy();
        buyPanel.setBounds(0, 200, 500, 100);
        mainPanel.add(buyPanel);
        guiView.add(mainPanel);
        guiView.pack();
        break;
      case "Composition":
        /**
         * calls composition panel (class)
         */
        radioDisplay.setText("Composition was selected");
        composition = new GUIComposition();
        composition.setBounds(0, 200, 500, 100);
        mainPanel.add(composition);
        guiView.add(mainPanel);
        guiView.pack();
        //call class
        break;
      case "Value":
        /**
         * calls class that asks what they want to evaluate.
         */
        radioDisplay.setText("Value was selected");
        getTypeValue = new GUIGetValue();
        getTypeValue.setBounds(0, 200, 500, 100);
        mainPanel.add(getTypeValue);
        guiView.pack();
        //call class
        break;

      case "Portfolio Selection":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          comboboxDisplay.setText("You selected: " + (String) box.getSelectedItem());
        }
        break;

      case "Create portfolio":
        radioDisplay.setText("Create Portfolio was selected");
        create = new GUICreatePortfolio();
        create.setBounds(0, 200, 500, 100);
        mainPanel.add(create);
        guiView.pack();
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

    mainPanel.revalidate();
    mainPanel.repaint();

  }
}

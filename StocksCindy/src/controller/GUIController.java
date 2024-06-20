package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import model.Model;
import view.GUIViewImpl;
import view.IGUIViewMain;

/**
 * This is the main GUI controller for the portfolio manager. It is the acton listener that
 * listens to all button actions and communicate with the model to send the output to the
 * GUI view.
 */
public class GUIController implements ActionListener, Controller {
  private final Model model;
  private final IGUIViewMain guiView;

  /**
   * This constructs the model and the GUI view.
   *
   * @param model   model
   * @param guiView GUI view
   */
  public GUIController(Model model, GUIViewImpl guiView) {
    this.model = model;
    this.guiView = guiView;
  }

  @Override
  public void goControl() {
    guiView.setListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    guiView.checkComponent();
    String portName = guiView.getSelectedPortfolioName();

    switch (e.getActionCommand()) {

      case "Portfolio Selection":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          guiView.setComboboxDisplay((String) box.getSelectedItem());
        }
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
        String ticker = guiView.getPurchaseTicker();
        String share = guiView.getPurchaseShares();
        String date = guiView.getPurchaseDate();
        try {
          model.buyStock(portName, ticker, share, date);
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        guiView.buySuccess();
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
        guiView.sellSuccess();
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
        guiView.compSuccess();
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
        guiView.valueSuccess();
        buildCompVal();
        break;
      default:
    }
  }

  private void buildBuySell() {
    if (guiView.getCombobox().getSelectedIndex() == 0) {
      guiView.msgBox();
    }
  }

  private void buildCompVal() {
    if (guiView.getCombobox().getSelectedIndex() == 0) {
      guiView.msgBox();
    }
  }
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        List<String> pastNames = List.of(model.getPortfolioNames().split("\\n"));
        if (!newName.isEmpty()
                && !newName.equals("Select Portfolio")
                && !pastNames.contains(newName)
        ) {
          model.createPortfolio(newName);
          guiView.updatePortfolio(newName);
          guiView.createSuccess();
        } else {
          guiView.invalidInput();;
        }
        break;

      case "buy button":
        String ticker = guiView.getPurchaseTicker();
        String share = guiView.getPurchaseShares();
        String date = guiView.getPurchaseDate();
        try {
          this.checkIfPortfolioSelected();
          model.buyStock(portName, ticker, share, date);
          guiView.buySuccess();
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        break;

      case "sell button":
        ticker = guiView.getPurchaseTicker();
        share = guiView.getPurchaseShares();
        date = guiView.getPurchaseDate();
        try {
          this.checkIfPortfolioSelected();
          model.sellStock(portName, ticker, share, date);
          guiView.sellSuccess();
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        break;

      case "comp button":
        date = guiView.getAnalysisDate();
        try {
          this.checkIfPortfolioSelected();
          String valueText = model.getPortfolioComposition(portName, date);
          guiView.setCompositionText(valueText);
          guiView.compSuccess();
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        break;

      case "value button":
        date = guiView.getAnalysisDate();
        try {
          this.checkIfPortfolioSelected();
          String valueText = model.getPortfolioDistribution(portName, date)
                  + "\n\nTotal Value: "
                  + model.evaluatePortfolio(portName, date);
          guiView.setCompositionText(valueText);
          guiView.valueSuccess();
        } catch (Exception exception) {
          guiView.invalidInput();
        }
        break;
      default:
    }
  }

  private void checkIfPortfolioSelected() {
    if (guiView.getCombobox().getSelectedIndex() == 0) {
      guiView.msgBox();
    }
  }
}

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


/**
 * This class is the GUI viewer that creates the main GUI window. It is the ultimate GUI window
 * that holds and create other panels within it (the mother GUI).
 */
public class GUIViewImpl extends JFrame implements IGUIViewMain {
  private final JPanel mainPanel;
  private final JLabel radioDisplay;
  private final JLabel radioOtherDisplay;
  private final JPanel radioPanel;
  private final JLabel comboboxDisplay;
  private final JPanel comboboxPanel;
  private GUIBuy buyPanel;
  private GUIComposition composition;
  private GUICreatePortfolio create;
  private final JButton createPortButton;
  private final JRadioButton[] radioButtons;
  private final JComboBox<String> combobox;


  /**
   * This constructor creates the main GUI menu option.
   */
  public GUIViewImpl(String[] names) {
    super();
    setTitle("Stocks Program");
    setSize(700, 500);

    // making the main panel
    this.setLayout(new BorderLayout());
    mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    mainPanel.setPreferredSize(new Dimension(700, 500));
    this.add(mainPanel);

    //this is the dropdown that asks for the list of existing portfolios
    comboboxPanel = new JPanel();
    comboboxPanel.setPreferredSize(new Dimension(500, 50));
    comboboxPanel.setBounds(0, 0, 500, 100);
    comboboxDisplay = new JLabel("Choose from existing portfolios");
    comboboxPanel.add(comboboxDisplay);
    combobox = new JComboBox<String>();
    combobox.addItem("Select Portfolio");
    //the event listener when an option is selected
    combobox.setActionCommand("Portfolio Selection");
    for (int i = 0; i < names.length; i++) {
      combobox.addItem(names[i]);
    }
    comboboxPanel.add(combobox);

    // create portfolio button
    createPortButton = new JButton("Create Portfolio");
    createPortButton.setActionCommand("Create portfolio");
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));

    // this is the radio panel of the menu (buy/sell and composition/value action choices).
    String[] buttons = {"Buy/Sell", "Composition/Value"};
    ButtonGroup rGroup1 = new ButtonGroup();
    radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    radioPanel.setBounds(0, 200, 500, 100);
    radioPanel.setBorder(BorderFactory.createTitledBorder("Portfolio Editor Menu"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    radioOtherDisplay = new JLabel("Which actions would you like to perform on"
            + " the portfolio?");
    radioPanel.add(radioOtherDisplay);
    radioButtons = new JRadioButton[buttons.length];
    for (int i = 0; i < buttons.length; i++) {
      radioButtons[i] = new JRadioButton(buttons[i]);
      radioButtons[i].setActionCommand(buttons[i]);
      rGroup1.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }
    radioDisplay = new JLabel("Which one did the user select?");
    radioPanel.add(radioDisplay);

    // add all these panels to the main frame
    mainPanel.add(comboboxPanel);
    mainPanel.add(createPortButton);
    mainPanel.add(radioPanel);
    this.pack();
  }

  @Override
  public void checkComponent() {
    if (mainPanel.getComponentCount() > 3) {
      mainPanel.remove(mainPanel.getComponentCount() - 1);
    }
  }

  @Override
  public void msgBox() {
    JOptionPane.showMessageDialog(null, "Please select a portfolio!");
  }

  @Override
  public void invalidInput() {
    JOptionPane.showMessageDialog(null, "Invalid Input!");
  }

  @Override
  public void setListener(ActionListener listener) {
    combobox.addActionListener(listener);
    createPortButton.addActionListener(listener);
    for (JRadioButton radioButton : radioButtons) {
      radioButton.addActionListener(listener);
    }
  }

  @Override
  public void buildBuyBox(ActionListener listener, String[] tickers) {
    /**
     * calls the GUIBuy panel that displays a new panel.
     */
    radioDisplay.setText("Buy/Sell was selected");
    buyPanel = new GUIBuy(tickers);
    buyPanel.setListener(listener);
    buyPanel.setBounds(0, 200, 500, 100);
    mainPanel.add(buyPanel);
    this.add(mainPanel);
    this.pack();
    //call the portcreate class
    updateMain();
  }

  @Override
  public void buildComponentBox(ActionListener listener) {
    /**
     * calls composition panel (class)
     */
    radioDisplay.setText("Composition/Value was selected");
    composition = new GUIComposition();
    composition.setListener(listener);
    composition.setBounds(0, 200, 500, 100);
    mainPanel.add(composition);
    this.add(mainPanel);
    this.pack();
    updateMain();
  }

  @Override
  public void buildCreatePortfolio(ActionListener listener) {
    radioDisplay.setText("Create Portfolio was selected");
    create = new GUICreatePortfolio(mainPanel);
    create.setListener(listener);
    create.setBounds(0, 200, 500, 100);
    mainPanel.add(create);
    this.pack();
    updateMain();
  }

  @Override
  public void setCompositionText(String result) {
    composition.setResultText(result);
    mainPanel.revalidate();
  }

@Override
  public void setComboboxDisplay(String portfolioName) {
    comboboxDisplay.setText("You selected: " + portfolioName);
    updateMain();
  }

  @Override
  public JComboBox<String> getCombobox() {
    return combobox;
  }

  @Override
  public void updatePortfolio(String newPortfolioName) {
    combobox.addItem(newPortfolioName);
    mainPanel.revalidate();
  }

  @Override
  public String getCreateName() {
    return create.getName();
  }

  public void createSuccess() {
    create.displaySuccess();
  }

  private void updateMain() {
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  public String getSelectedPortfolioName() {
    return (String) combobox.getSelectedItem();
  }

  public String getPurchaseTicker() {
    return buyPanel.getTicker();
  }

  public String getPurchaseDate() {
    return buyPanel.getDate();
  }

  public String getPurchaseShares() {
    return buyPanel.getShares();
  }

  public String getAnalysisDate() {
    return composition.getDate();
  }
}

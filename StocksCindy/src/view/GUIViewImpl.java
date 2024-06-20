package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;


import javax.swing.*;

public class GUIViewImpl extends JFrame implements GUIView {
  private JPanel mainPanel;
  private JLabel radioDisplay;
  private JLabel comboboxDisplay;
  private GUIBuy buyPanel;
  private GUIComposition composition;
  private GUICreatePortfolio create;
  private JButton createPortButton;
  private JRadioButton[] radioButtons;
  private JComboBox<String> combobox;


  /**
   * this is first called then the user runs the GUI.
   */
  public GUIViewImpl(String[] names) {
    super();
    setTitle("Stocks Program");
    setSize(700, 500);
//    this.setResizable(false);

    this.setLayout(new BorderLayout());
    mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    mainPanel.setPreferredSize(new Dimension(700, 500));
    this.add(mainPanel);
    /**
     * this is the dropdown that asks for the list of existing portfolios
     */
    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setPreferredSize(new Dimension(500, 50));
    comboboxPanel.setBounds(0, 0, 500, 100);
    mainPanel.add(comboboxPanel);
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

    createPortButton = new JButton("Create Portfolio");
    createPortButton.setActionCommand("Create portfolio");
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(createPortButton);

    /**
     * this is the radio panel of the menu.
     */
    //text area
    JPanel radioPanel = new JPanel();
    radioPanel.setBounds(0, 200, 500, 100);
    radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    radioPanel.setBorder(BorderFactory.createTitledBorder("Portfolio Editor Menu"));

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JLabel radioOtherDisplay = new JLabel("Which actions would you like to perform on"
            + " the portfolio?");
    radioPanel.add(radioOtherDisplay);

    String[] buttons = {"Buy/Sell", "Composition/Value"};
    radioButtons = new JRadioButton[buttons.length];
    ButtonGroup rGroup1 = new ButtonGroup();

    for (int i = 0; i < buttons.length; i++) {
      radioButtons[i] = new JRadioButton(buttons[i]);
      radioButtons[i].setActionCommand(buttons[i]);
      rGroup1.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }

    radioDisplay = new JLabel("Which one did the user select?");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);




    this.pack();
  }

  // TODO @Override
  public void checkComponent() {
    if (mainPanel.getComponentCount() > 3) {
      mainPanel.remove(mainPanel.getComponentCount() - 1);
    }
  }


  public void msgBox() {
    JOptionPane.showMessageDialog(null, "Please select a portfolio!");
  }


  // TODO @Override
  public void setListener(ActionListener listener) {
    combobox.addActionListener(listener);
    createPortButton.addActionListener(listener);
    for (int i = 0; i < radioButtons.length; i++) {
      System.out.println(radioButtons[i]);
      radioButtons[i].addActionListener(listener);
    }
  }

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

  public void buildCreatePortfolio(ActionListener listener) {
    radioDisplay.setText("Create Portfolio was selected");
    create = new GUICreatePortfolio();
    create.setListener(listener);
    create.setBounds(0, 200, 500, 100);
    mainPanel.add(create);
    this.pack();
    updateMain();

  }

  public void setComboboxDisplay(String portfolioName) {
    comboboxDisplay.setText("You selected: " + portfolioName);
    updateMain();
  }

  public JComboBox<String> getCombobox() {
    return combobox;
  }

  private void updateMain() {
    mainPanel.revalidate();
    mainPanel.repaint();
  }

}

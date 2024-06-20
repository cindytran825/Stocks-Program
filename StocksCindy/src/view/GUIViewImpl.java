package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIViewImpl extends JFrame implements ActionListener, GUIView {
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JLabel radioDisplay;
  private JLabel comboboxDisplay;
  private GUIBuy buyPanel;
  private GUIComposition composition;
  private GUIGetValue getTypeValue;
  private GUICreatePortfolio create;
  private GUINoPortfolio noPort;
  private JButton createPortButton;
  private JRadioButton[] radioButtons;
  private JComboBox<String> combobox;


  /**
   * this is first called then the user runs the GUI.
   */
  public GUIViewImpl() {
    super();
    setTitle("Stocks Program");
    setSize(600, 500);
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


    String[] options = {"Select Portfolio", "ADD LIST OF PORTFOLIOS"};
    combobox = new JComboBox<String>();

    //the event listener when an option is selected
    combobox.setActionCommand("Portfolio Selection");
    for (int i = 0; i < options.length; i++) {
      combobox.addItem(options[i]);
    }

    comboboxPanel.add(combobox);

    createPortButton = new JButton("Create Portfolio");
    createPortButton.setActionCommand("Create portfolio");
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));
//    comboboxPanel.add(createPortButton);
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

    radioButtons = new JRadioButton[4];
    ButtonGroup rGroup1 = new ButtonGroup();
    String[] buttons = {"Buy", "Sell", "Composition", "Value"};

    for (int i = 0; i < buttons.length; i++) {
      radioButtons[i] = new JRadioButton(buttons[i]);
      radioButtons[i].setActionCommand(buttons[i]);
      rGroup1.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }

    radioDisplay = new JLabel("Which one did the user select?");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);

//    buyPanel = new GUIBuy();
//    buyPanel.setBounds(0, 200, 500, 100);
////    buyPanel.add(new JLabel("Buy panel content goes here."));
//    mainPanel.add(buyPanel);
//    this.add(mainPanel);


    //dialog boxes
//    JPanel dialogBoxesPanel = new JPanel();
//    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
//    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
//    mainPanel.add(dialogBoxesPanel);
    this.pack();
  }

  // TODO @Override
  public void checkComponent() {
    if (mainPanel.getComponentCount() > 3) {
      mainPanel.remove(mainPanel.getComponentCount() - 1);
    }
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


  //  @Override
//  public void actionPerformed(ActionEvent e) {
//    if (mainPanel.getComponentCount() > 3) {
//      mainPanel.remove(mainPanel.getComponentCount() - 1);
//    }
//
//    // if no portfolio has been selected
////    String portfolioName;
////    if (e.getSource() instanceof JComboBox) {
////      JComboBox<String> box = (JComboBox<String>) e.getSource();
////      portfolioName = (String) box.getSelectedItem();
////      if (portfolioName.equals("Select Portfolio")) {
////        noPort = new GUINoPortfolio();
////        noPort.setBounds(0, 200, 500, 100);
////        mainPanel.add(noPort);
////        this.add(mainPanel);
////        this.pack();
////        return;
////      }
////    }
//
//    switch (e.getActionCommand()) {
//      case "Buy":
//        /**
//         * calls the GUIBuy panel that displays a new panel.
//         */
//        radioDisplay.setText("Buy was selected");
//        buyPanel = new GUIBuy();
//        buyPanel.setBounds(0, 200, 500, 100);
//        mainPanel.add(buyPanel);
//        this.add(mainPanel);
//        this.pack();
//        //call the portcreate class
//        break;
//      case "Sell":
//        radioDisplay.setText("Sell was selected");
//        buyPanel = new GUIBuy();
//        buyPanel.setBounds(0, 200, 500, 100);
//        mainPanel.add(buyPanel);
//        this.add(mainPanel);
//        this.pack();
//        break;
//      case "Composition":
//        /**
//         * calls composition panel (class)
//         */
//        radioDisplay.setText("Composition was selected");
//        composition = new GUIComposition();
//        composition.setBounds(0, 200, 500, 100);
//        mainPanel.add(composition);
//        this.add(mainPanel);
//        this.pack();
//        //call class
//        break;
//      case "Value":
//        /**
//         * calls class that asks what they want to evaluate.
//         */
//        radioDisplay.setText("Value was selected");
//        getTypeValue = new GUIGetValue();
//        getTypeValue.setBounds(0, 200, 500, 100);
//        mainPanel.add(getTypeValue);
//        this.pack();
//        //call class
//        break;
//
//      case "Portfolio Selection":
//        if (e.getSource() instanceof JComboBox) {
//          JComboBox<String> box = (JComboBox<String>) e.getSource();
//          comboboxDisplay.setText("You selected: " + (String) box.getSelectedItem());
//        }
//        break;
//
//      case "Create portfolio":
//        radioDisplay.setText("Create Portfolio was selected");
//        create = new GUICreatePortfolio();
//        create.setBounds(0, 200, 500, 100);
//        mainPanel.add(create);
//        this.pack();
//        break;
//
////      case "Open file": {
////        final JFileChooser fchooser = new JFileChooser(".");
////        FileNameExtensionFilter filter = new FileNameExtensionFilter(
////                "JPG & GIF Images", "jpg", "gif");
////        fchooser.setFileFilter(filter);
////        int retvalue = fchooser.showOpenDialog(SwingFeaturesFrame.this);
////        if (retvalue == JFileChooser.APPROVE_OPTION) {
////          File f = fchooser.getSelectedFile();
////          fileOpenDisplay.setText(f.getAbsolutePath());
////        }
////      }
////      break;
////      case "Save file": {
////        final JFileChooser fchooser = new JFileChooser(".");
////        int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
////        if (retvalue == JFileChooser.APPROVE_OPTION) {
////          File f = fchooser.getSelectedFile();
////          fileSaveDisplay.setText(f.getAbsolutePath());
////        }
////      }
////      break;
//    }
//
//    mainPanel.revalidate();
//    mainPanel.repaint();
//
//  }


  public void buildBuyBox() {
    /**
     * calls the GUIBuy panel that displays a new panel.
     */
    radioDisplay.setText("Buy was selected");
    buyPanel = new GUIBuy();
    buyPanel.setBounds(0, 200, 500, 100);
    mainPanel.add(buyPanel);
    this.add(mainPanel);
    this.pack();
    //call the portcreate class
    updateMain();
  }

  public void buildSellBox() {
    radioDisplay.setText("Sell was selected");
    buyPanel = new GUIBuy();
    buyPanel.setBounds(0, 200, 500, 100);
    mainPanel.add(buyPanel);
    this.add(mainPanel);
    this.pack();
    updateMain();
  }

  public void buildComponentBox() {
    /**
     * calls composition panel (class)
     */
    radioDisplay.setText("Composition was selected");
    composition = new GUIComposition();
    composition.setBounds(0, 200, 500, 100);
    mainPanel.add(composition);
    this.add(mainPanel);
    this.pack();
    updateMain();
  }

  public void buildValueBox() {
    /**
     * calls class that asks what they want to evaluate.
     */
    radioDisplay.setText("Value was selected");
    getTypeValue = new GUIGetValue();
    getTypeValue.setBounds(0, 200, 500, 100);
    mainPanel.add(getTypeValue);
    this.pack();
    updateMain();
  }

  public void buildCreatePortfolio() {
    radioDisplay.setText("Create Portfolio was selected");
    create = new GUICreatePortfolio();
    create.setBounds(0, 200, 500, 100);
    mainPanel.add(create);
    this.pack();
    updateMain();

  }

  public void setComboboxDisplay(String portfolioName) {
    comboboxDisplay.setText("You selected: " + portfolioName);
    updateMain();
  }

  private void updateMain() {
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e){
    System.out.println("hello");
  }


}

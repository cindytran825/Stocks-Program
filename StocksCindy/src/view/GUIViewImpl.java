package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


/**
 * This class is the GUI viewer that creates the main GUI window. It is the ultimate GUI window
 * that holds and create other panels within it (the mother GUI).
 */
public class GUIViewImpl extends JFrame implements IGUIViewMain {
  private final JPanel mainPanel;
  private final JLabel comboboxDisplay;
  private final JPanel comboboxPanel;
  private final JComboBox<String> combobox;
  private final JButton compButton;
  private final JButton valueButton;
  private final JComboBox<String> yearBoxComp;
  private final JComboBox<String> monthBoxComp;
  private final JComboBox<String> dayBoxComp;
  private JLabel tickerBoxDisplay;
  private JLabel buyDisplay;
  private JLabel portLabel;
  private JPanel insidePanel;
  private JPanel createPanel;
  private JButton buyButton;
  private JButton sellPortButton;
  private JPanel submitPanel;
  private final JComboBox<String> dayBox;
  private final JComboBox<String> monthBox;
  private final JComboBox<String> yearBox;
  private final JComboBox<String> tickerBox;
  private final JTextArea shareBox;
  private final JLabel warning;
  private final JPanel warningPanel;
  private final JButton pButton;
  private final JLabel display;
  private final JTextArea textArea;
  private final JTextArea sTextArea;


  /**
   * This constructor creates the main GUI menu option.
   */
  public GUIViewImpl(String[] names, String[] ticker) {
    super();
    setTitle("Stocks Program");
    setSize(700, 600);

    // making the main panel
    this.setLayout(new BorderLayout());
    mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    mainPanel.setPreferredSize(new Dimension(700, 600));
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
    mainPanel.add(comboboxPanel);


    warning = new JLabel("Please make sure to choose from an existing portfolio before submitting!");
    warningPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    warningPanel.add(warning);
    mainPanel.add(warningPanel);
    this.add(mainPanel);

    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBounds(0, 200, 500, 100);
    tickerBoxDisplay = new JLabel("Ticker: ");
    tickerBox = new JComboBox<String>();
    tickerBox.setActionCommand("Ticker");
    for (int i = 0; i < ticker.length; i++) {
      tickerBox.addItem(ticker[i]);
    }

    comboboxPanel.add(tickerBox);

    shareBox = new JTextArea(0, 10);
    JLabel shareBoxDisplay = new JLabel("Shares: ");
    comboboxPanel.add(shareBox);
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));


    java.util.List<String> yearing = new ArrayList<>();
    JLabel selectYear = new JLabel("Select Year");
    for (int i = 2000;  i < 2025 ; i++) { // MAKE IT SO THAT ITS THE CURRENT YEAR
      yearing.add(String.valueOf(i));
    }
    String[] years = new String[yearing.size()];
    years = yearing.toArray(years);
    yearBox = new JComboBox<String>();
    for (int i = 0; i < years.length; i++) {
      yearBox.addItem(years[i]);
    }
    JPanel selectionListPanel = new JPanel();

    selectionListPanel.add(selectYear);
    selectionListPanel.add(yearBox);

    java.util.List<String> monthList = new ArrayList<>();
    JLabel selectMonth = new JLabel("Select Month");
    for (int i = 1;  i < 13 ; i++) {
      monthList.add(String.valueOf(i));
    }
    String[] listOfMonths = new String[monthList.size()];
    listOfMonths = monthList.toArray(listOfMonths);
    monthBox = new JComboBox<String>();
    for (int i = 0; i < listOfMonths.length; i++) {
      monthBox.addItem(listOfMonths[i]);
    }
    JPanel monthListPanel = new JPanel();
    monthListPanel.add(selectMonth);
    monthListPanel.add(monthBox);


    List<String> dayList = new ArrayList<>();
    JLabel selectDay = new JLabel("Select Day");
    for (int i = 1;  i < 32 ; i++) {
      dayList.add(String.valueOf(i));
    }
    String[] listOfDays = new String[dayList.size()];
    listOfDays = dayList.toArray(listOfDays);
    dayBox = new JComboBox<String>();
    for (int i = 0; i < listOfDays.length; i++) {
      dayBox.addItem(listOfDays[i]);
    }
    JPanel dayListPanel = new JPanel();
    dayListPanel.add(selectDay);
    dayListPanel.add(dayBox);

    List<String> yearCompList = new ArrayList<>();
    JLabel selectYearComp = new JLabel("Select Year");

    for (int i = 2000; i < 2025; i++) {
      yearCompList.add(String.valueOf(i));
    }
    String[] yearsComp = new String[yearCompList.size()];
    yearsComp = yearCompList.toArray(yearsComp);
    yearBoxComp = new JComboBox<>();
    for (String s : yearsComp) {
      yearBoxComp.addItem(s);
    }
    List<String> monthListComp = new ArrayList<>();
    JLabel selectMonthComp = new JLabel("Select Month");
    for (int i = 1; i < 13; i++) {
      monthListComp.add(String.valueOf(i));
    }
    String[] listOfMonthsComp = new String[monthListComp.size()];
    listOfMonthsComp = monthListComp.toArray(listOfMonthsComp);
    monthBoxComp = new JComboBox<>();
    for (String listOfMonth : listOfMonthsComp) {
      monthBoxComp.addItem(listOfMonth);
    }

    List<String> dayListComp = new ArrayList<>();
    JLabel selectDayComp = new JLabel("Select Day");
    for (int i = 1; i < 32; i++) {
      dayListComp.add(String.valueOf(i));
    }
    String[] listOfDaysComp = new String[dayListComp.size()];
    listOfDaysComp = dayListComp.toArray(listOfDaysComp);
    dayBoxComp = new JComboBox<>();
    for (String listOfDay : listOfDaysComp) {
      dayBoxComp.addItem(listOfDay);
    }

    JPanel insideCompPanel = new JPanel();
    insideCompPanel.add(selectYearComp);
    insideCompPanel.add(yearBoxComp);
    insideCompPanel.add(selectMonthComp);
    insideCompPanel.add(monthBoxComp);
    insideCompPanel.add(selectDayComp);
    insideCompPanel.add(dayBoxComp);

    insideCompPanel.setBorder(BorderFactory.createTitledBorder("Comp/Value"));


    sTextArea = new JTextArea(5, 10);
    JScrollPane scrollPane = new JScrollPane(sTextArea);
    sTextArea.setLineWrap(true);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Scrollable Result"));

    insideCompPanel.add(scrollPane);

    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(insideCompPanel);

    JPanel buttonPanelComp = new JPanel();
    portLabel = new JLabel("");
    buttonPanelComp.add(portLabel);
    compButton = new JButton("composition");
    compButton.setActionCommand("comp button");
    buttonPanelComp.add(compButton);

    valueButton = new JButton("value");
    buttonPanelComp.add(valueButton);
    valueButton.setActionCommand("value button");
    insideCompPanel.add(buttonPanelComp);
    mainPanel.add(insideCompPanel);


    insidePanel = new JPanel();
    insidePanel.setBorder(BorderFactory.createTitledBorder("Buy/Sell"));
    insidePanel.add(comboboxPanel);
    insidePanel.add(selectionListPanel);
    insidePanel.add(monthListPanel);
    insidePanel.add(dayListPanel);

    mainPanel.add(insidePanel);
    insidePanel.add(tickerBoxDisplay);
    insidePanel.add(tickerBox);
    insidePanel.add(shareBoxDisplay);
    insidePanel.add(shareBox);
    submitPanel = new JPanel();
    buyDisplay = new JLabel("");
    submitPanel.add(buyDisplay);
    buyButton = new JButton("buy");
    buyButton.setActionCommand("buy button");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(buyButton);

    sellPortButton = new JButton("sell");
    sellPortButton.setActionCommand("sell button");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(sellPortButton);
    insidePanel.add(submitPanel);



    JPanel pPanel = new JPanel();
    pPanel.setBorder(BorderFactory.createTitledBorder("Name new portfolio"));

    pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.PAGE_AXIS));
    textArea = new JTextArea(0, 10);
    pPanel.add(textArea);
    pButton = new JButton("Create");
    display = new JLabel("");
    pButton.setActionCommand("Create");
    pPanel.add(display);
    pPanel.add(pButton);
    createPanel = new JPanel();
    createPanel.add(pPanel);
    mainPanel.add(createPanel);
    this.pack();
  }

  @Override
  public void checkComponent() {
    if (mainPanel.getComponentCount() > 5) {
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
    tickerBox.addActionListener(listener);
    compButton.addActionListener(listener);
    valueButton.addActionListener(listener);
    buyButton.addActionListener(listener);
    sellPortButton.addActionListener(listener);
    pButton.addActionListener(listener);

  }

  @Override
  public void setCompositionText(String result) {
    sTextArea.setText(result);
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
    String newName = this.textArea.getText();
    textArea.setText("");
    createPanel.revalidate();
    createPanel.repaint();
    return newName;
  }


  @Override
  public void createSuccess() {
    display.setText("Portfolio created successfully!");
    updateMain();
  }

  @Override
  public void buySuccess() {
    buyDisplay.setText("Share bought!");
    updateMain();
  }

  @Override
  public void sellSuccess() {
    buyDisplay.setText("Share sold!");
    updateMain();
  }

  @Override
  public void compSuccess() {
    portLabel.setText("You selected composition");
    updateMain();
  }

  @Override
  public void valueSuccess() {
    portLabel.setText("You selected get value");
    updateMain();
  }

  private void updateMain() {
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  @Override
  public String getSelectedPortfolioName() {
    return (String) combobox.getSelectedItem();
  }

  @Override
  public String getPurchaseTicker() {
    return (String) tickerBox.getSelectedItem();
  }

  private String getDay() {
    return (String) dayBox.getSelectedItem();
  }
  private String getMonth() {
    return (String) monthBox.getSelectedItem();
  }
  private String getYear() {
    return (String) yearBox.getSelectedItem();
  }
  private String getDayComp() {
    return (String) dayBoxComp.getSelectedItem();
  }
  private String getMonthComp() {
    return (String) monthBoxComp.getSelectedItem();
  }
  private String getYearComp() {
    return (String) yearBoxComp.getSelectedItem();
  }

  @Override
  public String getPurchaseDate() {
    return String.format("%s-%s-%s", this.getYear(), this.getMonth(), this.getDay());
  }

  @Override
  public String getPurchaseShares() {
    return shareBox.getText();
  }

  @Override
  public String getAnalysisDate() {
    return String.format("%s-%s-%s", this.getYearComp(), this.getMonthComp(), this.getDayComp());
  }
}

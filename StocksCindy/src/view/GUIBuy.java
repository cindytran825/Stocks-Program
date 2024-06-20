package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUIBuy extends JPanel implements GUIView {
  private JLabel tickerBoxDisplay;
  private JPanel mainPanel;
  private JPanel insidePanel;
  private JButton createPortButton;
  private JButton sellPortButton;
  private JPanel submitPanel;
  private JLabel portLabel;
  private final JComboBox<String> dayBox;
  private final JComboBox<String> monthBox;
  private final JComboBox<String> yearBox;
  private final JComboBox<String> tickerBox;
  private final JTextArea shareBox;
  private final JLabel warning;
  private final JPanel warningPanel;


  /**
   * this is called whn we need to ask for the ticker,
   * and date.
   */
  public GUIBuy(String[] tickers) {
    super();
    mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.CENTER);

    warning = new JLabel("Please make sure to choose from an existing portfolio before submitting!");
    warningPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    warningPanel.add(warning);
    this.add(mainPanel);

    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBounds(0, 200, 500, 100);
    tickerBoxDisplay = new JLabel("Ticker: ");
    comboboxPanel.add(tickerBoxDisplay);
    tickerBox = new JComboBox<String>();
    tickerBox.setActionCommand("Ticker");
    for (int i = 0; i < tickers.length; i++) {
      tickerBox.addItem(tickers[i]);
    }
    comboboxPanel.add(tickerBox);

    JPanel sharePanel = new JPanel();
    shareBox = new JTextArea(0, 10);
    JLabel shareBoxDisplay = new JLabel("Shares: ");
    sharePanel.add(shareBoxDisplay);
    sharePanel.add(shareBox);

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

    insidePanel = new JPanel();
    mainPanel.add(warningPanel);
    insidePanel.add(comboboxPanel);
    insidePanel.add(sharePanel);
    insidePanel.add(selectionListPanel);
    insidePanel.add(monthListPanel);
    insidePanel.add(dayListPanel);

    mainPanel.add(insidePanel);
    submitPanel = new JPanel();
    portLabel = new JLabel("");
    submitPanel.add(portLabel);
    createPortButton = new JButton("buy");
    createPortButton.setActionCommand("buy button");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(createPortButton);

    sellPortButton = new JButton("sell");
    sellPortButton.setActionCommand("sell button");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(sellPortButton);
    mainPanel.add(submitPanel);

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

  public String getDate() {
    return String.format("%s-%s-%s", this.getYear(), this.getMonth(), this.getDay());
  }

  public String getShares() {
    return shareBox.getText();
  }

  public String getTicker() {
    return (String) tickerBox.getSelectedItem();
  }

  public void setListener(ActionListener listener) {
    createPortButton.addActionListener(listener);
    sellPortButton.addActionListener(listener);
  }

}

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUIBuy extends JPanel implements GUIView {
  private JLabel comboboxDisplay;
  private JPanel mainPanel;
  private JPanel insidePanel;
  private JButton createPortButton;
  private JButton sellPortButton;
  private JPanel submitPanel;
  private JLabel portLabel;


  /**
   * this is called whn we need to ask for the ticker,
   * and date.
   */
  public GUIBuy(String[] tickers) {
    super();
    mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.CENTER);

    JLabel warning = new JLabel("Please make sure to choose from an existing portfolio before submitting!");
    JPanel warningPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    warningPanel.add(warning);
    this.add(mainPanel);

    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBounds(0, 200, 500, 100);
    comboboxDisplay = new JLabel("Ticker: ");
    comboboxPanel.add(comboboxDisplay);
    JComboBox<String> combobox = new JComboBox<String>();
    combobox.setActionCommand("Ticker");
    for (int i = 0; i < tickers.length; i++) {
      combobox.addItem(tickers[i]);
    }

    comboboxPanel.add(combobox);
    java.util.List<String> yearing = new ArrayList<>();
    JLabel selectYear = new JLabel("Select Year");
    for (int i = 2000;  i < 2025 ; i++) { // MAKE IT SO THAT ITS THE CURRENT YEAR
      yearing.add(String.valueOf(i));
    }
    String[] years = new String[yearing.size()];
    years = yearing.toArray(years);
    JComboBox<String> yearBox = new JComboBox<String>();
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
    JComboBox<String> monthBox = new JComboBox<String>();
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
    JComboBox<String> dayBox = new JComboBox<String>();
    for (int i = 0; i < listOfDays.length; i++) {
      dayBox.addItem(listOfDays[i]);
    }

    JPanel dayListPanel = new JPanel();
    dayListPanel.add(selectDay);
    dayListPanel.add(dayBox);



    insidePanel = new JPanel();
    mainPanel.add(warningPanel);
    insidePanel.add(comboboxPanel);
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

  public void setListener(ActionListener listener) {
    createPortButton.addActionListener(listener);
    sellPortButton.addActionListener(listener);
  }

}

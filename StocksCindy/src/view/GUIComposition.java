package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class creates a window for composition / distribution analysis. It prompts the user
 * for a valid day, month, and year.
 */
public class GUIComposition extends JPanel implements IGUIComposition {
  private final JButton compButton;
  private final JButton valueButton;
  private final JComboBox<String> yearBox;
  private final JComboBox<String> monthBox;
  private final JComboBox<String> dayBox;
  private final JTextArea sTextArea;

  /**
   * The constructor creates the GUI drop-down button for day, month, and year, and sets
   * them to default values.
   */
  public GUIComposition() {
    super();
    JPanel mainPanel = new JPanel();
    this.add(mainPanel, BorderLayout.CENTER);

    // warning
    JLabel warning = new JLabel("Please make sure to choose from an existing "
            + "portfolio before submitting!");
    JPanel warningPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    warningPanel.add(warning);
    this.add(mainPanel);

    // drop down for year list
    List<String> year = new ArrayList<>();
    JLabel selectYear = new JLabel("Select Year");

    for (int i = 2000; i < 2025; i++) {
      year.add(String.valueOf(i));
    }
    String[] years = new String[year.size()];
    years = year.toArray(years);
    yearBox = new JComboBox<>();
    for (String s : years) {
      yearBox.addItem(s);
    }
    JPanel selectionListPanel = new JPanel();
    selectionListPanel.add(selectYear);
    selectionListPanel.add(yearBox);

    // drop down for month list
    List<String> monthList = new ArrayList<>();
    JLabel selectMonth = new JLabel("Select Month");
    for (int i = 1; i < 13; i++) {
      monthList.add(String.valueOf(i));
    }
    String[] listOfMonths = new String[monthList.size()];
    listOfMonths = monthList.toArray(listOfMonths);
    monthBox = new JComboBox<>();
    for (String listOfMonth : listOfMonths) {
      monthBox.addItem(listOfMonth);
    }
    JPanel monthListPanel = new JPanel();
    monthListPanel.add(selectMonth);
    monthListPanel.add(monthBox);

    // drop down for day list
    List<String> dayList = new ArrayList<>();
    JLabel selectDay = new JLabel("Select Day");
    for (int i = 1; i < 32; i++) {
      dayList.add(String.valueOf(i));
    }
    String[] listOfDays = new String[dayList.size()];
    listOfDays = dayList.toArray(listOfDays);
    dayBox = new JComboBox<>();
    for (String listOfDay : listOfDays) {
      dayBox.addItem(listOfDay);
    }

    JPanel dayListPanel = new JPanel();
    dayListPanel.add(selectDay);
    dayListPanel.add(dayBox);

    sTextArea = new JTextArea(10, 20);
    JScrollPane scrollPane = new JScrollPane(sTextArea);
    sTextArea.setLineWrap(true);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Scrollable Result"));

    JPanel insidePanel = new JPanel();
    mainPanel.add(warningPanel);
    insidePanel.add(selectionListPanel);
    insidePanel.add(monthListPanel);
    insidePanel.add(dayListPanel);

    mainPanel.add(insidePanel);
    mainPanel.add(scrollPane);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(insidePanel);

    JPanel submitPanel = new JPanel();
    JLabel portLabel = new JLabel("");
    submitPanel.add(portLabel);
    compButton = new JButton("composition");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    compButton.setActionCommand("comp button");
    submitPanel.add(compButton);

    valueButton = new JButton("value");
    submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.PAGE_AXIS));
    submitPanel.add(valueButton);
    valueButton.setActionCommand("value button");
    mainPanel.add(submitPanel);
  }

  @Override
  public void setListener(ActionListener listener) {
    compButton.addActionListener(listener);
    valueButton.addActionListener(listener);
  }

  @Override
  public void setResultText(String result) {
    sTextArea.setText(result);
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

  @Override
  public String getDate() {
    return String.format("%s-%s-%s", this.getYear(), this.getMonth(), this.getDay());
  }
}

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUIGetValue extends JPanel implements ActionListener, GUIView {
  private JLabel comboboxDisplay;
  private JPanel mainPanel;
  private GUIComposition getPortValue;
  private GUIBuy getStockValue;


  public GUIGetValue() {
    super();
    setLayout(new BorderLayout());
    setSize(500, 500);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    this.add(mainPanel, BorderLayout.CENTER);

    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setLayout(new FlowLayout());
    comboboxDisplay = new JLabel("What're you evaluating? ");
    comboboxPanel.add(comboboxDisplay);

    String[] options = {"Portfolio", "Stock"};
    JComboBox<String> combobox = new JComboBox<>(options);
    combobox.addActionListener(this);
    comboboxPanel.add(combobox);

    mainPanel.add(comboboxPanel);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    JComboBox<String> box = (JComboBox<String>) e.getSource();
    String selectedItem = (String) box.getSelectedItem();
    comboboxDisplay.setText("You selected: " + selectedItem);

    if (mainPanel.getComponentCount() > 1) {
      mainPanel.remove(1);
    }

    switch (selectedItem) {
      case "Portfolio":
        comboboxDisplay.setText("Got Portfolio");
        if (getPortValue == null) {
          getPortValue = new GUIComposition();
          getPortValue.setPreferredSize(new Dimension(700, 100));
        }
        mainPanel.add(getPortValue);
        break;
      case "Stock":
        comboboxDisplay.setText("Got Stock");
        if (getStockValue == null) {
          getStockValue = new GUIBuy();
          getStockValue.setPreferredSize(new Dimension(700, 100));
        }
        mainPanel.add(getStockValue);
        break;
    }
    mainPanel.revalidate();
    mainPanel.repaint();
  }
}
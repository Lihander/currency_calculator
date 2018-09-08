import exceptions.FixerException;
import services.CalculateService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class FundCountForm {
    private JButton calculateButton;
    private JPanel mainPanel;
    private JTextField date;
    private JTextField amount;
    private JTextField result;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getAmount() {
        return amount;
    }

    public JTextField getDate() {
        return date;
    }

    public FundCountForm() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculateService service = new CalculateService();
                Double amount = Double.parseDouble(FundCountForm.this.amount.getText());

                try {
                    result.setText(service.calculate(date.getText(), amount));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Дата заполнена неверно.");
                } catch (FixerException | IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Неизвестная ошибка.");
                }

            }
        });
    }
}

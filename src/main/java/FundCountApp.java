import helpers.DigitFilter;

import javax.swing.*;
import javax.swing.text.PlainDocument;

public class FundCountApp {
    public static void main(String[] args) {
        FundCountForm form = new FundCountForm();
        JPanel mainPanel = form.getMainPanel();
        JTextField date = form.getDate();
        JTextField amount = form.getAmount();

        JFrame frame = new JFrame();
        frame.setTitle("Калькулятор для FundCount");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();

        PlainDocument document = (PlainDocument) amount.getDocument();
        document.setDocumentFilter(new DigitFilter("\\d+"));
        document = (PlainDocument) date.getDocument();
        document.setDocumentFilter(new DigitFilter("\\d|-"));

    }
}

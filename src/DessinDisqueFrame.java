import javax.swing.*;
import java.awt.*;

class DessinDisqueFrame extends JFrame {
    private final DessinPanel dessinPanel;

    public DessinDisqueFrame() {
        setTitle("Application de Dessin de Disques");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal pour le dessin
        dessinPanel = new DessinPanel();
        add(dessinPanel, BorderLayout.CENTER);

        // Panel des boutons
        JPanel buttonPanel = new JPanel();
        JButton clearButton = new JButton("Clear");
        JButton quitButton = new JButton("Quit");

        clearButton.addActionListener(e -> dessinPanel.clear());
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
}

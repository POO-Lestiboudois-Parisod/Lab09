/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 20/01/2025
 */

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la fenêtre principale de l'application.
 * Contient le panneau de dessin et les boutons "Clear" et "Quit".
 */
class DessinDisqueFrame extends JFrame {
    private final DessinPanel dessinPanel;
    /**
     * Constructeur de la fenêtre principale.
     * Initialise les composants graphiques et configure la disposition.
     */
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.ListIterator;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static javax.swing.text.StyleConstants.setBackground;

class DessinPanel extends JPanel implements MouseListener, MouseMotionListener {
    private final LinkedList<Disque> disques = new LinkedList<>();
    private Disque disqueTemporaire = null;
    private Disque disqueDeplace = null;
    private Point lastMousePosition = null;

    public DessinPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner tous les disques
        for (Disque disque : disques) {
            disque.dessiner(g);
        }

        // Dessiner le disque temporaire
        if (disqueTemporaire != null) {
            disqueTemporaire.dessiner(g);
        }
    }

    public void clear() {
        disques.clear();
        disqueTemporaire = null;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Bouton gauche
            disqueTemporaire = new Disque(e.getPoint(), 0, disques.size());
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Bouton droit
            ListIterator<Disque> iterator = disques.listIterator(disques.size());
            while (iterator.hasPrevious()) {
                Disque disque = iterator.previous();
                if (disque.contient(e.getPoint())) {
                    iterator.remove();
                    repaint();
                    break;
                }
            }
        } else if (e.isShiftDown()) { // Déplacement avec SHIFT
            for (Disque disque : disques) {
                if (disque.contient(e.getPoint())) {
                    disqueDeplace = disque;
                    lastMousePosition = e.getPoint();
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && disqueTemporaire != null) {
            disques.add(disqueTemporaire);
            disqueTemporaire = null;
            repaint();
        }
        disqueDeplace = null;
        lastMousePosition = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (disqueTemporaire != null) {
            int rayon = (int) disqueTemporaire.getCentre().distance(e.getPoint());
            disqueTemporaire.setRayon(rayon);
            repaint();
        } else if (disqueDeplace != null && lastMousePosition != null) {
            int dx = e.getX() - lastMousePosition.x;
            int dy = e.getY() - lastMousePosition.y;
            disqueDeplace.deplacer(dx, dy);
            lastMousePosition = e.getPoint();
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

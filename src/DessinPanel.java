import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.ListIterator;

class DessinPanel extends JPanel implements MouseListener, MouseMotionListener {
    private final LinkedList<Disque> disques = new LinkedList<>();
    private Disque disqueTemporaire = null;
    private Disque disqueDeplace = null; // Disque actuellement déplacé
    private Point lastMousePosition = null; // Dernière position de la souris pour le déplacement

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
        if (e.isShiftDown() && e.getButton() == MouseEvent.BUTTON1) { // SHIFT + clic gauche
            for (Disque disque : disques) {
                if (disque.contient(e.getPoint())) {
                    disqueDeplace = disque;
                    lastMousePosition = e.getPoint();
                    break;
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON1) { // Bouton gauche pour créer un disque
            disqueTemporaire = new Disque(e.getPoint(), 0, disques.size());
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Bouton droit pour effacer
            ListIterator<Disque> iterator = disques.listIterator(disques.size());
            while (iterator.hasPrevious()) {
                Disque disque = iterator.previous();
                if (disque.contient(e.getPoint())) {
                    iterator.remove();
                    repaint();
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && disqueTemporaire != null) { // Fin de création d'un disque
            disques.add(disqueTemporaire);
            disqueTemporaire = null;
            repaint();
        }
        disqueDeplace = null;
        lastMousePosition = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (disqueTemporaire != null) { // Ajuster le rayon du disque temporaire
            int rayon = (int) disqueTemporaire.getCentre().distance(e.getPoint());
            disqueTemporaire.setRayon(rayon);
            repaint();
        } else if (disqueDeplace != null && lastMousePosition != null) { // Déplacer un disque sélectionné
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

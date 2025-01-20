import java.awt.*;

class Disque {
    private Point centre;
    private int rayon;
    private final int couleurIndex;
    private static final Color[] COULEURS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA};

    public Disque(Point centre, int rayon, int couleurIndex) {
        this.centre = centre;
        this.rayon = rayon;
        this.couleurIndex = couleurIndex % COULEURS.length;
    }

    public Point getCentre() {
        return centre;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public void deplacer(int dx, int dy) {
        centre = new Point(centre.x + dx, centre.y + dy);
    }

    public boolean contient(Point p) {
        return centre.distance(p) <= rayon;
    }

    public void dessiner(Graphics g) {
        g.setColor(COULEURS[couleurIndex]);
        g.fillOval(centre.x - rayon, centre.y - rayon, rayon * 2, rayon * 2);
    }
}

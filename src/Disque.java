/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 20/01/2025
 */

import java.awt.*;

/**
 * Classe représentant un disque.
 * Chaque disque est défini par son centre, son rayon et sa couleur.
 */
class Disque {
    private Point centre;
    private int rayon;
    private final int couleurIndex;
    private static final Color[] COULEURS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.PINK, Color.cyan, Color.DARK_GRAY};

    /**
     * Constructeur pour créer un disque.
     *
     * @param centre      Le centre du disque.
     * @param rayon       Le rayon du disque.
     * @param couleurIndex L'index de la couleur dans le tableau prédéfini.
     */
    public Disque(Point centre, int rayon, int couleurIndex) {
        this.centre = centre;
        this.rayon = rayon;
        this.couleurIndex = couleurIndex % COULEURS.length;
    }

    /**
     * Retourne le centre du disque.
     *
     * @return Le centre du disque.
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Définit le rayon du disque.
     *
     * @param rayon Le nouveau rayon.
     */
    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    /**
     * Déplace le disque en ajoutant un décalage.
     *
     * @param dx Décalage en X.
     * @param dy Décalage en Y.
     */
    public void deplacer(int dx, int dy) {
        centre = new Point(centre.x + dx, centre.y + dy);
    }

    /**
     * Vérifie si un point est contenu dans le disque.
     *
     * @param p Le point à vérifier.
     * @return True si le point est dans le disque, sinon false.
     */
    public boolean contient(Point p) {
        return centre.distance(p) <= rayon;
    }

    /**
     * Dessine le disque sur le panneau.
     *
     * @param g L'objet Graphics pour dessiner.
     */
    public void dessiner(Graphics g) {
        g.setColor(COULEURS[couleurIndex]);
        g.fillOval(centre.x - rayon, centre.y - rayon, rayon * 2, rayon * 2);
    }
}

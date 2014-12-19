package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dm
 */
public class Exploration {

    final char[][] grille;
    final int dim;
    final Dictionnaire d;

    // Variables représentant l'état de l'exploration, modifiées par les fonctions
    // "explore" et "exploreTout". Elles seront initialisées par la fonction
    // "exploreTout`"
    boolean[][] masque;
    LinkedList<Character> prefix;
    LinkedList<String> motsTrouves;

    public Exploration(char[][] grille, int dim, Dictionnaire d) {
        this.grille = grille;
        this.dim = dim;
        this.d = d;
    }

    public void explore1(Position p, Noeud n) {

        Noeud nn = n.trouveFils(grille[p.x][p.y]);
        if (nn != null) {
            if (nn.estMot()) {
                prefix.add(grille[p.x][p.y]);
                masque[p.x][p.y] = true;

                System.out.println(nn.n);
                motsTrouves.add(versChaine(prefix));
                prefix.removeAll(prefix);
            }
        }
    }

    public void explore(Position p, Noeud n) {

        Noeud nn = n.trouveFils(grille[p.x][p.y]);
        if (nn != null) {

            masque[p.x][p.y] = true;
            prefix.add(grille[p.x][p.y]);

            if (nn.estMot()) {
                motsTrouves.add(versChaine(prefix));
            }

            for (Position pp : p.deplacementsLegaux()) {
                explore(pp, nn);
            }

            prefix.removeLast();
            masque[p.x][p.y] = false;
        }
    }

    public LinkedList<String> exploreTout() {
        masque = new boolean[dim][dim];
        motsTrouves = new LinkedList<String>();
        prefix = new LinkedList<Character>();

        for (int xx = 0; xx < dim; xx++) {
            for (int yy = 0; yy < dim; yy++) {
                Position p = new Position(this, xx, yy);
                explore(p, d.racine);
            }
        }

        Collections.sort(motsTrouves);
        Collections.sort(motsTrouves, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        
        return motsTrouves;
    }

    public static String versChaine(LinkedList<Character> l) {
        StringBuilder sb = new StringBuilder();
        for (Character c : l) {
            sb.append(c);
        }
        return sb.toString();
    }
}

class Position {

    final int x, y;
    final Exploration e;

    public Position(Exploration e, int x, int y) {
        this.x = x;
        this.y = y;
        this.e = e;
    }

    private boolean estLegal() {
        if (e.dim <= x || e.dim <= y || y < 0 || x < 0) {
            return false;
        }

        if (e.masque[x][y]) {
            return false;
        }

        return true;
    }

    public List<Position> deplacementsLegaux() {
        List<Position> positions = new ArrayList<Position>();

        for (int xx = -1; xx < 2; xx++) {
            for (int yy = -1; yy < 2; yy++) {
                Position pp = new Position(e, x + xx, y + yy);
                if (xx != 0 || yy != 0) {
                    if (pp.estLegal()) {
                        positions.add(pp);
                    }
                }
            }
        }
        return positions;
    }

}

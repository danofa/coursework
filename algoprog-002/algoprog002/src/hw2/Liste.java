package hw2;

import java.util.NoSuchElementException;

/**
 *
 * @author dm
 */
public class Liste {

    private Cellule head;

    public Liste() {
        head = null;
    }
    
    Liste ajouteTete(Objet o) {
        Cellule c = new Cellule(o, head);
        head = c;

        return this;
    }

    int longueur() {
        if (head == null) {
            return 0;
        }

        Cellule c = head;
        int i = 1;
        while (c.next != null) {
            i++;
            c = c.next;
        }
        return i;
    }

    boolean contient(Objet o) {

        Cellule c = head;

        while (c != null) {
            
            if (c.o.nom().equals(o.nom())) {
                return true;
            }
            c = c.next;
        }
        return false;
    }

    Liste supprimeTete() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        head = head.next;
        return this;
    }

}

class Cellule {

    Objet o;
    Cellule next;

    public Cellule(Objet o, Cellule next) {
        this.o = o;
        this.next = next;
    }
}

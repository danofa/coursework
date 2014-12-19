package hw1;



import java.util.Arrays;

public class LZ77 {

    private static boolean debug = true;

    int[] test1 = {0, 0, 1, 1, 0, 1, 1, 0, 1};

    public static Occurrence plusLongueOccurrence(int[] t, int pos, int tailleFenetre) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        if (t.length == 0 || pos == 0) {
            return new Occurrence(0, 0);
        }

        int wStart = 0;

        if (pos > tailleFenetre) {
            wStart = pos - tailleFenetre;
        }

        int w[] = Arrays.copyOfRange(t, wStart, pos);

//        System.out.println("W array: " + Arrays.toString(w));
        if (w.length == 1) {
            if (w[0] == t[pos]) {
                return new Occurrence(1, 1);
            } else {
                return new Occurrence(0, 0);
            }
        }

        Occurrence longest = new Occurrence(0, 0);
        Occurrence current = new Occurrence(0, 0);

        for (int i = 0; i < w.length; i++) {

            if (w[i] == t[pos]) {
                current.retour = w.length - i;
                for (int x = i, y = 0; x < w.length && y + pos < t.length; x++, y++) {
                    if (w[x] == t[y + pos]) {
//                        System.out.println("match; " +w[x] + " ; " +t[y+pos]);
                        if (x + 1 < w.length && y + pos + 1 < t.length) {
//                            System.out.println("next; "+w[x+1] + " ; " +t[y+pos+1]);
                        }
                        current.taille++;
                    } else {
//                        System.out.println("break.......");
                        break;
                    }
                }
//                System.out.println("next.......");
            }

            if (current.taille > longest.taille) {
                longest.taille = current.taille;
                longest.retour = current.retour;
            }
            current.taille = 0;
            current.retour = 0;
        }

        return longest;
    }

    public static int LZ77Longueur(int[] t, int tailleFenetre) {

        int elCount = 0;
        int pos = 0;
        while (pos < t.length) {
            if (t[pos] == 2) {
                return elCount + 1;
            }

            Occurrence o = plusLongueOccurrence(t, pos, tailleFenetre);
            elCount++;
            pos += o.taille + 1;
            if (pos == t.length - 2 && o.taille == 0) {
                elCount++;
            }
        }

        return elCount;
    }

    public static Element[] LZ77(int[] t, int tailleFenetre) {
        Element[] result = new Element[LZ77Longueur(t, tailleFenetre)];
        int pos = 0, i = 0;

        while (pos < t.length && i < result.length) {
            Occurrence o = plusLongueOccurrence(t, pos, tailleFenetre);
            Element e = null;
            
            if (o.taille == 0) {
                e = new Element(o, t[pos]);
                pos++;
            } else {
                e = new Element(o, t[pos + o.taille]);
                pos += o.taille + 1;
                
            }
            
            result[i] = e;
            i++;
        }

        return result;
    }

    public static int LZ77InverseLongueur(Element[] t){
        int result = 0;
        
        for(Element e : t){
            result += e.e.taille + 1;
        }
        return result;
    }
    
    
    public static int[] LZ77Inverse(Element[] t){
        int[] result = new int[LZ77InverseLongueur(t)];
        int i = 0;
        
        for(Element e : t){
            if(e.e.taille > 0){
                int y = i;
                for(int x = 0; x < e.e.taille; x++){
                    result[i] = result[(y - e.e.retour) + x];
                    i++;
                }
            }
            
            result[i] = e.s;
            i++;
        }      
        
        return result;
    }
    
    public static void afficheDecode(int[] decodedTab) {
        
        for(int i : decodedTab){
            System.out.printf("%d ", i);
        }
        System.out.print("\n");
    }
    
    public static void afficheEncode(Element[] encodedTab) {
        int i = 0;
        for (Element e : encodedTab) {
            if(e != null){
                System.out.printf("(%d,%d)%d", e.e.retour, e.e.taille, e.s, i);
            } else {
                System.out.println("null element!");
            }
            i++;
        }
        System.out.print("\n");
    }

}

class Occurrence {

    public int retour, taille;

    Occurrence(int retour, int taille) {
        this.retour = retour;
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "Occurrence{" + "retour=" + retour + ", taille=" + taille + '}';
    }
    
    
}

class Element {

    Occurrence e;
    int s;

    Element(Occurrence e, int s) {
        this.e = e;
        this.s = s;
    }

    @Override
    public String toString() {
        return "Element{" + "s=" + s + '}';
    }
    
    
    
}

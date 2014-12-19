package hw2;

/**
 *
 * @author nofa
 */
public class Objet2 extends Objet {

    private final String nom;

    public Objet2(String nom) {
        this.nom = nom;
    }
    
        
    @Override
    int hash() {
        int result = 5381;
    
        for(int i = 0; i < nom.length(); i++){
            result *= 33;
            result ^= nom.charAt(i);
        }
        return result;
    }

    @Override
    String nom() {
        return nom;
    }
    
}

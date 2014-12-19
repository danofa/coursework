package hw2;

public class Objet1 extends Objet {

    private String nom;

    public Objet1(String nom) {
        this.nom = nom;
    }

    @Override
    int hash() {
        int result = 0;

        for (int i = 0; i < nom.length(); i++) {
            int pow = 1;
            for (int x = 0; x < nom.length() - 1 - i; x++) {
                pow *= 31;
            }

            result += nom.charAt(i) * pow;
        }
        return result;
    }

    @Override
    String nom() {
        return nom;
    }

}

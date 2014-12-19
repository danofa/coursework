package hw5;

import java.util.Arrays;

class Seg {

    // Cette fonction effectue une régression linéaire sur les points allant des
    // indices "d" (début) à "f" (fin). Elle calcule les coefficients "a" et "b"
    // et renvoie également la somme des carrés des erreurs.
    static double[] erreur(double[] xtab, double[] ytab, int s, int f) {

        if (ytab.length != xtab.length || s > f || f >= xtab.length || s >= xtab.length) {
            throw new IllegalArgumentException();
        } else {
            double a, b, e = 0.0;
            double x = 0.0, x2 = 0.0, y = 0.0, xy = 0.0, n = (f - s) + 1;
            for (int i = s; i <= f; i++) {
                x += xtab[i];
                y += ytab[i];
                x2 += xtab[i] * xtab[i];
                xy += xtab[i] * ytab[i];
            }

            a = ((n * xy) - (x * y)) / ((n * x2) - (x * x));
            b = (y - (a * x)) / n;

            for (int i = s; i <= f; i++) {
                e += (ytab[i] - (a * xtab[i]) - b) * (ytab[i] - (a * xtab[i]) - b);
            }
            //System.out.printf("x:%.2f, y: %.2f, xy: %.2f, x2: %.2f, a: %.2f, b: %.2f, e: %.2f \n", x, y, xy, x2, a, b, e);

            return (new double[]{a, b, e});

        }
    }

//     Cette fonction calcule le nombre de segments pour un coût donné c.
    static int nbSeg(double[] xtab, double[] ytab, double c) {
        System.out.println("error **: " + Arrays.toString(erreur(xtab, ytab, 0, xtab.length - 1)));
        for (int i = 0; i < xtab.length; i++) {
            System.out.printf("#%d error: %f \n", i, c + erreur(xtab, ytab, 0, i)[2]);
            
            if(i+1 < xtab.length){
                System.out.printf("//#%d error: %f between x:%f/y:%f \n", i, c + erreur(xtab, ytab,i, i+1)[2], xtab[i], ytab[i]);
            }
        }
        return (0);
    }
}

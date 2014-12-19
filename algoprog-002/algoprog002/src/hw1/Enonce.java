package hw1;

public class Enonce {

    public static void main(String[] args) {
	//test1();
        //test2();
        //test3();
        //test4();
        test5();
    }

    public static void test1() {
        System.out.print("Devoir0Reponse0 ");
        System.out.print("Hello World ");
        System.out.print("done");
    }

    public static void test2() {
        System.out.print("Devoir0Reponse1 ");
        System.out.print(somme(10, 40) + " ");
        System.out.print(somme(2, -3) + " ");
        System.out.print(somme(2001, 4235) + " ");
        System.out.print("done");
    }

    public static void test3() {
        System.out.print("Devoir0Reponse2 ");
        System.out.print(u(10) + " ");
        System.out.print(u(1) + " ");
        System.out.print(u(20) + " ");
        System.out.print("done");
    }

    public static void test4() {
        System.out.print("Devoir0Reponse3 ");
        System.out.print(rotation('c') + " ");
        System.out.print(rotation('f') + " ");
        System.out.print(rotation('z') + " ");
        System.out.print(rotation(rotation('z')) + " ");
        System.out.print("done");
    }

    public static void test5() {
        System.out.print("Devoir0Reponse4 ");
        System.out.print(distance("aaaa", "bbbb") + " ");
        System.out.print(distance("aaaa", "azaz") + " ");
        System.out.print(distance("foo", "bar") + " ");
        System.out.print(distance("azerty", "qwerty") + " ");
        System.out.print("done");
    }

    public static int u(int n) {
        if (n == 0)
            return 1;
        return 2 * u(n - 1) + 4;
        // A COMPLETER VALEUR MISE POUR COMPILATION
    }

    public static int somme(int a, int b) {
        return a + b; // A COMPLETER VALEUR MISE POUR COMPILATION
    }

    public static char rotation(char c) {
        return (char) ((((c - 'a') + 1) % 26) + 'a');
        // A COMPLETER VALEUR MISE POUR COMPILATION
    }

    public static int distance(String s1, String s2) {
        int distance = 0;
        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();

        for (int i = 0; i < charArray1.length; i++) {
            int c1 = charArray1[i] - 'a';
            int c2 = charArray2[i] - 'a';

            int d = Math.abs(c1 - c2);
            
            if (d > 13 && c1 < c2) {
                d = (26 - c2) + c1;
            } else if (d > 13 && c2 < c1) {
                d = (26 - c1) + c2;
            }

            distance += d;
        }
        return distance;// A COMPLETER VALEUR MISE POUR COMPILATION
    }
}

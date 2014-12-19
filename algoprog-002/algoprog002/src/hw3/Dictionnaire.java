package hw3;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rossin
 */
public class Dictionnaire {

    Noeud racine;
    int count = 0;
    int idIndex = 0;

    public Dictionnaire() {
        racine = new Noeud(' ');
    }

    public boolean existeMot(String s) {
        if (s.length() == 0)
            return false;

        char[] m = s.toCharArray();
        Noeud curr = racine;
        boolean found = false;

        //System.out.println("testing for word : " + s);
        for (int i = 0; i < m.length; i++) {
            for (Noeud n : curr.children) {
                if (n.n == m[i]) {
                    found = true;
                    //System.out.println("found  match : " + n.n + ":" + m[i]);
                    curr = n;
                    if (n.children.getFirst().n == '*' && i == m.length - 1)
                        return true;
                }
            }

            if (!found) {
                //System.out.println("not found, early break");
                return false;
            } else
                found = false;
        }
        //System.out.println("----------------");
        return false;
    }

    public boolean ajouteMot(String s) {
        if (existeMot(s))
            return false;

        count++;
        Noeud curr = racine;

        s1:
        for (char c : s.toCharArray()) {
            for (Noeud n : curr.children) {
                if (n.n == c) {
//                    System.out.println("found match, skip node, " + n.n + ":" + c);
                    curr = n;
                    continue s1;
                }
            }
//            System.out.println("creating node : " + c);
            Noeud n = new Noeud(c);
            idIndex++;
            n.id = idIndex;
            curr.ajouteFils(n);
            curr = n;
        }

//        System.out.println("terminating last node of : " + curr.n);
        curr.ajouteFils(new Noeud('*'));
//        System.out.println("----------");
        return true;
    }

    public boolean estPrefixe(String s) {

        if (existeMot(s))
            return true;

        Noeud curr = racine;
//        System.out.println("Checking prefix: " + s);
        boolean match = false;

        s1:
        for (char c : s.toCharArray()) {
            match = false;
            for (Noeud n : curr.children) {
                if (n.n == c) {
//                    System.out.println("found match, next char, " + n.n + ":" + c);
                    curr = n;
                    match = true;
                    continue s1;
                }
            }
        }

        return match;
    }

    @Override
    public String toString() {
        return racine.toString();
    }

    boolean isMarkedChild(Noeud p) {
        for (Noeud c : p.children) {
            if (c.step != 2) {
                return false;
            }
        }
        return true;
    }

    public void listeMotsAlphabetique() {
        //    System.out.println(racine.children);
        //    System.out.println("word count: " + count);

        Noeud curr = racine;

        int wordcount = count;
        String word = "";

        LinkedList<Noeud> c = new LinkedList<Noeud>();
        
        start:
        while (wordcount > 0) {

            for (Noeud n : curr.children) {
                if (n.step == 2) {
                    continue;
                }
                if (n.n == '*') {
                    n.step = 2;
                    System.out.print(word + " ");
                    word = "";
                    wordcount--;
                    for (int i = c.size() - 1; i >= 0; i--) {
                        if (c.get(i).children.size() <= 1 || isMarkedChild(c.get(i))) {
                            c.get(i).step = 2;
                        } else {
                            curr = racine;
                            c.removeAll(c);
                            continue start;
                        }
                    }
                    continue start;
                }

                word += n.n;
                c.add(n);
                curr = n;
                continue start;
            }
            curr = racine;
        }
    }
}

class Noeud {

    LinkedList<Noeud> children;
    char n;
    int step = 0;
    int id;

    Noeud(char c) {
        n = c;
        children = new LinkedList<>();
    }

    public void ajouteFils(Noeud a) {
        for (Noeud n : children) {
            if (n.n > a.n) {
                children.add(children.indexOf(n), a);
                return;
            }
        }
        children.add(a);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(n);
        if (children.size() > 0) {
            if (n != ' ')
                sb.append("(");

            for (Noeud n : children) {
                sb.append(n.toString());
                if (children.indexOf(n) != children.size() - 1)
                    sb.append(", ");
            }

            if (n != ' ')
                sb.append(")");
        }

        return sb.toString();
    }

}

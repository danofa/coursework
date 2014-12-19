package hw6;

import java.util.Arrays;

class Sudoku {

    int[][] grille;

    Sudoku(int[][] t) {
        grille = t;
    }

    public boolean verifieLigne(int i) {
        
//        int[] line = grille[i];
//
//        for (int x = 0; x < line.length; x++) {
//            for (int y = x + 1; y < line.length; y++) {
//                if (line[x] != 0 && line[x] == line[y])
//                    return false;
//            }
//        }
//        return true;
        
        
        return isValidLine(grille[i]);
        
    }

    public boolean verifieColonne(int i) {
        for (int x = 0; x < 9; x++) {
            for (int y = x + 1; y < 9; y++) {
                if (grille[x][i] != 0 && grille[x][i] == grille[y][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifieCarre(int i, int j) {

        int[] l = new int[9];
        // starting points of sub square (row, col);
        int sr = ((i / 3) * 3);
        int sc = ((j / 3) * 3);
        int count = 0;

        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                l[count] = grille[r + sr][c + sc];
                count++;
            }
        }

        return isValidLine(l);
    }

    public boolean verifiePossible(int i, int j, int val) {
        int oldval = grille[i][j];
        if (oldval != 0)
            return false;

        grille[i][j] = val;

        if (!verifieLigne(i) || !verifieColonne(j) || !verifieCarre(i, j)) {
            grille[i][j] = oldval;
            return false;
        } else {
            grille[i][j] = oldval;
            return true;
        }
    }

    public boolean resousGrille() {
        // à compléter
        return false;
    }

    public int solutionUnique() {
        // à compléter
        return 0;
    }

    private void printSmallSquare(int sr, int sc) {

        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                System.out.print(grille[r + sr][c + sc] + " ");
            }
            System.out.println("");
        }
    }

    private boolean isValidLine(int[] line) {
        for (int x = 0; x < line.length; x++) {
            for (int y = x + 1; y < line.length; y++) {
                if (line[x] != 0 && line[x] == line[y])
                    return false;
            }
        }
        return true;
    }

    public void afficheGrille() {
        if (this.grille == null)
            return;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.grille[i][j] + " ");
            }
            System.out.println();
        }
    }
}

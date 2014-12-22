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

    private boolean solveGrid(int r, int c) {

        if(r == 9){
            return true;
        }
        
        if(grille[r][c] != 0){
                if (c == 8) {
                    if (solveGrid(r + 1, 0)) {
                        return true;
                    }
                } else {
                    if (solveGrid(r, c + 1)) {
                        return true;
                    }
                }
                return false;
        }
        
        
        for (int n = 1; n <= 9; n++) {
            if (verifiePossible(r, c, n)) {
                grille[r][c] = n;

                if (c == 8) {
                    if (solveGrid(r + 1, 0)) {
                        return true;
                    }
                } else {
                    if (solveGrid(r, c + 1)) {
                        return true;
                    }
                }
                
                grille[r][c] = 0;
            }
            
        }
        return false;
    }

    public boolean resousGrille() {

        solveGrid(0, 0);

        return true;
    }

    public int solutionUnique() {
        int count = 0;
        
        int[][] origGrid = new int[9][9];
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                origGrid[r][c] = grille[r][c];
                if(grille[r][c] != 0){
                    if(solveGrid(r, c)){
                        System.out.println("");
                        afficheGrille();
                    }
                }
            }
        }

        
        
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

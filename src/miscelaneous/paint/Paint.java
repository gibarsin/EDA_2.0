package miscelaneous.paint;

import java.util.Random;

public class Paint {
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private boolean[][] m;

    public Paint(int n) {
        m = new boolean[n][n];

        randomFill(m);
    }

    private void randomFill(boolean[][] mat) {
        Random r = new Random();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = r.nextBoolean();
            }
        }
    }

    private void paint(boolean color, int i, int j) {
        if (!withinBounds(m, i, j) || m[i][j] == color) {
            return;
        }
        m[i][j] = color;
        for (int[] move : moves) {
            int nextI = i + move[0];
            int nextJ = j + move[1];

            paint(color, nextI, nextJ);
        }
    }

    private boolean withinBounds(boolean[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 && j < m[0].length;
    }
}

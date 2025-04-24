public class DirectedOrUndirected {
    public static boolean directedGraphCheck(int[][] matrix) {
        int n = matrix.length;

        if (n == 0) {
            return false;
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},{1,0,0},{0,0,0}};
        int[][] matrix1 = {{0,1,1,0}, {1,0,0,1}, {1,0,0,0}, {0,1,0,0}};

        if (directedGraphCheck(matrix)) {
            System.out.println("This matrix is a directed graph.");
        } else {
            System.out.println("The matrix is a undirected graph.");
        }
    }
}

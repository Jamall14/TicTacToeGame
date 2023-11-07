import java.util.*;

public class TicTacToe2Player {
    static ArrayList<Integer> player1Pos = new ArrayList<>();
    static ArrayList<Integer> player2Pos = new ArrayList<>();

    public static void main(String[] args) {
        String result;

        char[][] board = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},};

        printBoard(board);

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Player 1 enter your placement (1-9): ");
            int p1Pos = in.nextInt();
            while (player1Pos.contains(p1Pos) || player2Pos.contains(p1Pos)) {
                System.out.println("Position taken!");
                p1Pos = in.nextInt();
            }
            placePiece(board, p1Pos, "player1");

            printBoard(board);

            result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }


            System.out.println("Player 2 enter your placement (1-9): ");
            int p2Pos = in.nextInt();
            while (player1Pos.contains(p2Pos) || player2Pos.contains(p2Pos)) {
                System.out.println("Position taken!");
                p2Pos = in.nextInt();
            }
            placePiece(board, p2Pos, "player2");

            printBoard(board);

            result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] board, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player1")) {
            symbol = 'X';
            player1Pos.add(pos);
        } else if (user.equals("player2")) {
            symbol = 'O';
            player2Pos.add(pos);
        }
        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> middleRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> middleCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(7, 5, 3);

        List<List> winningConditions = new ArrayList<>();

        winningConditions.add(topRow);
        winningConditions.add(middleRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftCol);
        winningConditions.add(middleCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List l : winningConditions) {
            if (player1Pos.containsAll(l)) {
                return "Player 1 wins";
            } else if (player2Pos.containsAll(l)) {
                return "Player 2 wins";
            } else if (player1Pos.size() + player2Pos.size() == 9) {
                return "Draw";
            }
        }

        return "";
    }
}

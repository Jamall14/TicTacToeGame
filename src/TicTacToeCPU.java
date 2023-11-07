import java.util.*;

public class TicTacToeCPU {
    static ArrayList<Integer> playerPos = new ArrayList<>();
    static ArrayList<Integer> cpuPos = new ArrayList<>();

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
            System.out.println("Enter your placement (1-9): ");
            int pos = in.nextInt();
            while (playerPos.contains(pos) || cpuPos.contains(playerPos)) {
                System.out.println("Position taken!");
                pos = in.nextInt();
            }
            result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }

            placePiece(board, pos, "player");

            Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1;
            while (playerPos.contains(cpuPosition) || cpuPos.contains(cpuPosition)) {
                cpuPosition = random.nextInt(9) + 1;
            }
            placePiece(board, cpuPosition, "cpu");

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
        if (user.equals("player")) {
            symbol = 'X';
            playerPos.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPos.add(pos);
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
            if (playerPos.containsAll(l)) {
                return "You won";
            } else if (cpuPos.containsAll(l)) {
                return "CPU wins";
            } else if (playerPos.size() + cpuPos.size() == 9) {
                return "Draw";
            }
        }

        return "";
    }
}

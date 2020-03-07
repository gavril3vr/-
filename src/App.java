import java.io.IOException;
import java.util.Random;

public class App {

    //    public static int widthWall = 20;
//    public static int heightWall = 40;
    public static boolean gameOver = false;
    public static int score = 0;
    public static int foodX = 1;
    public static int foodY = 1;
    public static int playerX = 3;
    public static int playerY = 3;
    public static int timer = 10;


    public static void main(String[] args) {
        MyKeyListener key = new MyKeyListener();
        game();
    }

    public static void drawGameGrid() {
        int[][] matrix = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 2},
                {21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21}
        };

        for (int i = 0; i < matrix.length; i++) { //редове
            for (int j = 0; j < matrix.length; j++) { //колони
                if (matrix[i][j] == 1 || matrix[i][j] == 21) {
                    System.out.print("-" + "  ");
                } else if (matrix[i][j] == 2) {
                    System.out.print("|");
                } else {
                    System.out.print("." + "  ");
                }
                if (matrix[i][j] >= 3 && matrix[i][j] <= 20) {
                    if (matrix[i][j] == playerX) {
                        System.out.print("\\_/");
                    }

                }
                if (matrix[i][j] >= 41 && matrix[i][j] <= 58) {
                    int random = 41 + (int) (Math.random() * (58 - 41 + 1));
                    if(random == matrix[i][j]) {
                        System.out.print("o");
                    }

                }
            }
            System.out.println();

        }
        System.out.println("Score : " + score);
    }

    public static int countdown() {

        while (timer > 0) {
            System.out.println("Remaining: " + timer + " seconds");
            try {
                timer--;
                Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
            } catch (InterruptedException e) {
                //I don't think you need to do anything for your particular problem
            }
        }
        return timer;
    }

    public static void moveRight() {
        if (MyKeyListener.moveRight) {
            playerX += 1;
        }
    }

    public static void moveLeft() {
        if (MyKeyListener.moveLeft) {
            playerX -= 1;
        }
    }


    public static void getFood() {
        // move food row
        if (foodX == playerX) {
            score += 10;
        }
    }

    public static void game() {
        while (!gameOver) {
            drawGameGrid();
            sleep();
            rules();
            controls();
            clearScreen();
        }
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void controls() {
        moveLeft();
        moveRight();
    }

    public static void sleep() {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void rules() {
        getFood();
        gameOver();
    }

    public static void gameOver() {
        if (timer == 0) {
            gameOver = true;
        }

    }

}

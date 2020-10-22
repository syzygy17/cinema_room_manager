import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static void fill(char[][] arr) {
        char sChar = 'S';

        for (char[] chars : arr) {
            Arrays.fill(chars, sChar);
        }
    }

    private static void printCinema(int rows, int seats, char[][] seatsArray) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
            if (i == seats) System.out.print("\n");
        }
        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(seatsArray[i - 1][j] + " ");
                if (j == seats - 1) System.out.print("\n");
            }
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int price = 10;
        int secondPrice = 8;
        int totalPrice;
        int purchasedTickets = 0;
        int currentIncome = 0;
        double percentage;


        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        final int totalSeats = rows * seats;
        final int firstHalf = rows / 2;
        final int secondHalf = rows - firstHalf;
        int totalIncome = (firstHalf * seats * price) + (secondHalf * seats * secondPrice);


        char[][] seatsArray = new char[rows][seats];
        fill(seatsArray);


        while (true) {
            printMenu();
            int action = scanner.nextInt();
            System.out.println();
            switch (action) {
                case 0:
                    return;
                case 1:
                    printCinema(rows, seats, seatsArray);
                    break;
                case 2:
                    while (true) {
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();

                        if (totalSeats < 60 || rowNumber <= firstHalf) {
                            totalPrice = price;
                        } else {
                            totalPrice = secondPrice;
                        }

                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();

                        System.out.println();
                        if (rowNumber > rows || seatNumber > seats ||
                                rowNumber < 0 || seatNumber < 0) {
                            System.out.println("Wrong input!");
                        } else if (seatsArray[rowNumber-1][seatNumber-1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            seatsArray[--rowNumber][--seatNumber] = 'B';
                            System.out.println("Ticket price: $" + totalPrice);
                            purchasedTickets++;
                            currentIncome += totalPrice;
                            break;
                        }
                    }
                    break;
                case 3:
                    percentage = purchasedTickets % totalSeats;
                    System.out.println("Number of purchased tickets: "
                            + purchasedTickets);
                    System.out.println("Percentage: "
                            + String.format("%.2f", percentage) + "%");
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + totalIncome);
                    break;
            }
        }
    }
}

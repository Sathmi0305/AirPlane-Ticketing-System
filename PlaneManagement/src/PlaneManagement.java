import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {
    public static Scanner scanner = new Scanner(System.in);               //Public scanner class to check for the user's option in continuing the program
    //Defining arrays for seat rows
    public static String[] row1_seats = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    public static String[] row2_seats = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    public static String[] row3_seats = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    public static String[] row4_seats = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    public static Ticket[] tickets = new Ticket[52];
    public static Ticket newticket;
    public static double total = 0;

    public static void main(String[] args) {                                  //Main method of the program
        Scanner user_input = new Scanner(System.in);                          //Scanner in the main method
        System.out.println("Welcome to the Plane Management application \n");
        System.out.print("**************************************************\n" +
                "*                      MENU OPTIONS               *\n" +
                "**************************************************\n" +
                "\t 1) Buy a seat\n" +
                "\t 2) cancel a seat\n" +
                "\t 3) Find first available  seat\n" +
                "\t 4) Show seating plan\n" +
                "\t 5) Print tickets information and total sales\n" +
                "\t 6) Search ticket\n" +
                "\t 0) Quit\n" +
                "******************************************************\n"
        );
        while (true) {
            System.out.println("\nPlease select an option :");
            String user = user_input.next();
            switch (user) {
                case "1":
                    buy_seat();                                            //call the buy ticket method
                    break;
                case "2":
                    cancel_seat();                                         //call the cancel ticket method
                    break;
                case "3":
                    find_first_available();                                //call the fin first available method
                    break;
                case "4":
                    show_seating_plan();                                   //call the show seating plan method
                    break;
                case "5":
                    print_ticket_info();                                   //call the ticket information method
                    break;
                case "6":
                    search_ticket();                                       //call the search ticket method
                    break;
                case "0":
                     exit();                                               //call the exit method
                     break;
                default:
                    System.out.println("Incorrect option. Please try again .");
            }
        }
    }

    public static void buy_seat() {                                                                       //method allows the user to buy a ticket
        System.out.println("Buy a Seat");
        Scanner scanner = new Scanner(System.in);
        char rowLetter;
        int seatNumber;

        while (true) {                                                                                      // Input validation for row letter
            try {
                System.out.print("Enter the row letter from A - D: ");                                      //getting row letter form A-D
                rowLetter = scanner.next().toUpperCase().charAt(0);
                if (rowLetter >= 'A' && rowLetter <= 'D') {                                                 //check whether user enter the correct row Letter
                    break;
                } else {
                    System.out.println("Not a valid seat.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not a valid seat.");
                scanner.next();
            }
        }

        while (true) {
            while (true) {                                                                                      // Input validation for seat number
                try {
                    System.out.print("Enter the seat number (1-14): ");                                         //getting seat numbers from 1-14
                    seatNumber = scanner.nextInt();
                    if (seatNumber >= 1 && seatNumber < 15) {
                        break;
                    } else {
                        System.out.println("Not a valid seat.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Not a valid seat.");
                    scanner.next();
                }
            }

            if ((rowLetter == 'A' || rowLetter == 'D') && seatNumber <= row1_seats.length) {                                   // Check if the row and seat are valid
                if (rowLetter == 'A') {
                    if (row1_seats[seatNumber - 1].equals("1")) {                                                              //check whether the seat is already booked by assigning seat number to 1
                        System.out.println("The seat is already booked.");
                        return;
                    } else {
                        row1_seats[seatNumber - 1] = "1";                                                                        //booking the seat
                        break;
                    }
                } else if (rowLetter == 'D') {
                    if (row4_seats[seatNumber - 1].equals("1")) {                                                                 //check whether the seat is already booked by assigning seat number to 1
                        System.out.println("The seat is already booked.");
                        return;
                    } else {
                        row4_seats[seatNumber - 1] = "1";                                                                         //booking the seat
                        break;
                    }
                }
            } else if ((rowLetter == 'B' || rowLetter == 'C') && seatNumber <= row2_seats.length) {                             // Check if the row and seat are valid
                if (rowLetter == 'B') {
                    if (row2_seats[seatNumber - 1].equals("1")) {                                                               //check whether the seat is already booked by assigning seat number to 1
                        System.out.println("The seat is already booked.");
                        return;
                    } else {
                        row2_seats[seatNumber - 1] = "1";                                                                         //booking the seat
                        break;
                    }
                } else if (rowLetter == 'C') {
                    if (row3_seats[seatNumber - 1].equals("1")) {                                                                 //check whether the seat is already booked by assigning seat number to 1
                        System.out.println("The seat is already booked.");
                        return;
                    } else {
                        row3_seats[seatNumber - 1] = "1";                                                                         //booking the seat
                        break;
                    }
                }
            } else {
                System.out.println("Not a valid seat.");
                continue;

            }
        }


        System.out.println("Seat purchased successfully.");                                     // Seat is available, proceed with ticket purchase
        System.out.print("Please enter your name: ");                                    //getting user's name
        String name = scanner.next();
        System.out.print("Please enter your surname: ");                               // getting user's surname
        String surname = scanner.next();
        System.out.print("Please enter your email: ");                                  //getting user's email
        String email = scanner.next();
        Person person = new Person(name, surname, email);

        Ticket newticket = new Ticket(rowLetter, seatNumber, calculate_seat_pricing(seatNumber), person);                // Create new ticket and allocate price
        newticket.save();
        ticketArray(newticket);
    }
    
    public static void cancel_seat() {                                                                          //method allows user to cancel a seat
        System.out.println("Cancel a Seat");
        Scanner scanner = new Scanner(System.in);
        char rowLetter;
        int seatNumber;
        while (true) {
            try {                                                                                                //validations for row letter
                System.out.print("Enter the row letter from A - D: ");
                rowLetter = scanner.next().toUpperCase().charAt(0);
                if (rowLetter >= 'A' && rowLetter <= 'D') {                                                            //getting row letter form A-D
                    break;
                } else {
                    System.out.println("Invalid row letter.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid row letter.");
                scanner.next();
            }
        }
        while (true) {
            while (true) {
                try {                                                                                                    //validations for seat number
                    System.out.print("Enter the seat number (1-14): ");
                    seatNumber = scanner.nextInt();
                    if (seatNumber >= 1 && seatNumber <= 14) {                                                           //getting seat numbers from 1 to 14
                        break;
                    } else {
                        System.out.println("Invalid seat number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid seat number.");
                    scanner.next();
                }
            }

            if ((rowLetter == 'A') || (rowLetter == 'D')) {                                  //check whether user enter the correct row Letter
                if (seatNumber <= row1_seats.length) {
                    switch (rowLetter) {
                        case 'A':
                            if (row1_seats[seatNumber - 1] .equals("0")) {                                //check whether the seat is already available by assigning seat number to 0
                                System.out.println("The seat is already available .");
                            } else {
                                row1_seats[seatNumber - 1] = "0";                                          //check whether the seat is cancelled by equating seat number to 0
                                String filePath = rowLetter + String.valueOf(seatNumber) + ".txt";
                                File fileToDelete = new File(filePath);
                                if (fileToDelete.exists()) {                                                  //deleting a file

                                    if (fileToDelete.delete()) {
                                        System.out.println("Ticket " + rowLetter + String.valueOf(seatNumber) + " deleted successfully.");
                                    } else {
                                        System.out.println("Failed to delete the Ticket.");
                                    }
                                } else {
                                    System.out.println("A Ticket " + rowLetter + String.valueOf(seatNumber) + " does not exist");
                                }
                                for (int i = 0; i < tickets.length; i++) {
                                    Ticket ticket = tickets[i];
                                    if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                                        tickets[i] = null;                                                                                  // Remove the ticket from the array
                                        System.out.println("Ticket " + rowLetter + seatNumber + " canceled successfully.");
                                        double totalAfterCancellation = calculate_total_price();
                                        return;                                                                     // Exit the method once the ticket is found and removed
                                    }
                                }
                                System.out.println("Seat is cancelled successfully .");


                            }

                            break;
                        case 'D':
                            if (row4_seats[seatNumber - 1].equals("0")) {                                    //check whether the seat is already available by assigning seat number to 0
                                System.out.println("The seat is already available .");
                            } else {
                                row4_seats[seatNumber - 1] = "0";                                                  //check whether the seat is cancelled by equating seat number to 0
                                String filePath = rowLetter + String.valueOf(seatNumber) + ".txt";
                                File fileToDelete = new File(filePath);
                                if (fileToDelete.exists()) {

                                    if (fileToDelete.delete()) {                                                                        //deleting a file
                                        System.out.println("Ticket " + rowLetter + String.valueOf(seatNumber) + " deleted successfully.");
                                    } else {
                                        System.out.println("Failed to delete the Ticket.");
                                    }
                                } else {
                                    System.out.println("A Ticket " + rowLetter + String.valueOf(seatNumber) + " does not exist");
                                }
                                for (int i = 0; i < tickets.length; i++) {
                                    Ticket ticket = tickets[i];
                                    if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                                        tickets[i] = null;                                                                                // Remove the ticket from the array
                                        System.out.println("Ticket " + rowLetter + seatNumber + " canceled successfully.");
                                        double totalAfterCancellation = calculate_total_price();
                                        return;                                                          // Exit the method once the ticket is found and removed
                                    }
                                }
                                System.out.println("Seat is cancelled successfully .");               //check whether the seat is cancelled by equating seat number to 0

                            }
                            break;
                    }
                } else {
                    System.out.println("Not a valid Seat .");
                }

            } else if ((rowLetter == 'B') || (rowLetter == 'C')) {                                                     //check whether user enter the correct row letter and the correct seat number
                if (seatNumber <= row2_seats.length) {
                    switch (rowLetter) {
                        case 'B':

                            if (row2_seats[seatNumber - 1] .equals("0")) {                                                   //check whether the seat is already available by assigning seat number to 0
                                System.out.println("The seat is already available  .");
                            } else {
                                double canceledTicketPrice = calculate_seat_pricing(seatNumber);
                                total -= canceledTicketPrice;
                                row2_seats[seatNumber - 1] = "0";                                                             //check whether the seat is cancelled by equating seat  number to 0
                                String filePath = rowLetter + String.valueOf(seatNumber) + ".txt";
                                File fileToDelete = new File(filePath);
                                if (fileToDelete.exists()) {                                                                     //deleting a file
                                    if (fileToDelete.delete()) {
                                        System.out.println("Ticket " + rowLetter + String.valueOf(seatNumber) + " deleted successfully.");
                                    } else {
                                        System.out.println("Failed to delete the Ticket.");
                                    }
                                } else {
                                    System.out.println("A Ticket " + rowLetter + String.valueOf(seatNumber) + " does not exist");
                                }
                                for (int i = 0; i < tickets.length; i++) {
                                    Ticket ticket = tickets[i];
                                    if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                                        tickets[i] = null;                                                                                    // Remove the ticket from the array
                                        System.out.println("Ticket " + rowLetter + seatNumber + " canceled successfully.");
                                        double totalAfterCancellation = calculate_total_price();
                                        return;                                                                          // Exit the method once the ticket is found and removed
                                    }
                                }
                                System.out.println("Seat is cancelled successfully .");


                            }
                            break;
                        case 'C':
                            if (row3_seats[seatNumber - 1].equals("0")) {                                                 //check whether the seat is already available by assigning seat number to 0
                                System.out.println("The seat is already available .");
                            } else {
                                row3_seats[seatNumber - 1] = "0";                                                               //check whether the seat is cancelled by equating seat  number to 0
                                String filePath = rowLetter + String.valueOf(seatNumber) + ".txt";
                                File fileToDelete = new File(filePath);
                                if (fileToDelete.exists()) {

                                    if (fileToDelete.delete()) {                                                                                 //deleting a file
                                        System.out.println("Ticket " + rowLetter + String.valueOf(seatNumber) + " deleted successfully.");
                                    } else {
                                        System.out.println("Failed to delete the Ticket.");
                                    }
                                } else {
                                    System.out.println("A Ticket " + rowLetter + String.valueOf(seatNumber) + " does not exist");
                                }
                                for (int i = 0; i < tickets.length; i++) {
                                    Ticket ticket = tickets[i];
                                    if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                                        tickets[i] = null;                                                                               // Remove the ticket from the array
                                        System.out.println("Ticket " + rowLetter + seatNumber + " canceled successfully.");
                                        double totalAfterCancellation = calculate_total_price();
                                        return;                                                                                     // Exit the method once the ticket is found and removed
                                    }
                                }
                                System.out.println("Seat is cancelled successfully .");

                            }
                            break;
                    }
                } else {
                    System.out.println("Not a valid seat .");
                }
            } else {
                System.out.println("Invalid seat . Please try again . ");
                return;
            }
        }


    }

    public static void find_first_available() {                                                                              //calling find first available method
        System.out.println("Show First available Seating option ");

        for (int seats = 0; seats < row1_seats.length; seats++) {                                                              //check the available seats in row 1
            if (row1_seats[seats] .equals("0")) {
                System.out.println("In Row 1 Seat " + (seats+1) + " is available");
                return;
            }
        }
        for (int seats = 0; seats < row2_seats.length; seats++) {                                                                //check the available seats in row 2
            if (row2_seats[seats].equals("0")) {
                System.out.println("In Row 2 Seat " + (seats+1) + " is available");
                return;
            }
        }
        for (int seats = 0; seats < row3_seats.length; seats++) {                                                                 //check the available seats in row 3
            if (row3_seats[seats] .equals("0")) {
                System.out.println("In Row 3 Seat " + (seats+1) + " is available");
                return;
            }
        }
        for (int seats = 0; seats < row4_seats.length; seats++) {                                                                  //check the available seats in row 4
            if (row4_seats[seats] .equals("0")) {
                System.out.println("In Row 4 Seat " + (seats+1) + " is available");
                return;
            }
        }
    }

    public static void show_seating_plan() {                                                         //calling show seating plan method
        System.out.println("Show Seating Plan");
        System.out.print("Seating Plan Row 1:");                                                      //print the seating plan of row 1
        for (String row : row1_seats) {
            if (row.equals("0")) {
                System.out.print("O");
            } else {
                System.out.print("X");                                                               // if the row1 seats booked show that as X in the seating plan
            }
        }

        System.out.print("\nSeating Plan Row 2:");                                                    //print the seating plan of row 2
        for (String row : row2_seats) {
            if (row.equals("0")) {
                System.out.print("O");
            } else {
                System.out.print("X");                                                                  // if the row2 seats booked show that as X in the seating plan
            }
        }

        System.out.print("\nSeating Plan Row 3:");                                                      //print the seating plan of row 3
        for (String row : row3_seats) {
            if (row.equals("0")) {
                System.out.print("O");
            } else {
                System.out.print("X");                                                                  // if the row3 seats booked show that as X in the seating plan
            }
        }

        System.out.print("\nSeating Plan Row 4:");                                                        //print the seating plan of row 4
        for (String row : row4_seats) {
            if (row.equals("0")) {
                System.out.print("O");
            } else {
                System.out.print("X");                                                                  // if the row3 seats booked show that as X in the seating plan
            }
        }
    }

    public static void print_ticket_info() {                                                           //calling the printing ticket information method
        System.out.println("Print Ticket Information");
        double total = 0;
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                System.out.println("Seat : " + ticket.getRow() + ticket.getSeat());
                double price = calculate_seat_pricing(ticket.getSeat());
                System.out.println("Price : " + price + "$");
                total = total + price;                                                                    //calculate the total price of the booked seats
            }
        }
        System.out.println("Total price of Tickets : " + total + "$");
    }

    private static double calculate_total_price(){
        double total = 0;
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                double price = calculate_seat_pricing(ticket.getSeat());
                total = total + price;
            }
        }
        System.out.println("Total price of Tickets after cancellation: " + total + "$");                               //print total amount of the remaining tickets after the cancellation

        return total;
    }

    private static int calculate_seat_pricing(int seat) {
        if (seat >= 10 && seat <= 14)                                                                                   //check the range of the seats numbers and calculate the seat pricing
            return 180;
        else if (seat >= 6)
            return 150;
        else if (seat > 0)
            return 200;

        return -1;
    }

    private static int ticketArray(Ticket newticket) {
        int index = 0;
        while (index < tickets.length) {
            if (tickets[index] == null) {                                                                               //adding tickets to the array
                tickets[index] = newticket;
                break;
            }
            index++;
        }
        return index;
    }

    public static void search_ticket() {
        System.out.println("Search for a Ticket");
        Scanner scanner = new Scanner(System.in);
        char rowLetter;
        int seatNumber;
        while (true) {
            try {                                                                                         // validation for row letter
                System.out.print("Enter the row letter from A - D  :");                                  //getting row letter form A-D
                rowLetter = scanner.next().toUpperCase().charAt(0);
                if (rowLetter >= 'A' && rowLetter <= 'D') {                                              //check whether user enter the correct row Letter
                    break;
                } else {
                    System.out.println("Invalid row letter.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid row letter.");
                scanner.next();
            }
        }
        int maxseatNumber;
        if(rowLetter =='A'|| rowLetter =='D') {
            maxseatNumber = 14;
        }else{
            maxseatNumber = 12;
        }
        while (true) {
            while (true) {                                                                                      // Input validation for seat number
                try {
                    System.out.print("Enter the seat number (1-14): ");                                         //getting seat numbers from 1-14
                    seatNumber = scanner.nextInt();
                    if (seatNumber >= 1 && seatNumber <=maxseatNumber) {                                                   //check whether user enter the correct seat number
                        break;
                    } else {
                        System.out.println("Invalid seat number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid seat number.");
                    scanner.next();
                }
            }
            boolean found = false;

            // Check if the seat is booked
            for (Ticket ticket : tickets) {
                if (ticket != null && ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                    ticket.show();
                    found = true;
                    break;
                }
            }

            if (!found) {
                // If the seat is not booked, check if it's available and print price
                double price = calculate_seat_pricing(seatNumber);
                if (price != -1) {
                    System.out.println("Seat is available.\nPrice of the seat " + rowLetter + seatNumber + " is $" + price);

                } else {
                    System.out.println("Invalid seat.");
                }
                return;
            }
        }
    }

    public static void exit(){                                                             //method allow user to exist the program
        System.out.println("Thank you for choosing the program... Exit from the system .");
        System.exit(0);
    }
}
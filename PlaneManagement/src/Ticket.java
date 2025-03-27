import java.io.FileWriter;

import java.io.IOException;

public class Ticket {                                  //Ticket class
    private char row;                                     //create a char variable named row
    private int seat;                                      //create an int variable named row
    private double price;                                 //create a double variable named row
    private Person person;

    public Ticket(char row, int seat, double price, Person person) {          //create a constructor for ticket class with 4 parameters
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;

    }

    public void show() {                                               //method for print all the ticket information
        System.out.println("Name : " + person.getName());
        System.out.println("Surname : " + person.getSurname());
        System.out.println("Email : " + person.getEmail());
        System.out.println("Row Number : " + row);
        System.out.println("Seat Number : " + seat);
        System.out.println("Price : " + price);
        System.out.println();

    }

    public double getPrice() {                                           //get the price and return it
        return price;
    }

    public char getRow() {                                                //get the row and return it
        return row;
    }

    public int getSeat() {                                               //get the seat and return it
        return seat;
    }

    public void save() {                                                           //method for save all the information as a .txt file
        String filename = row + String.valueOf(seat) + ".txt";
        try {
            FileWriter mywriter = new FileWriter(filename);
            mywriter.write("Name :" + person.getName() + "\n");
            mywriter.write("Surname :" + person.getSurname() + "\n");
            mywriter.write("Email :" + person.getEmail() + "\n");
            mywriter.write("Row Number :" + row + "\n");
            mywriter.write("Seat Number :" + seat + "\n");
            mywriter.write("Price :" + price + "\n");
            mywriter.close();
            System.out.println("Ticket information saved to the file : " + filename);

        } catch (IOException e) {
            System.out.println("An error occurred .");
            e.printStackTrace();

        }

    }

}

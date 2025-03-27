public class Person {                                  //person class
    private String name;                                   // create a string variable named name
    private String surname;
    private String email;
    public Person(String name , String surname , String email){                    //creating a constructor for the person class with 3 parameters
        this.name = name;                                                          //equalize name parameter with name variable
        this.surname = surname;                                                    //equalize surname parameter with name variable
        this.email = email;                                                       //equalize email parameter with name variable

    }
    public String getName() {return name;}                                       //get the person's name and return it
    public String getSurname() {return surname;}                                 //get the person's surname and return it
    public String getEmail() {return email;}                                     //get the person's email and return it

    public void show(){
        System.out.printf("Name : %s %s , email : %s ", name, surname,email);                  //print the personal information
    }
    public void setName(String name) {
        this.name = name;
    }                                      //set the name
    public void setSurname(String surname) {
        this.surname = surname;
    }                          //set the surname
    public void setEmail(String email) {
        this.email = email;
    }                                  //set the email

}





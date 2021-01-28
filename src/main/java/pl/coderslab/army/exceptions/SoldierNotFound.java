package pl.coderslab.army.exceptions;

public class SoldierNotFound extends RuntimeException {
    public SoldierNotFound(){

    }
    public String SoldierNotFound(){
        System.out.println("nie istnieje taki kolo z numerem: ");
       return "redirect:/404";
    }
//    public SoldierNotFound(String message) {
//        super(message);
//    }
}


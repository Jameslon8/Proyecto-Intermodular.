package controller;

import java.util.Scanner;

public class MenuTotales {
    private Scanner sc = new Scanner(System.in);

    private static final String PasswordMaestra = "Contraseña";

    public void inicio(){
        int opcion = 0;

        do{
            System.out.println("\n|=== HUNTPMO ===|");
            System.out.println("Como desea entrar?");
            System.out.println("1. Usuario");
            System.out.println("2. Agente");
            System.out.println("3. Registrarse como usuario");
            System.out.println("4. Acceso director");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
        }while(opcion != 0);
    }

    private void loginUser(){
        int opcion = 0;

        System.out.print("Introduzca nombre del usuario: ");
        String nombreUser = sc.nextLine();

        System.out.print("Introduzca contraseña con la que entrar: ");
        String contrasenya = sc.nextLine();

    }
}


package Principal;

import Tools.Ficheros;
import Objects.Palabra;
import Tools.Traductor;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    
    public static void menu(){
        
        Scanner tecladoA = new Scanner(System.in);
        
        Map <String, String> lista = new HashMap<String, String>();  
        
        int opcion = -1;
        
        while (opcion != 0){
            
            try{
            
            System.out.println("\n¿Qué quieres hacer? \n 1.-Modificar fichero \n 2.-Traducir texto \n 3.-Buscar palabra \n 4.-Mostrar listado de palabras \n 0.-Salir \n");
            
            opcion = tecladoA.nextInt();
            
            switch(opcion){
                
                case 1:
                
                    int opcionB = 1;
                    
                    while(opcionB != 0){
                        
                        try{
                            
                            System.out.println("\n¿Qué deseas modificar? \n 1.-Introducir nueva palabra \n 2.-Eliminar palabra \n 3.-Modificar palabra \n 0.-Salir\n");
                            
                            opcionB = tecladoA.nextInt();
                            
                        }catch (InputMismatchException | NumberFormatException e) {tecladoA.next(); opcionB=-1;}
                        
                        switch(opcionB){
                            
                            case 1:
                              
                                lista = Ficheros.leerFicheroEspIng(0);
                                
                                String esp = Menu.validarPalabra(0);
                
                                String eng = Menu.validarPalabra(1);
                                
                                Palabra palabra = new Palabra(esp, eng);
                                
                                if(lista.isEmpty()){
                                    
                                    Ficheros.escribirFicheroSobre(palabra);
                                    
                                }
                                else{
                                    
                                    Ficheros.escribirFicheroAdd(palabra);
                                    
                                }
  
                            break;
                            
                            case 2:
                            
                                Menu.modificarLista(1);
                                
                            break;
                            
                            case 3:
                            
                                Menu.modificarLista(2);
                                
                            break;
                            
                        }
                        
                    }                   
                    
                break;
                
                case 2:
                
                    Scanner tecladoB = new Scanner(System.in);
                
                    String oracion = "";

                    opcionB = -1;

                    System.out.print("\nIntroduce la frase a traducir: ");

                    oracion = tecladoB.nextLine();

                    String frase [] = oracion.split(" ");

                    while (opcionB < 0 || opcionB > 2){
                    
                        try{
                        
                        System.out.println("\nTraducir al: \n 1.-Inglés \n 2.-Español \n 0.-Salir\n");

                        opcionB = tecladoA.nextInt();
                        
                        }catch (InputMismatchException | NumberFormatException e) {tecladoA.next();}
                    
                    }
                
                    if(opcionB != 0){
                        
                        Traductor.traducirEspEng(frase, opcionB-1);    
                        
                    }                 
                    
                break;
                
                case 3:

                    Menu.modificarLista(0);

                break;
                
                case 4:
                                
                    lista = Ficheros.leerFicheroEspIng(0);
                    
                    System.out.println("\nLista de palabras:");
                                
                    for (Map.Entry<String, String> entry : lista.entrySet()) {
                        
                        Menu.toStringMap(entry.getKey(), entry.getValue());
            
                    } 
                                
                break;
                
            }
            
            }catch (InputMismatchException | NumberFormatException e) {tecladoA.next();}
            
        }
        
    }
    
    public static String validarPalabra(int tipo){
        
        Scanner teclado = new Scanner(System.in);
        
        String tipoS [] = {"español", "inglés"};
        
        String palabra = "";
        
        String valida = " ";
        
        while (!valida.isEmpty()) {

            System.out.print("\nIntroduce la palabra en " + tipoS[tipo] + ": ");

            palabra = teclado.next();
            
            palabra = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();

            valida = Traductor.comprobarPalabra(palabra, tipo);

            if (!valida.isEmpty()) {

                System.out.println("\nEsa palabra ya está registrada");

            }

        }

        return palabra;
        
    }
    
    public static void modificarLista(int accion){
        
        Scanner teclado = new Scanner(System.in);
        
        Map <String, String> lista = Ficheros.leerFicheroEspIng(0);
        
        String accionS [] = {"mostrar", "borrar", "modificar"};

        int tipo = 0;

        while (tipo != 1 && tipo != 2) {

            try {

                System.out.println("\n¿Cómo quieres buscar la palabra? \n 1.-En español \n 2.-En inglés\n");

                tipo = teclado.nextInt();

            } catch (InputMismatchException | NumberFormatException e) {
                teclado.next();
            }

        }

        System.out.print("\nIntroduce la palabra a " + accionS[accion] + ": ");

        String palabra = teclado.next();
        
        palabra = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();

        String valida = Traductor.comprobarPalabra(palabra, tipo - 1);

        if (!valida.isEmpty()) {

            if (tipo == 2) {

                if(accion == 0){
                    
                    System.out.println();
                    
                    Menu.toStringMap(valida, palabra);
                    
                }
                else if(accion == 1){
                    
                    lista.remove(valida); 
                    
                }
                else if(accion == 2){
                    
                    lista.remove(valida);
                    
                    palabra = Menu.validarPalabra(1);
                    
                    lista.put(valida, palabra);
                }    

            } 
            
            else {

                if(accion == 0){
                    
                    System.out.println();
                    
                   Menu.toStringMap(palabra, valida); 
                    
                }
                else if(accion == 1){
                    
                    lista.remove(palabra); 
                    
                }
                else if(accion == 2){
                    
                    lista.remove(palabra);
                    
                    palabra = Menu.validarPalabra(0);
                    
                    lista.put(palabra, valida);
                    
                }

            }

            Ficheros.escribirFicheroTodo(lista);

        } else {

            System.out.println("\nLa palabra no se encuentra en la lista");

        }
        
    }
    
    public static void toStringMap(String palabra1, String palabra2){
        
        System.out.println("En español: " + palabra1 + " --- En inglés: " + palabra2);
        
    }
    
}

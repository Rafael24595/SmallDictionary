
package Tools;

import Principal.Menu;
import Objects.Palabra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Traductor {
    
    public static void traducirEspEng(String palabras[], int tipo){
        
        Map <String, String> mapa = new HashMap<String, String>();
        
        ArrayList<String> vacio = new ArrayList<>();
        
        if(tipo == 0){
            
            mapa = Ficheros.leerFicheroEspIng(0);
            
        }
        else if(tipo == 1){
            
            mapa = Ficheros.leerFicheroEspIng(1);
            
        }

        System.out.println();
        
        String palabra = "";
        
        for (int i = 0; i < palabras.length; i++) {
            
            palabra = palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(1).toLowerCase();
            
            if(mapa.get(palabra) != null){
                
                System.out.print(mapa.get(palabra) + " ");
                
            }
            else{
                
                System.out.print("[---] ");
                
                vacio.add(palabra);
                
            }
            
        }  
        
        if(!vacio.isEmpty()){
            
            System.out.println();
            
            Traductor.nuevaPalabra(vacio, tipo);
            
            
        }
        
        
        System.out.println();
        
    }
    
    public static String comprobarPalabra(String palabra, int idioma){
        
        Map <String, String> mapa = new HashMap<String, String>();   
        
        String clave = "";
        
        if(idioma == 0){
            
            mapa = Ficheros.leerFicheroEspIng(0);
            
        }
        else{
            
            mapa = Ficheros.leerFicheroEspIng(1);
            
        }
        
        if(mapa.containsKey(palabra)){
            
            clave = mapa.get(palabra);
            
            if(clave.equals("")){
                
                clave = "NullError";
                
            }
            
        }       
        
        return clave;
            
        }       

    public static void nuevaPalabra(ArrayList<String> palabras, int tipo){
        
        Scanner teclado = new Scanner(System.in);
        
        int opcion = 0;
        
        while(opcion != 1 && opcion !=2){
            
            try{
                
                System.out.println("\nDurante el proceso se han encontrado " + palabras.size() +  " palabras sin traducir ¿Deseas incluirlas en el fichero? \n 1.-Sí \n 2.-No \n");
                
                opcion = teclado.nextInt();
                
                
            }catch (InputMismatchException | NumberFormatException e) {teclado.next();}
            
        }
        
        if(opcion == 1){
            
            Palabra nueva = new Palabra();
            
            String palabra = "";
            
            opcion = 0;
            
            for (int i = 0; i < palabras.size(); i++) {
                
                try{
                
                    while(opcion != 1 && opcion !=2){
                        
                        try{
                        
                        System.out.println("\nPalabra: " + palabras.get(i) + " ¿Deseas incluirla en el fichero? \n 1.-Sí \n 2.-No \n");
                
                        opcion = teclado.nextInt();
                        
                        }catch (InputMismatchException | NumberFormatException e) {teclado.next();}
                        
                    }

                }catch (InputMismatchException | NumberFormatException e) {teclado.next();}
                
                if(opcion == 1){
                    
                    if(tipo == 0){
                    
                        palabra = Menu.validarPalabra(1);

                        nueva = new Palabra(palabras.get(i), palabra);

                        Ficheros.escribirFicheroAdd(nueva);
                    
                    }
                    else{
                        
                        palabra = Menu.validarPalabra(0);

                        nueva = new Palabra(palabra, palabras.get(i));

                        Ficheros.escribirFicheroAdd(nueva);
                        
                    }
                    
                    
                } 
                
                opcion = 0;
                
            }
            
        }
        
    }
    
}


package Tools;

import Objects.Palabra;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Ficheros {
    
    public static Map<String, String> leerFicheroEspIng(int tipo){
        
        Map <String, String> mapa = new HashMap<>();       
        
        try{
            
            File file = new File("Palabras.obj");
            
            FileInputStream fis = new FileInputStream(file);
            
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            try{
                
                while(true){
                    
                    Palabra palabra = (Palabra)ois.readObject();
                    
                    if(tipo == 0){
                        
                        mapa.put(palabra.getEsp(), palabra.getEng());
                        
                    }
                    else{
                        
                        mapa.put(palabra.getEng(), palabra.getEsp());
                        
                    }
                    
                }
                
            }catch(EOFException e){}
            
            ois.close();
            
            fis.close();

        }catch(IOException e){
        }catch(ClassNotFoundException e){}
        
        return mapa;
        
    }
    
    public static void escribirFicheroSobre(Palabra palabra){
        
        try{
            
            File file = new File("Palabras.obj");
            
            FileOutputStream fos = new FileOutputStream(file);
            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
                
            oos.writeObject(palabra);

            oos.close();
            fos.close();

        }catch(IOException e){}
        
    }
    
    public static void escribirFicheroAdd(Palabra palabra){
        
        try{
            
            File file = new File("Palabras.obj");
            
            FileOutputStream fos = new FileOutputStream(file, true);
            
            ObjectOutputStreamAdd oos = new ObjectOutputStreamAdd(fos);
                
            oos.writeObject(palabra);

            oos.close();
            fos.close();

        }catch(IOException e){}
        
    }
    
    public static void escribirFicheroTodo(Map<String, String> palabras){
        
        try{
            
            File file = new File("Palabras.obj");
            
            FileOutputStream fos = new FileOutputStream(file);
            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            for (Map.Entry<String, String> entry : palabras.entrySet()) {
                
                Palabra palabra = new Palabra(entry.getKey(), entry.getValue());
                
                oos.writeObject(palabra);
            
            }           

            oos.close();
            fos.close();

        }catch(IOException e){}
        
    }
    
}



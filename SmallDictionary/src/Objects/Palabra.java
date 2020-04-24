
package Objects;

import java.io.Serializable;

public class Palabra implements Serializable {
    
    private String esp;
    
    private String eng;

    public Palabra() {
    }
    
    public Palabra(String esp, String eng) {
        this.esp = esp;
        this.eng = eng;
    }

    public String getEsp() {
        return esp;
    }

    public String getEng() {
        return eng;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }

    public void setIng(String eng) {
        this.eng = eng;
    }

    @Override
    public String toString() {
        return "Palabras{" + "esp=" + esp + ", eng=" + eng + '}';
    }
    
}


package Tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamAdd extends ObjectOutputStream{
    
    ObjectOutputStreamAdd(FileOutputStream f) throws IOException{
        
        super(f);
        
    }
    
    public void writeStreamHeader(){}
    
}

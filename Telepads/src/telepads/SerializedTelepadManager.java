package telepads;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedTelepadManager implements Serializable {
    private List<SerializedTelepad> serializedTelepads = new ArrayList();
    
    public SerializedTelepadManager(TelepadManager telepadManager) {
        for(Telepad telepad : telepadManager.getTelepads())
            serializedTelepads.add(new SerializedTelepad(telepad));
    }
    
    public TelepadManager getTelepadManager() {
        TelepadManager telepadManager = new TelepadManager();
        
        for(SerializedTelepad serializedTelepad : serializedTelepads)
            telepadManager.addTelepad(serializedTelepad.getTelepad());
        
        return telepadManager;
    }
}

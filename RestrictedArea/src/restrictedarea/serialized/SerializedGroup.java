package restrictedarea.serialized;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import restrictedarea.Area;
import restrictedarea.group.Group;

public class SerializedGroup implements Serializable {
    private String name;
    private String playerName;
    private List<SerializedArea> areas = new ArrayList();
    
    public SerializedGroup(Group group) {
        this.name = group.getName();
        this.playerName = group.getPlayerName();
        
        for(Area area : group.getAreas())
            areas.add(new SerializedArea(area));
    }
    
    public Group getGroup() {
        Group group = new Group(name, playerName);
        
        for(SerializedArea area : areas)
            group.addArea(area.getArea());
            
        return group;
    }
}

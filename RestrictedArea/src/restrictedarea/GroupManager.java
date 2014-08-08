package restrictedarea;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GroupManager {
    private Map<String, Group> groups = new LinkedHashMap();
    
    public void addGroup(Group group) {
        groups.put(group.getName(), group);
    }
    
    public Group getGroup(String playerName, String name) {
        Group group = groups.get(name);
        if(group == null)
            return null;
        
        if(group.getPlayerName().equalsIgnoreCase(playerName))
            return group;
        
        return null;
    }
    
    public void removeGroup(Group group) {
        groups.remove(group.getName());
    }
    
    public List<Group> getGroups() {
        return new ArrayList(groups.values());
    }
}

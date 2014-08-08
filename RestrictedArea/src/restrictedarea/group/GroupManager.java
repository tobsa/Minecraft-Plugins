package restrictedarea.group;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import restrictedarea.Area;

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
    
    public void removeAreaFromGroups(String playerName, Area area) {
        for(Group group : groups.values())
            if(group.getPlayerName().equalsIgnoreCase(playerName))
                group.removeArea(area);
    }
    
    public void renameGroup(Group group, String newName) {
        group.setName(newName);
        
        Map<String, Group> renamedGroups = new LinkedHashMap();
        
        for(Group oldGroup : groups.values())
            renamedGroups.put(oldGroup.getName(), oldGroup);
        
        groups = renamedGroups;  
    }

    public void renameAreaInGroups(String name, String originalName, String newName) {
        for(Group group : groups.values())
            if(group.getPlayerName().equals(name))
                group.renameArea(originalName, newName);
    }
}

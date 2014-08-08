package restrictedarea.serialized;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import restrictedarea.Area;
import restrictedarea.SubArea;

public class SerializedArea implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String playerName;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private String message;
    private List<SerializedSubArea> areas = new ArrayList();
    
    public SerializedArea(Area area) {
        this.name = area.getName();
        this.playerName = area.getPlayerName();
        this.message = area.getMessage();
        
        for(SubArea subArea : area.getSubAreas())
            areas.add(new SerializedSubArea(subArea));
        
        this.x = area.getLocation().getX();
        this.y = area.getLocation().getY();
        this.z = area.getLocation().getZ();
        this.yaw   = area.getLocation().getYaw();
        this.pitch = area.getLocation().getPitch();
    }
        
    public Area getArea() {
        Area area = new Area(name, playerName);
        
        for(SerializedSubArea subArea : areas)
            area.addSubArea(subArea.getSubArea());
        
        area.setMessage(message);
        area.setLocation(new Location(Bukkit.getWorld("world"), x, y ,z, yaw, pitch));
        
        return area;
    }
}

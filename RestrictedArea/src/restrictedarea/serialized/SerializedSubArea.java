package restrictedarea.serialized;

import com.sk89q.worldedit.BlockVector2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import restrictedarea.SubArea;

public class SerializedSubArea implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<SerializedVector2D> points = new ArrayList();
    private int minimumY;
    private int maximumY;
    
    public SerializedSubArea(SubArea area) {
        this.minimumY = area.getMinimumY();
        this.maximumY = area.getMaximumY();
        
        for(BlockVector2D point : area.getPoints())
            points.add(new SerializedVector2D(point));
    }
    
    public SubArea getSubArea() {
        List<BlockVector2D> points2D = new ArrayList();
        
        for(SerializedVector2D point : points)
            points2D.add(point.getPoint());
        
        return new SubArea(points2D, minimumY, maximumY);
    }
}

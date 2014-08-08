package restrictedarea;

import com.sk89q.worldedit.BlockVector2D;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class SubArea {
    private List<BlockVector2D> points = new ArrayList();
    private int minimumY;
    private int maximumY;
    
    public SubArea(List<BlockVector2D> points, int minimumY, int maximumY) {
        this.points = points;
        this.minimumY = minimumY;
        this.maximumY = maximumY;
    }
    
    public boolean contains(Location location) {
        Vector pt = new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        
        if (points.size() < 3)
            return false;
        
        int targetX = pt.getBlockX(); //wide
        int targetY = pt.getBlockY(); //height
        int targetZ = pt.getBlockZ(); //depth

        if (targetY < minimumY || targetY > maximumY)
            return false;

        boolean inside = false;
        int npoints = points.size();
        int xNew, zNew;
        int xOld, zOld;
        int x1, z1;
        int x2, z2;
        long crossproduct;
        int i;

        xOld = points.get(npoints - 1).getBlockX();
        zOld = points.get(npoints - 1).getBlockZ();

        for (i = 0; i < npoints; ++i) {
            xNew = points.get(i).getBlockX();
            zNew = points.get(i).getBlockZ();
            //Check for corner
            if (xNew == targetX && zNew == targetZ)
                return true;
            if (xNew > xOld) {
                x1 = xOld;
                x2 = xNew;
                z1 = zOld;
                z2 = zNew;
            } else {
                x1 = xNew;
                x2 = xOld;
                z1 = zNew;
                z2 = zOld;
            }
            
            if (x1 <= targetX && targetX <= x2) {
                crossproduct = ((long) targetZ - (long) z1) * (long) (x2 - x1) - ((long) z2 - (long) z1) * (long) (targetX - x1);
                if (crossproduct == 0) {
                    if ((z1 <= targetZ) == (targetZ <= z2)) 
                        return true; //on edge
                } else if (crossproduct < 0 && (x1 != targetX)) {
                    inside = !inside;
                }
            }
            
            xOld = xNew;
            zOld = zNew;
        }

        return inside;
    }

    public List<BlockVector2D> getPoints() {
        return points;
    }
        
    private int getExtremum(boolean min, boolean x) {
        int extremum = min ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        
        for(BlockVector2D point : points) {
            if(min)
                extremum = Math.min(extremum, x ? point.getBlockX() : point.getBlockZ());
            else
                extremum = Math.max(extremum, x ? point.getBlockX() : point.getBlockZ());
        }
        
        return extremum;
    }
    
    public int getMinimumX() {
        return getExtremum(true, true);
    }
    
    public int getMinimumY() {
        return minimumY;
    }
    
    public int getMinimumZ() {
        return getExtremum(true, false);
    }
    
    public int getMaximumX() {
        return getExtremum(false, true);
    }
    
    public int getMaximumY() {
        return maximumY;
    }

    public int getMaximumZ() {
        return getExtremum(false, false);
    }
}

package forestertool;

public class Tool {
    private int radius;
    private int leaves;
    private int air;
    private boolean replace;
    private boolean distanceMode;
    
    public int getRadius() {
        return radius;
    }
    
    public int getLeaves() {
        return leaves;
    }
    
    public int getAir() {
        return air;
    }
    
    public boolean getReplace() {
        return replace;
    }
    
    public boolean getDistanceMode() {
        return distanceMode;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public void setLeaves(int leaves) {
        this.leaves = leaves;
    }
    
    public void setAir(int air) {
        this.air = air;
    }
    
    public void setReplace(boolean replace) {
        this.replace = replace;
    }
    
    public void setDistanceMode(boolean distanceMode) {
        this.distanceMode = distanceMode;
    }
}

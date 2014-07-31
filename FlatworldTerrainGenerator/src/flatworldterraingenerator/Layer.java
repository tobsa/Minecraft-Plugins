package flatworldterraingenerator;

import org.bukkit.Material;

public class Layer {
    private int begin;
    private int end;
    private Material material;
    
    public Layer(int begin, int end, String material) {
        this.begin = begin;
        this.end = end;
        this.material = Material.matchMaterial(material);
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public Material getMaterial() {
        return material;
    }
}

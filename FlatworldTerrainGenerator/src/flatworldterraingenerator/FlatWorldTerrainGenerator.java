package flatworldterraingenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class FlatWorldTerrainGenerator extends JavaPlugin {
    private List<Layer> layers = new ArrayList();

    @Override
    public void onEnable() {
        if (!getConfig().isSet("Layer")) {
            String[] list = {"0,bedrock", "1-3,dirt", "4,grass"};
            getConfig().set("Layer", Arrays.asList(list));
            saveConfig();
        }

        List<String> layers = getConfig().getStringList("Layer");

        for (String str : layers) {
            String layerIndex = str.substring(0, str.indexOf(","));
            String layerMaterial = str.substring(str.indexOf(",") + 1);

            if (layerIndex.contains("-")) {
                int begin = Integer.parseInt(layerIndex.substring(0, layerIndex.indexOf("-")));
                int end = Integer.parseInt(layerIndex.substring(layerIndex.indexOf("-") + 1));

                this.layers.add(new Layer(begin, end, layerMaterial));
            } else {
                int index = Integer.parseInt(layerIndex);
                this.layers.add((new Layer(index, index, layerMaterial)));
            }
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new FlatWorldChunkGenerator(this);
    }

    public List<Layer> getLayers() {
        return layers;
    }
}

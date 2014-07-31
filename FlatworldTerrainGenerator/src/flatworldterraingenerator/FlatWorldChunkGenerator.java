package flatworldterraingenerator;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class FlatWorldChunkGenerator extends ChunkGenerator {

    private FlatWorldTerrainGenerator plugin;

    public FlatWorldChunkGenerator(FlatWorldTerrainGenerator plugin) {
        this.plugin = plugin;
    }

    void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
        if (chunk[y >> 4] == null) {
            chunk[y >> 4] = new byte[16 * 16 * 16];
        }
        if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0)) {
            return;
        }

        try {
            chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) material.getId();
        } catch (Exception e) {
        }
    }

    @Override
    public byte[][] generateBlockSections(World world, Random rand, int ChunkX, int ChunkZ, BiomeGrid biome) {
        byte[][] chunk = new byte[world.getMaxHeight() / 16][];

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (Layer layer : plugin.getLayers()) {
                    for (int index = layer.getBegin(); index <= layer.getEnd(); index++) {
                        setBlock(x, index, z, chunk, layer.getMaterial());
                    }
                }
            }
        }

        return chunk;
    }
}

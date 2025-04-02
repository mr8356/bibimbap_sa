package hdfs;

import java.util.HashMap;
import java.util.Map;
// Slave
class DataNode {
	private final String id;
	private final Map<String, String> storedBlocks = new HashMap<>();

	public DataNode(String id) {
		this.id = id;
	}

	public void storeBlock(String blockId, String data) {

	}

	public String getBlock(String blockName) {

	}

	public void printStoredBlocks() {
		System.out.println("DataNode " + id + " stored blocks:");
		if (storedBlocks.isEmpty()) {
			System.out.println("  (no blocks stored)");
		}
		for (Map.Entry<String, String> entry : storedBlocks.entrySet()) {
			System.out.println("  - " + entry.getKey() + " = " + entry.getValue());
		}
	}
}
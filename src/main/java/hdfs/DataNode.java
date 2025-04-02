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
		storedBlocks.put(blockId, data);
		System.out.println("DataNode " + id + ": 저장된 블록 - " + blockId + " = " + data);
	}

	public String getBlock(String blockName) {
		if (storedBlocks.containsKey(blockName)) {
			System.out.println("[" + id + "] Reading block: " + blockName);
		}
		return storedBlocks.get(blockName);
	}
}
package hdfs;

import java.util.*;

class NameNode {
	private final Map<String, List<DataNode>> fileLocations = new HashMap<>();

	public List<DataNode> chooseDataNodes(int count, List<DataNode> allDataNodes) {
		List<DataNode> selected = new ArrayList<>();
		Random random = new Random();

		while (selected.size() < count && selected.size() < allDataNodes.size()) {
			DataNode candidate = allDataNodes.get(random.nextInt(allDataNodes.size()));
			if (!selected.contains(candidate)) {
				selected.add(candidate);
			}
		}

		return selected;
	}

	public void saveFileMetadata(String filename, List<DataNode> nodes) {
		fileLocations.put(filename, nodes);
	}

	public List<DataNode> getFileLocations(String filename) {
		return fileLocations.getOrDefault(filename, new ArrayList<>());
	}
}
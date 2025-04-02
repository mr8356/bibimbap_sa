package hdfs;

import java.util.*;
// Master
class NameNode {
	private final Map<String, List<DataNode>> fileLocations = new HashMap<>();

	public List<DataNode> chooseDataNodes(int count, List<DataNode> allDataNodes) {
		List<DataNode> selected = new ArrayList<>();
		Random random = new Random();

		// 복제본 만큼 삽입

		return selected;
	}

	public void saveFileMetadata(String filename, List<DataNode> nodes) {

	}

	public List<DataNode> getFileLocations(String filename) {

	}
}
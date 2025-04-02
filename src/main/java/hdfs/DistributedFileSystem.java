package hdfs;

import java.util.ArrayList;
import java.util.List;

class DistributedFileSystem {
	private final NameNode nameNode;
	private final List<DataNode> dataNodes;

	public DistributedFileSystem(NameNode nameNode, List<DataNode> dataNodes) {
		this.nameNode = nameNode;
		this.dataNodes = dataNodes;
	}

	public void writeFile(String filename, String data) {
		// 복제본 수 지정
		int replication = 3;
		List<DataNode> selectedNodes = nameNode.chooseDataNodes(replication, dataNodes);

		for (DataNode dn : selectedNodes) {
			dn.storeBlock(filename, data);
		}

		nameNode.saveFileMetadata(filename, selectedNodes);
	}

	public String readFile(String filename) {
		List<DataNode> locations = nameNode.getFileLocations(filename);
		for (DataNode dn : locations) {
			String data = dn.getBlock(filename);
			if (data != null) return data;
		}
		return "[FILE_NOT_FOUND]";
	}
}
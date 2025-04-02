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

		// 선택한 노드에 저장 및 메타데이터 저장
	}

	public String readFile(String filename) {
		List<DataNode> locations = nameNode.getFileLocations(filename);

		// 메타데이터(locations)에서 data 하나라도 찾으면 반환

		return "[FILE_NOT_FOUND]"; // 찾지 못한 경우
	}
}
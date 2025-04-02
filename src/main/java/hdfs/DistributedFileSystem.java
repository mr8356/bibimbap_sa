package hdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DistributedFileSystem {
	private final NameNode nameNode;
	private final List<DataNode> dataNodes;

	public DistributedFileSystem(int numberOfDataNodes) {
		// NameNode 초기화
		this.nameNode = new NameNode();

		// DataNode 리스트 초기화
		List<DataNode> nodeList = new ArrayList<>();
		for (int i = 1; i <= numberOfDataNodes; i++) {
			nodeList.add(new DataNode("DN" + i));
		}
		this.dataNodes = nodeList;
	}

	public void writeFile(String filename, String data) {
		List<String> blocks = splitIntoBlocks(data, 8); // 블록 크기 8자
		List<List<DataNode>> pipelines = nameNode.assignDataNodes(blocks.size(), dataNodes);

		for (int i = 0; i < blocks.size(); i++) {
			String blockData = blocks.get(i);
			List<DataNode> pipeline = pipelines.get(i);
			for (DataNode dn : pipeline) {
				dn.storeBlock(filename + "_block_" + i, blockData);
			}
		}

		nameNode.saveFileMetadata(filename, blocks.size());
	}

	private List<String> splitIntoBlocks(String data, int blockSize) {
		List<String> blocks = new ArrayList<>();
		int len = data.length();
		for (int i = 0; i < len; i += blockSize) {
			blocks.add(data.substring(i, Math.min(len, i + blockSize)));
		}
		return blocks;
	}

	public String readFile(String filename) {
		List<String> resultBlocks = new ArrayList<>();
		int blockCount = nameNode.getBlockCount(filename);

		for (int i = 0; i < blockCount; i++) {
			String blockName = filename + "_block_" + i;
			boolean found = false;

			for (DataNode dn : dataNodes) {
				String blockData = dn.getBlock(blockName);
				if (blockData != null) {
					resultBlocks.add(blockData);
					found = true;
					break;
				}
			}

			if (!found) {
				resultBlocks.add("[MISSING_BLOCK_" + i + "]");
			}
		}

		return String.join("", resultBlocks);
	}
}

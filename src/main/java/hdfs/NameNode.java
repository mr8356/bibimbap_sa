package hdfs;
import java.util.*;

// Master
class NameNode {
	private final Map<String, Integer> fileMetadata = new HashMap<>();

	public List<List<DataNode>> assignDataNodes(int numBlocks, List<DataNode> allDataNodes) {
		List<List<DataNode>> pipelines = new ArrayList<>();
		Random rand = new Random();
		int replication = 3;

		for (int i = 0; i < numBlocks; i++) {
			List<DataNode> selected = new ArrayList<>();
			while (selected.size() < replication) {
				DataNode dn = allDataNodes.get(rand.nextInt(allDataNodes.size()));
				if (!selected.contains(dn)) selected.add(dn);
			}
			pipelines.add(selected);
		}

		return pipelines;
	}

	public void saveFileMetadata(String filename, int numBlocks) {
		fileMetadata.put(filename, numBlocks);
		System.out.println("NameNode: 저장된 파일 메타데이터 - " + filename + " (" + numBlocks + " blocks)");
	}

	// 블록 개수 조회
	public int getBlockCount(String filename) {
		return fileMetadata.getOrDefault(filename, 0);
	}

}
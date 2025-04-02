package hdfs;

import java.util.*;

public class HDFS_Mock_Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("== HDFS Client Simulation ==");

		// DataNode 수 설정
		System.out.print("Enter number of DataNodes: ");
		int numDataNodes = scanner.nextInt();
		scanner.nextLine(); // consume newline

		// 구성 요소 초기화
		NameNode nameNode = new NameNode();
		List<DataNode> dataNodes = new ArrayList<>();
		for (int i = 1; i <= numDataNodes; i++) {
			dataNodes.add(new DataNode("DN" + i));
		}
		DistributedFileSystem dfs = new DistributedFileSystem(nameNode, dataNodes);
		HDFSClient client = new HDFSClient(dfs);

		while (true) {
			System.out.println("\n[1] Write File  [2] Read File  [3] Show DataNode Status  [0] Exit");
			System.out.print("Select: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			if (choice == 0) break;

			switch (choice) {
				case 1:
					System.out.print("Filename: ");
					String filename = scanner.nextLine();
					System.out.print("Content to write: ");
					String content = scanner.nextLine();
					client.write(filename, content);
					break;
				case 2:
					System.out.print("Filename to read: ");
					String readFile = scanner.nextLine();
					String readData = client.read(readFile);
					System.out.println("Read content:\n" + readData);
					break;
				case 3:
					System.out.println("=== DataNode Block Status ===");
					for (DataNode dn : dataNodes) {
						dn.printStoredBlocks();
					}
					break;
				default:
					System.out.println("Invalid choice.");
			}
		}

		scanner.close();
		System.out.println("Exiting HDFS simulation.");
	}
}
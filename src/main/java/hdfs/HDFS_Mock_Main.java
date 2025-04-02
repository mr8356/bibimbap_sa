package hdfs;

import java.util.Scanner;

public class HDFS_Mock_Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Number of slaves(DataNodes): ");
		int numberOfDataNodes = scanner.nextInt();
		DistributedFileSystem dfs = new DistributedFileSystem(numberOfDataNodes);
		HDFSClient client = new HDFSClient(dfs);

		System.out.println("== HDFS Client Simulation ==");
		while (true) {
			System.out.println("\n[1] Write File  [2] Read File  [0] Exit");
			System.out.print("Select: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			if (choice == 0) break;

			System.out.print("Filename: ");
			String filename = scanner.nextLine();

			switch (choice) {
				case 1:
					System.out.print("Content to write: ");
					String content = scanner.nextLine();
					client.write(filename, content);
					break;
				case 2:
					String readData = client.read(filename);
					System.out.println("Read content:\n" + readData);
					break;
				default:
					System.out.println("Invalid choice.");
			}
		}

		scanner.close();
		System.out.println("Exiting HDFS simulation.");
	}
}
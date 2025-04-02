package hdfs;

class HDFSClient {
	private final DistributedFileSystem dfs;

	public HDFSClient(DistributedFileSystem dfs) {
		this.dfs = dfs;
	}

	public void write(String filename, String data) {
		dfs.writeFile(filename, data);
	}

	public String read(String filename) {
		return dfs.readFile(filename);
	}
}

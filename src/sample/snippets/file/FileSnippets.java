package sample.snippets.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileSnippets {

	private static final String SAMPLE_FILE_NAME = "temp/sample.txt";

	public static void main(String[] args) {
		try {
			deleteFileExample(SAMPLE_FILE_NAME);
			createFileExample(SAMPLE_FILE_NAME);
			writeFileByFileWriter(SAMPLE_FILE_NAME, true);
			readFileByFileReader(SAMPLE_FILE_NAME);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}

	private static void createFileExample(String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	private static void deleteFileExample(String fileName) {
		File file = new File(fileName);
		file.delete();
	}

	private static void writeFileByFileWriter(String fileName, boolean enableAppend) throws IOException {
		File file = new File(fileName);
		FileWriter fileWriter = new FileWriter(file, enableAppend);
		fileWriter.append("\r\n newdata \r\n");
		fileWriter.append("new line 1\n");
		fileWriter.append("new line 2\r");
		fileWriter.append("new line 3\n\r");
		fileWriter.append("new line 4\r\n");
		fileWriter.close();
	}

	private static void readFileByFileReader(String fileName) throws IOException {
		int storeOffset = 0;
		int bufferSize = 3;
		char[] buffer = new char[bufferSize];
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		
		int dataReaded = -1;
		do {
			dataReaded = fileReader.read(buffer, storeOffset, bufferSize);
			if (dataReaded == bufferSize) {
				System.out.print(buffer);
			} else if (dataReaded > -1) {
				char[] lastBuffer = Arrays.copyOf(buffer, dataReaded);
				System.out.print(lastBuffer);

			}
		} while (dataReaded == bufferSize);
		fileReader.close();
	}

}

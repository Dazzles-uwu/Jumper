import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FileIO {

    private String fileName;

    public FileIO()
    {

    }

    public FileIO(String name)
    {
        this.fileName = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String readFile()
    {
        String fileContent = "";
        try
        {
            FileReader reader = new FileReader(this.fileName);

            Scanner fileInput = new Scanner(reader);
            while (fileInput.hasNextLine())
            {
                fileContent += fileInput.nextLine() + "\n";
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in reading from file! Exiting...");
        }
        return fileContent;
    }

    public void writeFile(String line)
    {
        try
        {
            FileWriter writer = new FileWriter(this.fileName);
            writer.write(line);
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Error in writing to file! Exiting...");
        }
    }
}
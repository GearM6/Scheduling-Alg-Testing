import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestCaseGenerator {
    public static void Generate(int cases) throws IOException {
        File outputFile = new File("./" + cases + "_jobs.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for(int i  = 0; i < cases; i++){
            writer.write("Job " + (i+1) + "\n");
            Random time = new Random();
            writer.write(time.nextInt(500) + "\n");
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        Generate(15);
    }
}

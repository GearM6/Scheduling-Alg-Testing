import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ShortestJobFirst {

    public static long SJF(File file) throws IOException, InterruptedException {
        int jobCount = 0;
        System.out.println("RUNNING SHORTEST JOB FIRST");
        PriorityQueue<Job> jobPriorityQueue = new PriorityQueue<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while((line = reader.readLine()) != null){
            int time = Integer.parseInt(reader.readLine());
            Job job = new Job(time, line);
            jobPriorityQueue.add(job);
            ++jobCount;
        }
        long totalTime = 0;
        long start = System.currentTimeMillis();
        while(!jobPriorityQueue.isEmpty()){
            Job newestJob = jobPriorityQueue.remove();
            Thread.sleep(newestJob.getTime());
            System.out.println(newestJob.getName() + " has finished.");
            long end = System.currentTimeMillis();
            totalTime += (end - start);
        }
        System.out.println("Runtime: " + totalTime/jobCount);
        return totalTime/jobCount;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        File tests5 = new File("5_jobs.txt");
        File tests10 = new File("10_jobs.txt");
        File tests15 = new File("15_jobs.txt");

        ArrayList<File> testList = new ArrayList<>();
        File testResults = new File("./SJF_TestResults.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(testResults));

        testList.add(tests5);
        testList.add(tests10);
        testList.add(tests15);
        int n = 5;
        int totalAvgTime = 0;

        for(int i = 0; i < testList.size(); i++){
            writer.write("SHORTEST JOB FIRST TRIALS (n=" + n +")\n");
            for(int j = 0; j < 20; j++){
                long time = SJF(testList.get(i));
                totalAvgTime += time;
                writer.write("Trial " + (j+1) + " Average Turnaround Time: " + time + "ns\n");
            }
            writer.write("n=" + n +" Average Turnaround Time: " + totalAvgTime/20 + "\n");
            totalAvgTime = 0;
            n+=5;
        }
        writer.close();
        System.out.println("DONE");
    }
}

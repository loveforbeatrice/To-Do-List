package DataAccess;
import Business.*;
import java.io.*;
import java.util.ArrayList;

public class TxtFileHandle {
    public void writeToFile(String fileName, String content, boolean append){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public TxtFileHandle() {
    }

    public ArrayList<Job> readJobsFromFile(String fileName) {
        ArrayList<Job> jobs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine(); // manually read |ID| Status | Business.Job Name
            reader.readLine(); // manually read  ----------------------------------------------

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 2);
                if (parts.length >= 2) {
                    int id = Integer.parseInt(parts[0].trim());
                    String[] statusAndName = parts[1].trim().split("\\s+", 2);
                    if (statusAndName.length >= 2) {
                        String statusStr = statusAndName[0].trim();
                        JobStatus status = statusStr.equals("✔") ? JobStatus.COMPLETED : JobStatus.NOT_COMPLETED;
                        String jobName = statusAndName[1].trim();
                        Job job = new Job(id, jobName, status);
                        jobs.add(job);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return jobs;
    }

    public String readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return content.toString();
    }
}

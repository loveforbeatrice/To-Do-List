package Business;

import DataAccess.TxtFileHandle;

import java.util.ArrayList;
import java.util.Scanner;
public class ToDoList {

    ArrayList<Job> jobList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private final TxtFileHandle fileHandle = new TxtFileHandle();

    public void jobListRecognizer(){
        jobList.addAll(fileHandle.readJobsFromFile("src/DataAccess/todolist.txt"));
    }



    public void startToDoList() {

        jobListRecognizer();
        int condNumber = 0;
        while (condNumber != 1) {
            System.out.println();
            System.out.println("              MENU   ");
            System.out.println("---------------------------------");
            System.out.println("1: Add Business.Job");
            System.out.println("2: Delete Business.Job");
            System.out.println("3: Change Status of a Business.Job");
            System.out.println("4: Quit");
            System.out.println("---------------------------------");
            System.out.println();

            try {
                visualize(jobList);
                System.out.println();
                int option = readAndValidateInput();
                switch (option) {
                    case 1:
                        createJob();
                        break;
                    case 2:
                        deleteJob();
                        break;
                    case 3:
                        changeStatus();
                        break;
                    case 4:
                        condNumber = 1;
                        System.out.println("Goodbye!");
                        System.exit(23);
                        break;

                    default:
                        throw new InvalidOptionException();
                }
            } catch (InvalidOptionException e) {
                System.out.println("Invalid Input! Enter a valid option!");
            }
        }
    }

    private int readAndValidateInput() throws InvalidOptionException {
        System.out.println("Choose an option:");
        String input = scanner.nextLine().trim();
        try {
            int option = Integer.parseInt(input);
            return option;
        } catch (NumberFormatException e) {
            throw new InvalidOptionException();
        }
    }


    public void createJob(){
        Scanner scanner = new Scanner(System.in);
        int id = jobList.size()+1;
        System.out.println("Job Name : ");
        String name = scanner.nextLine();
        Job job = new Job(id,name, JobStatus.NOT_COMPLETED);
        jobList.add(job);
        System.out.println("New Job Added!");
        System.out.println();

    }

    public void deleteJob(){
        Job jobToDelete = idFinder();
        jobList.remove(jobToDelete);
        System.out.println("Job Deleted!");
        System.out.println();
        idUpdater(jobList,jobToDelete.getJobID());

    }

    public void changeStatus(){
        Job jobToChange = idFinder();
        if (jobToChange.getStatus()==JobStatus.NOT_COMPLETED){
            jobToChange.setStatus(JobStatus.COMPLETED);
        }else{
            jobToChange.setStatus(JobStatus.NOT_COMPLETED);
        }
        System.out.println("Changing Status is successful");
        System.out.println();
    }
    public Job idFinder() {
        while (true) {
            try {
                System.out.println("[Enter 0 for cancel]Please enter the ID of the job you want to operate on: ");
                int expectedId = Integer.parseInt(scanner.nextLine().trim());
                if (expectedId==0){
                    System.out.println("Operation canceled");
                    startToDoList();
                }
                for (Job job : jobList) {
                    if (job.getJobID() == expectedId) {
                        return job;
                    }
                }
                System.out.println("No job found with ID " + expectedId + ". Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid ID (a number).");
            }
        }
    }

    public void idUpdater(ArrayList<Job> jobList, int id){
        for (Job job : jobList) {
            if (job.getJobID() > id) {
                job.setJobID(job.getJobID()-1);
            }
        }
    }

    public void visualize(ArrayList<Job> jobList){
        TxtFileHandle file = new TxtFileHandle();
        StringBuilder content = new StringBuilder();
        System.out.println("|ID| Status | Job Name");
        content.append("|ID| Status | Job Name");
        content.append("\n----------------------------------------------\n");
        for (Job job : jobList) {
            JobStatus status = job.getStatus();
            int id = job.getJobID();
            String jobName = job.getJobName();
            String message1 = "  "+id +  "     " + status + "      " + jobName;
            String lines = "----------------------------------------------";
            System.out.println(message1);
            System.out.println(lines);
            content.append(message1).append("\n").append(lines).append("\n");
        }

        file.writeToFile("src\\DataAccess\\todolist.txt", content.toString(), false);
    }


}

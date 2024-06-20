package Business;

public class Job {
    private int JobID;
    private String JobName;
    private JobStatus status;

    public void setJobName(String name){
        JobName = name;
    }

    public Job(int jobID, String jobName, JobStatus status) {
        JobID = jobID;
        JobName = jobName;
        this.status = status;
    }

    @Override
    public String toString() {
        return   "status= " + status.toString()+" "+
                " JobID= " + JobID +
                ", JobName= '" + JobName + '\''

                ;
    }

    public int getJobID() {
        return JobID;
    }

    public void setJobID(int jobID) {
        JobID = jobID;
    }

    public String getJobName() {
        return JobName;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}

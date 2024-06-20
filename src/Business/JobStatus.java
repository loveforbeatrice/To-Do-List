package Business;

public enum JobStatus {
    COMPLETED("✔ "),NOT_COMPLETED("o");


    JobStatus(String s) {
    }

    @Override
    public String toString() {
        if(this == COMPLETED){
            return "✔";
        }else{
            return "o";
        }
    }
}

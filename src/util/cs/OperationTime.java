package util.cs;

public class OperationTime {

    private long startTime, endTime;
    private int seconds, miliseconds;
    private boolean hasStartTime, hasEndTime, isDone = false;

    public OperationTime() {

    }

    public void start() {
        if(hasStartTime) return;
        startTime = System.nanoTime();
        hasStartTime = true;
    }

    public void stop() {
        if(hasEndTime || !hasStartTime) return;
        endTime = System.nanoTime();
        hasEndTime = true;
        isDone = true;
        seconds = (int) (endTime-startTime)/(1000000000);
        miliseconds = (int) (endTime - startTime)/(1000000);
    }

    /**
     * Returns the latest start-stop nano time
     */
    public long getNano() {
        if(isDone) {
            reset();
            return endTime - startTime;
        } else return -1;
    }

    /**
     * Returns the latest start-stop
     */
    public int getMilliseconds() {
        if(isDone) {
            reset();
            return miliseconds;
        } else return -1;
    }

    public void verslag(String name) {
        if(isDone && hasEndTime && hasStartTime)
            System.out.println(name + " duurde " + getNano());
    }

    public void reset() {
        hasStartTime = hasEndTime = isDone = false;
    }

}

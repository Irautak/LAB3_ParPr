import java.io.Serializable;

public class FlightInfoSer implements Serializable {
    private long delayTime;
    private long cancelled;
    public FlightInfoSer() {}

    public FlightInfoSer(long delayTime, long cancelled) {
        this.delayTime = delayTime;
        this.cancelled = cancelled;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public long getCancelled() {
        return cancelled;
    }
}

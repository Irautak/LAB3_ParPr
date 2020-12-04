public class FlightTextObj {
    final String[] strings;
    private static int ORIGIN_ID = 11;
    private static int DEST_ID = 14;
    private static int DELAY_TIME = 18;
    private static int CANCELLED = 19;
    static private String COMMA = ",";
    static private String QUOT = "\"";
    static private String EMPTY_STR = "";

    private FlightTextObj(String line, String delim) {
        this.strings = line.split(delim);
    }

    static FlightTextObj FlightTextObj(String line) {
        return new FlightTextObj(line, COMMA);
    }

    public long getOriginID() {
        return Long.parseLong(removeQuot(strings[ORIGIN_ID]));
    }

    public long getDestID() {
        return Long.parseLong(removeQuot(strings[DEST_ID]));
    }

    public long getDelayTime() {
        if (!strings[DELAY_TIME].equals("")) {
            return (long)Double.parseDouble(removeQuot(strings[DELAY_TIME]));
        }
        return 0;
    }

    public long getCancelled() {
        if (!strings[CANCELLED].isEmpty()) {
            return (long)Double.parseDouble(removeQuot(strings[CANCELLED]));
        }
        return 0;
    }

    private String removeQuot(String str) {
        return str.replace(QUOT, EMPTY_STR);
    }

}

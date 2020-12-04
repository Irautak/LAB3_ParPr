public class AirportTextObj {
    final String[] strings;
    static private int AIRPORT_ID = 0;
    static private int AIRPORT_NAME = 1;
    static private String COMMA_QUOT = ",[\"]";
    static private String QUOT = "\"";
    static private String EMPTY_STR = "";
    private AirportTextObj(String line, String delim) {
        this.strings = line.split(delim);
    }

    static AirportTextObj AirportTextObj(String line) {
        return new AirportTextObj(line, COMMA_QUOT);
    }

    public long getID() {
        return Long.parseLong(removeQuot(strings[AIRPORT_ID]));
    }

    public String getName() {
        return removeQuot(strings[AIRPORT_NAME]);
    }

    private String removeQuot(String str) {
        return str.replace(QUOT, EMPTY_STR);
    }

}

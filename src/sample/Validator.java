package sample;

import javafx.css.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private Map<String, Function<String, Pattern>> functionalMap;
    private static final String NATURAL_REGEX_STRING = "([0]{1})|([1-9]\\d*)";
    private static final String INTEGER_REGEX_STRING = "((-?)[1-9]\\d*)|([0]{1})";
    private static final String FLOAT_REGEX_STRING = "[+-]?\\d*(([.]?\\d+)?)(([Ee][+-]?\\d*)?)";
    private static final String DATE_REGEX_STRING = "(([0-9]{4})[-./]((1[012])|(0[1-9]))[-./]((0[1-9])|(1[0-9])|(2[0-9])|3[01]))|" +
            "(((1[012])|(0[1-9]))[-./]((0[1-9])|(1[0-9])|(2[0-9])|3[01])[-./]([0-9]{4}))|" +
            "(((0[1-9])|(1[0-9])|(2[0-9])|3[01])[-./]((1[012])|(0[1-9]))[-./]([0-9]{4}))"; // YYYY:MM:DD ; MM:DD:YYYY, DD:MM:YYYY
    private static final String TIME_REGEX_STRING
            = "(([01][0-9])|(2[0-3]))[:]([0-5][\\d{1}])[:]([0-5][\\d{1}])"; //HH:MI:SS
    private static final String EMAIL_REGEX_STRING = "([a-zA-Z0-9_.]{5,})@([a-zA-Z]{3,6})[.]([a-z]{2,3})";

    private Matcher matcher;
    public Validator() {
        functionalMap = new HashMap<>();
        functionalMap.put("natural", (x) -> Pattern.compile(NATURAL_REGEX_STRING));
        functionalMap.put("integer", (x) -> Pattern.compile(INTEGER_REGEX_STRING));
        functionalMap.put("float", (x) -> Pattern.compile(FLOAT_REGEX_STRING));
        functionalMap.put("date", (x) -> Pattern.compile(DATE_REGEX_STRING));
        functionalMap.put("time", (x) -> Pattern.compile(TIME_REGEX_STRING));
        functionalMap.put("e-mail", (x) -> Pattern.compile(EMAIL_REGEX_STRING));
    }

    public boolean isValid(String requestString, String mode) throws IllegalArgumentException {
        if (!functionalMap.containsKey(mode.toLowerCase()))
            throw new IllegalArgumentException("No such mode found");

        return functionalMap.get(mode.toLowerCase()).apply(requestString).matcher(requestString).matches();
    }
}

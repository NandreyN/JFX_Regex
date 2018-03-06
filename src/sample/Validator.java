package sample;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Validator {
    private Map<String, Function<String, Pattern>> functionalMap;
    private static final String NATURAL_REGEX_STRING = "[+]?(\\d*[1-9]\\d*)";
    private static final String INTEGER_REGEX_STRING = "(([-+]?)\\d+)";
    private static final String FLOAT_REGEX_STRING = "([+-]?(((\\d*)[.]?(\\d+))|([.]\\d+)|(\\d+[.])))([Ee][+-]?(\\d+))?";
    //float d = .4654;
    //int b = -0000564654;
    private static final String DAYS = "((0[1-9])|(1[0-9])|(2[0-9])|3[01])";
    private static final String MONTHS = "((1[012])|(0[1-9]))";
    private static final String YEARS = "([0-9]{4})";
    private static final String SEP = "[.]";
    private static final String OR = "|";

    private static final String TILL_30 = "((0[1-9])|(1[0-9])|(2[0-9])|30)";
    private static final String TILL_31 = "((0[1-9])|(1[0-9])|(2[0-9])|3[01])";
    private static final String TILL_29 = "((0[1-9])|(1[0-9])|(2[0-9]))";


    private static final String DATE_REGEX_STRING = "((" + "((01)|(03)|(05)|(07)|(08)|(10)|(12))" + SEP + TILL_31 + ")" + OR + "(02" + SEP + TILL_29 + ")" + OR +
            "(" + "((04)|(06)|(09)|(11))" + SEP + TILL_30 + "))" + SEP + YEARS;
    //MM:DD:YYYY

    private static final String TIME_REGEX_STRING
            = "(([01][0-9])|(2[0-3]))[:]([0-5][0-9])[:]([0-5][0-9])"; //HH:MI:SS
    private static final String EMAIL_REGEX_STRING = "([a-zA-Z0-9_]([.]?)([a-zA-Z0-9_]([.]?))*[a-zA-Z0-9])@([a-zA-Z]{3,6})[.]([a-z]{2,3})";

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

package MapReduce.Util.DateParse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    private static final String[] formats = {
            "dd/MM/yyyy",
            "dd-MMM-yyyy",
            "MM dd, yyyy",
            "E, dd MMMM yyyy",
            "E, dd MM yyyy",
            "E, dd MMM yyyy",
            "E, dd MMMM yy",
            "E, dd MM yy",
            "E, dd MMM yy",
            "E, dd MMMM yy",
            "E dd MMMM yyyy",
            "E dd MM yyyy",
            "E dd MMM yyyy",
            "E dd MMMM yy",
            "E dd MM yy",
            "E dd MMM yy",
            "E dd MMMM yy",
            "E, MMM dd yyyy",
            "E, MMMM dd yyyy",
            "E, MMM dd yyyy HH:mm:ss",
            "E, MMM dd yyyy HH:mm:ssa",
            "E, MMMM dd yyyy HH:mm:ss",
            "E, MMMM dd yyyy HH:mm:ssa",
            "dd-MMM-yyyy HH:mm:ss",
            "MM/dd/yyyy",
            "dd-M-yyyy hh:mm:ss",
            "dd MMMM yyyy",
            "dd MMMM yyyy zzzz",
            "E, dd MMM yyyy HH:mm:ss z",
            "E, dd MM yyyy HH:mm:ss z",
            "HH:mm:ssa, E, MMMM YYYY",
            "HH:mm:ssa, E, dd MMMM YYYY",
            "HH:mm:ssa, E, MMM YYYY",
            "HH:mm:ssa, E, dd MMM YYYY",
            "HH:mm:ss.Sa, E, MMMM YYYY",
            "HH:mm:ss.Sa, E, dd MMMM YYYY",
            "HH:mm:ss.Sa, E, MMM YYYY",
            "HH:mm:ss.Sa, E, dd MMM YYYY",
            "HH:mm:ss.SSa, E, MMMM YYYY",
            "HH:mm:ss.SSa, E, dd MMMM YYYY",
            "HH:mm:ss.SSa, E, MMM YYYY",
            "HH:mm:ss.SSa, E, dd MMM YYYY",
            "HH:mm:ss.SSSa, E, MMMM YYYY",
            "HH:mm:ss.SSSa, E, dd MMMM YYYY",
            "HH:mm:ss.SSSa, E, MMM YYYY",
            "HH:mm:ss.SSSa, E, dd MMM YYYY",
            "HH:mm:ss.SSSSa, E, MMMM YYYY",
            "HH:mm:ss.SSSSa, E, dd MMMM YYYY",
            "HH:mm:ss.SSSSa, E, MMM YYYY",
            "HH:mm:ss.SSSSa, E, dd MMM YYYY",
            "dd-MM-yy",
            "MM-dd-yyyy",
            "yyyy-MM-dd HH:mm:ss",
            "EEEEE MMMMM yyyy HH:mm:ss.SSSZ",
    };

    public static String parse(String date, OutFormat format){

        SimpleDateFormat[] dateFormaters;
        dateFormaters = new SimpleDateFormat[formats.length];
        for (int i = 0; i < formats.length ; i++) {
            dateFormaters[i] = new SimpleDateFormat(formats[i]);
        }

        for (SimpleDateFormat sdf : dateFormaters){
            try {
                return producrFormat(sdf.parse(date),format);
            } catch (ParseException e) {
                // Ignore and try next date parser
            }
        }
        System.err.println("Error converting string value " + date + " into data type date");
        System.exit(0);
        return "";
    }

    public static String parse(String date, OutFormat format,String culture){

        SimpleDateFormat[] dateFormaters;
        dateFormaters = new SimpleDateFormat[formats.length];
        for (int i = 0; i < formats.length ; i++) {
            dateFormaters[i] = new SimpleDateFormat(formats[i], Locale.forLanguageTag(culture));
        }

        for (SimpleDateFormat sdf : dateFormaters){
            try {
                return producrFormat(sdf.parse(date),format,culture);
            } catch (ParseException e) {
                // Ignore and try next date parser
            }
        }
        System.err.println("Error converting string value " + date + " into data type date");
        System.exit(0);
        return "";
    }

    private static String producrFormat (Date input,OutFormat format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        switch (format){
            case date:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case datetime:
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                break;
            case datetime2:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                break;
            case smalldatetime:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case time:
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss.S");
                break;
        }
        return simpleDateFormat.format(input);
    }
    private static String producrFormat (Date input,OutFormat format,String culture){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        switch (format){
            case date:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.forLanguageTag(culture));
                break;
            case datetime:
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S",Locale.forLanguageTag(culture));
                break;
            case datetime2:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S",Locale.forLanguageTag(culture));
                break;
            case smalldatetime:
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.forLanguageTag(culture));
                break;
            case time:
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss.S",Locale.forLanguageTag(culture));
                break;
        }
        return simpleDateFormat.format(input);
    }

}

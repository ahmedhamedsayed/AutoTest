package util;

import java.util.List;

public class Converter {

    private static Converter converter;

    public static synchronized Converter getInstance() {
        if (converter == null)
            return converter = new Converter();
        return converter;
    }

    private String convertCountToTimerFormat(int number) {
        return ((number <= 9) ? "0" : "") + String.valueOf(number);
    }

    public String convertToTimerFormat(int hours, int minutes, int seconds) {
        return convertCountToTimerFormat(hours) + ":" + convertCountToTimerFormat(minutes) + ":" + convertCountToTimerFormat(seconds);
    }

    public String[] convertFromListToArray(List<String> list) {
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); ++i)
            strings[i] = list.get(i);
        return strings;
    }
    
	public String terminate(String current, int size) {
		String result = current.substring(0, Math.min(current.length(), size));
		while (result.length() < size)
			result += " ";
		return result;
	}
}

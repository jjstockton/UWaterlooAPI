package UWaterloo;

final class StringUtils {
    static String toCamelCase(String input) {

        StringBuilder str = new StringBuilder(input);

        while (str.toString().contains("_")) {

            str.setCharAt(str.indexOf("_") + 1, Character.toUpperCase(str.charAt(str.indexOf("_") + 1)));
            str.deleteCharAt(str.indexOf("_"));
        }
        return str.toString();
    }
}

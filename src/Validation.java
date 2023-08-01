public class Validation {

    public Validation()
    {

    }

    public static boolean isBlank(String input)
    {
        return input.equals("");
    }

    public static boolean lengthWithinRange(String input, int min, int max)
    {
        return input.length() >= min && input.length() <= max;
    }
}

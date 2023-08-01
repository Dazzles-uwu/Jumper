import java.util.Scanner;

public class Input {

    public Input()
    {

    }

    public static char acceptCharInput(String message, int index)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println(message);
        return keyboard.nextLine().charAt(index);
    }

    public static int acceptIntegerInput(String message)
    {
        int integerInput = 0;
        Scanner keyboard = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput)
        {
            try
            {
                System.out.println(message);
                integerInput = Integer.parseInt(keyboard.nextLine());
                validInput = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid" + e);
            }
        }
        return integerInput;
    }

    public String acceptStringInput(String message)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println(message);
        return keyboard.nextLine();
    }

    public static String playerNameInput(String message)
    {
        Scanner keyboard = new Scanner(System.in);
        boolean validName = false;
        String name = "";

        while (!validName)
        {
            System.out.println(message);
            name = keyboard.nextLine();

            if (!Validation.isBlank(name) && Validation.lengthWithinRange(name, 3, 12))
            {
                validName = true;
            }
        }

        return name;
    }
}

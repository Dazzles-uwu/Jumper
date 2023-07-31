import java.util.Scanner;

public class Input {

    public Input()
    {

    }

    public char acceptCharInput(String message, int index)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println(message);
        return keyboard.nextLine().charAt(index);
    }

    public int acceptIntegerInput(String message)
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
}

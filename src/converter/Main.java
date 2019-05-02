package converter;

import converter.excpetions.WrongRadixException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the radix from which you want to convert:");
        try {
            int inputRadix = reader.nextInt();
            if (inputRadix < 1 || inputRadix > 36) {
                throw new WrongRadixException();
            }
            System.out.println("Enter the number to convert");
            String inputValue = reader.next();
            System.out.println("Enter the radix you want to convert to:");
            int outputRadix = reader.nextInt();
            if (outputRadix < 1 || outputRadix > 36) {
                throw new WrongRadixException("radix < 1 or radix > 36");
            }
            System.out.println("The result is " + Converter.convertNumber(inputValue, inputRadix, outputRadix));
        } catch (InputMismatchException e) {
            System.out.println("Error: a number was expected");
        } catch (WrongRadixException e) {
            System.out.println("Error: the radix has to be between 1 and 36");
        } catch (NumberFormatException e) {
            System.out.println("Error: number is impossible");
        } catch (NoSuchElementException e) {
            System.out.println("Error: empty input");
        }
    }
}



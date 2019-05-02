package converter;

public class Converter {

    private Converter(){}

    public static String convertNumber(String inputValue, Integer inputRadix, Integer outputRadix) {
        if (inputValue.contains(".")) {
            if (inputValue.split("\\.")[1].equals("0")) {
                return convertInteger(inputValue.split("\\.")[0], inputRadix, outputRadix) + ".00000";
            }
            return convertDouble(inputValue, inputRadix, outputRadix);
        } else return convertInteger(inputValue, inputRadix, outputRadix);
    }

    /**
     * Method returns fractional part of a number (the one that is after ".") converted to a different radix,
     * so you can convert parts before "." and after it independently
     */
    private static String convertFractionPart(String inputValue, Integer inputRadix, Integer outputRadix) {
        Double inputNumber = 0d;
        int counter = 1;
        for (char ch : inputValue.toCharArray()) {
            inputNumber += (double) Long.parseLong(String.valueOf(ch), inputRadix) / Math.pow(inputRadix, counter);
            counter++;
        }
        String outputNumber = "";
        for (int i = 0; i < 5; i++) {
            inputNumber *= outputRadix;
            if (inputNumber >= 1) {
                outputNumber += Long.toString(inputNumber.intValue(), outputRadix);
                inputNumber -= (double) inputNumber.intValue();
            } else outputNumber += 0;
        }
        return outputNumber;
    }


    private static String convertDouble(String inputValue, Integer inputRadix, Integer outputRadix) {
        if (outputRadix == 1) {
            return convertInteger(inputValue.split("\\.")[0], inputRadix, outputRadix);
        }
        String[] parts = inputValue.split("\\.");
        String fractionPart = convertFractionPart(parts[1], inputRadix, outputRadix);
        if (!fractionPart.equals("")) {
            return convertInteger(parts[0], inputRadix, outputRadix) + "." + fractionPart;
        } else return convertInteger(parts[0], inputRadix, outputRadix);
    }

    private static String convertInteger(String inputValue, Integer inputRadix, Integer outputRadix) {
        if (outputRadix >= 2 && outputRadix <= 36) {
            if (inputRadix >= 2 && inputRadix <= 36) {                      //both input and output radixes are not 1
                Long inputNumber = Long.parseLong(inputValue, inputRadix);
                return Long.toString(inputNumber, outputRadix);
            } else {                                                        //only input radix is 1
                int inputNumber = 0;
                for (char c :
                        inputValue.toCharArray()) {
                    inputNumber++;
                }
                return Long.toString(inputNumber, outputRadix);

            }
        } else {
            if (inputRadix >= 2 && inputRadix <= 36) {                      //only output radix is 1
                String output = "";
                for (int i = 0; i < Long.parseLong(inputValue, inputRadix); i++) {
                    output += "1";
                }
                return output;
            } else {
                return inputValue;
            }
        }
    }
}

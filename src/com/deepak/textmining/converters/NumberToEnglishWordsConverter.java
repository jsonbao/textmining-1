package com.deepak.textmining.converters;

import java.text.DecimalFormat;

public class NumberToEnglishWordsConverter {

    private final String[] tensInWords = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy",
            " eighty", " ninety" };

    private final String[] numbersInWords = { "", " one", " two", " three", " four", " five", " six", " seven",
            " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
            " seventeen", " eighteen", " nineteen" };

    private String convertLessThanOneThousand(int number) {
        String numberInWords;
        if (number % 100 < 20) {
            numberInWords = numbersInWords[number % 100];
            number /= 100;
        } else {
            numberInWords = numbersInWords[number % 10];
            number /= 10;

            numberInWords = tensInWords[number % 10] + numberInWords;
            number /= 10;
        }
        if (number == 0)
            return numberInWords;
        return numbersInWords[number] + " hundred" + numberInWords;
    }

    public String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) {
            return "zero";
        }
        String stringNumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat decimalFormat = new DecimalFormat(mask);
        stringNumber = decimalFormat.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(stringNumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(stringNumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(stringNumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(stringNumber.substring(9, 12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1:
                tradBillions = convertLessThanOneThousand(billions) + " billion ";
                break;
            default:
                tradBillions = convertLessThanOneThousand(billions) + " billion ";
        }
        String result = tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1:
                tradMillions = convertLessThanOneThousand(millions) + " million ";
                break;
            default:
                tradMillions = convertLessThanOneThousand(millions) + " million ";
        }
        result = result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "one thousand ";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " thousand ";
        }
        result = result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;
        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

}

// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.Scanner;
// More packages may be imported in the space below
import java.io.File;
import java.io.FileNotFoundException;

class CustomerSystem{
    public static void main(String[] args) {
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below


        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                enterCustomerInfo();
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }

    public static void enterCustomerInfo(){
    }

    /*
     * Description: Checks if the user-inputted postal code is valid - meaning it fulfills the length requirement & is found in CSV file
     * 
     * @author - Murphy Lee
     * @param postCode - A String containing the user-inputted postal code
     * @throws FileNotFoundException - Exception raised when attempting to read CSV, thrown to enterCustomerInfo method
     * @return isExistingZip - a boolean whose value depends on if the zip code meets the requirements
     * */
    public static boolean validatePostalCode(String postCode) throws FileNotFoundException {
        // Check if the inputted postal code is long enough - at least 3 characters
        boolean validLen = isLongEnough(postCode, 3);
        if (validLen == false) {
            return false;
        }

        // Store the first 3 digits of user-input to compare with CSV values - this is the postal code
        String zipCode = postCode.substring(0, 3);

        // Compare the 3 characters with the postal codes found in CSV file
        boolean isExistingZip = compareZip(zipCode);
        return isExistingZip;
    }

    /*
    * Description: Checks if the user-inputted credit card is valid - meaning it fulfills the length requirement and passes the Luhn Algorithm
    * 
    * @param creditNum - An int containing the user-inputted credit card number
    * return isValid - A boolean whose value depends on if the user-input meets both requirements
    */
    public static boolean validateCreditCard(String creditNum){  

        // Check if the inputted credit card number is long enough - at least 9 characters
        boolean validLen = isLongEnough(creditNum, 9);
        if (validLen == false) {
            return false;
        }

        // Determine if the Luhn sum ends with 0, using the function
        // boolean isValid = passesLuhnTest(creditNum); 

        return true;
    }

    public static void generateCustomerDataFile(){
    }
    /*******************************************************************
    *                        ADDITIONAL METHODS:                       *
    *******************************************************************/
    
    /*
     * Description: Checks if the length of the inputted string matches/exceeds the specified length
     * 
     * @author - Murphy Lee
     * @param sequence - The String that needs to be validated
     * @param length - The minimum length that the String must be
     * @return isValid - Boolean whose value depends on whether the String is long enough
     * */
    public static boolean isLongEnough(String sequence, int length) {
        boolean isValid;   // Return value depends on validation

        // Store length of sequence in an int variable
        int sequenceLen = sequence.length();

        // Set boolean to true or false after comparing the user-input length to the recommended length
        if (sequenceLen >= length) {
            isValid = true;
        }
        else {
            isValid = false;
        }
        return isValid;
    }

    /*
     * Description: Checks if the user-inputted postal code is found in CSV file
     * 
     * @author - Murphy Lee
     * @param sequence - The String that needs to be validated
     * @throws FileNotFoundException - Exception raised when attempting to read CSV, thrown to ValidatePostalCode method
     * @return foundInFile - Boolean whose value depends on whether the String is found in the CSV file
     * */
    public static boolean compareZip(String postCode) throws FileNotFoundException {
        String line;                            // Will store each line of the file
        String code;                            // Will store postal code from each line
        boolean foundInFile = false;            // Will be set to true if the code is found in CSV
        String fileName = "postal_codes.csv";   // File name of the CSV

        // Create File instance of the CSV to read from
        File text = new File(fileName);

        // Create instances of Scanner
        Scanner reader = new Scanner(text, "ISO-8859-1");   // Will read text from CSV file in a different character set, to accept accented letters
        Scanner codeReader;                   // Will store each line from the CSV file

        // Read lines until there are no more
        while (reader.hasNextLine() == true) {
            // Read the next line in the file
            line = reader.nextLine();

            // Store line into seperate scanner - this way we can use the delimeter method
            codeReader = new Scanner(line);

            // Split line by using the "|" character as a delimiter - since "|" is a special character in Java, we need to escape it by using "\\"
            codeReader.useDelimiter("\\|");
            code = codeReader.next();

            // If the user-input matches the postal code, the found variable will be set to true
            if (postCode.equals(code)) {
                foundInFile = true;
            }
        }

        reader.close();
        return foundInFile;
    }

    /*
     * Description: Reverses a user-inputted String
     * 
     * @author - Murphy Lee
     * @param num - The String that needs to be reversed
     * @return reverse - The String whose characters have been reversed
     * */
    public static String reverseString(String text) {
        String reverse = "";  // Will store the reversed number
        String digit;         // Placeholder for the digits that will be added

        // Set a for loop that processes all digits - use the string length as a boundary
        for (int i = 1; i <= text.length(); i++) {
            // Extract each character in a String variable backwards - find index by subtracting i from the length
            digit = String.valueOf(text.charAt(text.length() - i));

            // Add new character to the reversed String variable
            reverse = reverse.concat(digit);
        }
        return reverse;
    }

}
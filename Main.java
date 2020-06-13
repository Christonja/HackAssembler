import java.util.Scanner;
import java.io.*;

//Initialises the I/O files and drives the process
public class Main {
    public static void main(String[] args) throws IOException {
        //if no input file supplied print usage message to console
        if (args.length < 1) {
            System.out.println("Usage: HackAssembler.java *FILE*.asm");
        } else {
            File readFile = new File(args[0]); //Prep new file reading object to open .asm file
            Scanner readIn = new Scanner(readFile); //Prep new scanning object to read through .asm file
            String convertFile = args[0].replaceAll("asm", "hack"); //Initialise binary output file to be named the same as the input file with a .hack extension instead of .asm
            FileWriter writeFile = new FileWriter(convertFile); //Prep new file writing object to write to .hack file
            PrintWriter writeOut = new PrintWriter(writeFile); //Prep new printing object to print characters to .hack file
            String lineReader;
            String symbolReader = "";
            String buildString = "";
            Code translate = new Code(); //Construct Code object
            SymbolTable symbolicTable = new SymbolTable(); //Construct Symbol object

            //Pass through file once, grabbing all the bracketed labels to input into the hashmap
            while(readIn.hasNextLine()){
                symbolReader = readIn.nextLine();
                symbolicTable.setSymbol(symbolReader);
            }

            readIn.close();

            //Read file in again from the start
            Scanner readInAgain = new Scanner(readFile); //Prep new scanning object to read through .asm file

            //Loop through file readings and writing from assembly to binary one line at a time.
            while(readInAgain.hasNextLine()){
                //decipher assembly language
                lineReader = readInAgain.nextLine(); //Read line
                System.out.println(lineReader);
                Parser parseLineAgain = new Parser(lineReader); //Construct Parser object
                char instructionType = parseLineAgain.checkInstructionType(lineReader);
                buildString = "";
                if (instructionType == 'a'){
                    translate.setOpCode(symbolicTable.getSymbol(parseLineAgain.getAInstruction()));
                    buildString += translate.getOpCode(); 
                } else if (instructionType == 'c') {
                    buildString += "111";
                    translate.setControlBits(parseLineAgain.getCInstruction());
                    buildString += translate.getControlBits(); 
                    translate.setDestination(parseLineAgain.getDestination());
                    buildString += translate.getDestination(); 
                    translate.setJumpBits(parseLineAgain.getJump());
                    buildString += translate.getJumpBits(); 
                } else {
                    buildString += "";
                }
                //If line isn't empty, write to file
                if (!buildString.isEmpty()) {
                    writeOut.println(buildString);
                }
            }
            //Close .asm file for reading and .hack file for writing
            readInAgain.close();
            writeFile.close();
        }
    }
}
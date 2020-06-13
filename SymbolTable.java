import java.util.HashMap;

//Manages the symbol table
public class SymbolTable { 
    private HashMap<String, String> labelTable;
    private int lineCounter;
    private int memLocation;
    private String bracketedLabel;

    //Constructor 
    public SymbolTable(){
        labelTable = new HashMap<String, String>();
        lineCounter = 0;
        memLocation = 16;
        bracketedLabel = "";
        //Initialise static hashmap values
        labelTable.put("@R0", "@0");
        labelTable.put("@R1", "@1");
        labelTable.put("@R2", "@2");
        labelTable.put("@R3", "@3");
        labelTable.put("@R4", "@4");
        labelTable.put("@R5", "@5");
        labelTable.put("@R6", "@6");
        labelTable.put("@R7", "@7");
        labelTable.put("@R8", "@8");
        labelTable.put("@R9", "@9");
        labelTable.put("@R10", "@10");
        labelTable.put("@R11", "@11");
        labelTable.put("@R12", "@12");
        labelTable.put("@R13", "@13");
        labelTable.put("@R14", "@14");
        labelTable.put("@R15", "@15");
        labelTable.put("@SCREEN", "@16384");
        labelTable.put("@KBD", "@24576");
        labelTable.put("@SP", "@0");
        labelTable.put("@LCL", "@1");
        labelTable.put("@ARG", "@2");
        labelTable.put("@THIS", "@3");
        labelTable.put("@THAT", "@4");
    }
    
    public void setSymbol(String symbolReader){
        //If bracketed label, remove brackets and change to address, place in hashmap with corresponding line number.
        if (!symbolReader.isEmpty() && symbolReader.charAt(0) == '('){
            bracketedLabel = symbolReader;
            bracketedLabel = bracketedLabel.replace("(","@");
            bracketedLabel = bracketedLabel.replace(")","");
            labelTable.put(bracketedLabel, "@" + Integer.toString(lineCounter));
        //else increment linecounter, as we aren't counting '(' lines only instructions.
        } else if (!symbolReader.isEmpty() && symbolReader.charAt(0) != '/')
            lineCounter++;
    }

    public String getSymbol(String aIns){
        /*If a instruction isn't a number check if key exists, if so, get associated address, otherwise, place
        instruction into hashmap and return corresponding address value, not to be confused with linecounter 
        which counts lines for corresponding label jumps, memLocation counts from 16 as the ALU is designed as 
        such that memory location 16 and up are for variables*/
        if (!(aIns.substring(1).chars().allMatch(Character::isDigit))){
            if (labelTable.containsKey(aIns)) {
                return labelTable.get(aIns);
            } else {
                labelTable.put(aIns, '@'+Integer.toString(memLocation));
                memLocation++;
                return labelTable.get(aIns);
            }
        } else {
            return aIns;
        }
    }
}

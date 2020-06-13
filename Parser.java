//Unpacks each instruction into it's underlying fields
public class Parser {
    private String aInstruction;
    private String destination;
    private String cInstruction;
    private String jump;

    //Constructor
    public Parser(String lineReader){
        this();
        //If line isn't empty and isn't a comment construct as normal otherwise ensure they're empty strings
        if (!lineReader.isEmpty() && lineReader.charAt(0) != '/') {
            //If line has an inline comment, remove it
            if (lineReader.indexOf("//") != -1){
                lineReader=lineReader.substring(0, 18);
            }
            lineReader=lineReader.trim();
            if (lineReader.charAt(0) == '@')
                this.aInstruction = lineReader;
            else if (lineReader.indexOf('=') != -1){
                String temp[] = lineReader.split("=");
                this.destination = temp[0];
                this.cInstruction = temp[1];
            } else if (lineReader.indexOf(';') != -1){
                String temp[] = lineReader.split(";");
                this.cInstruction = temp[0];
                this.jump = temp[1];
            }
        } else {
            this.aInstruction = "";
            this.destination = "";
            this.cInstruction = "";
            this.jump = "";
        }
    }

    //Default Constructor
    public Parser(){
        this.aInstruction = "";
        this.destination = "";
        this.cInstruction = "";
        this.jump = "";
    }

    public String getAInstruction(){
        return aInstruction;
    }
    public String getDestination(){
        return destination;
    }
    public String getCInstruction(){
        return cInstruction;
    }
    public String getJump(){
        return jump;
    }
    public void setAInstruction(String aIns){
        this.aInstruction = aIns;
    }
    public void setDestination(String des){
        this.destination = des;
    }
    public void setCInstruction(String cIns){
        this.cInstruction = cIns;
    }
    public void setJump(String jmp){
        this.jump = jmp;
    }
    //If instruction type is an a instruction return 'a' if it's a 'c' instruction return 'c', if it's neither, return 'f'
    public char checkInstructionType(String lineReader){
        if (!lineReader.isEmpty() && lineReader.charAt(0) != '/' && lineReader.charAt(0) != '(') {
            lineReader=lineReader.trim();
            if (lineReader.charAt(0) == '@'){
                return 'a';
            }
            else if (lineReader.indexOf('=') != -1 || lineReader.indexOf(';') != -1)
                return 'c';
        }
        return 'f';
    }
}
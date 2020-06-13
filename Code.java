//Translates each field into its corresponding binary value
public class Code {
    private String opC;
    private String dst;
    private String alu;
    private String jmp;
    
    //Constructor, defaults everything to an empty string;
    public Code() {
        this.opC = "";
        this.dst = "";
        this.alu = "";
        this.jmp = "";
    }

    //Setter to convert a instruction into a binary string and set opC to be this value
    public void setOpCode(String aIns){
        String opCode = Integer.toBinaryString(Integer.parseInt(aIns.substring(1)));
        opC = String.format("%1$" + 16 + "s", opCode).replace(' ', '0');
    }

    //Setter to convert destination into it's corresponding binary value according to ALU Arcitecture
    public void setDestination(String dest){
        switch(dest) {
            case "M":
                this.dst = "001";
                break;
            case "D":
                this.dst = "010";
                break;
            case "MD":
                this.dst = "011";
                break;
            case "A":
                this.dst = "100";
                break;
            case "AM":
                this.dst = "101";
                break;
            case "AD":
                this.dst = "110";
                break;
            case "AMD":
                this.dst = "111";
                break;
            default:
                this.dst = "000";
        }
    }

    //Setter to convert destination into it's corresponding binary value according ALU architecture
    public void setControlBits(String ctrl){
        String buildCtrl;
        switch(ctrl) {
            case "0":
                buildCtrl = "101010";
                break;
            case "1":
                buildCtrl = "111111";
                break;
            case "-1":
                buildCtrl = "111010";
                break;
            case "D":
                buildCtrl = "001100";
                break;
            case "M":
            case "A":
                buildCtrl = "110000";
                break;
            case "!D":
                buildCtrl = "001101";
                break;
            case "!M":
            case "!A":
                buildCtrl = "110001";
                break;
            case "-D":
                buildCtrl = "001111";
                break;
            case "-M":
            case "-A":
                buildCtrl = "110011";
                break;
            case "D+1":
                buildCtrl = "011111";
                break;
            case "M+1":
            case "A+1":
                buildCtrl = "110111";
                break;
            case "D-1":
                buildCtrl = "001110";
                break;
            case "M-1":
            case "A-1":
                buildCtrl = "110010";
                break;
            case "D+M":
            case "D+A":
                buildCtrl = "000010";
                break;
            case "D-M":
            case "D-A":
                buildCtrl = "010011";
                break;
            case "M-D":
            case "A-D":
                buildCtrl = "000111";
                break;
            case "D&M":
            case "D&A":
                buildCtrl = "000000";
                break;
            case "D|M":
            case "D|A":
                buildCtrl = "010101";
                break;
            default:
                buildCtrl = "ERROR";
        }   //Once control bits figured out, determine A bit via presence of 'M' in instruction
            if(ctrl.indexOf('M') == -1)
                this.alu = '0' + buildCtrl;
            else
                this.alu = '1' + buildCtrl;
    }   
    
    //Setter to convert jump bits into corresponding binary value according ALU architecture
    public void setJumpBits(String jump){
        switch(jump) {
            case "JGT":
                this.jmp = "001";
                break;
            case "JEQ":
                this.jmp = "010";
                break;
            case "JGE":
                this.jmp = "011";
                break;
            case "JLT":
                this.jmp = "100";
                break;
            case "JNE":
                this.jmp = "101";
                break;
            case "JLE":
                this.jmp = "110";
                break;
            case "JMP":
                this.jmp = "111";
                break;
            default:
                this.jmp = "000";
        }
    }

    public String getOpCode(){
        return this.opC;
    }
    public String getDestination(){
        return this.dst;
    }
    public String getControlBits(){
        return this.alu;
    }
    public String getJumpBits(){
        return this.jmp;
    }
}


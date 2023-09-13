import java.util.ArrayList;
import java.util.Arrays;

public class Translator {
    String input;

    String modifiedText;

    public Translator(String input){
        this.input = input;
        this.modifiedText = input;
    }

    public String convertToString (ArrayList<String> input){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            output.append(input.get(i));
            output.append(" ");
        }
        this.modifiedText = output.toString();
        return this.modifiedText;
    }

    /*static String convertToString (String[] input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            output.append(input[i]);
            output.append(" ");
        }
        return output.toString();
    }*/

    public String convertToStringWithNewLines(String[] input) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            temp.append(input[i]);
            temp.append("\n");
        }
        return temp.toString();
    }

    public String convertToStringWithNewLines(ArrayList<String> input){
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            temp.append(input.get(i));
            temp.append("\n");
        }
        return temp.toString();
    }

    public void remove(String wordToBeRemoved){
        this.modifiedText = this.modifiedText.replace(wordToBeRemoved, "");
    }

    public String remove(String textToBeModified, String wordToBeRemoved){
        textToBeModified = textToBeModified.replace(wordToBeRemoved, "");
        return textToBeModified;
    }

    public void removesFinalSemicolons(){
        remove(";");
    }

    public void convertComments() {
        if (this.modifiedText.contains("//")) {
            this.modifiedText = this.modifiedText.replace("//", "#");
        }
    }

    public void convertIntegerDeclarations() {
        if (this.modifiedText.contains("int ")) {
            String[] temp2 = this.modifiedText.split("\n");
            for (int i = 0; i < temp2.length; i++) {
                if (temp2[i].contains("int ")) {
                    if (!temp2[i].contains("=")) {
                        ArrayList<String> temp3 = new ArrayList<String>();
                        temp3.add(temp2[i].split(" ")[0]);
                        temp3.add(temp2[i].split(" ")[1]);
                        temp3.add("= 0");
                        temp3.addAll(Arrays.asList(temp2[i].split(" ")).subList(2, temp2[i].split(" ").length));
                        temp2[i] = convertToString(temp3);
                        temp2[i] = remove(temp2[i], " int");
                    }
                }
            }
            this.modifiedText = convertToStringWithNewLines(temp2);
        }
    }

    public void convertDoubleToFloat() {
        this.modifiedText = this.modifiedText.replace("double", "float");
    }

    public void convertFloatDeclarations() {
        if (this.modifiedText.contains("float")) {
            String[] temp2 = this.modifiedText.split("\n");
            for (int i = 0; i < temp2.length; i++) {
                if (temp2[i].contains("float")) {
                    if (!temp2[i].contains("=")) {
                        ArrayList<String> temp3 = new ArrayList<String>();
                        temp3.add(temp2[i].split(" ")[0]);
                        temp3.add(temp2[i].split(" ")[1]);
                        temp3.add("= 0.0");
                        temp3.addAll(Arrays.asList(temp2[i].split(" ")).subList(2, temp2[i].split(" ").length));
                        temp2[i] = convertToString(temp3);
                        temp2[i] = remove(temp2[i], " float");
                    }
                }
            }
            this.modifiedText = convertToStringWithNewLines(temp2);
        }
    }

    public void convertCharDeclarations(){
        if (this.modifiedText.contains("char")) {
            String[] temp2 = this.modifiedText.split("\n");
            for (int i = 0; i < temp2.length; i++) {
                if (temp2[i].contains("char")) {
                    if (!temp2[i].contains("=")) {
                        ArrayList<String> temp3 = new ArrayList<String>();
                        temp3.add(temp2[i].split(" ")[0]);
                        temp3.add(temp2[i].split(" ")[1]);
                        temp3.add("= ''");
                        temp3.addAll(Arrays.asList(temp2[i].split(" ")).subList(2, temp2[i].split(" ").length));
                        temp2[i] = convertToString(temp3);
                        temp2[i] = remove(temp2[i], " char");
                    }
                }
            }
            this.modifiedText = convertToStringWithNewLines(temp2);
        }
    }

    public void convertStringDeclarations(){
        String[] temp2 = this.modifiedText.split("\n");
        for (int i = 0; i < temp2.length; i++) {
            if (temp2[i].contains("String")) {
                if (!temp2[i].contains("=")) {
                    ArrayList<String> temp3 = new ArrayList<String>();
                    temp3.add(temp2[i].split(" ")[0]);
                    temp3.add(temp2[i].split(" ")[1]);
                    temp3.add("= \"\"");
                    temp3.addAll(Arrays.asList(temp2[i].split(" ")).subList(2, temp2[i].split(" ").length));
                    temp2[i] = convertToString(temp3);
                    temp2[i] = remove(temp2[i], " String");
                }
            }
        }
        this.modifiedText = convertToStringWithNewLines(temp2);
    }

    public void convertBooleanDeclarations(){
        if (this.modifiedText.contains("boolean")) {
            String[] temp2 = this.modifiedText.split("\n");
            for (int i = 0; i < temp2.length; i++) {
                if (temp2[i].contains("boolean")) {
                    if (!temp2[i].contains("=")) {
                        ArrayList<String> temp3 = new ArrayList<String>();
                        temp3.add(temp2[i].split(" ")[0]);
                        temp3.add(temp2[i].split(" ")[1]);
                        temp3.add("= False");
                        temp3.addAll(Arrays.asList(temp2[i].split(" ")).subList(2, temp2[i].split(" ").length));
                        temp2[i] = convertToString(temp3);
                        temp2[i] = remove(temp2[i], " boolean");
                    }
                }
            }
            this.modifiedText = convertToStringWithNewLines(temp2);
        }
    }

    public void removeTypeSpecificators(){
        remove("int ");
        remove("float ");
        remove("char ");
        remove("String "); // removed auch in Variablennamen
        remove("boolean ");
    }

    public void convertFunction(){
        String[] temp = this.modifiedText.split("\n");
        for (int i = 0; i < temp.length; i++) {
            if(temp[i].contains("public ") && !temp[i].contains("class")){
                temp[i] = temp[i].replace("public ", "def ");
                if(temp[i].contains("static")){ // eigentlich vor die Zeile dann in Python @classmethod, bei aktueller input Komplexität resultiert daraus aber kein funktionierender Code
                    temp[i] = remove(temp[i], "static ");
                }
                String[] temp2 = temp[i].split(" ");
                temp[i] = remove(temp[i], temp2[1]);
                temp[i] = temp[i] + ":";
                temp[i] = remove(temp[i], "{");
            }
            temp[i] = remove(temp[i], "}");
        }
        this.modifiedText = convertToStringWithNewLines(temp);
    }

    public void convertPrintStatements() {
        if(this.modifiedText.contains("System.out.println(")){
            this.modifiedText = this.modifiedText.replace("System.out.println", "print");
        }

        if(this.modifiedText.contains("System.out.print(")){
            this.modifiedText = this.modifiedText.replace("System.out.print", "print");
        }
    }

    public void convertIfClauses(){
        if(this.modifiedText.contains("if(")) {
            this.modifiedText = this.modifiedText.replace("if(", "if ");
        }
        this.modifiedText = this.modifiedText.replace("){", ":");
        remove("}");
    }

    public void removeDoubleSpaces(){
        this.modifiedText = this.modifiedText.replace("  ", " ");
    }

    public void printInputAndOutput() {
        System.out.println("Original code in Java:");
        System.out.println(this.input);
        System.out.println();
        System.out.println("Translated code in Python:");
        System.out.println(this.modifiedText);
    }
}

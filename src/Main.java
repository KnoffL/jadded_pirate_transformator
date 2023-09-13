// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String input = """
                int declaredInteger;
                float declaredFloat;
                double declaredDouble; // Double has twice the precision of a Float
                char declaredChar;
                String declaredStr;
                boolean declaredBoolean;

                public static String my_function(){
                \tdeclaredInteger = 5;
                }
                my_function()

                System.out.println("lmao")

                if(declaredInteger < 2){
                \tdeclaredInteger = 1
                }
                System.out.print(declaredInteger);
                """;

        Translator translator = new Translator(input);

        translator.removesFinalSemicolons();
        translator.convertFunction();
        translator.convertComments();
        translator.convertIntegerDeclarations();
        translator.convertDoubleToFloat();
        translator.convertFloatDeclarations();
        translator.convertCharDeclarations();
        translator.convertStringDeclarations();
        translator.convertBooleanDeclarations();
        translator.removeTypeSpecificators();
        translator.convertIfClauses();
        translator.convertPrintStatements();
        translator.removeDoubleSpaces();
        translator.printInputAndOutput();

        System.out.println("This is a fairly useless statement");
    }
}

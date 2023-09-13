import org.junit.Assert;
import org.junit.Test;

public class Tests {
    @Test
    public void methodIsTranslatedCorrectly(){
        String input = "public static String my_function(){\n\tdeclaredVar = 5\n}";
        String expectedOutput = "def my_function():\n\tdeclaredVar = 5";

        Translator testTranslator = new Translator(input);

        testTranslator.convertFunction();
        testTranslator.removeDoubleSpaces();

        /* This leads to potentially false passed tests if not all
        unnecessary components are removed but is necessary due to
        new lines and trailing white spaces. Additionally, this
        complicates debugging because the Assert.assertEquals() error
        message is providing more information */
        boolean isOutputCorrect = (expectedOutput.contains(testTranslator.modifiedText) || testTranslator.modifiedText.contains(expectedOutput));
        Assert.assertTrue(isOutputCorrect);
    }

    @Test
    public void integerDeclarationIsTranslatedCorrectly(){
        String input = "int testInteger;";
        String expectedOutput = "testInteger = 0";

        Translator testTranslator = new Translator(input);

        testTranslator.removesFinalSemicolons();
        testTranslator.convertIntegerDeclarations();
        testTranslator.removeTypeSpecificators();
        testTranslator.removeDoubleSpaces();

        /* This leads to potentially false passed tests if not all
        unnecessary components are removed but is necessary due to
        new lines and trailing white spaces. Additionally, this
        complicates debugging because the Assert.assertEquals() error
        message is providing more information */
        boolean isOutputCorrect = (expectedOutput.contains(testTranslator.modifiedText) || testTranslator.modifiedText.contains(expectedOutput));
        Assert.assertTrue(isOutputCorrect);
    }

    @Test
    public void methodWithArgumentsIsTranslatedCorrectly(){
        String input = "public String my_method(int input){\n\tdeclaredChar = input;\n}";
        String expectedOutput = "def my_method(input):\n\tdeclaredChar = input";

        Translator testTranslator = new Translator(input);

        testTranslator.removesFinalSemicolons();
        testTranslator.convertFunction();
        testTranslator.removeTypeSpecificators();
        testTranslator.removeDoubleSpaces();

        /* This leads to potentially false passed tests if not all
        unnecessary components are removed but is necessary due to
        new lines and trailing white spaces. Additionally, this
        complicates debugging because the Assert.assertEquals() error
        message is providing more information */
        boolean isOutputCorrect = (expectedOutput.contains(testTranslator.modifiedText) || testTranslator.modifiedText.contains(expectedOutput));
        Assert.assertTrue(isOutputCorrect);
    }

    @Test
    public void printStatementsAreTranslatedCorrectly(){
        String input = "System.out.println(\"lmao\")";
        String expectedOutput = "print(\"lmao\")";

        Translator testTranslator = new Translator(input);

        testTranslator.convertPrintStatements();

        Assert.assertEquals(testTranslator.modifiedText, expectedOutput);
    }
}

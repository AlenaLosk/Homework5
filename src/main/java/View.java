public class View {

    public static void refresh(String[][] symbols) {
        StringBuilder resultString;
        for (int i = 0; i < 3; i++) {
            resultString = new StringBuilder("|");
            for (int j = 0; j < 3; j++) {
                resultString.append(symbols[i][j]).append("|");
            }
            System.out.println(resultString);
        }
    }
}

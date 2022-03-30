public class ConsoleUserInterface {
    public static void start() {
        ConsoleHelper.printMessage("Welcome to game 'TicTacToe' menu!", true);
        int menuPoint = 0;
        while (menuPoint == 0 || menuPoint == 1 || menuPoint == 2 || menuPoint == 3) {
            menuPoint = askMenuPoint();
            switch (menuPoint) {
                case 1:
                    Game game = new Game();
                    Writer writer1 = new JSONWriter();
                    Writer writer2 = new XMLWriter();
                    Player[] players = null;
                    boolean willGameContinue = true;
                    players = askPlayersNames(players);
                    while (willGameContinue) {
                        game.getGameplay().setModel(new Model());
                        Player winner = game.getGameplay().process(players, true);
                        if (winner == null) {
                            ConsoleHelper.printMessage("Drawn game!", true);
                        } else {
                            ConsoleHelper.printMessage(String.format("Winner is %s!", winner.getName()), true);
                        }
                        writer1.write(game, "src/main/resources/gameplay.json");
                        writer2.write(game, "src/main/resources/gameplay.xml");
                        ConsoleHelper.printMessage("Do you want to play again?", true);
                        ConsoleHelper.printMessage("If no, enter '1'. For continue playing enter any symbol: ");
                        String wantToPlay = ConsoleHelper.readMessage();
                        if (wantToPlay.equals("1")) willGameContinue = false;
                    }
                    break;
                case 2:
                    Reader reader = new JSONReader();
                    reader.readAndPlay("src/main/resources/gameplay.json");
                    menuPoint = 0;
                    break;
                case 3:
                    ConsoleHelper.printMessage("Menu is closed.");
                    menuPoint = 4;
                    break;
                default:
                    break;
            }
        }
    }

    private static int askMenuPoint() {
        int menuPoint = 0;
        while (menuPoint != 1 && menuPoint != 2 && menuPoint != 3) {
            ConsoleHelper.printMessage("Enter '1', if you want to play TicTacToe,"
                    + System.lineSeparator() + "Enter '2' - to see previous game"
                    + System.lineSeparator() + "Enter '3', if you want to exit: ");
            String temp = ConsoleHelper.readMessage();
            if (temp.equals("1") || temp.equals("2") || temp.equals("3")) {
                menuPoint = Integer.parseInt(temp);
            } else {
                ConsoleHelper.printMessage("Invalid input! Please, try again.", true);
            }
        }
        return menuPoint;
    }

    private static Player[] askPlayersNames(Player[] players) {
        Player[] result = null;
        if (players == null) {
            result = new Player[2];
            ConsoleHelper.printMessage("Enter 1st player's name: ");
            String name = "";
            while (name.isEmpty()) {
                name = ConsoleHelper.readMessage();
            }
            result[0] = new Player(1, name, "X");
            name = "";
            ConsoleHelper.printMessage("Enter 2nd player's name: ");
            while (name.isEmpty()) {
                name = ConsoleHelper.readMessage();
                if (name.equals(result[0].getName())) {
                    ConsoleHelper.printMessage("Don't repeat 1st player's name and enter 2nd player's name again: ");
                    name = "";
                }
            }
            result[1] = new Player(2, name, "O");
        } else {
            result = players;
        }
        return result;
    }
}

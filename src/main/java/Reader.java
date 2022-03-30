public interface Reader {
    void readAndPlay(String file);

    default void formatAndPrint(int pause, String[][] gameField) {
        View.refresh(gameField);
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

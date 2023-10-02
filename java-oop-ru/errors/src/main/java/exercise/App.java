package exercise;

// BEGIN
class App {

    public static void printSquare(Circle circle) {

        try {
            circle.getSquare();
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END

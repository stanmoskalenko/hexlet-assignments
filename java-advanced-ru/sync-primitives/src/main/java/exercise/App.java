package exercise;

class App {

    public static void main(String[] args) {
        var list = new SafetyList();
        var firstThread = new ListThread(list);
        var secondThread = new ListThread(list);

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("List size => " + list.getSize());
    }
}


package java_0117;



public class SequencePrint2 {

    public static void main(String[] args) {
        Thread a = new Thread(new Task("A"));
        Thread b = new Thread(new Task("B"));
        Thread c = new Thread(new Task("C"));
        c.start();
        b.start();
        a.start();
    }


    private static class Task implements Runnable {
        private static String[] ARR = {"A", "B", "C"};

        private String content;
        private static int INDEX;

        public Task(String content) {
            this.content = content;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    synchronized (ARR) {
                        while (!content.equals(ARR[INDEX])) {

                            ARR.wait();
                        }
                        System.out.println(content);
                        if (INDEX == ARR.length - 1) {
                            System.out.println();
                        }

                        INDEX = (INDEX + 1) % ARR.length;
                        ARR.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class TestDemo {
    public static void main(String[] args) {
        DoubleLinedList doubleLinedList = new DoubleLinedList();
        doubleLinedList.addFirst(4);
        doubleLinedList.addFirst(7);
        doubleLinedList.addFirst(7);
        doubleLinedList.addFirst(7);
        doubleLinedList.addLast(2);
        doubleLinedList.addIndex(5,5);
        System.out.println(doubleLinedList.contains(5));
        doubleLinedList.removeAllKey(7);
        doubleLinedList.display();
    }
}

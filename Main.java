public class Main {
    public static void main(String[] args) {
        In file = new In("./files/caso10000k.txt");
        String[] words = file.readAllStrings();
        Mutate mutate = new Mutate(words[0]);
        String newChain = mutate.reduceChain();
        System.out.println(newChain);
    }
}
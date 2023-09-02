public class Main {
    private static String fileName = "caso100k.txt";

    public static void main(String[] args) {
        In file = new In("./files/" + fileName);
        String[] words = file.readAllStrings();
        // MutateList mutate = new MutateList(words[0]);
        MutateArray mutate = new MutateArray(words[0]);
        long inicio = System.nanoTime();
        String newChain = mutate.reduceChain();
        long fim = System.nanoTime();
        System.out.println("Tempo executando o arquivo " + fileName + " " + ((fim - inicio) / 1e9));
        System.out.println(newChain);
    }
}
import java.util.ArrayList;
import java.util.HashMap;

public class MutateArray2 {
    private ArrayList<Character> mainChain;
    private ArrayList<Character> unMutaded;
    private int nextMutationIndex = 0;
    private HashMap<String, Character> MUTATION_DICT = new HashMap<>();

    public MutateArray2(String chain) {
        this.mainChain = new ArrayList<>(chain.length());
        this.unMutaded = new ArrayList<>(chain.length());
        for (int i = 0; i < chain.length(); i++) {
            this.mainChain.add(chain.charAt(i));
        }

        MUTATION_DICT.put("DN", 'A');
        MUTATION_DICT.put("ND", 'A');
        MUTATION_DICT.put("DA", 'N');
        MUTATION_DICT.put("AD", 'N');
        MUTATION_DICT.put("NA", 'D');
        MUTATION_DICT.put("AN", 'D');
    }

    public String reduceChain() {
        boolean mutated = true;

        while (mutated) {
            mutated = false;
            nextMutationIndex = this.findMutation(nextMutationIndex);

            if (nextMutationIndex >= 0) {
                char mutation = this.mutate(nextMutationIndex);
                mutated = true;
                this.copyChains(nextMutationIndex + 2);
                this.mainChain = this.unMutaded;
                this.mainChain.add(mutation);
                this.unMutaded = new ArrayList<>(this.mainChain.size());
            }
        }

        return this.mainChain.toString();
    }

    public int findMutation(int startingIndex) {
        if (startingIndex < 0) {
            return -1;
        }
        for (int i = startingIndex; i < this.mainChain.size() - 1; i++) {
            if (this.mainChain.get(i) != this.mainChain.get(i + 1)) {
                return i;
            } else {
                this.unMutaded.add(i, this.mainChain.get(i));
            }
        }

        return -1;
    }

    public char mutate(int index) {
        String chain = new StringBuilder().append(this.mainChain.get(index)).append(this.mainChain.get(index + 1))
                .toString();
        if (this.nextMutationIndex == 0) {
            this.nextMutationIndex = this.nextMutationIndex + 2;
        } else {
            this.nextMutationIndex = 0;
        }
        return this.MUTATION_DICT.get(chain);
    }

    public void copyChains(int startingIndex) {
        for (int i = startingIndex; i < this.mainChain.size(); i++) {
            this.unMutaded.add(this.mainChain.get(i));
        }
    }
}

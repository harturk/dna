import java.util.ArrayList;
import java.util.HashMap;

public class MutateArray0 {
    private ArrayList<Character> q = new ArrayList<>();
    private HashMap<String, Character> MUTATION_DICT = new HashMap<>();
    private int nextMutationIndex = 0;

    public MutateArray0(String chain) {
        for (int i = 0; i < chain.length(); i++) {
            q.add(chain.charAt(i));
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
            int index = this.findMutation(this.nextMutationIndex);

            if (index >= 0) {
                this.q.add(this.mutate(index));
                mutated = true;
            }
        }

        return this.q.toString();
    }

    public int findMutation(int index) {
        for (int i = index; i < this.q.size() - 1; i++) {
            if (q.get(i) != q.get(i + 1))
                return i;
        }

        return -1;
    }

    public char mutate(int index) {
        if (index == 0) {
            this.nextMutationIndex = 0;
        } else {
            this.nextMutationIndex = index - 1;
        }

        String chain = new StringBuilder().append(this.q.remove(index)).append(this.q.remove(index)).toString();
        return this.MUTATION_DICT.get(chain);
    }
}

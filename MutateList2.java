import java.util.HashMap;

public class MutateList2 {
    private DoublyLinkedList q = new DoublyLinkedList();
    private Node nextMutation = null;
    private HashMap<String, Character> MUTATION_DICT = new HashMap<>();

    public MutateList2(String chain) {
        for (int i = 0; i < chain.length(); i++) {
            q.insertAtEnd(chain.charAt(i));
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
        this.nextMutation = q.start;

        while (mutated) {
            mutated = false;

            nextMutation = this.findMutation(nextMutation);

            if (nextMutation != null) {
                this.q.insertAtEnd(this.mutate());
                mutated = true;
            }
        }

        return this.q.toString();
    }

    public Node findMutation(Node nextNode) {
        if (nextMutation == null) {
            return null;
        }
        while (nextNode.getNext() != null) {
            if (nextNode.getData() != nextNode.getNext().getData())
                return nextNode;
            nextNode = nextNode.getNext();
        }
        return null;
    }

    public char mutate() {
        String chain = new StringBuilder().append(nextMutation.getData()).append(nextMutation.getNext().getData())
                .toString();

        // char chain = ' ';

        // if (nextMutation.getData() == 'A') {
        // if (nextMutation.getNext().getData() == 'D') {
        // chain = 'N';
        // } else {
        // chain = 'D';
        // }
        // }
        // if (nextMutation.getData() == 'D') {
        // if (nextMutation.getNext().getData() == 'N') {
        // chain = 'A';
        // } else {
        // chain = 'N';
        // }
        // }
        // if (nextMutation.getData() == 'N') {
        // if (nextMutation.getNext().getData() == 'A') {
        // chain = 'D';
        // } else {
        // chain = 'A';
        // }
        // }

        Node current = nextMutation;
        Node next = nextMutation.getNext();
        if (nextMutation.getLinkPrev() == null) {
            nextMutation = nextMutation.getNext().getNext();
        } else {
            nextMutation = nextMutation.getLinkPrev();
        }
        q.removeNode(current);
        q.removeNode(next);
        // return chain;
        return this.MUTATION_DICT.get(chain);
    }
}

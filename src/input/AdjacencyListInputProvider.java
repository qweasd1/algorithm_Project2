package input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utility.Utility;

public class AdjacencyListInputProvider extends InputProvider<Node[]> {

	public AdjacencyListInputProvider(int size) {
		super(size);
	}

	private void addNode(Node[] input, int i, int j, double weight) {
		if (input[i] == null) {
			Node newNode = new Node();
			newNode.index = j;
			newNode.weight = weight;
			input[i] = newNode;
		} else {
			Node cursor = input[i];
			while (true) {
				if (cursor.next != null) {
					cursor = cursor.next;
				} else {
					break;
				}
			}

			cursor.append(j, weight);
		}

	}

	@Override
	public Node[] createCompleteInput() {
		Node[] input = new Node[size];
		for (int i = 0; i < size; i++) {
			input[i] = new Node();
		}

		Node[] cursor = Arrays.copyOf(input, size);

		for (int i = 0; i < size; i++) {
			Node head = cursor[i];

			for (int j = i; j < size; j++) {
				double weight = Utility.nextDouble();

				// update (i,j)
				head.index = j;
				if (i == j) {
					head.weight = 0;
				} else {
					head.weight = weight;
				}

				if (j < size - 1) {
					Node nextNode = new Node();
					head.next = nextNode;
					head = nextNode;
				} else {
					head.next = null;
				}

				// update (j,i)
				if (i == j) {
					continue;
				} else {
					Node node = cursor[j];
					node.index = i;
					node.weight = weight;

				    if (i < size - 1) {
						Node nextNode = new Node();
						node.next = nextNode;
						cursor[j] = nextNode;
					} else {
						head.next = null;
					}
				}
			}
		}

		return input;
	}

	@Override
	public Node[] createSparseInput() {
		List<Integer> selected = new ArrayList<>();
		List<Integer> rest = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			rest.add(i);
		}

		int head = Utility.nextInt(size);
		selected.add(head);
		rest.remove(head);

		Node[] input = new Node[size];

		addNode(input, 0, 0, 0);

		for (int i = 1; i < size; i++) {
			int node_0 = Utility.nextIntFrom(selected);
			int node_1 = Utility.nextRemoveIntFrom(rest);

			double weight = Utility.nextDouble();

			addNode(input, node_0, node_1, weight);
			addNode(input, node_1, node_0, weight);

			addNode(input, i, i, 0);

			selected.add(node_1);
		}

		return input;
	}
}
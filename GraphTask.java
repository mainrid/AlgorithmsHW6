import java.util.*;

public class GraphTask {

	public static void main(String[] args) {
		GraphTask a = new GraphTask();
		a.run();
		throw new RuntimeException("Nothing implemented yet!"); // delete this
	}

	public void run() {
		Scanner reader = new Scanner(System.in);

		Graph g = new Graph("G");

		System.out.println("If you want to find max bandwidth of a path for your own telephone network, type 1");
		System.out.println("If you want to find max bandwidth of a path for random telephone network type 2");
		

		while (true) {
			int n = reader.nextInt();
			if (n == 1) {
				System.out.println("Enter the number of switching centers");
				int numberOfVertices = reader.nextInt();

				int[][] graph = new int[numberOfVertices][numberOfVertices];
				System.out.println("Enter the bandwidth of the lines: ");
				for (int i = 0; i < graph.length; i++) {
					for (int j = 0; j < graph.length; j++) {
						graph[i][j] = reader.nextInt();
					}
				}
				
				System.out.println("Your graph matrix: ");
				System.out.println();
				for (int i = 0; i < graph.length; i++) {
					for (int j = 0; j < graph.length; j++) {
						System.out.print(graph[i][j] + " ");
					}
					System.out.println();
				}

				System.out.println("Please enter source switching center: ");
				int source = reader.nextInt();
				System.out.println("Please enter target switching center: ");
				int target = reader.nextInt();

				int maxBandwidth = g.maxBandwidth(source, target, graph);

				System.out.println(
						"Max bandwidth of a path from " + source + " to sink: " + target + " is " + maxBandwidth);
			} else if (n == 2) {
				System.out.println("Enter the number of switching centers");
				int numberOfVertices = reader.nextInt();
				System.out.println("Enter the number of communication lines");
				int numberOfLines = reader.nextInt();

				int[][] graph = g.createRandomSimpleGraph(numberOfVertices, numberOfLines);
				
				System.out.println("Graph Matrix generated: ");
				System.out.println();
				for (int i = 0; i < graph.length; i++) {
					for (int j = 0; j < graph.length; j++) {
						System.out.print(graph[i][j] + " ");
					}
					System.out.println();
				}

				System.out.println("Please enter source switching center: ");
				int source = reader.nextInt();
				System.out.println("Please enter target switching center: ");
				int target = reader.nextInt();

				int maxBandwidth = g.maxBandwidth(source, target, graph);

				System.out.println(
						"Max bandwidth of a path from " + source + " to sink: " + target + " is " + maxBandwidth);

			} else {
				System.out.println("Please enter 1 or 2");
			}
		}

		// int[][] matrix = g.createRandomSimpleGraph(6, 9);
		// for (int i = 0; i < matrix.length; i++) {
		// for (int j = 0; j < matrix.length; j++) {
		// System.out.print(matrix[i][j] + " ");
		// }
		// System.out.println();
		// }
		//
		// System.out.println(g);
		//
		// // testing findPath method
		// // test 1
		// System.out.println("Testing findPath() method");
		// int[][] testGraphMatrix = { { 0, 0, 2 }, { 3, 0, 5 }, { 3, 1, 0 } };
		// boolean ifPathFound = g.findPath(1, 2, testGraphMatrix);
		// System.out.println("Test 1. PathFound should be true, actual: " +
		// ifPathFound);
		// // test 2
		// int parentsCounter = 0;
		// for (int i = 0; i < g.getVisitedVertex().length; i++) {
		// if (g.getVisitedVertex()[i] == true) {
		// parentsCounter++;
		// }
		// }
		// System.out.println("Test 2. In visitedVertex massive all values
		// should be true (3), actual: " + parentsCounter);
		// // test 3
		// boolean parentVertexTest = false;
		// if (g.getParentVertex()[0] == 1 && g.getParentVertex()[1] == -1 &&
		// g.getParentVertex()[2] == 1) {
		// parentVertexTest = true;
		// }
		// System.out.println("Test 3. If values in parentVertex correct then
		// test result is True, actual result: "
		// + parentVertexTest);
		// //test 4
		// int maxBandwidth= g.maxBandwidth(2, 3, testGraphMatrix);
		// System.out.println("MaxBandwidth should be 7: actual result " +
		// maxBandwidth);

	}

	class Vertex {

		private String id;
		private Vertex next;
		private Arc first;
		private int info = 0;

		Vertex(String s, Vertex v, Arc e) {
			id = s;
			next = v;
			first = e;
		}

		Vertex(String s) {
			this(s, null, null);
		}

		@Override
		public String toString() {
			return id;
		}

		// TODO!!! Your Vertex methods here!
	}

	class Arc {

		private String id;
		private Vertex target;
		private Arc next;
		private int info = 0;
		private int bandwidth;

		Arc(String s, Vertex v, Arc a) {
			id = s;
			target = v;
			next = a;
			bandwidth = (int) (Math.random() * 15);
		}

		Arc(String s) {
			this(s, null, null);
		}

		@Override
		public String toString() {
			return id;
		}

		// TODO!!! Your Arc methods here!
	}

	class Graph {

		private String id;
		private Vertex first;
		private int info = 0;
		private boolean[] visitedVertex;
		private int[] parentVertex;
		private LinkedList<Integer> nextVertex;

		Graph(String s, Vertex v) {
			id = s;
			first = v;
			nextVertex = new LinkedList<Integer>();
		}

		Graph(String s) {
			this(s, null);
		}

		@Override
		public String toString() {
			String nl = System.getProperty("line.separator");
			StringBuffer sb = new StringBuffer(nl);
			sb.append(id);
			sb.append(nl);
			Vertex v = first;
			while (v != null) {
				sb.append(v.toString());
				sb.append(" -->");
				Arc a = v.first;
				while (a != null) {
					sb.append(" ");
					sb.append(a.toString());
					sb.append(" (");
					sb.append(v.toString());
					sb.append("->");
					sb.append(a.target.toString());
					sb.append(")");
					a = a.next;
				}
				sb.append(nl);
				v = v.next;
			}
			return sb.toString();
		}

		public Vertex createVertex(String vid) {
			Vertex res = new Vertex(vid);
			res.next = first;
			first = res;
			return res;
		}

		public Arc createArc(String aid, Vertex from, Vertex to) {
			Arc res = new Arc(aid);
			res.next = from.first;
			from.first = res;
			res.target = to;
			return res;
		}

		/**
		 * Create a connected undirected random tree with n vertices. Each new
		 * vertex is connected to some random existing vertex.
		 * 
		 * @param n
		 *            number of vertices added to this graph
		 */
		public void createRandomTree(int n) {
			if (n <= 0)
				return;
			Vertex[] varray = new Vertex[n];
			for (int i = 0; i < n; i++) {
				varray[i] = createVertex("v" + String.valueOf(n - i));
				if (i > 0) {
					int vnr = (int) (Math.random() * i);
					createArc("a" + varray[vnr].toString() + "_" + varray[i].toString(), varray[vnr], varray[i]);
					createArc("a" + varray[i].toString() + "_" + varray[vnr].toString(), varray[i], varray[vnr]);
				} else {
				}
			}
		}

		/**
		 * Create an adjacency matrix of this graph. Side effect: corrupts info
		 * fields in the graph
		 * 
		 * @return adjacency matrix
		 */
		public int[][] createAdjMatrix() {
			info = 0;
			Vertex v = first;
			while (v != null) {
				v.info = info++;
				v = v.next;
			}
			int[][] res = new int[info][info];
			v = first;
			while (v != null) {
				int i = v.info;
				Arc a = v.first;
				while (a != null) {
					int j = a.target.info;
					res[i][j] = a.bandwidth;
					a = a.next;
				}
				v = v.next;
			}
			return res;
		}

		/**
		 * Create a connected simple (undirected, no loops, no multiple arcs)
		 * random graph with n vertices and m edges.
		 * 
		 * @param n
		 *            number of vertices
		 * @param m
		 *            number of edges
		 */
		public int[][] createRandomSimpleGraph(int n, int m) {
			if (n <= 0)
				throw new IllegalArgumentException("Incorrect argument: " + (n + 1));
			if (n > 2500)
				throw new IllegalArgumentException("Too many vertices: " + n);
			if (m < n - 1 || m > n * (n - 1) / 2)
				throw new IllegalArgumentException("Impossible number of edges: " + m);

			// initializing variables needed for findPath() method
			this.parentVertex = new int[n];
			this.visitedVertex = new boolean[n];

			first = null;
			createRandomTree(n); // n-1 edges created here
			Vertex[] vert = new Vertex[n];
			Vertex v = first;
			int c = 0;
			while (v != null) {
				vert[c++] = v;
				v = v.next;
			}
			int[][] connected = createAdjMatrix();
			int edgeCount = m - n + 1; // remaining edges
			while (edgeCount > 0) {
				int i = (int) (Math.random() * n); // random source
				int j = (int) (Math.random() * n); // random target
				if (i == j)
					continue; // no loops
				if (connected[i][j] != 0 || connected[j][i] != 0)
					continue; // no multiple edges
				Vertex vi = vert[i];
				Vertex vj = vert[j];

				connected[i][j] = createArc("a" + vi.toString() + "_" + vj.toString(), vi, vj).bandwidth;
				connected[j][i] = createArc("a" + vj.toString() + "_" + vi.toString(), vj, vi).bandwidth;

				edgeCount--; // a new edge happily created
			}
			return connected;
		}

		// TODO!!! Your Graph methods here!

		public boolean[] getVisitedVertex() {
			return this.visitedVertex;
		}

		public int[] getParentVertex() {
			return this.parentVertex;
		}

		public int maxBandwidth(int source, int target, int[][] graphMatrix) {
			if (source == target) {
				return 0;
			}

			this.parentVertex = new int[graphMatrix.length];
			this.visitedVertex = new boolean[graphMatrix.length];

			source = source - 1;
			target = target - 1;

			int[][] residualGraphMatrix = new int[graphMatrix.length][graphMatrix.length];

			for (int i = 0; i < residualGraphMatrix.length; i++) {
				for (int j = 0; j < residualGraphMatrix.length; j++) {
					residualGraphMatrix[i][j] = graphMatrix[i][j];
				}
			}

			int pathFlow;
			int vertexHolder;
			int parentV;
			int maxBandwidth = 0;

			while (findPath(source, target, residualGraphMatrix) == true) {
				pathFlow = 500000;
				for (vertexHolder = target; vertexHolder != source; vertexHolder = parentVertex[vertexHolder]) {

					parentV = parentVertex[vertexHolder];

					pathFlow = Math.min(pathFlow, residualGraphMatrix[parentV][vertexHolder]);
				}

				for (vertexHolder = target; vertexHolder != source; vertexHolder = parentVertex[vertexHolder]) {
					parentV = parentVertex[vertexHolder];

					residualGraphMatrix[parentV][vertexHolder] -= pathFlow;
					residualGraphMatrix[vertexHolder][parentV] += pathFlow;
				}

				maxBandwidth += pathFlow;
			}

			return maxBandwidth;
		}

		private boolean findPath(int source, int target, int[][] graphMatrix) {
			if (source < 0 || source >= graphMatrix.length) {
				throw new IllegalArgumentException("Incorrect input: " + (source+1));
			}

			if (target < 1 || target >= graphMatrix.length) {
				throw new IllegalArgumentException("Incorrect input: " + (target+1));
			}

			for (int i = 0; i < graphMatrix.length; i++) {

				this.visitedVertex[i] = false;
				this.parentVertex[i] = -1;
			}

			this.nextVertex.add(source);
			visitedVertex[source] = true;

			while (!this.nextVertex.isEmpty()) {
				int sourceVertex = nextVertex.remove();

				for (int targetVertex = 0; targetVertex < graphMatrix.length; targetVertex++) {

					if (graphMatrix[sourceVertex][targetVertex] > 0 && !visitedVertex[targetVertex]) {
						nextVertex.add(targetVertex);
						this.parentVertex[targetVertex] = sourceVertex;
						this.visitedVertex[targetVertex] = true;
					}
				}
			}

			if (visitedVertex[target]) {
				return true;
			}
			return false;
		}
	}

}

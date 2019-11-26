package enepuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Tablero {
	private int N;
    private int[][] blocks;
    private String cadenaMatriz;
    private int cost;
    private ArrayList<Tablero> children = new ArrayList<Tablero>() ;
    private Tablero parent;
    private int totalCost;
    //private int [][] goal;
 
    public Tablero(int[][] blocks) {
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
        N = blocks[0].length;
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.blocks[i][j] = blocks[i][j];
                //this.cadenaMatriz = cadenaMatriz + blocks[i][j] + "";
            }
        }
        cadenaMatriz= matrizToString(this.blocks);
        //cadenaMatriz =Arrays.deepToString(blocks);
    }
    
    public Tablero getParent() {
        return parent;
    }

    public void setParent(Tablero parent) {
        this.parent = parent;
    }
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public ArrayList<Tablero> getChildren() {
        return children;
    }

    // Public interface
    public void addChild(Tablero child) {
        children.add(child);
    }
    
    public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }
    public int getTotalCost() {
        return totalCost;
    }
    
    public Iterable<Tablero> neighbors() {
        // all neighboring boards
            if (N < 2) {
                return null;
            }
            ArrayList<Tablero> boards = new ArrayList<Tablero>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (blocks[i][j] == 0) {
                        if (j > 0) {
                            boards.add(getNeighbor(i, j, i, j - 1));
                        }
                        if (j < N - 1) {
                            boards.add(getNeighbor(i, j, i, j + 1));
                        }
                        if (i > 0) {
                            boards.add(getNeighbor(i, j, i - 1, j));
                        }
                        if (i < N - 1) {
                            boards.add(getNeighbor(i, j, i + 1, j));
                        }
                        return boards;
                    }
                }
            }
            return null;
        }
    
    private void exch(int i0, int j0, int i, int j) {
        int temp = blocks[i0][j0];
        blocks[i0][j0] = blocks[i][j];
        blocks[i][j] = temp;
    }
 
    
    public String matrizToString(int [][] m) {
        // string representation of this board (in the output format specified below)
            String s = "";
            for (int i = 0; i < N; i++) {
                s += "";
                for (int j = 0; j < N; j++) {
                    s += m[i][j] + "";
                }
                s += "";
            }
            return s;
        }
    
    
    private Tablero getNeighbor(int i0, int j0, int i, int j) {
        Tablero newBoard = new Tablero(blocks);
        newBoard.exch(i0, j0, i, j);
        newBoard.cadenaMatriz=matrizToString(newBoard.blocks);
        return newBoard;
    }
    
    public String toString() {
        // string representation of this board (in the output format specified below)
            String s = "" + "\n";
            for (int i = 0; i < N; i++) {
                s += " ";
                for (int j = 0; j < N; j++) {
                    s += blocks[i][j] + "  ";
                }
                s += "\n";
            }
            return s;
        }
    
    public static boolean isGoal(int [][] actual, int [][] goal) {
    	if (Arrays.deepEquals(actual, goal)) {
    		  System.out.println("Matriz objetivo alcanzada");
    	return true;
    	}else {
    		  
    	 return false;
        }
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(blocks);
		result = prime * result + ((cadenaMatriz == null) ? 0 : cadenaMatriz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		if (!Arrays.deepEquals(blocks, other.blocks))
			return false;
		if (cadenaMatriz == null) {
			if (other.cadenaMatriz != null)
				return false;
		} else if (!cadenaMatriz.equals(other.cadenaMatriz))
			return false;
		return true;
	}

	/*public static ArrayList<Tablero> encontrarTablero(Tablero source, Tablero target) {
		Queue<Tablero> nivelActual = new LinkedList<>();
		ArrayList<Tablero> nivelSig = new ArrayList<Tablero>();
		ArrayList<Tablero> visitados = new ArrayList<Tablero>();
		int i=0;
		nivelActual.add(source);

		while (!isGoal(nivelActual.element().blocks,target.blocks)) {// Procesar Nivel Actual , iteracion por nivel
			//for(int i = 0; i<nivelActual.size();i++ ) {
			
			
			
			if (nivelActual.isEmpty())
				return null;

			else {
				for(Tablero p : nivelActual ) {
					
					for(Tablero a : p.neighbors()){
						
						if (!(visitados.contains(a) || nivelSig.contains(a) ||nivelActual.contains(a))) {
							nivelSig.add(a);
						}
					}
				}
			
				 for(Tablero x : nivelActual ) {
					visitados.add(x);
				}
					// Añadir los del nivel actual a visitados
				
				 nivelActual.remove();
				//nivelSig.remove(0);
				nivelActual.addAll(nivelSig);
				
				nivelSig.clear();
			}
		//i++;
		}
		

		return visitados;
	}

	*/
	
	
/*private static boolean encontrarTablero2(Tablero source, Tablero origen, Tablero destination, ArrayList<String> visited ) {
    	
    	Iterable<Tablero> adyacentes = source.neighbors();
    	
    	if (visited.contains(source.cadenaMatriz)) {
    		
            return false;
        }
    	
        visited.add(source.cadenaMatriz);
        
        
       if (isGoal(source.blocks,destination.blocks))
    	   return true;
       
        for (Tablero a : adyacentes) {
        	
            if (encontrarTablero2(a, origen, destination, visited)) {
            	
                return true;
            }
           
        }
        
        visited.remove(visited.get(visited.size()-1));
        return false;
    }
*/

/*public static boolean buscarCamino(Tablero source, Tablero destination) {
	String aux = "";
	ArrayList<String> visit = new ArrayList<String>();
	boolean val = false ;
	

    	
      val =   encontrarTablero2(source , source, destination, visit);
    
    //quitar comparacion
    if(val==true) {
    	
    	for(int i =0; i<visit.size(); i++) {
      	  aux = aux + " => " + visit.get(i).toString()   ;
      	
         }
    	
    	System.out.println("+ "  + aux);
    	return true;
    	
    }else{
    	
    	System.out.println("No hay solucion ");
         return false;
    }
      
    
}
*/
private static int heuristicOne(String currentState, String goalSate) {
    int difference = 0;
    for (int i = 0; i < currentState.length(); i += 1)
        if (currentState.charAt(i) != goalSate.charAt(i))
            difference += 1;
    return difference;
}

public static void bestFirstSearch(Tablero inicial, Tablero target) {
    // stateSet is a set that contains node that are already visited
    Set<String> stateSets = new HashSet<String>();
    int totalCost = 0;
    int time = 0;
    Tablero nodo = new Tablero(inicial.blocks);
    nodo.setCost(0);

    // the comparator compare the cost values and make the priority queue to be sorted based on cost values
    ComparadorCosto comparator = new ComparadorCosto();

    // a queue that contains nodes and their cost values sorted. 10 is the initial size
    PriorityQueue<Tablero> nodePriorityQueue = new PriorityQueue<Tablero>(10, comparator);
    Tablero currentNode = nodo;
    while (!isGoal(currentNode.blocks,target.blocks)) {
        stateSets.add(currentNode.cadenaMatriz);
        Iterable<Tablero> nodeSuccessors = currentNode.neighbors();//NodeUtil.getSuccessors(currentNode.getState());
        for (Tablero n : nodeSuccessors) {
            if (stateSets.contains(n.cadenaMatriz))
                continue;
            stateSets.add(n.cadenaMatriz);
            Tablero child = new Tablero(n.blocks);
            currentNode.addChild(child);
            child.setParent(currentNode);

            child.setTotalCost(0, heuristicOne(child.cadenaMatriz, target.cadenaMatriz));
            nodePriorityQueue.add(child);

        }
        currentNode = nodePriorityQueue.poll();
        time += 1;
    }
    // Here we try to navigate from the goal node to its parent( and its parent's parent and so on) to find the path
   // NodeUtil.printSolution(currentNode, stateSets, root, time);
    for(String s : stateSets) {
    	System.out.println(s);
    }
    System.out.println("Total de movimientos: " + time);

}

    public static void main(String[] args) {
        // unit tests (not graded)
    		/*Scanner StdIn = new Scanner(System.in);
            int N = StdIn.nextInt();
            int[][] blocks = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    blocks[i][j] = StdIn.nextInt();
                }
            }*/
    	    
    	//1 2 4 0 6 3 7 5 8
    	
    	int[][] blocks =  {{1,2,3},{4,5,6},{7,8,0}}; //{{1,3},{2,0}};//
	    int[][] blocks2 = {{1,2,4},{0,6,3},{7,5,8}}; //{{1,2},{3,0}};//
    	//134862705
    	  
    	     
            Tablero board = new Tablero(blocks);
            Tablero board2 = new Tablero(blocks2);
            

            //System.out.println(isGoal(board.blocks,board2.blocks));
            //System.out.println(board.cadenaMatriz);
            //System.out.println(board);
            //System.out.println("Neighbors:");
            //Iterable<Tablero> it = board.neighbors();
            
            
            /*for (Tablero b : it) {
                System.out.println(b);
                System.out.println(b.cadenaMatriz);
            }*/
            
            //ArrayList<Tablero>tf = encontrarTablero(board,board2);
            
            bestFirstSearch(board,board2);
            //System.out.println(buscarCamino(board,board2));
           // for (Tablero t : tf) {
              //  System.out.println(t);
                //System.out.println(b.cadenaMatriz);
            //}
            
            // System.out.println(board);
        }
}

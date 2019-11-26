package enepuzzle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

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
    
    public String getState() {
    	return cadenaMatriz;
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
	
	long tStart = System.currentTimeMillis();
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
        //time += 1;
    }
    
    long tEnd = System.currentTimeMillis();
    long tDelta = tEnd - tStart;
    double elapsedSeconds = tDelta / 1000.0;
        Stack<Tablero> sol = new Stack<Tablero>();
        
        while(currentNode!=null) {
        	sol.push(currentNode);
        	currentNode=currentNode.parent;
        }
        
        System.out.println("Numero de movimientos: " + sol.size() + "\n");
        System.out.println("Tiempo en segundos: " + elapsedSeconds + "\n");
        while(!sol.empty()) {
        	System.out.println(sol.pop());
        }
        
        
   

}

    public static void main(String[] args) throws IOException {
        
    	String archivo = "puzzle.txt";
    	String cadena = "";
    	FileReader fr = new FileReader(archivo);
		BufferedReader bf = new BufferedReader(fr);
		//int i =0;
		cadena = bf.readLine();
		int juegos = Integer.parseInt(cadena);	
		
		for(int i = 0; i<juegos;i++) {
			cadena = bf.readLine();
			cadena = bf.readLine();
			int tamMatriz = Integer.parseInt(cadena);
			cadena = bf.readLine();
		String [] meta = cadena.split(" ");
		cadena = bf.readLine();
		String [] inicial = cadena.split(" ");	
		
		int[][] matrizMeta= new int[tamMatriz][tamMatriz];
		int[][] matrizInicial= new int[tamMatriz][tamMatriz];
		
        for (int j = 0; j < tamMatriz; j++) {
            for (int k = 0; k < tamMatriz; k++) {
            	matrizMeta[j][k] = Integer.parseInt(meta[tamMatriz*j+k]);
            	matrizInicial[j][k] = Integer.parseInt(inicial[tamMatriz*j+k]);
                //this.cadenaMatriz = cadenaMatriz + blocks[i][j] + "";
            	
            	
            }
        }
        Tablero board = new Tablero(matrizInicial);
        Tablero board2 = new Tablero(matrizMeta);
        
        bestFirstSearch(board,board2);
			
		}
		  bf.close();
    	
             
                    
        }
}

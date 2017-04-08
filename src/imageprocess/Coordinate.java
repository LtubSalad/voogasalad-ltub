package imageprocess;

/**
 * 
 */
public class Coordinate<A, B> {
    private final A first;
    private final B last;

    public Coordinate (A key, B value) {  
        this.first = key;
        this.last = value;
    }

   
    public A getFirst () {
        return first;
    }
    
    public B getLast () {
        return last;
    }

    public String toString() { 
        return "(" + first + ", " + last + ")";  
    }

    
    public boolean equals(Coordinate<A,B> p){
    	
    	return(this.toString().equals(p.toString()));
    }
    
}
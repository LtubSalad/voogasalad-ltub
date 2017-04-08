package imageprocess;
 
/**
 * Connected Component Labeling (union-find)
 * 
 * @author Zhiyong
 *
 */
public class CCLabeling {
 
	// original component image/array
	private int[][] image;
	private int width,height;
 
	// graph of nodes
	private int[] roots = null;
	private final int NOROOT=-1;
 
	/**
         * @param image image filled with component's id
         * @param width width of the image
         * @param height height of the image
         */
	public CCLabeling (int[][] image, int width, int height) {
		this.image = image;
		this.width = width;
		this.height = height;
		this.roots = new int[width*height];
	}
 
	// find the root of the node at position pos 
	private int find(int pos) {
		while(roots[pos]!=pos) pos=roots[pos];
		return pos;
	}
 
	// union of the 2 path formed by the 2 roots
	private int union(int root0, int root1) {
		if (root0==root1) return root0;
		if (root0==NOROOT) return root1;
		if (root1==NOROOT) return root0;
		if (root0<root1) {
			roots[root1]=root0;
			return root0;
		} else {
			roots[root0]=root1;
			return root1;
		}
	}
 
	// set the root of the node at position pos  
	private void add(int pos, int root) {
		if (root==NOROOT) 
			roots[pos]=pos;
		else 
			roots[pos]=root;
	}
 
	// build the connected component labels array
	private int[][] buildLabelArray() {
 
		// remove indirections
		for(int pos=0;pos<(width*height);pos++)
			roots[pos] = find(pos);
 
		// relabel the root
		int label=1;
		for(int pos=0;pos<(width*height);pos++)
			if (roots[pos]==pos)
				roots[pos] = label++;
			else
				roots[pos] = roots[roots[pos]];
 
		System.out.println("labels: "+(label-1));
 
		// copy label to new array
		int[][] labels = new int[width][height];
		for(int y=0,pos=0;y<height;y++)
			for(int x=0;x<width;x++,pos++)
				labels[x][y]=roots[pos];
 
		return labels;
	}
 
	/**
         * @return the connected component labels array
         */
	public int[][] compute() {
		int root;
		for(int y=0,pos=0;y<height;y++) {
			for(int x=0;x<width;x++,pos++) {
				root = NOROOT;
 
				if ( (x>0) && (image[x-1][y]==image[x][y]) )
					root = union( find(pos-1) , root);
 
				if ( (x>0 && y>0) && (image[x-1][y-1]==image[x][y]) )
					root = union( find(pos-1-width) , root);
 
				if ( (y>0) && (image[x][y-1]==image[x][y]) )
					root = union( find(pos-width) , root);
 
				if ( (x<(width-1) && y>0) && (image[x+1][y-1]==image[x][y]) )
					root = union( find(pos+1-width) , root);
 
				add( pos, root );
			}
		}
 
		return buildLabelArray();
	}
}
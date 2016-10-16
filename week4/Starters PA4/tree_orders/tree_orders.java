import java.util.*;
import java.io.*;
import java.util.Stack;

public class tree_orders {
    class FastScanner {
  StringTokenizer tok = new StringTokenizer("");
  BufferedReader in;

  FastScanner() {
   in = new BufferedReader(new InputStreamReader(System.in));
  }

  String next() throws IOException {
   while (!tok.hasMoreElements())
    tok = new StringTokenizer(in.readLine());
   return tok.nextToken();
  }
 
  int nextInt() throws IOException {
   return Integer.parseInt(next());
  }
 }

 //Definition for binary tree
 public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { 
       val = x;
       left = null;
       right = null;
        }
  }

 public class TreeOrders {
  int n;
  int[] key, left, right, parent;
  TreeNode root;
  
  void read() throws IOException {
   FastScanner in = new FastScanner();
   n = in.nextInt();
   key = new int[n];
   left = new int[n];
   right = new int[n];
   parent = new int[n];
   parent[0] = -1;
   
   for (int i = 0; i < n; i++) { 
    key[i] = in.nextInt();
    left[i] = in.nextInt();
    right[i] = in.nextInt();
 
    if (left[i] != -1) parent[left[i]] = i;
    if (right[i] != -1) parent[right[i]] = i;
    
   }
   
  }
  
  void createNode(int parent[], int i, TreeNode[] created) {
      // If this node is already created
        if (created[i] != null)
            return;
  
        // Create a new node and set created[i]
        created[i] = new TreeNode(key[i]);
  
        // If 'i' is root, change root pointer and return
        if (parent[i] == -1) 
        {
            root = created[i];
            return;
        }
  
        // If parent is not created, then create parent first
        if (created[parent[i]] == null)
            createNode(parent, parent[i], created);
  
        // Find parent pointer
        TreeNode p = created[parent[i]];
  
        // If this is first child of parent
        if (left[parent[i]] == i)
            p.left = created[i];
        else // If second child
          
            p.right = created[i];

    }
  
  TreeNode createTree(int parent[], int n) 
    {    
        // Create an array created[] to keep track
        // of created nodes, initialize all entries
        // as NULL
        TreeNode[] created = new TreeNode[n];
        for (int i = 0; i < n; i++) 
            created[i] = null;
  
        for (int i = 0; i < n; i++)
            createNode(parent, i, created);
  
        return root;
    }
  
  List<Integer> inOrder() {
   ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
   root = createTree(parent,n);
  
   if (root == null) {

    return result;
   }

   Stack<TreeNode> stack = new Stack<TreeNode>();
   TreeNode node = root;

   while (node != null) {
    stack.push(node);
    
    node = node.left;
   }

   while (stack.size() > 0) {
    node = stack.pop();
    result.add(node.val);

    if (node.right != null ) {

     node = node.right;
     while (node != null) {
      stack.push(node);

      node = node.left;
     }
    }
   }
          
   return result;
  }

  List<Integer> preOrder() {
   ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
   root = createTree(parent,n);
   
   if (root == null) {
    return result;
   }

   Stack<TreeNode> stack = new Stack<TreeNode>();
   TreeNode node = root;
   stack.push(node);

   while (stack.size()>0) {
    node = stack.peek();
    result.add(node.val);
    stack.pop();
    if (node.right != null) {
     stack.push(node.right);
    }

    if (node.left != null) {
     stack.push(node.left);
    }
   }  

   return result;
  }

  List<Integer> postOrder() {
   ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
   root = createTree(parent,n);
   
   if (root == null) {
    return result;
   }

   Stack<TreeNode> stack = new Stack<TreeNode>();
   TreeNode node = root;
   TreeNode prev = null;

   stack.push(node);

   while (stack.size()>0) {

    node = stack.peek();

    if (prev == null || prev.left == node || prev.right == node) {
     if (node.left != null) {
      stack.push(node.left);
     } else if (node.right != null) {
      stack.push(node.right);
     } else {
      stack.pop();
      result.add(node.val);
     }
    }

    else if (node.left == prev) {
      if (node.right != null) {
       stack.push(node.right);
      } else {
       stack.pop();
       result.add(node.val);
      }
     }

    else if (node.right == prev) {
     stack.pop();
     result.add(node.val);
    }

    prev = node;
    
   }
                        
   return result;
  }
 }

 static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
 }

 public void print(List<Integer> x) {
  for (Integer a : x) {
   System.out.print(a + " ");
  }
  System.out.println();
 }

 public void run() throws IOException {
  TreeOrders tree = new TreeOrders();
  tree.read();
  print(tree.inOrder());
  print(tree.preOrder());
  print(tree.postOrder());
 }
}

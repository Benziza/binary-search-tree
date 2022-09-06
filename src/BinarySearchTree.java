public class BinarySearchTree {
    // 1-A class that contains the left and right nodes of the current node and the key value
    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // 2-Binary search tree root
    static Node root;

    // 3-Contructor
    BinarySearchTree() {
        root = null;
    }

    // 4-This method calls the function insertRec
    void insert(int key) {
        root = insertRec(root, key);
    }

    // deleteRect This function is used to call
    void deleteKey(int key)
    {
        root = deleteRec(root, key);
    }

    // A recursive function to insert a new key into the binary search tree
    Node deleteRec(Node root, int key)
    {
        // Base case: If the tree is empty
        if (root == null) return root;

        // If the tree is not empty, the function calls itself down the tree
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);

        // If the key matches the root key, this is the node to delete
        else
        {
            // A node that has one child node or no child node
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // A node that has two child nodes, we get the middle suffix node
            // smallest value in the right branch
            root.key = minValue(root.right);

            // Delete the middle post node
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root)
    {
        int minv = root.key;
        while (root.left != null)
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }



    /* 5-A recursive function to insert a new key into the binary search tree */
    Node insertRec(Node root, int key) {

        /* 6-If the tree is empty, the function returns a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* 7-Otherwise the function will call itself back on the tree branches */
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        /* 8-The function returns the pointer of the node */
        return root;
    }

    // 9-This method calls the function inorderRec
    void inorder() {
        inorderRec(root);
    }

    // 10-A helper function to perform mean navigation in a binary search tree
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    public Node search(Node root, int key)
    {
        // Basic case: the key is empty or the key is in the root
        if (root==null || root.key==key)
            return root;

        // The key must be greater than the root key
        if (root.key > key)
            return search(root.left, key);

        // The key must be smaller than the root key
        return search(root.right, key);
    }

    // Test
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

		/* Let's create the following binary search tree
		    50
		 /	   \
		30	   70
		/ \   / \
	   20 40 60 80
	   */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal of the given tree");
        tree.inorder();

        System.out.println("\nDelete 20");
        tree.deleteKey(20);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 30");
        tree.deleteKey(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();

        System.out.println("\nDelete 50");
        tree.deleteKey(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.inorder();
    }
}

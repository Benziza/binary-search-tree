# binary-search-tree

## Introduction

A binary search tree is a binary tree that contains nodes and has the following properties:

1-The left branch of a node contains nodes that have keys with a value less than the key value of this node.<br>
2-The right branch of a node contains nodes that have keys with a value greater than the key value of this node.<br>
3-The left and right branch must also be binary search trees, and no nodes can be the same.<br>
<strong>Remark</strong> : These properties impose a special order of the elements in the binary data tree that depends on the value of the key, and as a result, the execution of searches and finds the minimum or maximum value is faster.<br>
<img src="./Images/img1.png">

## Insert a key into the binary search tree

The new element is always inserted into the leaf node. We start searching for a specific key starting from the root and ending with the leaf node. If we find a leaf node, we include the new node as its child node.

```
         100                               100
        /   \        Insert 40            /    \
      20     500    --------->          20     500
     /  \                              /  \
    10   30                           10   30
                                            \
                                            40
```

With Java :

```
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
    Node root;

    // 3-Contructor
    BinarySearchTree() {
        root = null;
    }

    // 4-This method calls the function insertRec
    void insert(int key) {
        root = insertRec(root, key);
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

        // Printing
        tree.inorder();
    }
}
```

<strong>time complexity</strong><br>
The worst possible search and insert time complexity is O(h) and h is the height of the binary search tree. In the worst case scenario, we will have to navigate through the tree elements from the root to the last leaf node. Sometimes the height of the skewed data tree can be n and then the insert and lookup times complexity is O(n).

## Search for a key

The process of searching for a specific key in the binary search tree begins by comparing this key with the root. In the left branch of the node.

```
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

        // Printing
        tree.inorder();

        //search
        System.out.println(tree.search(root,55).key);
    }
}
```

## Delete a node from the binary search tree

Three possibilities appear when a node is deleted from the binary search tree:

1- The node to be deleted is a leaf node: this node is deleted directly from the tree.

              50                            50
           /     \         delete(20)      /   \
          30      70       --------->    30     70
         /  \    /  \                     \    /  \
       20   40  60   80                   40  60   80

2- The node to be deleted has only one child node: the child node is copied to replace the parent node and the child node is deleted.

              50                            50
           /     \         delete(30)      /   \
          30      70       --------->    40     70
            \    /  \                          /  \
            40  60   80                       60   80

3- The node to be deleted has two child nodes: we look for the intermediate inorder successor for this node, and then copy and delete the value of the intermediate successor node. It is worth noting that the median pre-node can be used as well.

              50                            60
           /     \         delete(50)      /   \
          40      70       --------->    40    70
                 /  \                            \
                60   80                           80

With Java :

```
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
```

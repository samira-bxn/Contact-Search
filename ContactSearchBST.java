
import java.util.Scanner;

// Node class
class Node {
    String name;
    String phone;
    Node left, right;

    Node(String name, String phone) {
        this.name = name;
        this.phone = phone;
        left = right = null;
    }
}

// Binary Search Tree class
class ContactBST {

    Node root;

    // Insert a new contact
    Node insert(Node root, String name, String phone) {

        if (root == null) {
            return new Node(name, phone);
        }

        int comparison = name.compareTo(root.name);

        if (comparison < 0) {
            root.left = insert(root.left, name, phone);
        }
        else if (comparison > 0) {
            root.right = insert(root.right, name, phone);
        }

        return root;
    }

    // Search for a contact
    Node search(Node root, String name) {

        if (root == null) {
            return null;
        }

        int comparison = name.compareTo(root.name);

        if (comparison == 0) {
            return root;
        }
        else if (comparison < 0) {
            return search(root.left, name);
        }
        else {
            return search(root.right, name);
        }
    }

    // Display contacts in alphabetical order
    void inorder(Node root) {

        if (root != null) {
            inorder(root.left);

            System.out.println("Name: " + root.name +
                    " | Phone: " + root.phone);

            inorder(root.right);
        }
    }

    // Find minimum value node
    Node findMin(Node root) {

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    // Delete a contact
    Node delete(Node root, String name) {

        if (root == null) {
            return null;
        }

        int comparison = name.compareTo(root.name);

        if (comparison < 0) {
            root.left = delete(root.left, name);
        }
        else if (comparison > 0) {
            root.right = delete(root.right, name);
        }
        else {

            // Node has no left child
            if (root.left == null) {
                return root.right;
            }

            // Node has no right child
            if (root.right == null) {
                return root.left;
            }

            // Node has two children
            Node successor = findMin(root.right);

            root.name = successor.name;
            root.phone = successor.phone;

            root.right = delete(root.right, successor.name);
        }

        return root;
    }
}

// Main class
public class ContactSearchBST {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactBST tree = new ContactBST();

        int choice;

        do {
            System.out.println("\n===== CONTACT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Display All Contacts");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter contact name: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty!");
                        break;
                    }

                    if (tree.search(tree.root, name) != null) {
                        System.out.println("Contact already exists!");
                    }
                    else {
                        tree.root = tree.insert(tree.root, name, phone);
                        System.out.println("Contact added successfully!");
                    }
                    break;

                case 2:
                    System.out.print("Enter name to search: ");
                    String searchName = sc.nextLine().trim();

                    Node result = tree.search(tree.root, searchName);

                    if (result != null) {
                        System.out.println("\nContact Found!");
                        System.out.println("Name: " + result.name);
                        System.out.println("Phone: " + result.phone);
                    }
                    else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 3:
                    if (tree.root == null) {
                        System.out.println("Contact list is empty!");
                    }
                    else {
                        System.out.println(
                            "\n--- CONTACT LIST (Alphabetical Order) ---"
                        );

                        tree.inorder(tree.root);
                    }
                    break;

                case 4:
                    System.out.print("Enter name to delete: ");
                    String deleteName = sc.nextLine().trim();

                    if (tree.search(tree.root, deleteName) != null) {
                        tree.root = tree.delete(tree.root, deleteName);
                        System.out.println("Contact deleted successfully!");
                    }
                    else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 5:
                    System.out.println(
                        "Exiting Contact Management System..."
                    );
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}

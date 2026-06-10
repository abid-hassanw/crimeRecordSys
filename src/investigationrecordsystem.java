import java.util.*;

class CrimeRecord {
    int crimeId;
    String crimeType;
    String suspectName;
    String status;

    public CrimeRecord(int crimeId, String crimeType,
                       String suspectName, String status) {
        this.crimeId = crimeId;
        this.crimeType = crimeType;
        this.suspectName = suspectName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Crime ID: " + crimeId +
                ", Type: " + crimeType +
                ", Suspect: " + suspectName +
                ", Status: " + status;
    }
}


class BSTNode {
    CrimeRecord record;
    BSTNode left, right;

    BSTNode(CrimeRecord record) {
        this.record = record;
    }
}


class BSTTree {
    BSTNode root;

    BSTNode insert(BSTNode root, CrimeRecord record) {
        if (root == null)
            return new BSTNode(record);

        if (record.crimeId < root.record.crimeId)
            root.left = insert(root.left, record);
        else if (record.crimeId > root.record.crimeId)
            root.right = insert(root.right, record);

        return root;
    }

    public void insert(CrimeRecord record) {
        root = insert(root, record);
    }

    CrimeRecord search(BSTNode root, int crimeId) {
        if (root == null)
            return null;

        if (root.record.crimeId == crimeId)
            return root.record;

        if (crimeId < root.record.crimeId)
            return search(root.left, crimeId);

        return search(root.right, crimeId);
    }

    public CrimeRecord search(int crimeId) {
        return search(root, crimeId);
    }

    void inorder(BSTNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.record);
            inorder(root.right);
        }
    }

    public void display() {
        inorder(root);
    }
}


public class investigationrecordsystem {

    static ArrayList<CrimeRecord> crimeList = new ArrayList<>();
    static LinkedList<String> investigationLogs = new LinkedList<>();
    static HashMap<Integer, CrimeRecord> crimeMap = new HashMap<>();
    static TreeMap<String, CrimeRecord> categoryMap = new TreeMap<>();
    static TreeSet<String> crimeTypes = new TreeSet<>();
    static Stack<String> undoStack = new Stack<>();
    static Queue<CrimeRecord> pendingCases = new LinkedList<>();
    static BSTTree bst = new BSTTree();


    public static void addCrime(CrimeRecord record) {

        if (crimeMap.containsKey(record.crimeId)) {
            System.out.println("Crime ID already exists!");
            return;
        }

        crimeList.add(record);
        crimeMap.put(record.crimeId, record);
        categoryMap.put(record.crimeType + "_" + record.crimeId, record);
        crimeTypes.add(record.crimeType);
        pendingCases.add(record);
        bst.insert(record);

        investigationLogs.add("Added Crime ID: " + record.crimeId);
        undoStack.push("ADD " + record.crimeId);

        System.out.println("Crime Record Added Successfully!");
    }


    public static void searchCrime(int crimeId) {

        CrimeRecord record = crimeMap.get(crimeId);

        if (record != null)
            System.out.println(record);
        else
            System.out.println("Record Not Found!");
    }

    public static void displayAllCrimes() {
        System.out.println("\n===== ALL CRIME RECORDS (BST SORTED) =====");
        bst.display();
    }


    public static void showCrimeTypes() {
        System.out.println("\n===== UNIQUE CRIME TYPES =====");
        System.out.println(crimeTypes);
    }

    public static void showPendingCases() {
        System.out.println("\n===== PENDING CASES =====");

        if (pendingCases.isEmpty()) {
            System.out.println("No Pending Cases");
            return;
        }

        for (CrimeRecord c : pendingCases)
            System.out.println(c);
    }


    public static void showLogs() {
        System.out.println("\n===== INVESTIGATION LOGS =====");

        if (investigationLogs.isEmpty()) {
            System.out.println("No Logs Available");
            return;
        }

        for (String log : investigationLogs)
            System.out.println(log);
    }
    public static void undoOperation() {

        if (!undoStack.isEmpty())
            System.out.println("Undo Operation: " + undoStack.pop());
        else
            System.out.println("Nothing to Undo");
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=================================");
            System.out.println(" CRIME INVESTIGATION RECORD SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Crime Record");
            System.out.println("2. Search Crime Record");
            System.out.println("3. Display All Crime Records");
            System.out.println("4. Show Unique Crime Types");
            System.out.println("5. Show Pending Cases");
            System.out.println("6. Show Investigation Logs");
            System.out.println("7. Undo Last Operation");
            System.out.println("8. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Crime ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Crime Type: ");
                    String type = sc.nextLine();

                    System.out.print("Enter Suspect Name: ");
                    String suspect = sc.nextLine();

                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();

                    CrimeRecord record =
                            new CrimeRecord(id, type, suspect, status);

                    addCrime(record);
                    break;

                case 2:

                    System.out.print("Enter Crime ID to Search: ");
                    int searchId = sc.nextInt();

                    searchCrime(searchId);
                    break;

                case 3:
                    displayAllCrimes();
                    break;

                case 4:
                    showCrimeTypes();
                    break;

                case 5:
                    showPendingCases();
                    break;

                case 6:
                    showLogs();
                    break;

                case 7:
                    undoOperation();
                    break;

                case 8:
                    System.out.println("Exiting System...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
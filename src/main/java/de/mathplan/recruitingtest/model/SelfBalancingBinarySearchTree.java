package de.mathplan.recruitingtest.model;


import java.util.ArrayList;
import java.util.List;


public class SelfBalancingBinarySearchTree {

   public class Node {
         Node left, right;
        Booking booking; // Data related part
        String curriculum; // Data related part
        int max; // Data related part
        int height;

        public Node() {
            left = null;
            right = null;
            height = 0;
        }

        public Node(Booking booking, String curriculum) {
            this.curriculum = curriculum;
            this.booking = booking;
            this.height = 0;
            this.max = booking.getEndTime().getHour();
            this.left = null;
            this.right = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Booking getBooking() {
            return booking;
        }

        public void setBooking(Booking booking) {
            this.booking = booking;
        }

        public String getCurriculum() {
            return curriculum;
        }

        public void setCurriculum(String curriculum) {
            this.curriculum = curriculum;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }
    //    Constructore

    public SelfBalancingBinarySearchTree() {
        root = null;
    }

    /* Function to get height of node */
    public int height(Node t) {
        return t == null ? -1 : t.height;
    }

    public void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    public Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        updateMax(x);
        updateMax(y);
        return x;
    }

    public Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        updateMax(y);
        updateMax(x);
        return x;
    }

    void updateMax(Node n){
        int result = n.getBooking().getEndTime().getHour();
        if (n.left != null) {
            if (n.left.getMax() > result) {
                result = n.left.getMax();
            }
        }
        if (n.right != null) {
            if (n.right.getMax() > result) {
                result = n.right.getMax();
            }
        }
        n.setMax(result);

    }

    public Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    public void insertRec(Booking booking, String curriculum){
        root = insert(root, booking, curriculum);
    }
    public Node insert(Node node, Booking booking, String curriculum) {
        if (node == null) {
            return new Node(booking, curriculum);
        } else if (node.booking.getStartTime().getHour() >= booking.getStartTime().getHour()) {
            if (node.max <= booking.endTime.getHour()){
                node.setMax(booking.endTime.getHour());
            }
            node.left = insert(node.left, booking, curriculum);
        }
        else {
            if (node.max <= booking.endTime.getHour()){
                node.setMax(booking.endTime.getHour());
            }
            node.right = insert(node.right, booking, curriculum);
        }
        return rebalance(node);
    }

    public Node delete(Node node, Booking booking) {
        if (node == null) {
            return null;
        }
        else if (node.booking.equals(booking)) {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            }
            else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.booking = mostLeftChild.booking;
                node.curriculum = mostLeftChild.curriculum;
                node.right = delete(node.right, node.booking);
            }
        }
        else if (node.booking.getStartTime().getHour() >= booking.getStartTime().getHour()) {
            node.left = delete(node.left, booking);
        }
        else if (node.booking.getStartTime().getHour() < booking.getStartTime().getHour()) {
            node.right = delete(node.right, booking);
        }

        if (node != null) {
            node = rebalance(node);
            updateMax(node);
        }
        return node;
    }

    public Node mostLeftChild(Node node) {
        Node result = node;
        while (result.left != null) {
            result = result.left;
        }
        return result;
    }

    public int findMax(Node node) {
        if (node == null) {
            return -1;
        }
        int maxLeft = findMax(node.left);
        int maxRight = findMax(node.right);
        int maxx = Math.max(maxLeft, maxRight);
        return Math.max(node.max, maxx);

    }

    public int doesOverlap(Node node, Booking booking, String curriculum) {

        if (!(node.booking.equals(booking))) {
            if ((node.booking.getStartTime().getHour() <= booking.getEndTime().getHour() &&
                    node.booking.getEndTime().getHour() >= booking.getStartTime().getHour()) ||
                    (node.booking.getStartTime().getHour() == booking.getStartTime().getHour() &&
                            node.booking.getEndTime().getHour() == booking.getEndTime().getHour())) {

                if (node.booking.getWeekday().equals(booking.getWeekday())) {
                    if (node.booking.getRoom().equals(booking.getRoom())) {
                        if (node.curriculum.equals(curriculum)) {
                            return 3;
                        }
                        return 2;
                    }
                    if (node.curriculum.equals(curriculum)) {
                        return 1;
                    }

                }

            }
        }
        return 0;
    }

    public List<Conflict> findOverlappingIntervals(Node root, Booking booking, String curriculum) {
        List<Conflict> conflicts = new ArrayList<>();
        Node helper = root;
        while (helper != null) {
//            System.out.println(booking.getStartTime() + " " + booking.getEndTime()  + " " +
//                    booking.getRoom()+ " " +booking.getWeekday() +" -- " +
//                    helper.getBooking().getStartTime() + " " + helper.getBooking().getEndTime()+
//                    " "+ helper.getBooking().getRoom()+ " " +helper.getBooking().getWeekday()  );

            if (doesOverlap(helper, booking, curriculum) == 0) {
//                System.out.println(0);
                if (helper.left != null && helper.left.max > booking.getStartTime().getHour()) {
                    helper = helper.left;
                } else
                    helper = helper.right;

            } else if (doesOverlap(helper, booking, curriculum) == 1) {
//                System.out.println(1);
                conflicts.add(new Conflict("Curricular Conflict", booking, helper.booking));
                if (helper.left != null && helper.left.max > booking.getStartTime().getHour()) {
                    helper = helper.left;
                } else helper = helper.right;
            } else if (doesOverlap(helper, booking, curriculum) == 2) {
//                System.out.println(2);
                conflicts.add(new Conflict("Room Conflict", booking, helper.booking));
                if (helper.left != null && helper.left.max > booking.getStartTime().getHour()) {
                    helper = helper.left;
                } else helper = helper.right;
            } else if (doesOverlap(helper, booking, curriculum) == 3) {
//                System.out.println(3);
                conflicts.add(new Conflict("Curricular Conflict", booking, helper.booking));
                conflicts.add(new Conflict("Room Conflict", booking, helper.booking));
                if (helper.left != null && helper.left.max > booking.getStartTime().getHour()) {
                    helper = helper.left;
                } else helper = helper.right;
            }
        }
        return conflicts;
    }

    public void inorder(Node r) {
        if (r != null) {
            inorder(r.left);
            System.out.print("( "+ (r.left!=null ? r.left.getBooking().getWeekday() : " ")+ "---" + r.getBooking().getWeekday() + ": " + r.max +", "+r.getBooking().getEndTime() +"---"
                    +(r.right!=null ? r.right.getBooking().getWeekday() : " ")+" )");

            inorder(r.right);
        }
    }
    public void inorderr(Node r) {
        if (r != null) {
            inorderr(r.left);
            System.out.print("( "+r.getLeft()+" " + r.getBooking().getWeekday() + ": " + r.booking.endTime + " )");
            inorderr(r.right);
        }
    }



}

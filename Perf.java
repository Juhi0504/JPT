public class Perf {
    public static void main(String[] args) {
        int n = 10;
        // declare an array of n
        String[] users = new String[n];

        for(int i=0; i<n; i++) {
            users[i] = "User" + (i+1);
            System.out.println(users[i]);
        }
        System.out.println("Done");
    }
}

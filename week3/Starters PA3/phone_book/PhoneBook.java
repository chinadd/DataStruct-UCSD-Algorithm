import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

    class DirectAddressingTable
    {
        private String[] arr ;
        private final int DEFAULT_CAPACITY = 10000001;
     
        /* Constructor */
        public DirectAddressingTable()
        {
            arr = new String[DEFAULT_CAPACITY];
        }
        /* Constructor */
        public DirectAddressingTable(int capacity)
        {
            arr = new String[capacity + 1];
        }
        /* function to insert */
        public void insert(int k, String d)
        {
            arr[k] = d;
        }
        /* function to delete */
        public void delete(int k)
        {
            arr[k] = null;
        }
        /* function to get value */
        public String get(int k)
        {
            return arr[k];
        }
        /* function to clear */
        public void clear()
        {
            int l = arr.length;
            arr = new String[l];
        }
        /* function to print */
        public void printTable()
        {
            System.out.println("\nDirect Addressing Table : ");
            int l = arr.length;
            for (int i = 0; i < l; i++)
                if (arr[i] != null)
                    System.out.println(i +" "+ arr[i]);
            System.out.println();        
        }
    }

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    
    // hash table for contact
    DirectAddressingTable dat = new DirectAddressingTable();

    public static void main(String[] args) {

        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }


    private void processQuery(Query query) {

        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name
            boolean wasFound = false;
            
            if (dat.arr[query.number] != null) {
                    
                dat.arr[query.number] = query.name;
                wasFound = true;
            }

            // otherwise, just add it
            if (!wasFound)
                dat.arr[query.number]=query.name;
        } else if (query.type.equals("del")) {
            if (dat.arr[query.number] != null){
                dat.arr[query.number] = null;
            }
        } else {
            String response;
            if (dat.arr[query.number] == null){
                response = "not found";
            }else{
                response = dat.arr[query.number];
            }
           
            writeResponse(response);
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }


    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {

        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

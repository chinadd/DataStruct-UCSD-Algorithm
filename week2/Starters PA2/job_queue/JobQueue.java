import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;
    // This is a list of list that contains index of workers and its next free time
    private int[][] workers;
    private int m;
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
        workers = new int[numWorkers][2];
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void sift_up(int idx){
        
      
        int[] latest = workers[idx];
        
        // getting parent index
        int parent = idx / 2;
        if (idx%2 == 0) {
            parent -= 1;
        }
        if (parent <0) {
            return;
        }
        if ( (latest[1] < workers[parent][1]) || ((latest[1] == workers[parent][1]) && (latest[0] < workers[parent][0])) ){
            int tmp0 = workers[idx][0];
            int tmp1 = workers[idx][1];
            workers[idx][0] = workers[parent][0];
            workers[idx][1] = workers[parent][1];
            workers[parent][0] = tmp0;
            workers[parent][1] = tmp1; 
            sift_up(parent);
        }
    }

    private void sift_down(int idx){
        int minimum = idx;

        int leftChild = 2*idx + 1;
        if ((leftChild < workers.length) && ((workers[leftChild][1] < workers[idx][1]) || (workers[leftChild][1] == workers[idx][1] && workers[leftChild][0] < workers[idx][0]))){
            minimum = leftChild;
        }

        int rightChild = 2*idx + 2;
        if ((rightChild < workers.length) && ((workers[rightChild][1] < workers[idx][1]) || (workers[rightChild][1] == workers[idx][1] && workers[rightChild][0] < workers[idx][0]))){
            minimum = rightChild;
        }

        if (minimum != idx){
            int tmp0 = workers[idx][0];
            int tmp1 = workers[idx][1];
            workers[idx][0] = workers[minimum][0];
            workers[idx][1] = workers[minimum][1];
            workers[minimum][0] = tmp0;
            workers[minimum][1] = tmp1; 
            sift_down(minimum);
        }
    }

    private void insert(int idx, int nextFreeTime){
        
        workers[idx][0] = idx;
        workers[idx][1] = nextFreeTime;
        sift_up(idx);
    }

    private int[] update(int nft){
        int[] n = workers[0];
        out.println("hello world");
        n[1] += nft;
        sift_down(0);
        return n;
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        
        for (int i = 0; i < numWorkers; i++) {
            insert(i,0);
        }
        for (int i= 0; i< m ; i++){
            update(jobs[i]);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        //writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}') 
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        
        int left = 0;
        int right = 0;
        int[] matchright = new int[text.length()];  
        int rightmatch = 0; //count matched right half

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.add(new Bracket(next,position));
                left += 1;
            }

            if (next == ')' || next == ']' || next == '}') {
            	right += 1;
            	
                // Process closing bracket, write your code here
                if(opening_brackets_stack.isEmpty()){
                        System.out.println(position+1);                        
                        break;
                    }
                    
                    else if (opening_brackets_stack.peek().Match(next)){
                        opening_brackets_stack.pop();
                        left -= 1;
                        right -=1;
                        matchright[rightmatch] = position;
                        rightmatch +=1;

                    }else {
                        System.out.println(position+1);                        
                        break;
                        
                    }
            }
        }

        // Printing answer, write your code here
        if (left ==0 && right ==0){
                System.out.println("Success");
                
            }
        if (left!=0 && right ==0){
        	
        	System.out.println(opening_brackets_stack.peek().position+1);
        }

    }
}

package Days.March;

import java.util.Stack;

public class maxDepthAfterSplit5 {

    public static int[] maxdepth(String seq){
        char[] s = seq.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        int[] deep = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '(') {
                stack.push(s[i]);
                deep[i] = stack.size();
            }else{
            deep[i] = stack.size();
            stack.pop();}
        }
        for (int i = 0; i <deep.length ; i++) {
            if (deep[i] % 2 != 0) {
                deep[i] = 0;
            }else{deep[i]=1;}
        }
        return deep;
    }

    public static void main(String[] args) {

        int[] result = maxdepth("(()())");
        for (int i :
                result) {
            System.out.println(i);
        }
    }
}

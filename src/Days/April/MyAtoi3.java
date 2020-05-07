package Days.April;

public class MyAtoi3 {
    public static int myAtoi(String str){
        str=str.trim();
        char[] symbol = str.toCharArray();

        boolean flag = true;
        int i = 0;
        while(i<symbol.length&&(symbol[i]<48 || symbol[i]>57)){
            i++;
        }
        if(i==symbol.length||i>=2)
            return 0;
        if (i - 1 >= 0 && (symbol[i - 1] != '+'||symbol[i - 1] != '+')) {
            return 0;
        }
        if(i>0)flag = symbol[i - 1] == '-' ? false : true;
        if(i == 0) flag = symbol[i] == '-' ? false : true;
        str = Character.toString(symbol[i++]);
        while (i<symbol.length && symbol[i] >= 48 && symbol[i] <= 57) {
            str += symbol[i++];
        }
        str=flag?str:'-'+str;
        int INT_MIN= (int) - Math.pow(2,31);
        int INT_MAX= (int) (Math.pow(2,31)-1);
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return flag==true?INT_MAX:INT_MIN;
        }

    }

    public static void main(String[] args) {

        System.out.println(myAtoi("     -.41"));
    }
}

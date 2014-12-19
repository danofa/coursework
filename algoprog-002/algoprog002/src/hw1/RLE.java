package hw1;

/**
 *
 * @author dm
 */
public class RLE {
    
    public static int longueurRLE(int[] t){
        if(t == null)
            throw new IllegalArgumentException();
        
        int length = 0;
        int current = -1;
        for(int i : t){
            if(current != i){
                length++;
            }
            current = i;
        }
        return length * 2;
    }
    
    public static int[] RLE(int[] t){
        int[] result = new int[longueurRLE(t)];

        if(t == null) throw new IllegalArgumentException();
        
        if(t.length == 0) return new int[]{};

        int last = t[0];
        int index = 0;
        int count = 0;
        
        for(int i = 0; i < t.length; i++){
            if(last != t[i]){
                result[index] = last;
                result[++index] = count;
                index++;
                count = 0;
            } 
            if(i+1 == t.length){
                result[index] = t[i];
                result[++index] = ++count;
            }
            count++;
            last = t[i];
        }
        
        return result;
    }

    public static int longueurRLEInverse(int[] t) {
        if(t.length % 2 != 0) throw new IllegalArgumentException();
        
        int result = 0;
        for(int i = 0; i+1 < t.length; i+=2){
            result += t[i+1];
        }
        return result;
    }
    
    public static int[] RLEInverse(int[] t){
        int[] result = new int[longueurRLEInverse(t)];
        int index = 0;
        
        for(int i = 0; i+1 < t.length; i+=2){
            for(int x = 0; x < t[i+1]; x++, index++){
                result[index] = t[i];
            }
        }

        return result;
    }
    
}

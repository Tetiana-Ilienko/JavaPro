package app;

public class Main {
    // 5 -> [1, 2 , 3, 4, 5]
    // 3 -> [1, 2 , 3]

    public static int[] getArray(int size){
        if (size <0 ){
            throw new IllegalArgumentException("Размер массива не может быть отрицательным");
        }
        int [] result = new int [size];
        for (int i = 0; i < result.length; i++) {
            result[i] = i+1;

        }
        return result;
    }
}

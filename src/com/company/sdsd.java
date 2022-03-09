package com.company;


import java.util.Arrays;
import java.util.Vector;

class Combinations
{
    private static final int M = 4;
    private static final int N = 16;
    public static int[] GenerateCombinations(int[] arr)
    {
        if (arr == null)
        {
            arr = new int[M];
            for (int i = 0; i < M; i++)
                arr[i] = i + 1;
            return arr;
        }
        for (int i = M - 1; i >= 0; i--)
            if (arr[i] < N - M + i + 1)
            {
                arr[i]++;
                for (int j = i; j < M - 1; j++)
                    arr[j + 1] = arr[j] + 1;
                return arr;
            }
        return null;
    }

    public static void FindingTheMinimumVector(Functions F){
        int a = 0;
        int[] arr = null;
        while ((arr = GenerateCombinations(arr)) != null) {
            NeuralNetwork network = new NeuralNetwork();
            Vector<Boolean> vector = new Vector<>();
            network.Logistic(F.MinVector(arr),F.MinFun(arr));
            for (boolean[] i:F.GetTableFunction()) {
                vector.add(network.PrintResult(i[0],i[1],i[2],i[3]));
            }
            if (vector.equals(F.GetBooleanFunctionValues())){
                a = a+1;
                if (a ==4)
                    System.out.println(Arrays.toString(arr)); //3 7 12 14
            }
        }
    }
}

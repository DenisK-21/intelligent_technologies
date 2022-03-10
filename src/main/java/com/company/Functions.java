package com.company;

import java.util.Vector;


public class Functions {
    private final boolean[][] matrix;
    private  final Vector<Boolean> Fun;
    Functions(int number){
        int lines = (int) Math.pow(2, number);
        Fun = new Vector<>();
        matrix = new boolean[lines][number];
        for (int i = 1; i <= number; i++) {
            boolean controller = false;
            for (int j = 1; j <= lines; j++) {
                matrix[j - 1][i - 1] = controller;
                if (j % (lines / (Math.pow(2, i))) == 0) {
                    controller ^= true;
                }
            }
        }
    }
    public void BooleanFunctionValues(String function){
        for (char i: function.toCharArray()) {
            if (i == '0') {
                Fun.add(false);
            }
            else if (i == '1')
                Fun.add(true);
        }
    }
    public Vector<Boolean> GetBooleanFunctionValues(){
        return Fun;
    }
    public boolean[][] GetTableFunction(){
        return matrix;
    }

    public boolean[][] MinVector(int[] arr){
        boolean[][] minM = new boolean[arr.length][4];
        for (int i = 0; i < minM.length; i++) {
            minM[i] = matrix[arr[i]-1];
        }
        return minM;
    }
    public Vector<Boolean> MinFun(int[] arr){

       Vector<Boolean> MinFun = new Vector<>();
        for (int ar: arr) {
            MinFun.add(Fun.get(ar - 1));
        }
        return MinFun;
    }
}

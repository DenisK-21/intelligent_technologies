package com.company;
import java.util.Scanner;


public class Main {   //0001001100110011
    public static void GetPrint(NeuralNetwork network) {
        for (String string : network.GetTable()) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) { //0001001100110011
        Functions booleanFunction = new Functions(4);
        Scanner in = new Scanner(System.in);
        System.out.print("enter the function vector: ");
        String Func = in.nextLine();
        booleanFunction.BooleanFunctionValues(Func);

        //Обучение НС с использованием всех комбинаций переменных используя пороговую ФА
        NeuralNetwork neural1 = new NeuralNetwork();
        neural1.Threshold(booleanFunction.GetTableFunction(),booleanFunction.GetBooleanFunctionValues());
        GetPrint(neural1);
        try {
            neural1.Draw();
        } catch (Exception E){
            System.err.print(E.getMessage());
        }

        //Обучение НС с использованием всех комбинаций переменных используя логистическую ФА
        NeuralNetwork neural2 = new NeuralNetwork();
        neural2.Logistic(booleanFunction.GetTableFunction(),booleanFunction.GetBooleanFunctionValues());
        GetPrint(neural2);

        try {
            neural2.Draw();
        } catch (Exception E){
            System.err.print(E.getMessage());
        }

        //Обучение НС с использованием части комбинаций переменных используя пороговую ФА
        NeuralNetwork neural3 = new NeuralNetwork();
        neural3.Threshold(booleanFunction.MinVector(new int[]{3, 8, 11, 14}),booleanFunction.MinFun(new int[]{3, 8, 11, 14}));
        GetPrint(neural3);

        try {
            neural3.Draw();
        } catch (Exception E){
            System.err.print(E.getMessage());
        }

        //Обучение НС с использованием части комбинаций переменных используя логистическую ФА
        NeuralNetwork neural4 = new NeuralNetwork();
        neural4.Logistic(booleanFunction.MinVector(new int[]{3, 7, 12, 14}),booleanFunction.MinFun(new int[]{3, 7, 12, 14}));
        GetPrint(neural4);

        try {
            neural4.Draw();
        } catch (Exception E){
            System.err.print(E.getMessage());
        }

        /*for (boolean[] i:booleanFunction.GetTableFunction()) {
            System.out.print(neural4.PrintResult(i[0],i[1],i[2],i[3]) ? 1 :0);
        }*/
    }
}


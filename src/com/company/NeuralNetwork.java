package com.company;

import java.util.Vector;

public class NeuralNetwork {
    private final Vector<String> table;
    private double w0,w1,w2,w3,w4;
    private int era;

    NeuralNetwork(){
        w0 = w1 = w2 = w3 = w4 = 0;
        era = 0;
        table = new Vector<>();
        table.add("№ |      Vector Weight W             |            Output vector Y                       | error E |");
    }

    private void Fit(boolean[][] variables, Vector<Boolean> F, boolean check){
        int errors;
        do {
            String info = String.format("%d   (%.3f, %.3f, %.3f, %.3f, %.3f)    (", era, w0, w1, w2, w3, w4);
            errors = 0;
            for (int i = 0; i < F.size(); i++) {
                double net = w1*(variables[i][0]?1:0) + w2*(variables[i][1]?1:0) + w3*(variables[i][2]?1:0)
                        + w4*(variables[i][3]?1:0) + w0;
                int error;
                double fNetLog = 0;
                if (check) {
                    boolean fNet = net >= 0;
                    info = info.concat(String.format("%d, ", fNet ? 1 : 0));
                    boolean t = F.get(i);
                    error = (t ? 1 : 0) - (fNet ? 1 : 0);
                } else {
                    fNetLog = 1 / (1 + Math.exp(-net));
                    boolean yOut = Math.round(fNetLog) == 1;
                    info = info.concat(String.format("%d, ", yOut ? 1 : 0));
                    boolean t = F.get(i);
                    error = (t ? 1 : 0) -(yOut ? 1 : 0);
                }

                if (error != 0 ){
                    double lamdaW;
                    if (check)
                        lamdaW = 0.3*error;
                    else
                        lamdaW = 0.3*error*Math.abs(fNetLog * (1-fNetLog));
                    w0 += lamdaW;
                    w1 += lamdaW*(variables[i][0]?1:0);
                    w2 += lamdaW*(variables[i][1]?1:0);
                    w3 += lamdaW*(variables[i][2]?1:0);
                    w4 += lamdaW*(variables[i][3]?1:0);
                    errors +=1;
                }
            }
            era +=1;
            info = info.substring(0,info.length()-2);
            info = info.concat(String.format(")    E = %d",errors));
            table.add(info);
        } while (errors != 0);
    }


    public void Threshold(boolean[][] variables, Vector<Boolean> F) {
        Fit(variables,F,true);
    }
    public void Logistic(boolean[][] variables, Vector<Boolean> F) {
        Fit(variables,F,false);
    }

    public boolean PrintResult(boolean x1, boolean x2, boolean x3, boolean x4){
        double net = w1*(x1?1:0) + w2*(x2 ? 1 : 0) + w3*(x3?1:0)
                + w4*(x4?1:0) +w0;
        return net >= 0;
    }
    public Vector<String> GetTable() {
        return table;
    }
}


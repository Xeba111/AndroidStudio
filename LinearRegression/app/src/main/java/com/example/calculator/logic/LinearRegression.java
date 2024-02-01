package com.example.calculator.logic;

import java.util.LinkedList;

public class LinearRegression {
    private LinkedList<Float> x;
    private LinkedList<Float> y;
    private int n;
    public Float a, b, r;

    public LinearRegression(LinkedList<Float> x, LinkedList<Float> y) {
        this.x = x;
        this.y = y;
        n = x.size();
        lineal();
        correlacion();
    }

    public void lineal() {
        Float pxy, sx, sy, sx2, sy2;
        pxy = sx = sy = sx2 = sy2 = 0.0f;
        for (int i = 0; i < n; i++) {
            sx += x.get(i);
            sy += y.get(i);
            sx2 += x.get(i) * x.get(i);
            sy2 += y.get(i) * y.get(i);
            pxy += x.get(i) * y.get(i);
        }
        b = (n * pxy - sx * sy) / (n * sx2 - sx * sx);
        a = (sy - b * sx) / n;
    }

    public Float correlacion() {

        Float suma = 0.0f;
        for (int i = 0; i < n; i++) {
            suma += x.get(i);
        }
        Float mediaX = suma / n;

        suma = 0.0f;
        for (int i = 0; i < n; i++) {
            suma += x.get(i);
        }
        Float mediaY = suma / n;

        Float pxy, sx2, sy2;
        pxy = sx2 = sy2 = 0.0f;
        for (int i = 0; i < n; i++) {
            pxy += (x.get(i) - mediaX) * (y.get(i) - mediaY);
            sx2 += (x.get(i) - mediaX) * (x.get(i) - mediaX);
            sy2 += (y.get(i) - mediaY) * (y.get(i) - mediaY);
        }
        r = Float.parseFloat(pxy / Math.sqrt(sx2 * sy2) + "");
        return r;
    }

}

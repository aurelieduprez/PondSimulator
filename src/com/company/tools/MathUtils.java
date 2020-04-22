package com.company.tools;

import java.awt.geom.Point2D.Double;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class MathUtils {

    public static Double translate2D(double x, double y, double angle, int moveSpeed){
        double radian = Math.toRadians(angle);
        double x1 = x + cos(radian) * moveSpeed;
        double y1 = y + sin(radian) * moveSpeed;

        Double res = new Double(x1, y1);
        return res;
    }
    public static Vector2D reflect(Vector2D vector, Vector2D normal)
    {
        // from: return vector - 2 * Vector2.Dot(vector, normal) * normal;
        return vector.minus(vector.scaleTo(2 * vector.getScalarProduct(normal)));
    }


}



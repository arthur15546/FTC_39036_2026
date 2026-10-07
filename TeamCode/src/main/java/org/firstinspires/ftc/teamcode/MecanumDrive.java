package org.firstinspires.ftc.teamcode;

public class MecanumDrive {
    double[] calculate(double lY, double lX, double rX){
        double vFL = lY + lX + rX;
        double vFR = lY - lX - rX;
        double vBL = lY - lX + rX;
        double vBR = (lY + lX) - rX;

        double max = Math.max(
                Math.abs(vFL),
                Math.max(
                        Math.abs(vFR),
                        Math.max(
                                Math.abs(vBL),
                                Math.abs(vBR)
                        )
                )
        );
        if(max > 1){
            vFL /= max;
            vFR /= max;
            vBL /= max;
            vBR /= max;
        }
        return new double[]{vFL,vFR, vBL, vBR};
    }
}

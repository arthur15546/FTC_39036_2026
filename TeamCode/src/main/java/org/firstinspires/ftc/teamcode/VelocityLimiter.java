package org.firstinspires.ftc.teamcode;
public class VelocityLimiter {
    double marcha=0;
    public double[] cauculate(double[] velocity, boolean rightBumper, boolean leftBumper){
        if(rightBumper){
            if(marcha < 1.0) {
                marcha += 0.25;
            }
        } else if (leftBumper){
            if(marcha > 0.25) {
                marcha -= 0.25;
            }
        }

        for(int i=0;i<4;i++){
            velocity[i] *= marcha;
        }

        return velocity;
    }
}

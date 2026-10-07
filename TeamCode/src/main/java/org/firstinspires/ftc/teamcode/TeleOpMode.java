package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOpMode extends OpMode {
    MecanumDrive mecanum = new MecanumDrive();
    VelocityLimiter limiter = new VelocityLimiter();
    double lY=0,lX=0,rX=0;

    @Override
    public  void init(){

    }
    @Override
    public void loop(){
        lY=-gamepad1.left_stick_y;
        lX=gamepad1.left_stick_x;
        rX=gamepad1.right_stick_x;

        double[] vel = mecanum.cauculate(lY, lX, rX);

        vel = limiter.cauculate(
                vel,
                gamepad1.rightBumperWasPressed(),
                gamepad1.leftBumperWasPressed()
        );







    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOpMode extends OpMode {
    MecanumDrive mecanum = new MecanumDrive();
    VelocityLimiter limiter = new VelocityLimiter();
    double lY=0,lX=0,rX=0;

    Motor motorFL = new Motor();
    Motor motorFR = new Motor();
    Motor motorBL = new Motor();
    Motor motorBR = new Motor();

    @Override
    public  void init(){
        motorFL.init(hardwareMap, "motorFL");
        motorFR.init(hardwareMap, "motorFR");
        motorBL.init(hardwareMap, "motorBL");
        motorBR.init(hardwareMap, "motorBR");
    }
    @Override
    public void loop(){
        lY=-gamepad1.left_stick_y;
        lX=gamepad1.left_stick_x;
        rX=gamepad1.right_stick_x;

        double[] vel = mecanum.calculate(lY, lX, rX);

        vel = limiter.calculate(
                vel,
                gamepad1.rightBumperWasPressed(),
                gamepad1.leftBumperWasPressed()
        );

        motorFL.setMotorSpeed(vel[0], false);
        motorFR.setMotorSpeed(vel[1], true);
        motorBL.setMotorSpeed(vel[2], true);
        motorBR.setMotorSpeed(vel[3], false);
    }
}

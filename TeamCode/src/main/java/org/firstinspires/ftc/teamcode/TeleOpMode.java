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

    Motor colector = new Motor();
    int colectorGiro = 0;
    boolean reverseFL = false;
    boolean reverseFR = true;
    boolean reverseBL = false;
    boolean reverseBR = true;


    @Override
    public  void init(){
        motorFL.init(hardwareMap, "motorFL");
        motorFR.init(hardwareMap, "motorFR");
        motorBL.init(hardwareMap, "motorBL");
        motorBR.init(hardwareMap, "motorBR");
        colector.init(hardwareMap, "coletor");
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
        if(gamepad1.b){
            motorFL.setMotorSpeed(0, reverseFL);
            motorFR.setMotorSpeed(0, reverseFR);
            motorBL.setMotorSpeed(0, reverseBL);
            motorBR.setMotorSpeed(0, reverseBR);
        }
        else {
            motorFL.setMotorSpeed(vel[0], reverseFL);
            motorFR.setMotorSpeed(vel[1], reverseFR);
            motorBL.setMotorSpeed(vel[2], reverseBL);
            motorBR.setMotorSpeed(vel[3], reverseBR);
        }

        if(gamepad1.dpad_up) colectorGiro = 1;
        else if(gamepad1.dpad_down) colectorGiro = -1;
        else if(gamepad1.dpad_right) colectorGiro = 0;

        colector.setMotorSpeed(colectorGiro, true);

    }
}

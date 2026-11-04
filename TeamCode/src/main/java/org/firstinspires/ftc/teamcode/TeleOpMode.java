package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

@TeleOp
public class TeleOpMode extends OpMode {
    MecanumDrive mecanum = new MecanumDrive();
    VelocityLimiter limiter = new VelocityLimiter();
    double lY=0,lX=0,rX=0;

    Motor motorFL = new Motor();
    Motor motorFR = new Motor();
    Motor motorBL = new Motor();
    Motor motorBR = new Motor();

    Motor climb = new Motor();
    double climbGiro = 0;

    Motor colector = new Motor();
    double colectorGiro = 0;
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
        climb.init(hardwareMap,"climb");


        //http://192.168.43.1:8080/dash
        telemetry = new MultipleTelemetry(
                telemetry,
                FtcDashboard.getInstance().getTelemetry()
        );
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
        if(gamepad1.b) {
            vel[0] = 0;
            vel[1] = 0;
            vel[2] = 0;
            vel[3] = 0;
        }
        motorFL.setMotorSpeed(vel[0], reverseFL);
        motorFR.setMotorSpeed(vel[1], reverseFR);
        motorBL.setMotorSpeed(vel[2], reverseBL);
        motorBR.setMotorSpeed(vel[3], reverseBR);

        if(gamepad1.dpad_up) {
            colectorGiro = 1;
            climbGiro = 0.5;
        }
        else if(gamepad1.dpad_down){
            colectorGiro = -1;
            climbGiro = -0.5;
        }
        else if(gamepad1.dpad_right){
            colectorGiro = 0;
            climbGiro = 0;
        }
        else if(gamepad1.dpad_left){
            colectorGiro = -1;
        }

        colector.setMotorSpeed(colectorGiro, true);
        climb.setMotorSpeed(climbGiro, false);



        telemetry.addData("lY", "%.3f", lY);
        telemetry.addData("lX", "%.3f", lX);
        telemetry.addData("rX", "%.3f", rX);

        telemetry.addData("FL", "%.3f", vel[0]);
        telemetry.addData("FR", "%.3f", vel[1]);
        telemetry.addData("BL", "%.3f", vel[2]);
        telemetry.addData("BR", "%.3f", vel[3]);

        telemetry.update();
    }
}

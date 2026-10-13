package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class TeleOpIMU extends OpMode {
    MecanumDrive mecanum = new MecanumDrive();
    VelocityLimiter limiter = new VelocityLimiter();
    double lY=0,lX=0,rX=0;

    Motor motorFL = new Motor();
    Motor motorFR = new Motor();
    Motor motorBL = new Motor();
    Motor motorBR = new Motor();

    boolean reverseFL = false;
    boolean reverseFR = true;
    boolean reverseBL = false;
    boolean reverseBR = true;

    IMU imu;

    @Override
    public  void init(){
        motorFL.init(hardwareMap, "motorFL");
        motorFR.init(hardwareMap, "motorFR");
        motorBL.init(hardwareMap, "motorBL");
        motorBR.init(hardwareMap, "motorBR");

        imu = hardwareMap.get(IMU.class, "imu");

        telemetry.addData("IMU", imu != null ? "Encontrado" : "Nulo");
        telemetry.update();

        imu.initialize(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )
        ));
    }
    @Override
    public void start() {
        imu.resetYaw();
    }
    @Override
    public void loop(){
        lY=-gamepad1.left_stick_y;
        lX=gamepad1.left_stick_x;
        rX=gamepad1.right_stick_x;

        double heading = imu.getRobotYawPitchRollAngles()
                .getYaw(AngleUnit.RADIANS);

        double rotX = lX * Math.cos(-heading) - lY * Math.sin(-heading);
        double rotY = lX * Math.sin(-heading) + lY * Math.cos(-heading);

        double[] vel = mecanum.calculate(rotY, rotX, rX);

        vel = limiter.calculate(
                vel,
                gamepad1.rightBumperWasPressed(),
                gamepad1.leftBumperWasPressed()
        );



        motorFL.setMotorSpeed(vel[0], reverseFL);
        motorFR.setMotorSpeed(vel[1], reverseFR);
        motorBL.setMotorSpeed(vel[2], reverseBL);
        motorBR.setMotorSpeed(vel[3], reverseBR);

        telemetry.addData("Heading", heading);
        telemetry.addData("lX original", lX);
        telemetry.addData("lY original", lY);
        telemetry.addData("rotX", rotX);
        telemetry.addData("rotY", rotY);
        telemetry.update();

    }
}

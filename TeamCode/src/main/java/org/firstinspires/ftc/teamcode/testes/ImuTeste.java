package org.firstinspires.ftc.teamcode.testes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;

@TeleOp
public class ImuTeste extends OpMode {
    private IMU imu;

    @Override
    public void init() {
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
    public void loop() {
        YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();

        telemetry.addData("Yaw", angles.getYaw(AngleUnit.DEGREES));
        telemetry.addData("Pitch", angles.getPitch(AngleUnit.DEGREES));
        telemetry.addData("Roll", angles.getRoll(AngleUnit.DEGREES));

        AngularVelocity velocidade =
                imu.getRobotAngularVelocity(AngleUnit.DEGREES);

        telemetry.addData("Yaw",
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

        telemetry.addData("Velocidade Z", velocidade.zRotationRate);
        telemetry.update();
    }
}
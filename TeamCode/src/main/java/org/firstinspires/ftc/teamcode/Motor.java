package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Motor {
    private DcMotor motor;

    public void init(HardwareMap hardwareMap, String motorName) {
        motor = hardwareMap.get(DcMotor.class, motorName);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setMotorSpeed(double speed) {
        motor.setPower(speed);
    }
}
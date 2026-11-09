package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Motor;

@Autonomous
public class AutonomusMode extends OpMode {
    Motor motorFL = new Motor();
    Motor motorFR = new Motor();
    Motor motorBL = new Motor();
    Motor motorBR = new Motor();

    ElapsedTime timer = new ElapsedTime();

    @Override
    public void init(){
        motorFL.init(hardwareMap, "motorFL");
        motorFR.init(hardwareMap, "motorFR");
        motorBL.init(hardwareMap, "motorBL");
        motorBR.init(hardwareMap, "motorBR");
    }

    @Override
    public void start(){
        timer.reset();
    }

    @Override
    public void loop(){

        if (timer.seconds() < 0.5) {
            motorFL.setMotorSpeed(1, false);
            motorFR.setMotorSpeed(1, true);
            motorBL.setMotorSpeed(1, false);
            motorBR.setMotorSpeed(1, true);
        } else {
            motorFL.setMotorSpeed(0, false);
            motorFR.setMotorSpeed(0, true);
            motorBL.setMotorSpeed(0, false);
            motorBR.setMotorSpeed(0, true);
        }
    }
}

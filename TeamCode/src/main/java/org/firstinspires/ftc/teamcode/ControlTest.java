package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ControlTest extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        // Analógico Esquerdo
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);

        // Analógico Direito
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Right Stick Y", gamepad1.right_stick_y);

        // Botões de Letras (A, B, X, Y)
        telemetry.addData("Button A", gamepad1.a);
        telemetry.addData("Button B", gamepad1.b);
        telemetry.addData("Button X", gamepad1.x);
        telemetry.addData("Button Y", gamepad1.y);

        // Gatilhos (Triggers - variam de 0.0 a 1.0)
        telemetry.addData("Left Trigger", gamepad1.left_trigger);
        telemetry.addData("Right Trigger", gamepad1.right_trigger);

        // Botões de Ombro (Bumpers - verdadeiro ou falso)
        telemetry.addData("Left Bumper", gamepad1.left_bumper);
        telemetry.addData("Right Bumper", gamepad1.right_bumper);

        // star e back
        telemetry.addData("star:", gamepad1.start);
        telemetry.addData("back:", gamepad1.back);
    }
}

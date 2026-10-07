package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOpRevision extends OpMode {

    MecanumDrive mecanum = new MecanumDrive();
    VelocityLimiter limiter = new VelocityLimiter();

    // =====================================================
    // ENTRADAS DO CONTROLE
    // =====================================================

    double lY = 0;
    double lX = 0;
    double rX = 0;


    // =====================================================
    // MOTORES
    // =====================================================

    Motor motorFL = new Motor();
    Motor motorFR = new Motor();
    Motor motorBL = new Motor();
    Motor motorBR = new Motor();


    // =====================================================
    // CONFIGURAÇÃO DE INVERSÃO
    // =====================================================
    //
    // MUDE SOMENTE ESTES VALORES.
    //
    // true  = motor invertido
    // false = motor normal
    //

    boolean reverseFL = false;
    boolean reverseFR = true;
    boolean reverseBL = false;
    boolean reverseBR = true;


    @Override
    public void init() {

        motorFL.init(hardwareMap, "motorFL");
        motorFR.init(hardwareMap, "motorFR");
        motorBL.init(hardwareMap, "motorBL");
        motorBR.init(hardwareMap, "motorBR");

        telemetry.addLine("=== TELEOP REVISION ===");
        telemetry.addLine("Sistema iniciado");
        telemetry.update();
    }


    @Override
    public void loop() {

        // =====================================================
        // 1. LEITURA DO CONTROLE
        // =====================================================

        lY = -gamepad1.left_stick_y;
        lX = gamepad1.left_stick_x;
        rX = gamepad1.right_stick_x;


        // =====================================================
        // 2. CALCULO MECANUM
        // =====================================================

        double[] velCalculada = mecanum.calculate(lY, lX, rX);


        // =====================================================
        // 3. MARCHA
        // =====================================================

        double[] velFinal = limiter.calculate(
                velCalculada,
                gamepad1.rightBumperWasPressed(),
                gamepad1.leftBumperWasPressed()
        );


        // =====================================================
        // 4. VALORES QUE REALMENTE SERÃO ENVIADOS
        // =====================================================

        double motorFLPower = reverseFL ? -velFinal[0] : velFinal[0];
        double motorFRPower = reverseFR ? -velFinal[1] : velFinal[1];
        double motorBLPower = reverseBL ? -velFinal[2] : velFinal[2];
        double motorBRPower = reverseBR ? -velFinal[3] : velFinal[3];


        // =====================================================
        // 5. ENVIA PARA OS MOTORES
        // =====================================================

        motorFL.setMotorSpeed(velFinal[0], reverseFL);
        motorFR.setMotorSpeed(velFinal[1], reverseFR);
        motorBL.setMotorSpeed(velFinal[2], reverseBL);
        motorBR.setMotorSpeed(velFinal[3], reverseBR);


        // =====================================================
        // 6. TELEMETRIA
        // =====================================================

        telemetry.addLine("========== ENTRADA ==========");

        telemetry.addData("lY", "%.3f", lY);
        telemetry.addData("lX", "%.3f", lX);
        telemetry.addData("rX", "%.3f", rX);


        telemetry.addLine("");
        telemetry.addLine("====== MECANUM CALCULADO ======");

        telemetry.addData("FL", "%.3f", velCalculada[0]);
        telemetry.addData("FR", "%.3f", velCalculada[1]);
        telemetry.addData("BL", "%.3f", velCalculada[2]);
        telemetry.addData("BR", "%.3f", velCalculada[3]);


        telemetry.addLine("");
        telemetry.addLine("======= APOS MARCHA =======");

        telemetry.addData("FL", "%.3f", velFinal[0]);
        telemetry.addData("FR", "%.3f", velFinal[1]);
        telemetry.addData("BL", "%.3f", velFinal[2]);
        telemetry.addData("BR", "%.3f", velFinal[3]);


        telemetry.addLine("");
        telemetry.addLine("==== APOS INVERSAO FISICA ====");

        telemetry.addData("FL", "%.3f", motorFLPower);
        telemetry.addData("FR", "%.3f", motorFRPower);
        telemetry.addData("BL", "%.3f", motorBLPower);
        telemetry.addData("BR", "%.3f", motorBRPower);


        telemetry.addLine("");
        telemetry.addLine("======= INVERSOES =======");

        telemetry.addData("FL reversed", reverseFL);
        telemetry.addData("FR reversed", reverseFR);
        telemetry.addData("BL reversed", reverseBL);
        telemetry.addData("BR reversed", reverseBR);


        telemetry.addLine("");
        telemetry.addLine("======= MARCHA =======");

        telemetry.addData(
                "RB",
                gamepad1.rightBumperWasPressed()
        );

        telemetry.addData(
                "LB",
                gamepad1.leftBumperWasPressed()
        );


        telemetry.update();
    }
}
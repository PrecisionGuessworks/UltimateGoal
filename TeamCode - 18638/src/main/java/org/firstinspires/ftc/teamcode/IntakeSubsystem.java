package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem {
    private DcMotor intakeMotor;
    private Servo intakeServoLeft, intakeServoRight;
    Telemetry telemetry;
    private double intakeDeployTarget = 0.5;
    private double intakeStowTarget = 1;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        // Intake Motor
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Intake Servos
        intakeServoLeft = hardwareMap.get(Servo.class, "intakeServoLeft");
        intakeServoRight = hardwareMap.get(Servo.class, "intakeServoRight");
        intakeServoRight.setDirection(Servo.Direction.REVERSE);

        this.telemetry = telemetry;
    }

    public void runIntakeIn(double speed) {
        intakeMotor.setPower(speed);
    }
    public void runIntakeOut(double speed) {
        intakeMotor.setPower(-speed);
    }
    public void deployIntake(){
        intakeServoRight.setPosition(intakeDeployTarget);
        intakeServoLeft.setPosition(intakeDeployTarget);
    }
    public void stowIntake(){
        intakeServoLeft.setPosition(intakeStowTarget);
        intakeServoRight.setPosition(intakeStowTarget);
    }

}

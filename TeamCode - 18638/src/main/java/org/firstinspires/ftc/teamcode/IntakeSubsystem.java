package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem {
    private DcMotor intakeMotor;
    private Servo intakeServoLeft, intakeServoRight;
    Telemetry telemetry;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeServoLeft = hardwareMap(Servo.class, "intakeServoLeft");
        intakeServoRight = hardwareMap(Servo.class, "intakeServoRight");
        intakeServoRight.setDirection(Servo.Direction.REVERSE);

        this.telemetry = telemetry;
    }

    public void runIntake(double speed) {
        intakeMotor.setPower(speed);
    }

}

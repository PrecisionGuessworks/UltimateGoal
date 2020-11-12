package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem {
    private DcMotor intakeMotor;
    Telemetry telemetry;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        this.telemetry = telemetry;
    }

    public void runIntake(double speed) {
        intakeMotor.setPower(speed);
    }

}

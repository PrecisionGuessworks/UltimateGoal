package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WobbleSubsystem {
    private DcMotor wobbleMotor;
    Telemetry telemetry;

    public WobbleSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        wobbleMotor = hardwareMap.get(DcMotor.class, "wobble");
        wobbleMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.telemetry = telemetry;
    }

}

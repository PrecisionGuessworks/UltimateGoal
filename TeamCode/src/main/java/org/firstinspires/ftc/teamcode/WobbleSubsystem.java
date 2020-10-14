package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WobbleSubsystem {
    //hewwo.
    private DcMotor wobbleMotor;

    public WobbleSubsystem(HardwareMap hardwareMap) {
        wobbleMotor = hardwareMap.get(DcMotor.class, "wobble");
        wobbleMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

}

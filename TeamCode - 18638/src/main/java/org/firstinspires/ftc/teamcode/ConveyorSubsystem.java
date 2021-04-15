package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ConveyorSubsystem {
    private DcMotor conveyorMotor;
    Telemetry telemetry;
    private double conSpeedForShooting = 1;
    private double conSpeedForIntaking = 0.25;

    public ConveyorSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        conveyorMotor = hardwareMap.get(DcMotor.class, "conveyorMotor");
        conveyorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.telemetry = telemetry;
    }

    public void runConveyorUpIntaking(){
        conveyorMotor.setPower(conSpeedForIntaking);
    }
    public void runConveyorUpShooting(){
        conveyorMotor.setPower(conSpeedForShooting);
    }
    public void flushConveyorDown(){
        conveyorMotor.setPower(-1);
    }
}

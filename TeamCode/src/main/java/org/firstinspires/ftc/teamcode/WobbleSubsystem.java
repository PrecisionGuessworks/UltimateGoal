package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WobbleSubsystem {
    private CRServo wobbleMotor;
    Telemetry telemetry;
    private Servo wobbleServo;

    public WobbleSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        wobbleMotor = hardwareMap.get(CRServo.class, "wobbleArm");
        wobbleMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        wobbleServo = hardwareMap.get(Servo.class, "wobbleServo");
        wobbleServo.setDirection(Servo.Direction.REVERSE);
        this.telemetry = telemetry;
    }

    public void setWobbleMotor(double speed) {
        wobbleMotor.setPower(speed);
    }

    public void closeServo(){
        wobbleServo.setPosition(1);
    }

    public void openServo(){
        wobbleServo.setPosition(0);
    }
}

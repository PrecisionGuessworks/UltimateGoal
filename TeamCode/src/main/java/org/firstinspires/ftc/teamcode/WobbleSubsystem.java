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
    private Servo wobbleServo, wobbleClamp;

    public WobbleSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        wobbleMotor = hardwareMap.get(CRServo.class, "wobbleArm");
        wobbleMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        wobbleServo = hardwareMap.get(Servo.class, "wobbleServo");
        wobbleServo.setDirection(Servo.Direction.REVERSE);
        wobbleClamp = hardwareMap.get(Servo.class, "wobbleClamp");
        wobbleClamp.setDirection(Servo.Direction.FORWARD);
        this.telemetry = telemetry;
    }

    public void setWobbleMotorPower(double speed) {
        wobbleMotor.setPower(speed);
    }

    public void closeMecServo(){
        wobbleServo.setPosition(1);
    }

    public void openWobbleClampServo(){
        wobbleClamp.setPosition(.1);
    }
    public void closeWobbleClampServo(){
        wobbleClamp.setPosition(.95);
    }

    public void openMecServo(){
        wobbleServo.setPosition(0.4);
    }

    public void closeTankServo() {
        wobbleServo.setPosition(.75);
    }

    public void openTankServo() {
        wobbleServo.setPosition(1);
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WobbleSubsystem {
    private CRServo wobbleMotor; // Motor running on SPARKmini
    private CRServo wobbleIntakeServoLeft, wobbleIntakeServoRight;
    Telemetry telemetry;
    private double wobbleIntakeSpeed = 1;

    public WobbleSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        wobbleMotor = hardwareMap.get(CRServo.class, "wobbleArm");
        wobbleMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        wobbleIntakeServoLeft = hardwareMap.get(CRServo.class, "wobbleIntakeServoLeft");
        wobbleIntakeServoLeft.setDirection(CRServo.Direction.REVERSE);
        wobbleIntakeServoRight = hardwareMap.get(CRServo.class, "wobbleIntakeServoRight");
        wobbleIntakeServoRight.setDirection(CRServo.Direction.FORWARD);

        this.telemetry = telemetry;
    }

    public void runWobbleIntakeIn(){
        wobbleIntakeServoLeft.setPower(wobbleIntakeSpeed);
        wobbleIntakeServoRight.setPower(wobbleIntakeSpeed);
    }
    public void runWobbleIntakeOut(){
        wobbleIntakeServoLeft.setPower(-wobbleIntakeSpeed);
        wobbleIntakeServoRight.setPower(-wobbleIntakeSpeed);
    }

    public void setWobbleMotorPower(double speed) {
        wobbleMotor.setPower(speed);
    }

}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WobbleSubsystem {
    public CRServo wobbleArmMotor;
    public Servo wobbleSero;
    Telemetry telemetry;

    public WobbleSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        //Todo: CRSERVo
        //Todo: Make the Servo
        //Todo: Set Direction of Servo
        //wobbleServo
        wobbleArmMotor = hardwareMap.get(CRServo.class, "wobbleArm");
        wobbleArmMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        wobbleSero = hardwareMap.get(Servo.class, "wobbleServo");

        this.telemetry = telemetry;
    }

    public void setArmPower(double power){
        wobbleArmMotor.setPower(power);
    }

    public void setServoPos(double pos){
        wobbleSero.setPosition(pos);
    }

}

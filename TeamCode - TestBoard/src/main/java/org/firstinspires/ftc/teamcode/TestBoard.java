package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestBoard {
    private Servo myServo;
    private CRServo myCRServo;
    private DcMotor myMotor;
    private TouchSensor myRevTouch, vexPushButton;
    private LED flashlight;
    private AnalogInput pot;
    Telemetry telemetry;

    public TestBoard(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        myServo = hardwareMap.get(Servo.class, "vexservo");
        myRevTouch = hardwareMap.get(TouchSensor.class, "revTouchButton");
        flashlight = hardwareMap.get(LED.class, "flashlight");
        vexPushButton = hardwareMap.get(TouchSensor.class, "vexPushButton");
        pot = hardwareMap.get(AnalogInput.class, "potentiometer");
    }

    public void turnFlashlightOn(){
        //flashlight.enable(true);
        flashlight.enableLight(true);
    }
    public void turnFlashlightOff(){
        flashlight.enable(false);
    }
    public boolean isRevTouchPressed(){
        return myRevTouch.isPressed();
    }
    public boolean isVexBumpPressed(){
        return vexPushButton.isPressed();
    }
    public double getPotValue(){
        return pot.getVoltage();
    }

}

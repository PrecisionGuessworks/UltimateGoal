package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class DrivetrainSubsystem {
    private DcMotor frontLeft, backLeft, frontRight, backRight;
    BotUtilities botStuff;

    public DrivetrainSubsystem(HardwareMap hardwareMap) {
        frontLeft  = hardwareMap.get(DcMotor.class, "frontleft");
        backLeft  = hardwareMap.get(DcMotor.class, "backleft");
        frontRight = hardwareMap.get(DcMotor.class, "frontright");
        backRight = hardwareMap.get(DcMotor.class, "backright");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        botStuff = new BotUtilities();
    }

    public void setMotors(double leftSpeed, double rightSpeed) {
        frontLeft.setPower(leftSpeed);
        backLeft.setPower(leftSpeed);
        frontRight.setPower(rightSpeed);
        backRight.setPower(rightSpeed);
    }

    public void stopDriving() {
        setMotors(0, 0);
    }

    public int[] readDrivetrainEncoders() {
        int encoderValues[] = new int[4];
        encoderValues[1] = botStuff.readEncoder(frontLeft);
        encoderValues[2] = botStuff.readEncoder(backLeft);
        encoderValues[3] = botStuff.readEncoder(frontRight);
        encoderValues[4] = botStuff.readEncoder(backRight);
        return encoderValues;
    }

    public double[] arcadeDrive(double throttle, double direction) {
        double motorSpeeds[] = new double[2];
        double leftDrive, rightDrive;

        deadband(throttle);
        deadband(direction);
        leftDrive = throttle + direction;
        rightDrive = throttle - direction;

        motorSpeeds[1] = leftDrive;
        motorSpeeds[2] = rightDrive;
        motorSpeeds = normalize(motorSpeeds);

        setMotors(motorSpeeds[1], motorSpeeds[2]);

        return motorSpeeds;
    }   // arcadeDrive

    private double[] normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++){
            double magnitude = Math.abs(wheelSpeeds[i]);
            if (magnitude > maxMagnitude){
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0) {
            for (int i = 0; i < wheelSpeeds.length; i++){
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
        return wheelSpeeds;
    }   //normalize

    public double deadband(double x) {
        double deadBand = 0.1;
        if (Math.abs(x) < deadBand) {
            x = 0.0;
        }
        return x;
    }   // deadband
}

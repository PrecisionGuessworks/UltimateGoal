package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DrivetrainSubsystem {
    private DcMotor frontLeft, backLeft, frontRight, backRight;
    BotUtilities botStuff;
    Telemetry telemetry;

    public DrivetrainSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        frontLeft  = hardwareMap.get(DcMotor.class, "FL");
        backLeft  = hardwareMap.get(DcMotor.class, "BL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backRight = hardwareMap.get(DcMotor.class, "BR");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.telemetry = telemetry;

        botStuff = new BotUtilities(telemetry);
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
        encoderValues[1] = botStuff.getEncoderValue(frontLeft);
        encoderValues[2] = botStuff.getEncoderValue(backLeft);
        encoderValues[3] = botStuff.getEncoderValue(frontRight);
        encoderValues[4] = botStuff.getEncoderValue(backRight);
        return encoderValues;
    }

    public void tankDrive(double left, double right) {
        setMotors(deadband(left), deadband(right));
    }

    public double[] arcadeDrive(double throttle, double direction) {
        double motorSpeeds[] = new double[2];
        double leftDrive, rightDrive;

        deadband(throttle);
        deadband(direction);
        leftDrive = throttle + direction;
        rightDrive = throttle - direction;

        motorSpeeds[0] = leftDrive;
        motorSpeeds[1] = rightDrive;
        motorSpeeds = normalize(motorSpeeds);

        setMotors(motorSpeeds[0], motorSpeeds[1]);

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

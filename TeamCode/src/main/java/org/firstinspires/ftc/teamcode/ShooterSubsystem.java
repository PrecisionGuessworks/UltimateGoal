package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

// THE PEW-PEW SUBSYSTEM
public class ShooterSubsystem {
    private DcMotor accelerator, flywheel;
    BotUtilities botStuff;

    public ShooterSubsystem (HardwareMap hardwareMap) {
        accelerator = hardwareMap.get(DcMotor.class, "accelerator");
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");

        accelerator.setDirection(DcMotor.Direction.REVERSE);
        flywheel.setDirection(DcMotor.Direction.REVERSE);

        accelerator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        botStuff = new BotUtilities();
    }

    public void setAccelerator(double speed){
        accelerator.setPower(speed);
    }

    public void setFlywheel(double speed) {
        flywheel.setPower(speed);
    }

    public void setShooter(double flywheelSpeed, double acceleratorSpeed) {
        setAccelerator(acceleratorSpeed);
        setFlywheel(flywheelSpeed);
    }

    public int[] readShooterEncoders() {
        int encoderValues[] = new int[2];
        encoderValues[0] = botStuff.readEncoder(accelerator);
        encoderValues[1] = botStuff.readEncoder(flywheel);
        return encoderValues;
    }

    public void stopAll() {
        setShooter(0,0);
    }
}

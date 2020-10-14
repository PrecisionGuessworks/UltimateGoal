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
        encoderValues[1] = botStuff.readEncoder(accelerator);
        encoderValues[2] = botStuff.readEncoder(flywheel);
        return encoderValues;
    }

    public void stopAll() {
        setShooter(0,0);
    }
}

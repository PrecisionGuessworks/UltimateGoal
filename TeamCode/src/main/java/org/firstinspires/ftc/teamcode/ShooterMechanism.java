package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterMechanism {
    private DcMotor accelWheel, shooterWheel;
    public ShooterMechanism (HardwareMap hardwareMap) {
        accelWheel  = hardwareMap.get(DcMotor.class, "accelerator");
        shooterWheel = hardwareMap.get(DcMotor.class, "flywheel");

        accelWheel.setDirection(DcMotor.Direction.REVERSE);
        shooterWheel.setDirection(DcMotor.Direction.REVERSE);
    }

    public void shootRings(double accelWheelSpeed, double shooterWheelSpeed) {
        accelWheel.setPower(accelWheelSpeed);
        shooterWheel.setPower(shooterWheelSpeed);
    }
}

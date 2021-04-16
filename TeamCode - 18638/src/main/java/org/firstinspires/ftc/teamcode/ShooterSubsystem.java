package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// THE PEW-PEW SUBSYSTEM
public class ShooterSubsystem {
    private DcMotor flywheel;
    private Telemetry telemetry;
    BotUtilities botStuff;

    public ShooterSubsystem (HardwareMap hardwareMap, Telemetry telemetry) {
        flywheel = hardwareMap.get(DcMotor.class, "shooterMotor");
        flywheel.setDirection(DcMotor.Direction.REVERSE);
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        botStuff = new BotUtilities(telemetry);
        this.telemetry = telemetry;
    }

    public void setFlywheel(double speed) {
        flywheel.setPower(speed);
    }

    public void setShooter(double flywheelSpeed, double acceleratorSpeed) {
        setFlywheel(flywheelSpeed);
    }

    public int[] readShooterEncoders() {
        int encoderValues[] = new int[2];
        encoderValues[1] = botStuff.getEncoderValue(flywheel);
        return encoderValues;
    }

}

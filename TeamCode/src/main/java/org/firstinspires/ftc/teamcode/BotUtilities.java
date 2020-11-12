package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BotUtilities {
    Telemetry telemetry;

    public BotUtilities(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    // TODO: Check if encoder values are int or double
    public int getEncoderValue(DcMotor motor) {
        return motor.getCurrentPosition();
    }

    public void readEncoder(DcMotor motor) {
        telemetry.addData(String.valueOf(motor), motor.getCurrentPosition());
    }

}

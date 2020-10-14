package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class BotUtilities {
    public BotUtilities() {

    }

    public int readEncoder(DcMotor motor) {
        return motor.getCurrentPosition();
    }

}

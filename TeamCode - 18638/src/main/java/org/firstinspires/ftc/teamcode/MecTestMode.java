/*
17012
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Map;

//////////////////////////////////////////////////////////////////////////////////////////
@TeleOp(name="Mec Mode", group="Mecanum")
//@Disabled        // Comment/Uncomment this line as needed to show/hide this opmode
//////////////////////////////////////////////////////////////////////////////////////////

public class MecTestMode extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    BotUtilities utilities;
    MecanumDrivetrainSubsystem mecdrivetrain;
    WobbleSubsystem wobble;
    IntakeSubsystem intake;
    ShooterSubsystem shooter;

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run ONCE when the driver hits INIT */
    @Override
    public void init() {
        mecdrivetrain = new MecanumDrivetrainSubsystem(this.hardwareMap, this.telemetry);
        wobble = new WobbleSubsystem(this.hardwareMap, this.telemetry);
        utilities = new BotUtilities(this.telemetry);
        // Set up our telemetry dashboard
        getTelemetry();

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized    :)");
    }

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY */
    @Override
    public void init_loop() {
    }

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run ONCE when the driver hits PLAY */
    @Override
    public void start() {
        runtime.reset();
        getTelemetry();
    }

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP */
    @Override
    public void loop() {
        checkDriverController();

        // Call Telemetry
        getTelemetry();

    }

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run ONCE after the driver hits STOP */
    @Override
    public void stop() {
        telemetry.addData("Robot Stopped. ", "Have a nice day.");
        telemetry.addData("Final runtime: ", runtime.toString());
        telemetry.update();
    }

//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
/*                              TELEOP-SPECIFIC METHODS                                 */
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////

    public void checkDriverController() {
        mecdrivetrain.mecanumDrive_Cartesian(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        telemetry.addData("Left stick value: ", gamepad1.left_stick_y);
        telemetry.addData("Right stick value: ", gamepad1.right_stick_y);

        if (gamepad1.left_bumper) {
            wobble.setWobbleMotorPower(0.5);
        } else if (gamepad1.right_bumper) {
            wobble.setWobbleMotorPower(-0.5);
        } else {
            wobble.setWobbleMotorPower(0);
        }

        if (gamepad1.square) {
            wobble.closeMecServo();
        } else if (gamepad1.circle) {
            wobble.openMecServo();
        }

        if (gamepad1.triangle) {
            wobble.openWobbleClampServo();
        } else if (gamepad1.cross) {
            wobble.closeWobbleClampServo();
        }
    }

    public void getTelemetry() {
        // Show the elapsed game time
        telemetry.addData("Run Time: ", runtime.toString());

        // Telemetry about motion
        //telemetry.addData("Motors", "leftFront (%.2f), rightFront (%.2f), rightRear (%.2f), leftRear (%.2f)", telemValues[0], telemValues[1], telemValues[2], telemValues[3]);
        telemetry.update();
    }  // getTelemetry

}    // The Almighty Curly Brace For Everything


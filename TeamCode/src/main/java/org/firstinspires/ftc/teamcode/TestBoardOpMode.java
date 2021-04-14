/*
17012
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

//////////////////////////////////////////////////////////////////////////////////////////
@TeleOp(name="TestBoard", group="TestBoard")
//@Disabled        // Comment/Uncomment this line as needed to show/hide this opmode
//////////////////////////////////////////////////////////////////////////////////////////

public class TestBoardOpMode extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    BotUtilities utilities;
    TestBoard testboard;


//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run ONCE when the driver hits INIT */
    @Override
    public void init() {
        utilities = new BotUtilities(this.telemetry);
        testboard = new TestBoard(this.hardwareMap, this.telemetry);
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
        testboard.turnFlashlightOn();
        telemetry.addData("Pot value: ", testboard.getPotValue());
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
        telemetry.addData("Left stick value: ", gamepad1.left_stick_y);
        telemetry.addData("Right stick value: ", gamepad1.right_stick_y);

        if (testboard.isRevTouchPressed()) {
            telemetry.addLine("REV TOUCH PRESSED");
        } else {
            telemetry.addLine("No rev touch");
        }

        if (testboard.isVexBumpPressed()) {
            telemetry.addLine("VEX BUMP");
        } else {
            telemetry.addLine("No vex bump");
        }

        if (gamepad1.triangle) {
            testboard.turnFlashlightOn();
        } else {
            testboard.turnFlashlightOff();
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


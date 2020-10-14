/*
17012
 */

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import java.util.Locale;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.MyRobot;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.util.Locale;

//////////////////////////////////////////////////////////////////////////////////////////
@TeleOp(name="Testing 2", group="Iterative Opmode")
// @Disabled        // Comment/Uncomment this line as needed to show/hide this opmode
//////////////////////////////////////////////////////////////////////////////////////////

public class TestMode extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    MyRobot robot;

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run ONCE when the driver hits INIT */
    @Override
    public void init() {
        //  All setup found in MyRobot
        robot = new MyRobot(this.hardwareMap);

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

        double flywheelValue = 0.65;
        double acceleratorValue = 0.8;

        if (gamepad1.cross) {
            robot.setMotors(acceleratorValue, flywheelValue);
            telemetry.addData("Shoot the", "pew pew. \nBoth motors should be spinning.");
        } else if (gamepad1.square) {
            robot.setMotors(0, flywheelValue);
            telemetry.addData("Flying wheel spinning with speed of ", flywheelValue);
        } else if (gamepad1.circle) {
            robot.setMotors(acceleratorValue, 0);
            telemetry.addData("Accelerator wheel spinning with speed of ", acceleratorValue);
        } else if (gamepad1.triangle) {
            robot.runVexMotor(.5);
            telemetry.addData("Vex Motor spin .4", "TRIANGLE PUSHED");
        } else {
            robot.stopAllMotors();
            robot.runVexMotor(0);
        }

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

    public void getTelemetry() {
        // Show the elapsed game time
        telemetry.addData("Run Time: ", runtime.toString());

        // Telemetry about motion
        //telemetry.addData("Motors", "leftFront (%.2f), rightFront (%.2f), rightRear (%.2f), leftRear (%.2f)", telemValues[0], telemValues[1], telemValues[2], telemValues[3]);
        telemetry.update();
    }  // getTelemetry

}    // The Almighty Curly Brace For Everything


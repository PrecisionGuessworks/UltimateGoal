package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.MyRobot;
import java.util.Locale;

@Autonomous(name = "Vision TestAuto", group = "Testing")
//@Disabled                            // Comment this out to add to the opmode list
public class TestAutoMode extends LinearOpMode {
    ShooterSubsystem shooter;
    DrivetrainSubsystem drivetrain;
    IntakeSubsystem intake;
    WobbleSubsystem wobble;
    VisionSubsystem vision;
    BotUtilities utilities;

    @Override

    public void runOpMode() {
        // Initialize auto:
        drivetrain = new DrivetrainSubsystem(this.hardwareMap, this.telemetry);
        vision = new VisionSubsystem(this.hardwareMap, this.telemetry);
        utilities = new BotUtilities(this.telemetry);

        vision.startupVision();
        // End of auto initialization
        waitForStart();
        ///////////////////////////////////////////////////////////////////

        // Drive to ring stack location
        drivetrain.tankDrive(0.5, 0.5);
        utilities.delay(250);
        drivetrain.stopDriving();

        // Identify and Run correct automode
        selectAuto(vision.runVisionSystem());

    }   // Run OpMode

//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////


    public void selectAuto(String visionReading) {
        if (visionReading.equalsIgnoreCase("Single")) {
            runOneRingAuto();
        } else if (visionReading.equalsIgnoreCase("Quad")) {
            runFourRingAuto();
        } else {
            runNoRingAuto();
        }
        telemetry.update();
    }

    public void runNoRingAuto(){
        // Target Zone A. (close)
        telemetry.addLine("Running the No Ring Auto. Heading to zone A.");
        drivetrain.driveTankForTime(0.7, 0.7, 3);
        drivetrain.turn(0.5, "right");
        drivetrain.driveTankForTime(0.7, 0.7, 3);
    }

    public void runOneRingAuto(){
        // Target Zone B. (mid)
        telemetry.addLine("Running the One Ring Auto. Heading to zone B.");
    }

    public void runFourRingAuto(){
        // Target Zone C. (far)
        telemetry.addLine("Running the Four Ring Auto. Heading to zone C.");
    }

    public void getTelemetry() {
        /*telemetry.addData("Y1 Encoder: ", robot.getY1Disp());
        telemetry.addData("Y2 Encoder: ", robot.getY2Disp());
        telemetry.addData("Y Encoder Avg: ", robot.getYAvgDisp());
        telemetry.addData("X Encoder: ", robot.getXDisp());
        telemetry.addData("Current Heading: ", robot.angles.firstAngle);*/
        telemetry.update();

    }   // getTelemetry

}       // The Almighty Curly Brace For Everything



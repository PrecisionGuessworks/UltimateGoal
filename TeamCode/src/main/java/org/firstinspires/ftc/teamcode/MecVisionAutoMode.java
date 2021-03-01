package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Mec Vision TestAuto", group = "Mec")
//@Disabled                            // Comment this out to add to the opmode list
public class MecVisionAutoMode extends LinearOpMode {
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
        wobble = new WobbleSubsystem(this.hardwareMap, this.telemetry);

        vision.startupVision();
        // End of auto initialization
        waitForStart();
        ///////////////////////////////////////////////////////////////////

        // Drive to ring stack location
        drivetrain.tankDrive(0.5, 0.5);
        utilities.delay(650);
        drivetrain.stopDriving();
        utilities.delay(500);

        // Identify and Run correct automode
        selectAuto(vision.runVisionSystem());
        utilities.delay(2000);
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



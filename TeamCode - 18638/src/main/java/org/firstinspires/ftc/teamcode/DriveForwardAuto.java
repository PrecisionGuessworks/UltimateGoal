package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "DriveForwardAuto", group = "Testing")
//@Disabled                            // Comment this out to add to the opmode list
public class DriveForwardAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initialize auto:
        ShooterSubsystem shooter;
        DrivetrainSubsystem drivetrain = new DrivetrainSubsystem(this.hardwareMap, this.telemetry);
        IntakeSubsystem intake;
        WobbleSubsystem wobble;
        VisionSubsystem vision = new VisionSubsystem(this.hardwareMap, this.telemetry);
        BotUtilities utilities = new BotUtilities(this.telemetry);

        //vision.startupVision();
        // End of auto initialization
        waitForStart();
        ///////////////////////////////////////////////////////////////////
        int delay = 0; // seconds
        int drivetime = 2; // seconds


        utilities.delay(delay * 1000);
        drivetrain.tankDrive(1,1);;
        utilities.delay(drivetime);
        drivetrain.stopDriving();
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



package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Mec Vision TestAuto", group = "Mec")
//@Disabled                            // Comment this out to add to the opmode list
public class MecVisionAutoMode extends LinearOpMode {
    ShooterSubsystem shooter;
    MecanumDrivetrainSubsystem drivetrain;
    IntakeSubsystem intake;
    WobbleSubsystem wobble;
    VisionSubsystem vision;
    BotUtilities utilities;

    @Override

    public void runOpMode() {
        // Initialize auto:
        drivetrain = new MecanumDrivetrainSubsystem(this.hardwareMap, this.telemetry);
        vision = new VisionSubsystem(this.hardwareMap, this.telemetry);
        utilities = new BotUtilities(this.telemetry);
        wobble = new WobbleSubsystem(this.hardwareMap, this.telemetry);

        vision.startupVision();
        // End of auto initialization
        waitForStart();
        ///////////////////////////////////////////////////////////////////

        // Drive to ring stack location
        drivetrain.mecanumDrive_Cartesian(0.65, 0.0, 0);
        utilities.delay(450);
        drivetrain.stopDriving();
        utilities.delay(750);

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

    public void dropWobble(){
        // TODO write dropWobble method
    }

    public void runNoRingAuto(){
        // Target Zone A. (close)
        telemetry.addLine("Running the No Ring Auto. Heading to zone A.");
        // straight forwards, fling rings, zone A
        drivetrain.mecanumDrive_Cartesian(1, 0, 0);
        utilities.delay(2400);
        drivetrain.stopDriving();

        // this technically isn't the wobble. but too lazy to change right now
        wobble.setWobbleMotorPower(-.8);
        utilities.delay(800);
        wobble.setWobbleMotorPower(0);
        wobble.setWobbleMotorPower(.4);
        utilities.delay(800);
        wobble.setWobbleMotorPower(0);

        drivetrain.mecanumDrive_Cartesian(-1, 0 , 0);
        utilities.delay(650);
        drivetrain.stopDriving();

        dropWobble();
    }

    public void runOneRingAuto(){
        // Target Zone B. (mid)
        telemetry.addLine("Running the One Ring Auto. Heading to zone B.");

        // go to the right to clear the ring
        drivetrain.mecanumDrive_Cartesian(0, -0.85, 0);
        utilities.delay(950);
        drivetrain.stopDriving();

        //go forwards past the ring
        drivetrain.mecanumDrive_Cartesian(1, 0, 0);
        utilities.delay(1200);
        drivetrain.stopDriving();

        // go back left
        drivetrain.mecanumDrive_Cartesian(0, .85, 0);
        utilities.delay(950);
        drivetrain.stopDriving();

        // go forwards to wall
        drivetrain.mecanumDrive_Cartesian(1, 0, 0);
        utilities.delay(1000);
        drivetrain.stopDriving();

        // ring fling
        // this technically isn't the wobble. but too lazy to change right now
        wobble.setWobbleMotorPower(-.8);
        utilities.delay(800);
        wobble.setWobbleMotorPower(0);
        wobble.setWobbleMotorPower(.4);
        utilities.delay(800);
        wobble.setWobbleMotorPower(0);

        // drive backwards to zone b
        drivetrain.mecanumDrive_Cartesian(-1, 0 , 0);
        utilities.delay(500);
        drivetrain.stopDriving();

        // drive left to drop in zone
        drivetrain.mecanumDrive_Cartesian(0, 0.6, 0);
        utilities.delay(250);
        drivetrain.stopDriving();

        // drive back to line
        drivetrain.mecanumDrive_Cartesian(-.5, 0, 0);
        utilities.delay(250);
        drivetrain.stopDriving();

        dropWobble();
    }

    public void runFourRingAuto(){
        // Target Zone C. (far)
        telemetry.addLine("Running the Four Ring Auto. Heading to zone C.");

        dropWobble();
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



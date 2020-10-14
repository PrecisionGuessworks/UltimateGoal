package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

//////////////////////////////////////////////////////////////////////////////////////////
@TeleOp(name="TeleopDev", group="Iterative Opmode")
@Disabled       // Comment/Uncomment this line as needed to show/hide this opmode
//////////////////////////////////////////////////////////////////////////////////////////
public class TeleopDevelopment extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    double requestedMotorSpeeds[] = new double[2];

    DrivetrainSubsystem drivetrain;
    ShooterSubsystem shooter;
    IntakeSubsystem intake;
    WobbleSubsystem wobble;

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run ONCE when the driver hits INIT */
    @Override
    public void init() {
        drivetrain = new DrivetrainSubsystem(this.hardwareMap);
        shooter = new ShooterSubsystem(this.hardwareMap);
        intake = new IntakeSubsystem(this.hardwareMap);
        wobble = new WobbleSubsystem(this.hardwareMap);

        // Set up our telemetry dashboard
        getTelemetry();

        // Tell the driver that initialization is complete.
        telemetry.addLine("Status: Initialized   :)");
        telemetry.update();
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

        if (gamepad1.cross) {           // for shooter testing
            shooter.setShooter(flywheelValue, acceleratorValue);
            telemetry.addData("Shoot the", "pew pew. \nBoth motors should be spinning.");
        } else if (gamepad1.square) {   // for flywheel only testing
            shooter.setFlywheel(flywheelValue);
            telemetry.addData("Flying wheel spinning with speed of ", flywheelValue);
        } else if (gamepad1.circle) {   // for accelerator only testing
            shooter.setAccelerator(acceleratorValue);
            telemetry.addData("Accelerator wheel spinning with speed of ", acceleratorValue);
        } else if (gamepad1.triangle) { // for testing other things...
            telemetry.addLine("TRIANGLE PUSHED");
        } else {                        // stop all
            shooter.stopAll();
        }
        
        requestedMotorSpeeds = drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);

        // Call Telemetry
        getTelemetry();

    }

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run ONCE after the driver hits STOP */
    @Override
    public void stop() {
        telemetry.addLine("Robot Stopped. Have a nice day. :)");
        telemetry.addData("Final runtime: ", runtime.toString());
        telemetry.update();
    }

//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
/*                              TELEOP-SPECIFIC METHODS                                 */
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////

    public void getTelemetry() {
        int shooterEncoders[] = new int[2];
        shooterEncoders = shooter.readShooterEncoders();

        // Show the elapsed game time
        telemetry.addData("Status", "Run Time: " + runtime.toString());

        // Telemetry about motion
        //telemetry.addData("Motors", "leftFront (%.2f), rightFront (%.2f), rightRear (%.2f), leftRear (%.2f)", telemValues[0], telemValues[1], telemValues[2], telemValues[3]);
        telemetry.addLine("Current Motor Encoder Readings.");
        telemetry.addData("Accelerator", shooterEncoders[1]);
        telemetry.addData("Flywheel", shooterEncoders[2]);
        telemetry.update();
    }  // getTelemetry

}    // The Almighty Curly Brace For Everything


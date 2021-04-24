/*
17012
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.ConveyorSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TankDrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.WobbleSubsystem;

//////////////////////////////////////////////////////////////////////////////////////////
@Autonomous(name="Tank Mode", group="Mecanum")
//@Disabled        // Comment/Uncomment this line as needed to show/hide this opmode
//////////////////////////////////////////////////////////////////////////////////////////

public class AutoMode extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    BotUtilities utilities;
    TankDrivetrainSubsystem tankDrivetrain;
    WobbleSubsystem wobble;
    IntakeSubsystem intake;
    ShooterSubsystem shooter;
    ConveyorSubsystem conveyor;

//////////////////////////////////////////////////////////////////////////////////////////

    /* Code to run ONCE when the driver hits INIT */
    @Override
    public void runOpMode() {
        tankDrivetrain = new TankDrivetrainSubsystem(this.hardwareMap, this.telemetry);
        wobble = new WobbleSubsystem(this.hardwareMap, this.telemetry);
        intake = new IntakeSubsystem(this.hardwareMap, this.telemetry);
        shooter = new ShooterSubsystem(this.hardwareMap, this.telemetry);
        conveyor = new ConveyorSubsystem(this.hardwareMap, this.telemetry);
        utilities = new BotUtilities(this.telemetry);

        //Drive Forward for half 1 and a half seconds
        tankDrivetrain.arcadeDrive(0.5, 0.0);
        utilities.delay(1500);
        tankDrivetrain.arcadeDrive(0.0, 0.0);

        //wobble arm up and then release
        wobble.setArmPower(-0.5);
        utilities.delay(500);
        wobble.setArmPower(0.0);
        utilities.delay(500);
        wobble.runIntakeOut();

        //Shoot rings
        shooter.setPower(0.8);
        utilities.delay(1000);
        intake.runIntakeIn(0.8);
        conveyor.runConveyorUpShooting();
        utilities.delay(2000);
        shooter.setPower(0.0);
        intake.idle();
        conveyor.idle();

        //Drive backwards 0.5 seconds
        tankDrivetrain.arcadeDrive(-0.5, 0.0);
        utilities.delay(    500);
        tankDrivetrain.arcadeDrive(0.0, 0.0);

    }

    public void getTelemetry() {
        // Show the elapsed game time
        telemetry.addData("Run Time: ", runtime.toString());

        // Telemetry about motion
        //telemetry.addData("Motors", "leftFront (%.2f), rightFront (%.2f), rightRear (%.2f), leftRear (%.2f)", telemValues[0], telemValues[1], telemValues[2], telemValues[3]);
        telemetry.update();
    }  // getTelemetry

}    // The Almighty Curly Brace For Everything


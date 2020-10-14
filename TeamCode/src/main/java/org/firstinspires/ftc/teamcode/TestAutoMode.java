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

@Autonomous(name = "TestAuto", group = "Testing")
@Disabled                            // Comment this out to add to the opmode list
public class TestAutoMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initialize auto:
        ShooterSubsystem shooter;
        DrivetrainSubsystem drivetrain;
        IntakeSubsystem intake;
        WobbleSubsystem wobble;

        // End of auto initialization
        waitForStart();

    }   // Run OpMode

//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////


    public void getTelemetry() {
        /*telemetry.addData("Y1 Encoder: ", robot.getY1Disp());
        telemetry.addData("Y2 Encoder: ", robot.getY2Disp());
        telemetry.addData("Y Encoder Avg: ", robot.getYAvgDisp());
        telemetry.addData("X Encoder: ", robot.getXDisp());
        telemetry.addData("Current Heading: ", robot.angles.firstAngle);*/
        telemetry.update();

    }   // getTelemetry

}       // The Almighty Curly Brace For Everything



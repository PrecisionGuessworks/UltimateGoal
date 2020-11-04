package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import java.util.Locale;



public class MyRobot {
    // Possible configs: DcMotor, ColorSensor, DistanceSensor, Blinker, Servo, etc...
    public DcMotor leftFront, leftRear, rightFront, rightRear, flywheel, accelerator;
    public Servo revServo;
    public CRServo vexMotor;


    //////////////////////////////////////////////////////////////////////////////////////////

    public MyRobot(HardwareMap hardwareMap) {
//        leftFront  = hardwareMap.get(DcMotor.class, "FL");
//        leftRear  = hardwareMap.get(DcMotor.class, "BL");
//        rightFront = hardwareMap.get(DcMotor.class, "FR");
//        rightRear = hardwareMap.get(DcMotor.class, "BR");
//        revServo = hardwareMap.servo.get("revservo");
        //accelerator = hardwareMap.get(DcMotor.class, "accelerator");
        //flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        vexMotor = hardwareMap.get(CRServo.class, "vexmotor");
        revServo = hardwareMap.get(Servo.class, "revservo");
        //accelerator.setDirection(DcMotor.Direction.FORWARD);
        //flywheel.setDirection(DcMotor.Direction.REVERSE);

    }   // MyRobot(HardwareMap)

    //////////////////////////////////////////////////////////////////////////////////////////

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // no-op
        }
    }   // sleep

    //////////////////////////////////////////////////////////////////////////////////////////

    public void stopAllMotors() {
        // Stop driving. No vroom.
        setMotors(0,0);
    } //    stopAllMotors

//////////////////////////////////////////////////////////////////////////////////////////

    public void setMotors(double acceleratorWheelValue, double flywheelValue) {
        accelerator.setPower(acceleratorWheelValue);
        flywheel.setPower(flywheelValue);
    }

//////////////////////////////////////////////////////////////////////////////////////////

    public void runCRServo (double speed) {
        vexMotor.setPower(speed);
    }

    public void setServo (double position) {
        revServo.setPosition(position);
    }

} // The Almighty Curly Brace for Everything
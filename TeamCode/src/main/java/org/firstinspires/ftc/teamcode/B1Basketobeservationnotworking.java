package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "B1_Basket_Observation_notworking", group = "Autonomous")
public class B1Basketobeservationnotworking extends LinearOpMode {


    hardware robot   = new hardware();
    private DcMotor leftfDrive = null;
    private DcMotor rightfDrive = null;
    private DcMotor leftbDrive = null;
    private DcMotor rightbDrive = null;
    private ElapsedTime runtime = new ElapsedTime();
    static final double MAX_SPEED = 1.0;
    static final double FORWARD_SPEED = 0.8;
    static final double REVERSE_SPEED = -0.6;
    static final double STRAFE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;



    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();


        //LeftFront.setPower(MAX_SPEED);
        rightfDrive.setPower(MAX_SPEED);
        leftbDrive.setPower(MAX_SPEED);
        rightbDrive.setPower(MAX_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftfDrive.setPower(TURN_SPEED);
        rightfDrive.setPower(-TURN_SPEED);
        leftbDrive.setPower(TURN_SPEED);
        rightbDrive.setPower(-TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftfDrive.setPower(REVERSE_SPEED);
        rightfDrive.setPower(REVERSE_SPEED);
        leftbDrive.setPower(REVERSE_SPEED);
        rightbDrive.setPower(REVERSE_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();

            robot.Lift.setPower(0.8);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 2.5)) {
                telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
                telemetry.update();

            }

        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardwareclasses.Lift;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="R1Preloaded_Sample_Observation", group="Autonomous")

public class R1preloadedsampleobservation extends LinearOpMode {

    /* Declare OpMode members. */
    //HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware

    hardware robot = new hardware();
    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = -0.8;
    static final double REVERSE_SPEED = 0.8;
    static final double TURN_SPEED = 0.5;
    static final double LIFT_SPEED = 0.5;
    static final double STRAFE_SPEED = -0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 3 seconds
        robot.LeftFront.setPower(FORWARD_SPEED);
        robot.RightFront.setPower(FORWARD_SPEED);
        robot.LeftBack.setPower(FORWARD_SPEED);
        robot.RightBack.setPower(FORWARD_SPEED);
        sleep(430);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        // Step 2:  Spin right for 1.3 seconds
        // robot.leftDrive.setPower(TURN_SPEED);
        //robot.rightDrive.setPower(-TURN_SPEED);
        robot.LeftFront.setPower(-TURN_SPEED);
        robot.RightFront.setPower(TURN_SPEED);
        robot.LeftBack.setPower(-TURN_SPEED);
        robot.RightBack.setPower(TURN_SPEED);
        sleep(330);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        // Step 3:  Drive Backwards for 1 Second
        robot.LeftFront.setPower(REVERSE_SPEED);
        robot.RightFront.setPower(REVERSE_SPEED);
        robot.LeftBack.setPower(REVERSE_SPEED);
        robot.RightBack.setPower(REVERSE_SPEED);
        sleep(680);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.Lift.setTargetPosition((int) (39 * Lift.InchesPerTick) + robot.Lift.getCurrentPosition());
        robot.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Lift.setPower(LIFT_SPEED);
        while (opModeIsActive() && robot.Lift.isBusy()){

        }
        sleep(250);
        robot.Bucket.setPosition(0);
        sleep(1500);
        robot.Lift.setPower(0);




        robot.Bucket.setPosition(0.5);
        sleep(2000);

        robot.Lift.setTargetPosition( robot.Lift.getCurrentPosition() - (int) (34 * Lift.InchesPerTick));
        robot.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Lift.setPower(LIFT_SPEED);
        while (opModeIsActive() && robot.Lift.isBusy()){

        }
        robot.Lift.setPower(0);

        robot.LeftFront.setPower(TURN_SPEED);
        robot.RightFront.setPower(TURN_SPEED);
        robot.LeftBack.setPower(TURN_SPEED);
        robot.RightBack.setPower(-TURN_SPEED);
        sleep(340);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(FORWARD_SPEED);
        robot.RightFront.setPower(FORWARD_SPEED);
        robot.LeftBack.setPower(FORWARD_SPEED);
        robot.RightBack.setPower(FORWARD_SPEED);
        sleep(250);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(STRAFE_SPEED);
        robot.RightFront.setPower(-STRAFE_SPEED);
        robot.LeftBack.setPower(-STRAFE_SPEED);
        robot.RightBack.setPower(STRAFE_SPEED);
        sleep(7500);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);
        // Step 4:  Stop and close the claw.
        //robot.leftDrive.setPower(0);
        //robot.rightDrive.setPower(0);
        //robot.leftClaw.setPosition(1.0);
        //robot.rightClaw.setPosition(0.0);
        //robot.Lift.setPower(0.5);
        //telemetry.addData("Path", "Complete");
        //telemetry.update();
        //sleep(4000);
    }
}


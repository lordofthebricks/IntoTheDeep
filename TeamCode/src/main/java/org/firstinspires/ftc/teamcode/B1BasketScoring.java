package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardwareclasses.Lift;

@Autonomous(name="B1Basket_Scoring", group="Autonomous")

public class B1BasketScoring extends LinearOpMode {

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

        //Start of Scoring specific code
        robot.Arm.setPower(0.5);
        sleep(500);
        robot.Wrist1.setPosition(0.5);
        robot.Wrist2.setPosition(0.5);
        robot.Claw.setPosition(0.5);
        sleep(500);
        robot.Claw.setPosition(0.0);
        robot.Wrist2.setPosition(0.001);
        robot.Wrist1.setPosition(-0.2);
        robot.Arm.setPower(-0.5);
        sleep(500);
        robot.Claw.setPosition(0.5);
        sleep(250);

        robot.LeftFront.setPower(REVERSE_SPEED);
        robot.RightFront.setPower(REVERSE_SPEED);
        robot.LeftBack.setPower(REVERSE_SPEED);
        robot.RightBack.setPower(REVERSE_SPEED);
        sleep(750);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(-TURN_SPEED);
        robot.RightFront.setPower(TURN_SPEED);
        robot.LeftBack.setPower(-TURN_SPEED);
        robot.RightBack.setPower(TURN_SPEED);
        sleep(330);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

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
        //End of Scoring Specific Code

        //Strafe to the observation
        robot.LeftFront.setPower(STRAFE_SPEED);
        robot.RightFront.setPower(-STRAFE_SPEED);
        robot.LeftBack.setPower(-STRAFE_SPEED);
        robot.RightBack.setPower(STRAFE_SPEED);
        sleep(7500);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

    }
}



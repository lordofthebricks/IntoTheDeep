package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardwareclasses.Lift;

@Autonomous(name="Observation_Preloaded_Sample_Observation", group="Autonomous")

public class Observationpreloadedsampleobservation extends LinearOpMode {

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
        robot.LeftFront.setPower(FORWARD_SPEED);
        robot.RightFront.setPower(FORWARD_SPEED);
        robot.LeftBack.setPower(FORWARD_SPEED);
        robot.RightBack.setPower(FORWARD_SPEED);
        sleep(300);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(0.6);
        robot.RightFront.setPower(STRAFE_SPEED);
        robot.LeftBack.setPower(STRAFE_SPEED);
        robot.RightBack.setPower(0.6);
        sleep(3500);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(-TURN_SPEED);
        robot.RightFront.setPower(TURN_SPEED);
        robot.LeftBack.setPower(-TURN_SPEED);
        robot.RightBack.setPower(TURN_SPEED);
        sleep(510);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(REVERSE_SPEED);
        robot.RightFront.setPower(REVERSE_SPEED);
        robot.LeftBack.setPower(REVERSE_SPEED);
        robot.RightBack.setPower(REVERSE_SPEED);
        sleep(350);
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

        robot.LeftFront.setPower(-TURN_SPEED);
        robot.RightFront.setPower(-TURN_SPEED);
        robot.LeftBack.setPower(-TURN_SPEED);
        robot.RightBack.setPower(TURN_SPEED);
        sleep(400);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(-0.6);
        robot.RightFront.setPower(-STRAFE_SPEED);
        robot.LeftBack.setPower(-STRAFE_SPEED);
        robot.RightBack.setPower(-0.6);
        sleep(400);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(FORWARD_SPEED);
        robot.RightFront.setPower(FORWARD_SPEED);
        robot.LeftBack.setPower(FORWARD_SPEED);
        robot.RightBack.setPower(FORWARD_SPEED);
        sleep(2300);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);

        robot.LeftFront.setPower(-0.6);
        robot.RightFront.setPower(-STRAFE_SPEED);
        robot.LeftBack.setPower(-STRAFE_SPEED);
        robot.RightBack.setPower(-0.6);
        sleep(600);
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);
    }
}

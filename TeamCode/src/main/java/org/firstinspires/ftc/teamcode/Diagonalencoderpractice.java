package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Diagonal_Encoder_Practice", group="Autonomous")

public class Diagonalencoderpractice extends LinearOpMode {

    hardware robot = new hardware();

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    static final double FORWARD_SPEED = 0.8;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.LeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Starting at", "%7d :%7d",
                robot.LeftFront.getCurrentPosition(),
                robot.RightFront.getCurrentPosition(),
                robot.LeftBack.getCurrentPosition(),
                robot.RightBack.getCurrentPosition());
        telemetry.update();

        waitForStart();

       // encoderDrive(FORWARD_SPEED, 48, 48, 48, 48, 3.0);

        encoderLift(FORWARD_SPEED, 102, 3.0);

        robot.Bucket.setPosition(0);

        sleep(1000);
        robot.Bucket.setPosition(0.5);
        sleep(500);
        encoderLift(FORWARD_SPEED, -100, 3.0);

        encoderArm(FORWARD_SPEED,40,3.0);

        encoderArm(FORWARD_SPEED,-40,3.0);
    }

    public void encoderDrive(double speed,
                             double leftfrontInches, double rightfrontInches, double leftbackInches, double rightbackInches,
                             double timeout) {
        int newLeftfrontTarget;
        int newRightfrontTarget;
        int newLeftbackTarget;
        int newRightbackTarget;

        if (opModeIsActive()) ;
        {

            newLeftfrontTarget = robot.LeftFront.getCurrentPosition() + (int) (leftfrontInches * COUNTS_PER_INCH);
            newRightfrontTarget = robot.RightFront.getCurrentPosition() + (int) (rightfrontInches * COUNTS_PER_INCH);
            newLeftbackTarget = robot.LeftBack.getCurrentPosition() + (int) (leftbackInches * COUNTS_PER_INCH);
            newRightbackTarget = robot.RightBack.getCurrentPosition() + (int) (rightbackInches * COUNTS_PER_INCH);
            robot.LeftFront.setTargetPosition(newLeftfrontTarget);
            robot.RightFront.setTargetPosition(newRightfrontTarget);
            robot.LeftBack.setTargetPosition(newLeftbackTarget);
            robot.RightBack.setTargetPosition(newRightbackTarget);

            robot.LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.LeftFront.setPower(Math.abs(speed));
            robot.RightFront.setPower(Math.abs(speed));
            robot.LeftBack.setPower(Math.abs(speed));
            robot.RightBack.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeout) &&
                    (robot.LeftFront.isBusy() && robot.RightFront.isBusy() && robot.LeftBack.isBusy() && robot.RightBack.isBusy())

            ) ;
        }
    }

    public void encoderLift(double speed,
                            double liftInches, double timeout) {
        int newliftTarget;

        if (opModeIsActive()) ;
        {


            newliftTarget = robot.Lift.getCurrentPosition() + (int) (liftInches * COUNTS_PER_INCH);
            robot.Lift.setTargetPosition(newliftTarget);

            robot.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.Lift.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeout) &&
                    (robot.Lift.isBusy())
            ) ;
        }
    }

    public void encoderArm(double speed,
                           double armInches, double timeout) {
        int newarmTarget;

        if (opModeIsActive()) ;
        {


            newarmTarget = robot.Arm.getCurrentPosition() + (int) (armInches * COUNTS_PER_INCH);
            robot.Arm.setTargetPosition(newarmTarget);

            robot.Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.Arm.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeout) &&
                    (robot.Arm.isBusy())
            ) ;
        }
    }
}


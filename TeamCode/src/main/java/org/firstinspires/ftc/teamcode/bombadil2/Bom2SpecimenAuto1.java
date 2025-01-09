package org.firstinspires.ftc.teamcode.bombadil2;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "B2_1_Specimen_Autonomous")

public class Bom2SpecimenAuto1 extends LinearOpMode {

    BombadilHardware robot = new BombadilHardware();

    private ElapsedTime runtime = new ElapsedTime();
    static final double DISTANCE_FROM_WALL = 17.35;

    // was put as 537.7
    static final double COUNTS_PER_MOTOR_REV = 384.5;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);




    public void runOpMode() {

        if (robot.init(hardwareMap) != 1) {
            telemetry.addLine("Issue Initalizing");
            telemetry.update();
            stop();
        }
        waitForStart();

        //strafe in front of the top scoring bar
        robot.leftFront.setPower(0.5);
        robot.rightFront.setPower(-0.5);
        robot.leftBack.setPower(-0.5);
        robot.rightBack.setPower(0.5);
        sleep(600);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //come off of the wall slightly
        robot.leftFront.setPower(-0.55);
        robot.rightFront.setPower(-0.5);
        robot.leftBack.setPower(-0.5);
        robot.rightBack.setPower(-0.5);
        sleep(800);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //deploy the tilt
        robot.tilt.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoderTilt(0.3, -9, 3.0);
        //tilt inches was formerly -14
        robot.tilt.setTargetPosition(robot.tilt.getCurrentPosition());

        //deploy the slide
        robot.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoderSlide(0.3, -13, 3.0);
        robot.slide.setTargetPosition((robot.slide.getCurrentPosition()));
        sleep(1500);

        //set the wrist position
        robot.wrist.setPosition(0.4);
        sleep(1500);

        //pull down the tilt
        encoderTilt(0.3,7,3.0);

        //get the wrist in position to pull away from the bar
        encoderSlide(0.3,13,3.0);

        //expel the specimen
        robot.intake1.setPower(0.8);
        robot.intake2.setPower(-0.8);
        sleep(1000);

        //retract the slide
        encoderSlide(0.3,-13,3.0);

        //retract the tilt
        encoderTilt(0.3,2,3.0);

        //reverse onto the wall
        robot.leftFront.setPower(0.55);
        robot.rightFront.setPower(0.5);
        robot.leftBack.setPower(0.5);
        robot.rightBack.setPower(0.5);
        sleep(1200);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //strafe to the observation zone
        robot.leftFront.setPower(-0.5);
        robot.rightFront.setPower(0.5);
        robot.leftBack.setPower(0.5);
        robot.rightBack.setPower(-0.5);
        sleep(1200);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);
    }

    //using a negative on the tilt inches will cause it to move towards the rear of the robot, using a positive inches will do the opposite
    public void encoderTilt ( double speed,
                              double tiltInches, double timeout){
        int newtiltTarget;

        if (opModeIsActive()) ;
        {


            newtiltTarget = robot.tilt.getCurrentPosition() + (int) (tiltInches * BombadilHardware.TILT_TICKS_PER_INCH);
            robot.tilt.setTargetPosition(newtiltTarget);

            robot.tilt.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.tilt.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeout) &&
                    (robot.tilt.isBusy())
            ) ;
        }
    }

    public void encoderSlide ( double speed, double slideInches, double timeout){
        int newslideTarget;

        if (opModeIsActive()) ;
        {


            newslideTarget = robot.slide.getCurrentPosition() + (int) (slideInches * BombadilHardware.SLIDE_TICKS_PER_INCH);
            robot.slide.setTargetPosition(newslideTarget);

            robot.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.slide.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeout) &&
                    (robot.slide.isBusy())
            ) ;
        }
    }
}
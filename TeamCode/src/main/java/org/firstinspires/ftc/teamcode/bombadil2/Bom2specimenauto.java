package org.firstinspires.ftc.teamcode.bombadil2;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name = "B2_Specimen_Observation_Autonomous")

public class Bom2specimenauto extends LinearOpMode {

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

        robot.init(hardwareMap);

        //stop the slide from moving in initialization
        robot.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.slide.setTargetPosition((robot.slide.getCurrentPosition()));

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
        encoderTilt(0.3, -7, 3.0);
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
        encoderTilt(0.3,5,3.0);

        //get the wrist in position to pull away from the bar
        encoderSlide(0.3,11,3.0);

        //expel the specimen
        robot.intake1.setPower(0.8);
        robot.intake2.setPower(-0.8);
        sleep(1000);

        //turn the robot 90 degrees to the right
        robot.leftFront.setPower(-0.5);
        robot.rightFront.setPower(0.5);
        robot.leftBack.setPower(-0.5);
        robot.rightBack.setPower(0.5);
        sleep(500);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //strafe to the wall to align the robot
        robot.leftFront.setPower(-0.5);
        robot.rightFront.setPower(0.5);
        robot.leftBack.setPower(0.5);
        robot.rightBack.setPower(-0.5);
        sleep(1700);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //get the wrist into position to grab to specimen off of the wall
        robot.wrist.setPosition(0.3);

        //go to the edge of the observation zone
        robot.leftFront.setPower(-0.4);
        robot.rightFront.setPower(-0.4);
        robot.leftBack.setPower(-0.4);
        robot.rightBack.setPower(-0.4);
        sleep(1900);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        /*    while (robot.distance1.getDistance(DistanceUnit.INCH) < DISTANCE_FROM_WALL && robot.distance2.getDistance(DistanceUnit.INCH) < DISTANCE_FROM_WALL){
                robot.leftFront.setPower(0.7);
                robot.leftBack.setPower(0.7);
                robot.rightFront.setPower(0.7);
                robot.rightBack.setPower(0.7);
            }

            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);

            while (robot.distance1.getDistance(DistanceUnit.INCH) >= robot.distance2.getDistance(DistanceUnit.INCH) + 0.5){
                //correction code goes here
                robot.leftFront.setPower(-0.5);
                robot.leftBack.setPower(-0.5);
                robot.rightFront.setPower(0.5);
                robot.leftBack.setPower(0.5);
            }
            while (robot.distance1.getDistance(DistanceUnit.INCH) <= robot.distance2.getDistance(DistanceUnit.INCH) - 0.5){
                //correction code goes here
                robot.leftFront.setPower(0.5);
                robot.leftBack.setPower(0.5);
                robot.rightFront.setPower(-0.5);
                robot.leftBack.setPower(-0.5);
            }



//            if (robot.distance1.getDistance(DistanceUnit.INCH) < 6 && robot.distance2.getDistance(DistanceUnit.INCH) < 6) {
//                robot.leftFront.setPower(0);
//                robot.leftBack.setPower(0);
//                robot.rightFront.setPower(0);
//                robot.rightBack.setPower(0);
//            } else {
//
//                if (robot.distance1.getDistance(DistanceUnit.INCH) < 6) ;
//                {
//                    robot.leftFront.setPower(0);
//                    robot.leftBack.setPower(0);
//                }
//
//                if (robot.distance2.getDistance(DistanceUnit.INCH) < 6) ;
//                {
//                    robot.rightFront.setPower(0);
//                    robot.rightBack.setPower(0);
//                }
//            }
//        }*/
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


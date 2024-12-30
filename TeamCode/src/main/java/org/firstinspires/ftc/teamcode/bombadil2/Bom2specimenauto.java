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
@Autonomous(name = "Bombadil2_Specimen_Autonomous", group = "Autonomous")

public class Bom2specimenauto extends LinearOpMode {

    BombadilHardware robot = new BombadilHardware();

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    private DistanceSensor distance1;
    private DistanceSensor distance2;


    public void runOpMode() {

        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        distance2 = hardwareMap.get(DistanceSensor.class, "distance2");

        Rev2mDistanceSensor sensorTimeOfFlight1 = (Rev2mDistanceSensor) distance1;

        telemetry.addData(">>", "Press start to continue");
        telemetry.update();

        Rev2mDistanceSensor sensorTimeOfFlight2 = (Rev2mDistanceSensor) distance2;

        telemetry.addData(">>", "Press start to continue");
        telemetry.update();

        robot.init(hardwareMap);

        waitForStart();


        //come off of the wall slightly
        robot.leftFront.setPower(-0.5);
        robot.rightFront.setPower(-0.5);
        robot.leftBack.setPower(-0.5);
        robot.rightBack.setPower(-0.5);
        sleep(150);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //strafe in front of the top scoring bar
        robot.leftFront.setPower(-1);
        robot.rightFront.setPower(1);
        robot.leftBack.setPower(1);
        robot.rightBack.setPower(-1);
        sleep(600);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //deploy the tilt
        encoderTilt(0.3, -4, 3.0);

        //deploy the slide
        encoderSlide(0.3, 5, 3.0);

        while (opModeIsActive()) {

            robot.leftFront.setPower(-0.7);
            robot.leftBack.setPower(-0.7);
            robot.rightFront.setPower(-0.7);
            robot.rightBack.setPower(-0.7);

            if (distance1.getDistance(DistanceUnit.CM) < 10 && distance2.getDistance(DistanceUnit.CM) < 10) {
                robot.leftFront.setPower(0);
                robot.leftBack.setPower(0);
                robot.rightFront.setPower(0);
                robot.rightBack.setPower(0);
            } else {

                if (distance1.getDistance(DistanceUnit.CM) < 10) ;
                {
                    robot.leftFront.setPower(0);
                    robot.leftBack.setPower(0);
                }

                if (distance2.getDistance(DistanceUnit.CM) < 10) ;
                {
                    robot.rightFront.setPower(0);
                    robot.rightBack.setPower(0);
                }
            }
        }
        //get the wrist in position to pull away from the bar
        robot.wrist.setPosition(0);

        //pull the slide down
        encoderSlide(-0.3, 10, 3.0);

    }

        //using a negative on the tilt inches will cause it to move towards the rear of the robot, using a positive inches will do the opposite
        public void encoderTilt ( double speed,
        double tiltInches, double timeout){
            int newtiltTarget;

            if (opModeIsActive()) ;
            {


                newtiltTarget = robot.tilt.getCurrentPosition() + (int) (tiltInches * COUNTS_PER_INCH);
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


                newslideTarget = robot.tilt.getCurrentPosition() + (int) (slideInches * COUNTS_PER_INCH);
                robot.tilt.setTargetPosition(newslideTarget);

                robot.tilt.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();
                robot.tilt.setPower(Math.abs(speed));

                while (opModeIsActive() &&
                        (runtime.seconds() < timeout) &&
                        (robot.tilt.isBusy())
                ) ;
            }
        }
    }


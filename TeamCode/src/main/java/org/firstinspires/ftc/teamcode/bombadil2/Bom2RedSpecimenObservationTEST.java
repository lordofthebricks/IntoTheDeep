package org.firstinspires.ftc.teamcode.bombadil2;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.bombadil2.rrhardware.Intake;
import org.firstinspires.ftc.teamcode.bombadil2.rrhardware.Slide;
import org.firstinspires.ftc.teamcode.bombadil2.rrhardware.Tilt;

@Autonomous (name = "Bom2_Red_Specimen_ObservationTEST")
public class Bom2RedSpecimenObservationTEST extends LinearOpMode {

//    BombadilHardware robot = new BombadilHardware();

    private ElapsedTime runtime = new ElapsedTime();
    Slide slide = new Slide(hardwareMap);
    Tilt tilt = new Tilt(hardwareMap);
    Intake intake = new Intake(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d beginPose = new Pose2d(12, -65.5, 3*Math.PI/2);
        Pose2d scorePose = new Pose2d(0,-36,3*Math.PI/2);
        Pose2d midScorePose = new Pose2d(0, -38, 3*Math.PI/2);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Slide slide = new Slide(hardwareMap);
        Tilt tilt = new Tilt(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        Action firstPath = drive.actionBuilder(beginPose)
                .splineToConstantHeading(new Vector2d(0, -38), 3*Math.PI/2)
                .build();
        Action SecondPath = drive.actionBuilder(midScorePose)
                .strafeTo(new Vector2d(0, -36))
                .build();
        Action ThirdPath = drive.actionBuilder(scorePose)
                .strafeTo(new Vector2d( 56, -56))
                .build();
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        new InstantAction( () -> slide.wrist.setPosition(0.8)),
                        firstPath,
                        //tilt.toUprightPosition(),
                        new InstantAction( () -> encoderTilt(0.3,-19,3.0)),
                        //slide.out(7),
                        new InstantAction( () -> encoderSlide(0.3,-7,3.0)),
                        SecondPath,
                        //slide.in(7),
                        new InstantAction( () -> encoderSlide(0.3,7,3.0)),
                        intake.putOut(),
                        ThirdPath
                )
        );


    }
    public void encoderTilt ( double speed,
                              double tiltInches, double timeout){
        int newtiltTarget;

        if (opModeIsActive()) ;
        {


            newtiltTarget = tilt.motor.getCurrentPosition() + (int) (tiltInches * BombadilHardware.TILT_TICKS_PER_INCH);
            tilt.motor.setTargetPosition(newtiltTarget);

            tilt.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            tilt.motor.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeout) &&
                    (tilt.motor.isBusy())
            ) ;
        }
    }

    public void encoderSlide ( double speed, double slideInches, double timeout){
        int newslideTarget;

        if (opModeIsActive()) ;
        {


            newslideTarget = slide.motor.getCurrentPosition() + (int) (slideInches * BombadilHardware.SLIDE_TICKS_PER_INCH);
            slide.motor.setTargetPosition(newslideTarget);

            slide.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            slide.motor.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeout) &&
                    (slide.motor.isBusy())
            ) ;
        }
    }
}


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

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.bombadil2.rrhardware.Intake;
import org.firstinspires.ftc.teamcode.bombadil2.rrhardware.Slide;
import org.firstinspires.ftc.teamcode.bombadil2.rrhardware.Tilt;

@Autonomous
public class Bom2RedSpecimenObservation extends LinearOpMode {



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
                        tilt.toUprightPosition(),
                        slide.out(7),
                        SecondPath,
                        slide.in(7),
                        intake.putOut(),
                        ThirdPath
                )
        );


    }
}

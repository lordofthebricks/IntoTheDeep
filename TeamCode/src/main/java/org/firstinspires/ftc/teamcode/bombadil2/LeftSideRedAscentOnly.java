package org.firstinspires.ftc.teamcode.bombadil2;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
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
public class LeftSideRedAscentOnly extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d beginPose = new Pose2d(-12, -65.5, Math.PI/2);
        Pose2d scorePose = new Pose2d(-28,-12,Math.PI);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Slide slide = new Slide(hardwareMap);
        Tilt tilt = new Tilt(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        Action firstPath = drive.actionBuilder(beginPose)
                .splineTo(new Vector2d(-38, -40), Math.PI)
                .strafeTo(new Vector2d(-38, -12))
                .strafeTo(new Vector2d(-28, -12))
                .build();
        Action SecondPath = drive.actionBuilder(scorePose)
                .strafeTo(new Vector2d(-24, -12))

                .build();
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        firstPath,
                        tilt.toUprightPosition(),
                                SecondPath

                        //TODO scoring code here

                )
        );


    }
}

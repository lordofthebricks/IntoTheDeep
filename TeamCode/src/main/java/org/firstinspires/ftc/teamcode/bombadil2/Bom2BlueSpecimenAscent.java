package org.firstinspires.ftc.teamcode.bombadil2;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Action;
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
public class Bom2BlueSpecimenAscent extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d beginPose = new Pose2d(12, 65.5, 3*Math.PI/2);
        Pose2d scorePose = new Pose2d(0,40,Math.PI/2);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Slide slide = new Slide(hardwareMap);
        Tilt tilt = new Tilt(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        Action firstPath = drive.actionBuilder(beginPose)
                .splineTo(new Vector2d(0, 40), 3*Math.PI/2)
                .build();
        Action SecondPath = drive.actionBuilder(scorePose)
                .strafeToSplineHeading(new Vector2d(38, 37), 2*PI)
                .splineToConstantHeading(new Vector2d(38, 12),2*PI)
                .strafeTo(new Vector2d(24, 12))
                .build();
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        firstPath,
                        new SleepAction(9), //TODO scoring code here
                        SecondPath
                )
        );


    }
}

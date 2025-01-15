package org.firstinspires.ftc.teamcode.bombadil2;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
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

@Autonomous(name = "Left Specimin Ascent")
public class Bom2RedSpecimenAscent extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d beginPose = new Pose2d(-12, -65.5, Math.PI/2);
        Pose2d scorePose = new Pose2d(0,-45,3*Math.PI/2);
        Pose2d midScorePose = new Pose2d(0, -42, 3*Math.PI/2);
        Pose2d finalpose = new Pose2d(-28,-12,Math.PI);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Slide slide = new Slide(hardwareMap);
        Tilt tilt = new Tilt(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        Action firstPath = drive.actionBuilder(beginPose)
                .strafeToSplineHeading(new Vector2d(0, -42), 3*Math.PI/2)
                .build();
        Action SecondPath = drive.actionBuilder(midScorePose)
                .strafeTo(new Vector2d(0, -45))
                .build();
        Action ThirdPath = drive.actionBuilder(scorePose)
                .strafeToLinearHeading(new Vector2d(-38, -48), Math.PI)
                .strafeTo(new Vector2d(-38, -12))
                .strafeTo(new Vector2d(-28, -12))
                .build();
        Action FourthPath = drive.actionBuilder(finalpose)
                        .strafeTo(new Vector2d(-26,-12))
                                .build();
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        new SleepAction(15),
                        new InstantAction( () -> slide.wrist.setPosition(0.8)),
                        firstPath,
                        tilt.toUprightPosition(),
                        slide.out(14),
//                        SecondPath,
                        new ParallelAction(
                                intake.takeIn(),
                                slide.out(4)
                        ),
                        SecondPath,
                        intake.stop(),
                        new ParallelAction(tilt.toOutPosition(),
                                slide.in(18),
                                ThirdPath
                        ),
                        tilt.toUprightPosition(),
                        FourthPath

                )
        );


    }
}

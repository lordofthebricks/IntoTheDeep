package org.firstinspires.ftc.teamcode;

//RR specific imports

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
//Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardwareclasses.Claw;
import org.firstinspires.ftc.teamcode.hardwareclasses.FrontSlide;
import org.firstinspires.ftc.teamcode.hardwareclasses.Lift;
import org.firstinspires.ftc.teamcode.hardwareclasses.MecanumDrive;

@Config
@Autonomous(name = "B1_Basket_Scoring", group = "Autonomous")

public class B1BasketScoring extends LinearOpMode {

    @Override
    public void runOpMode() {

        Pose2d initialPose = new Pose2d(38, 56, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Claw claw = new Claw(hardwareMap);

        Lift lift = new Lift(hardwareMap);

        FrontSlide slide = new FrontSlide(hardwareMap);

        hardware robot = new hardware();

        Action movement1 = drive.actionBuilder(initialPose)
                .lineToX(56)
                .turn(.6)
                .turn(-.6)
                .lineToX(-56)
                .build();

        //Action movement2 = drive.actionBuilder(new Pose2d(56,56,0));
        //.lin



























        //.build();
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        movement1,
                        slide.slideOut(18),
                        new InstantAction(() -> robot.Claw.setPosition(0)),
                        new InstantAction(() -> robot.Lift.setPower(0.5)),
                        new SleepAction(3),
                        new InstantAction(() -> robot.Lift.setPower(0)),
                        lift.upLift(48),
                        new InstantAction(() -> robot.Bucket.setPosition(0))
                )
        );
    }
}
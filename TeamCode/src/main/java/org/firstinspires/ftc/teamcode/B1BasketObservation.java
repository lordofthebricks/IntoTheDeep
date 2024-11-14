package org.firstinspires.ftc.teamcode;

//RR specific imports
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
//Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardwareclasses.Claw;
import org.firstinspires.ftc.teamcode.hardwareclasses.FrontSlide;
import org.firstinspires.ftc.teamcode.hardwareclasses.Lift;
import org.firstinspires.ftc.teamcode.hardwareclasses.MecanumDrive;

@Config
@Autonomous(name = "B1_Basket_Observation", group = "Autonomous")
public class B1BasketObservation extends LinearOpMode{

    @Override
    public void runOpMode() {



        // instantiate your MecanumDrive at a particular pose.
        Pose2d initialPose = new Pose2d(34, 63.5, Math.PI);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        // make a Claw instance
        Claw claw = new Claw(hardwareMap);
        // make a Lift instance
//        Lift lift = new Lift(hardwareMap);
        //make a Slide instance
        FrontSlide slide = new FrontSlide(hardwareMap);
        //add a normal hardware just in case anything is needed

        hardware robot = new hardware();

        robot.init(hardwareMap);

        Action movement1 = drive.actionBuilder(initialPose)
                //.splineToConstantHeading(new Vector2d(56,56), Math.PI)
               // .strafeToConstantHeading(new Vector2d(56,56))
                .strafeToConstantHeading(new Vector2d(34,56))
                .waitSeconds(1)
                .strafeToConstantHeading(new Vector2d(56,56))
                .waitSeconds(1)
                .turnTo(135)
                .build();

        Action movement2 = drive.actionBuilder(new Pose2d(56,56,Math.toRadians(135)))
                .turn(Math.PI)
                .strafeTo(new Vector2d(-56,56))
                .turnTo(Math.toRadians(270))
                .build();


       waitForStart();


        Actions.runBlocking(
                new SequentialAction(
                        movement1,
                        new InstantAction(() -> robot.Lift.setPower(0.8)),
                        new SleepAction(1.5),
                        new InstantAction(() -> robot.Bucket.setPosition(0.5)),
                        movement2
                        )
                    );


    }
}

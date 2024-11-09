package org.firstinspires.ftc.teamcode;

//RR specific imports
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
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
        Pose2d initialPose = new Pose2d(38, 56, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        // make a Claw instance
        Claw claw = new Claw(hardwareMap);
        // make a Lift instance
        Lift lift = new Lift(hardwareMap);
        //make a Slide instance
        FrontSlide slide = new FrontSlide(hardwareMap);
        //add a normal hardware just in case anything is needed

        hardware robot = new hardware();

        Action movement1 = drive.actionBuilder(initialPose)
                .lineToX(56)

                .build();
        Action movement2 = drive.actionBuilder(initialPose)
                .lineToX(-56)

                .build();

        robot.init(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                      //  movement1,
                      //  slide.slideOut(18),
                      //  new InstantAction(() -> robot.Claw.setPosition(0)),
                        //new InstantAction(() -> robot.Lift.setPower(0.5)),
                        //new InstantAction(() -> robot.Lift.setPower(sleep(3000);)),
                        //lift.upLift(48),
                        //new InstantAction(() -> robot.Bucket.setPosition(0))


                )
        );

        //robot.init(hardwareMap);




    }
}

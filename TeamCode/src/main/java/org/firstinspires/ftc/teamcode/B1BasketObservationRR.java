package org.firstinspires.ftc.teamcode;

//RR specific imports

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
//Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardwareclasses.Claw;
import org.firstinspires.ftc.teamcode.hardwareclasses.FrontSlide;
import org.firstinspires.ftc.teamcode.hardwareclasses.MecanumDrive;

@Config
@Autonomous(name = "B1_Basket_ObservationRR", group = "Autonomous")
public class B1BasketObservationRR extends LinearOpMode{

    @Override
    public void runOpMode() {



        // instantiate your MecanumDrive at a particular pose.
        Pose2d initialPose = new Pose2d(34, 63.5, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        hardware robot = new hardware();
        // make a Claw instance
        Claw claw = new Claw(hardwareMap, robot.LeftFront, robot.LeftBack, robot.RightFront, robot.RightBack);
        // make a Lift instance
//        Lift lift = new Lift(hardwareMap);
        //make a Slide instance
        FrontSlide slide = new FrontSlide(hardwareMap);
        //add a normal hardware just in case anything is needed



        robot.init(hardwareMap);

        Action movement1 = drive.actionBuilder(initialPose)
                //.splineToConstantHeading(new Vector2d(56,56), Math.PI)
               // .strafeToConstantHeading(new Vector2d(56,56))
                .lineToX(56)
                .turn(Math.toRadians(45))
                .build();

        Action movement2 = drive.actionBuilder(new Pose2d(56,56,Math.toRadians(225)))
                .turn(-Math.toRadians(-45))
                .strafeTo(new Vector2d(-56,56))
                .turnTo(Math.toRadians(270))
                .build();

       // robot.init(hardwareMap);
       waitForStart();
       // if (isStopRequested()) return

        Actions.runBlocking(
                new SequentialAction(
                        movement1,
                        new InstantAction(() -> {
                            robot.Lift.setPower(0.8);
                        }),
                        new SleepAction(1.5),
                        new InstantAction(() -> robot.Bucket.setPosition(0.5)),
                        movement2
                        )
                    );


    }
}

package org.firstinspires.ftc.teamcode.bombadil2;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
@Autonomous
public class Bom2OdometryPractice extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d beginPose = new Pose2d(-72+6.5, 12, 0);
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .strafeToConstantHeading(new Vector2d(10,0))
                        .strafeToConstantHeading(new Vector2d(10,10))
                        .strafeToConstantHeading(new Vector2d(0,10))
                        .strafeToConstantHeading(new Vector2d(0,0))
                        .build()
        );


    }
}

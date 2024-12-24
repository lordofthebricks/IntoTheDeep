package org.firstinspires.ftc.teamcode.bombadil2;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "Bombadil2_Specimen_Autonomous", group = "Autonomous")

public class Bom2specimenauto extends LinearOpMode {

    BombadilHardware robot = new BombadilHardware();

    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();
        //NOTE: the robot will start with the rear facing the inside of field

        //come off of the wall slightly
        robot.leftFront.setPower(-1);
        robot.rightFront.setPower(-1);
        robot.leftBack.setPower(-1);
        robot.rightBack.setPower(-1);
        sleep(500);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //strafe in front of the top scoring bar
        robot.leftFront.setPower(1);
        robot.rightFront.setPower(-1);
        robot.leftBack.setPower(-1);
        robot.rightBack.setPower(1);
        sleep(2500);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //reverse slightly so that there is clearance for the tilt
        robot.leftFront.setPower(-1);
        robot.rightFront.setPower(-1);
        robot.leftBack.setPower(-1);
        robot.rightBack.setPower(-1);
        sleep(700);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //get the tilt in position
        robot.tilt.setPower(0.7);
        sleep(500);

        //deploy the slide
        robot.slide.setPower(0.7);
        sleep(500);

        //reverse so that the tilt is in position to score on the top bar
        robot.leftFront.setPower(-1);
        robot.rightFront.setPower(-1);
        robot.leftBack.setPower(-1);
        robot.rightBack.setPower(-1);
        sleep(1300);
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);

        //get the wrist in position to pull away from the bar
        robot.wrist.setPosition(0.3);

         while (robot.slide.isBusy()){

         }

        //pull the slide down
        robot.slide.setPower(-0.4);
        sleep(250);


    }

}

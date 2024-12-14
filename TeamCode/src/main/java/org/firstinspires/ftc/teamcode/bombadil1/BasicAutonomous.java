package org.firstinspires.ftc.teamcode.bombadil1;


// RR-specific imports
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
        import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//import org.firstinspires.ftc.teamcode.hardwareclasses.Claw;
import org.firstinspires.ftc.teamcode.bombadil1.hardwareclasses.FrontSlide;
import org.firstinspires.ftc.teamcode.bombadil1.hardwareclasses.Lift;
import org.firstinspires.ftc.teamcode.MecanumDrive;

//Important: Do Not Use this File, Make sure to copy and paste this to create your autonomouses
@Autonomous(name = "Basic Auto", group = "Autonomous")
public class BasicAutonomous extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        //import the hardware


        // instantiate your MecanumDrive at a particular pose.
        //this is where the robot starts on the field
        Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        // make a Claw instance
//        Claw claw = new Claw(hardwareMap);
        // make a Lift instance
        Lift lift = new Lift(hardwareMap);
        //make a Slide instance
        FrontSlide slide = new FrontSlide(hardwareMap);
        //add a normal hardware just in case anything is needed
        RRHardware robot = new RRHardware();


        //create strings of movements like this. Make sure to include
        //create more if needed
        Action movement1 = drive.actionBuilder(initialPose)

                .build();


        waitForStart();

        //This is Where all the actions Happens
        // make sure to use actions, if you need to make happen that there is not an action for, make sure to use new InstantAction()
        Actions.runBlocking(
                new SequentialAction(
                    movement1,
                    slide.slideOut(18),
                    new InstantAction(() -> robot.Claw.setPosition(0))
                )
        );

    }
}

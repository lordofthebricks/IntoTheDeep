package org.firstinspires.ftc.teamcode.bombadil1;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//import org.firstinspires.ftc.teamcode.hardwareclasses.Claw;
import org.firstinspires.ftc.teamcode.bombadil1.hardwareclasses.FrontSlide;

import java.util.ArrayList;
import java.util.List;


@TeleOp
public class TestingTeleop extends OpMode {
   // Claw claw;
    FrontSlide arm;

    hardwareRoadRunner robot;

    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {

        robot = new hardwareRoadRunner();
        robot.init(hardwareMap);
       // claw = new Claw(hardwareMap, robot.LeftFront, robot.LeftBack, robot.RightFront, robot.RightBack);

        arm = new FrontSlide(hardwareMap);
        //claw.init();
    }
    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        /*LLStatus status = claw.limelight3A.getStatus();
        telemetry.addData("Name", "%s",
                status.getName());
        telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                status.getTemp(), status.getCpu(),(int)status.getFps());
        telemetry.addData("Pipeline", "Index: %d, Type: %s",
                status.getPipelineIndex(), status.getPipelineType());

        //update with gamepad
        if(gamepad1.a){
            runningActions.add(new SequentialAction(
//                    claw.LLLineupHorizontal(),
//                    claw.LLLVertical(),
                    claw.LLGrab()

            ));
        }
        if(gamepad1.b){
            runningActions.add(new SequentialAction(
                    arm.slideOut(18),
                    claw.ReadyToGrab()

            ));
        }

        if(gamepad1.y){
            runningActions.add(new SequentialAction(
                    claw.BringUp(),
                    arm.slideIn(18)

            ));
        } */



        // update running actions
        List<Action> newActions = new ArrayList<>();
        for (Action action : runningActions) {
            action.preview(packet.fieldOverlay());
            if (action.run(packet)) {
                newActions.add(action);
            }
        }
        runningActions = newActions;

        dash.sendTelemetryPacket(packet);

    }


}

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardwareclasses.Claw;
import org.firstinspires.ftc.teamcode.hardwareclasses.FrontSlide;

import java.util.ArrayList;
import java.util.List;


@TeleOp
public class TestingTeleop extends OpMode {
    Claw claw = new Claw(hardwareMap);
    FrontSlide arm = new FrontSlide(hardwareMap);

    hardwareRoadRunner robot = new hardwareRoadRunner();

    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();

    @Override
    public void init() {
        robot.init(hardwareMap);
        claw.init();
    }
    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();

        //update with gamepad
        if(gamepad1.a){
            runningActions.add(new SequentialAction(
                    claw.LLGrab()

            ));
        }



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

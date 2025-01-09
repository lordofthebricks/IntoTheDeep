package com.example.meepmeeptesting;

import static java.lang.Math.*;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, PI, PI, 11.64)
                .build();
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12, 65.5, 3*PI/2))
                        //.splineToConstantHeading(new Vector2d(56,56), Math.PI)

                                .splineTo(new Vector2d(0, 40), 3*PI/2)
                                .waitSeconds(9)
                                .strafeTo(new Vector2d( -56,56))
               //.turn(Math.toRadians(90))
               //.lineToX(0)
                //.turn(Math.toRadians(90))
                //.lineToY(0)
                //.turn(Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
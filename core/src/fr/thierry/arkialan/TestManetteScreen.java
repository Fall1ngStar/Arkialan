package fr.thierry.arkialan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;

/**
 * TestManetteScreen class
 * Created by Thierry
 * 25/03/2017
 */
public class TestManetteScreen implements Screen {

    Controller manette;

    public TestManetteScreen(){
        for (Controller controller : Controllers.getControllers()) {
            Gdx.app.log("Contolleur : ", controller.getName());
        }

        manette = Controllers.getControllers().first();

        manette.addListener(new ControllerAdapter(){
            @Override
            public boolean buttonDown(Controller controller, int buttonIndex) {
                Gdx.app.log("Button pressed", buttonIndex + "");
                return super.buttonDown(controller, buttonIndex);
            }

            @Override
            public boolean axisMoved(Controller controller, int axisIndex, float value) {
                Gdx.app.log("Axis moved", "axis : " + axisIndex + " value : " + value);
                return super.axisMoved(controller, axisIndex, value);
            }

            @Override
            public boolean xSliderMoved(Controller controller, int sliderIndex, boolean value) {
                Gdx.app.log("X Slider moved","index : " + sliderIndex + " value : ");
                return super.xSliderMoved(controller, sliderIndex, value);
            }

            @Override
            public boolean ySliderMoved(Controller controller, int sliderIndex, boolean value) {
                Gdx.app.log("Y Slider moved","index : " + sliderIndex + " value : ");
                return super.ySliderMoved(controller, sliderIndex, value);
            }
        });


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void Input(){

    }
}

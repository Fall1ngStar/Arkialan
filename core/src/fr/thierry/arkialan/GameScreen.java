package fr.thierry.arkialan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;


/**
 * GameScreen class
 * Created by Thierry
 * 21/03/2017
 */
public class GameScreen implements Screen {

    public static ShapeRenderer sr = new ShapeRenderer();

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private World world;

    private float previousX, previousY;
    private float offsetX, offsetY;
    private boolean previousTouched;


    public GameScreen() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        world = new World();
        previousTouched = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        sr.setProjectionMatrix(camera.combined);
        input();

        world.  render();

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


    public void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            world.addBuilding(new Plateform(new Vector2(Gdx.input.getX() + offsetX, (Main.SCREEN_HEIGHT - Gdx.input.getY()) + offsetY)));
        }
        if (Gdx.input.isTouched()) {
            if (previousTouched) {
                camera.translate(-(Gdx.input.getX() - previousX) * camera.zoom, -(Main.SCREEN_HEIGHT - Gdx.input.getY() - previousY) * camera.zoom);
                offsetX -= (Gdx.input.getX() - previousX) * camera.zoom;
                offsetY -= (Main.SCREEN_HEIGHT - Gdx.input.getY() - previousY) * camera.zoom;
                previousX = Gdx.input.getX();
                previousY = Main.SCREEN_HEIGHT - Gdx.input.getY();
            } else {
                previousTouched = true;
                previousX = Gdx.input.getX();
                previousY = Main.SCREEN_HEIGHT - Gdx.input.getY();
            }
        } else {
            previousTouched = false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            camera.zoom -= 0.02f;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            camera.zoom += 0.02f;
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
            world.clear();
        }
    }
}

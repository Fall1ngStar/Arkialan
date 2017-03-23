package fr.thierry.arkialan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    public static ShapeRenderer debugSr = new ShapeRenderer();

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;

    private World world;

    private float previousFps;
    private float previousX, previousY;
    private float offsetX, offsetY;
    private boolean previousTouched;



    public GameScreen() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(new MyInput(camera));

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

        world.render();

        batch.begin();
        font.setColor(0,0,0,1);
        font.draw(batch,"FPS : " + Math.round(((1/delta)+previousFps)/2),30, Main.SCREEN_HEIGHT-30);
        previousFps = 1/delta;
        batch.end();

        /*debugSr.begin(ShapeRenderer.ShapeType.Line);
        debugSr.setColor(0, 0, 0, 1);
        debugSr.line(Gdx.input.getX(), -5000, Gdx.input.getX(), 5000);
        debugSr.line(-5000, (Main.SCREEN_HEIGHT - Gdx.input.getY()), 5000, (Main.SCREEN_HEIGHT - Gdx.input.getY()));
        debugSr.end();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(0, 0, 0, 1);
        sr.line(getRelativeX(), -5000, getRelativeX, 5000);
        sr.line(-5000, getRelativeY(), 5000, getRelativeY());
        sr.end();

        batch.begin();
        font.setColor(0,0,0,1);
        font.draw(batch,"Camera zoom : " + camera.zoom, 100,200);
        font.draw(batch,"OffsetX : " + (1-camera.zoom) * Main.SCREEN_WIDTH, 100,175);
        font.draw(batch,"MyInput X : " + Gdx.input.getX() * camera.zoom + offsetX, 100,150);
        font.draw(batch,"MyInput Y : " + (Main.SCREEN_HEIGHT - Gdx.input.getY()) * camera.zoom + offsetY, 100,100);
        batch.end();*/
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
            world.addBuilding(new Plateform(new Vector2(getRelativeX(), getRelativeY())));
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

        /*if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.zoom -= 0.007f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.zoom += 0.007f;
        }*/


        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            world.clear();
        }
    }

    private float getRelativeX(){
        return Gdx.input.getX() * camera.zoom + offsetX + (1-camera.zoom) * Main.SCREEN_WIDTH/2;
    }

    private float getRelativeY(){
        return (Main.SCREEN_HEIGHT - Gdx.input.getY()) * camera.zoom + offsetY + (1-camera.zoom) * Main.SCREEN_HEIGHT/2;
    }
}

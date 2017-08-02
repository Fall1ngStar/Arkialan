package fr.thierry.arkialan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


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
    private IndexedAStarPathFinder<Building> finder;
    private PlateformRoadPath path;
    private PathProvider provider;

    private float previousFps;
    private float previousX, previousY;
    private float offsetX, offsetY;
    private boolean previousTouched;

    private SelectableBuilding pathFrom, pathTo;
    private List<Unit> units;


    public GameScreen() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(new MyInput(camera));

        camera.setToOrtho(false, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        
        world = new World();
        previousTouched = false;


        path = new PlateformRoadPath();
        units = new ArrayList<>();
        provider = new PathProvider(world);
        Unit.setPathProvider(provider);
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

        world.render(delta);

        batch.begin();
        font.setColor(0, 0, 0, 1);
        font.draw(batch, "FPS : " + Math.round(((1 / delta) + previousFps) / 2), 30, Main.SCREEN_HEIGHT - 30);
        previousFps = 1 / delta;
        font.draw(batch, "Roads : " + world.getRoadsNumber()/2, 30, Main.SCREEN_HEIGHT - 60);
        font.draw(batch, "Buildings : " + world.getBuildingsNumber(), 30, Main.SCREEN_HEIGHT - 90);
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

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1, 0, 0, 1);
        Iterator<Connection<Building>> iterator = path.iterator();
        while (iterator.hasNext()) {
            Road r = ((Road) iterator.next());
            sr.rectLine(r.getFromNode().pos.x, r.getFromNode().pos.y, r.getToNode().pos.x, r.getToNode().pos.y, 10);
        }
        sr.end();
        units.forEach(Unit::render);
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
            Gdx.app.log("Key pressed", "A key has been pressed");
            world.addBuilding(new Plateform(new Vector2(getRelativeX(), getRelativeY())));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            world.addBuilding(new Mine(new Vector2(getRelativeX(), getRelativeY())));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.R))
            world.generateRandom();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
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

        /*if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            world.selectForRoad(getRelativeX(), getRelativeY());
        }*/
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            path.clear();
            world.selectForRoad(getRelativeX(), getRelativeY());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            units.clear();
            path.clear();
            world.clear();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            path.clear();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P) && world.getBuildingsNumber() > 0) {
            if(pathTo == null){
                pathTo = world.selectForPath(getRelativeX(), getRelativeY());
            } else{
                pathFrom = world.selectForPath(getRelativeX(), getRelativeY());
            }

            if(pathFrom != null && pathTo != null) {
                path.clear();
                finder = new IndexedAStarPathFinder<>(world);
                finder.searchConnectionPath(pathFrom, pathTo, new SimpleHeuristic(), path);
                if (path.getCount() > 0) {
                    units.add(new Unit(path.clone()));
                }
                pathFrom = null;
                pathTo = null;
            }
        }

    }

    private float getRelativeX() {
        return Gdx.input.getX() * camera.zoom + offsetX + (1 - camera.zoom) * Main.SCREEN_WIDTH / 2;
    }

    private float getRelativeY() {
        return (Main.SCREEN_HEIGHT - Gdx.input.getY()) * camera.zoom + offsetY + (1 - camera.zoom) * Main.SCREEN_HEIGHT / 2;
    }
}

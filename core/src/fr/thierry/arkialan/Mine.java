package fr.thierry.arkialan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Mine extends SelectableBuilding {

    private float animationAnglePosition;
    private float particleDistance;
    private float particleSpeed;
    private float particleRadius;
    private float particleNumber;

    public Mine(Vector2 pos) {
        this.pos = pos;
        this.radius = 60f;
        animationAnglePosition = 0;
        particleDistance = 25f;
        particleSpeed = 0.02f;
        particleRadius = 10f;
        particleNumber = 6;
    }

    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.PLUS))
            particleNumber++;
        if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS))
            particleNumber--;
        sr.begin(ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.circle(pos.x, pos.y, radius + 4);
        sr.setColor(Color.CYAN);
        sr.circle(pos.x, pos.y, radius);
        sr.translate(pos.x, pos.y, 0);
        animationAnglePosition += particleSpeed;
        for (int i = 0; i < particleNumber; i++) {
            float angle = MathUtils.PI * 2 * i / particleNumber;
            sr.setColor(Color.BLACK);
            sr.circle(MathUtils.cos(animationAnglePosition + angle) * (radius + particleDistance),
                    MathUtils.sin(animationAnglePosition + angle) * (radius + particleDistance), particleRadius + 2);
            sr.setColor(Color.CYAN);
            sr.circle(MathUtils.cos(animationAnglePosition + angle) * (radius + particleDistance),
                    MathUtils.sin(animationAnglePosition + angle) * (radius + particleDistance), particleRadius);
        }
        sr.translate(-pos.x, -pos.y, 0);
        sr.end();
    }

    
}
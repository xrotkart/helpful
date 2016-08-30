package pl.slivka.starfighter2d;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Tomek on 2016-08-28.
 */
public class SFGameView extends GLSurfaceView {
    private SFRenderer renderer;

    public SFGameView(Context context) {
        super(context);

        renderer = new SFRenderer();
        this.setRenderer(renderer);
    }
}

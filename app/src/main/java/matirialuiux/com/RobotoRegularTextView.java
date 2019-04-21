package matirialuiux.com;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by MATERIALUIUX.
 * for more visit http://materialuiux.com
 */
public class RobotoRegularTextView extends AppCompatTextView
{

    public RobotoRegularTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoRegularTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public RobotoRegularTextView(Context context)
    {
        super(context);
        init();
    }

    private void init()
    {
        if (!isInEditMode())
        {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
            setTypeface(tf);
        }
    }
}

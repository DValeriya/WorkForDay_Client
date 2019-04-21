package matirialuiux.com;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by MATERIALUIUX.
 * for more visit http://materialuiux.com
 */
public class RobotoBoldTextView extends AppCompatTextView
{
    public RobotoBoldTextView(Context context)
    {
        super(context);
        init();
    }

    public RobotoBoldTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public RobotoBoldTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        if (!isInEditMode())
        {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
            setTypeface(tf);
        }
    }
}
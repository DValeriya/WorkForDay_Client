package materialuiux.com;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by MATERIALUIUX.
 * for more visit http://materialuiux.com
 */
public class robotoRegularTextView  extends AppCompatTextView
{

    public robotoRegularTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    public robotoRegularTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public robotoRegularTextView(Context context)
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

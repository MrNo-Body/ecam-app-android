package be.ecam.chowdetails.chowdetails;
import android.app.Activity;
import android.content.Intent;


public class themeUtils

{

    private static int cTheme;

    public final static int GREEN = 4;

    public final static int RED = 3;

    public final static int BLUE = 2;

    public final static int BLACK = 1;

    public final static int WHITE = 0;

    public static void changeToTheme(Activity activity, int theme)

    {


        cTheme = theme;

        activity.finish();


        activity.startActivity(new Intent(activity, activity.getClass()));


    }

    public static void onActivityCreateSetTheme(Activity activity)

    {

        switch (cTheme)

        {

            default:

            case BLACK:

                activity.setTheme(R.style.BlackTheme);

                break;

            case WHITE:

                activity.setTheme(R.style.LightTheme);

                break;

            case BLUE:

                activity.setTheme(R.style.Blue);

                break;

            case RED:

                activity.setTheme(R.style.Red);

                break;

            case GREEN:

                activity.setTheme(R.style.Green);

                break;

        }

    }

}
package be.ecam.chowdetails.chowdetails;
import android.app.Activity;
import android.content.Intent;


public class ThemeUtils

{

    private static int cTheme;

    public final static int Normal = 7;

    public final static int Petit = 6;

    public final static int Grand = 5;

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

            case Grand:

                activity.setTheme(R.style.textGrand);

                break;

            case Petit:

                activity.setTheme(R.style.textPetit);

                break;

            case Normal:

                activity.setTheme(R.style.textNormal);

                break;
        }

    }

    public static void onActivityCreateSetTheme(Activity activity, int Theme)

    {

        switch (Theme)

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

            case Grand:

                activity.setTheme(R.style.textGrand);

                break;

            case Petit:

                activity.setTheme(R.style.textPetit);

                break;

            case Normal:

                activity.setTheme(R.style.textNormal);

                break;
        }

    }

}
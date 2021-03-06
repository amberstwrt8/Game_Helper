package game.gamehelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Mark Andrews on 2/23/2015.
 * Fragment for selecting the end piece to calculate runs
 */
public class EndSelectFragment extends DialogFragment{


        public interface EndListener {
            public void onClose(int var1);
        }

        EndListener mListener;
        GridView gridView;
        View drawView;
        ImageView endValue;
        int var1 = 0;

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try{
                mListener = (EndListener) activity;
            }catch (ClassCastException e) {
                throw new ClassCastException(getActivity().toString()
                        + " must implement interface EndListener");
            }
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Clicker clickListener = new Clicker();

            //retrieve draw_layout view
            drawView = View.inflate(getActivity(), R.layout.end_select_layout, null);

            int[] mList = new int[] {
                    R.drawable.dom_one,
                    R.drawable.dom_two,
                    R.drawable.dom_three,
                    R.drawable.dom_four,
                    R.drawable.dom_five,
                    R.drawable.dom_six,
                    R.drawable.dom_seven,
                    R.drawable.dom_eight,
                    R.drawable.dom_nine,
                    R.drawable.dom_ten,
                    R.drawable.dom_eleven,
                    R.drawable.dom_twelve

            };

            //get imageview from top left of layout and place the domino background
            endValue = (ImageView)drawView.findViewById(R.id.endValue);
            endValue.setOnClickListener(clickListener);

            //retrieve gridview from layout, set adapter
            gridView = (GridView)drawView.findViewById(R.id.gridView);
            gridView.setAdapter(new BitmapAdapter(getActivity(), mList));
            gridView.setNumColumns(3);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //mark value
                    position++;
                    var1 = position;
                    endValue.setImageDrawable(getSide(position));
                }
            });

            //create alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(drawView);
            builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //set head value of train
                    mListener.onClose(var1);

                }
            })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //close window

                        }
                    });

            return builder.create();
        }

        //Load image for domino side value
        private Drawable getSide(int value){


            switch(value){
                case 1:
                    return getResources().getDrawable(R.drawable.dom_one);
                case 2:
                    return getResources().getDrawable(R.drawable.dom_two);
                case 3:
                    return getResources().getDrawable(R.drawable.dom_three);
                case 4:
                    return getResources().getDrawable( R.drawable.dom_four);
                case 5:
                    return getResources().getDrawable(R.drawable.dom_five);
                case 6:
                    return getResources().getDrawable(R.drawable.dom_six);
                case 7:
                    return getResources().getDrawable(R.drawable.dom_seven);
                case 8:
                    return getResources().getDrawable(R.drawable.dom_eight);
                case 9:
                    return getResources().getDrawable(R.drawable.dom_nine);
                case 10:
                    return getResources().getDrawable(R.drawable.dom_ten);
                case 11:
                    return getResources().getDrawable(R.drawable.dom_eleven);
                case 12:
                    return getResources().getDrawable(R.drawable.dom_twelve);
                case 0:
                default:
                    return null;
            }
        }
        public class Clicker implements View.OnClickListener {
            @Override
            public void onClick(View v) {

                if(v == endValue ) {
                    var1 = 0;
                    endValue.setImageDrawable(null);
                }
            }
        }
    }



package hvcnbcvt_uddd.study_english.Ui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hvcnbcvt_uddd.study_english.Data.Model.Lesson;
import hvcnbcvt_uddd.study_english.R;

public class LessonAdapter extends ArrayAdapter<Lesson> {

    public LessonAdapter(@NonNull Context context, ArrayList<Lesson> lessons) {
        super(context, 0, lessons);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_lesson,parent,false);
        }

        TextView tvTitle  = (TextView) convertView.findViewById(R.id.tv_title_lesson_vocabulary);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tv_description_lesson_vocabulary);

        Lesson l = getItem(position);
        if(l != null)
        {
            tvTitle.setText(l.getName());
            tvDescription.setText(l.getDescription());
        }
        return convertView;
    }
}

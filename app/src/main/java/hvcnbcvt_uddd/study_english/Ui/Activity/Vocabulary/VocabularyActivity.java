package hvcnbcvt_uddd.study_english.Ui.Activity.Vocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import hvcnbcvt_uddd.study_english.Data.Model.Lesson;
import hvcnbcvt_uddd.study_english.R;
import hvcnbcvt_uddd.study_english.Ui.Adapter.LessonAdapter;

public class VocabularyActivity extends AppCompatActivity {

    GridView gv_leson;

    private ArrayList<Lesson> mLessons;
    private LessonAdapter mLessonAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_activity_lession);
        initData();
        initView();
    }

    private void initView() {
        gv_leson = (GridView) findViewById(R.id.gv_lesson_vocabulary);

        mLessonAdapter = new LessonAdapter(this, mLessons);
        gv_leson.setAdapter(mLessonAdapter);

        gv_leson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(VocabularyActivity.this,SlideActivity.class);
                intent.putExtra("num_exam",i+1);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mLessons = new ArrayList<>();
        mLessons.add(new Lesson(1, "Bài 1", "Description"));
        mLessons.add(new Lesson(2, "Bài 2", "Description"));
    }
}

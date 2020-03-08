package com.example.mvprecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvprecyclerview.adapter.FakePostsAdapter;
import com.example.mvprecyclerview.model.Post;
import com.example.mvprecyclerview.presenter.FakepostsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FakepostsPresenter.FakepostsDisplay {

    @BindView(R.id.fakepostRecyclerView)
    RecyclerView fakepostRecyclerView;


    private final FakepostsPresenter presenter = new FakepostsPresenter(this);
    private FakePostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void displayFakePosts(List<Post> fakeposts) {
        if (fakepostRecyclerView.getAdapter() == null) {
            FakePostsAdapter fakePostsAdapter = new FakePostsAdapter(fakeposts);
            fakepostRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            fakepostRecyclerView.setAdapter(fakePostsAdapter);
        } else {
            fakepostRecyclerView.swapAdapter(adapter, true);
            adapter.setFakeposts(fakeposts);
            adapter.notifyDataSetChanged();
        }
    }
}

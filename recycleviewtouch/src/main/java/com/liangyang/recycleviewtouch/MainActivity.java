package com.liangyang.recycleviewtouch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.liangyang.recycleviewtouch.adapter.RecycleViewAdapter;
import com.liangyang.recycleviewtouch.bean.DataBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecycleView;
    private ImageView mStitchBtn;
    private List<DataBean> dataBeanList = new ArrayList<>();
    private boolean isList = true;
    private RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        initView();
        //初始化数据源
        initData();
        //创建适配器，并绑定数据
        initAdapter();
        //初始化监听事件
        initListener();
        //为recycleView绑定触摸事件
        initTouch();
    }

    /**
     * 为recycleView绑定触摸事件
     */
    private void initTouch() {
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//拖拽手势
//                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//删除
                int swipeFlags = 0;//不删除
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(dataBeanList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑删除事件
                dataBeanList.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //是否可拖拽
                return true;
            }
        });

        touchHelper.attachToRecyclerView(mRecycleView);
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        //转换模式按钮点击监听事件
        mStitchBtn.setOnClickListener(this);
        //RecyclerView的setOnItemClickListener回调监听事件
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转页面
                Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", dataBeanList.get(position).getName());
                bundle.putString("time", dataBeanList.get(position).getTime());
                bundle.putString("message", dataBeanList.get(position).getMessage());
                bundle.putInt("image_id",dataBeanList.get(position).getImage_id());
                intent.putExtra("data",bundle);
                startActivity(intent);
                //刷新适配器
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_btn:
                if (isList) {
                    isList = false;//切换网格模式
                    //创建布局管理器 ---> GridLayoutManager(垂直方向滚动，参数二表示列数，按列排序)
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                    mRecycleView.setLayoutManager(gridLayoutManager);
                } else {
                    isList = true;//切换列表模式
                    //创建布局管理器
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    mRecycleView.setLayoutManager(linearLayoutManager);
                }

                break;
        }


    }


    /**
     * 创建适配器，并绑定数据
     */
    private void initAdapter() {
        adapter = new RecycleViewAdapter(MainActivity.this, dataBeanList);
        mRecycleView.setAdapter(adapter);
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        dataBeanList.add(new DataBean("Hensen", "下午1:22", "老板：哈哈哈", R.drawable.icon1));
        dataBeanList.add(new DataBean("流年不利", "早上10:31", "美女：呵呵哒", R.drawable.icon2));
        dataBeanList.add(new DataBean("1402", "下午1:55", "嘻嘻哈哈", R.drawable.icon3));
        dataBeanList.add(new DataBean("Unstoppable", "下午4:32", "美美哒", R.drawable.icon4));
        dataBeanList.add(new DataBean("流年不利", "晚上7:22", "萌萌哒", R.drawable.icon2));
        dataBeanList.add(new DataBean("Hensen", "下午1:22", "哈哈哈", R.drawable.icon1));
        dataBeanList.add(new DataBean("Unstoppable", "下午4:32", "美美哒", R.drawable.icon4));
        dataBeanList.add(new DataBean("1402", "下午1:55", "嘻嘻哈哈", R.drawable.icon3));
        dataBeanList.add(new DataBean("Hensen", "下午1:22", "哈哈哈", R.drawable.icon1));
        dataBeanList.add(new DataBean("Hensen", "下午1:22", "哈哈哈", R.drawable.icon1));
        dataBeanList.add(new DataBean("Hensen", "下午1:22", "老板：哈哈哈", R.drawable.icon1));
        dataBeanList.add(new DataBean("流年不利", "下午上10:31", "美女：呵呵哒", R.drawable.icon2));

    }

    /**
     * 初始化视图
     */
    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.rv);
        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mStitchBtn = (ImageView) findViewById(R.id.switch_btn);
    }


}

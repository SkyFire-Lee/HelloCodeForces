package io.github.skyfire_lee.hellocodeforces.ui;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.adapter.InfoAdapter;
import io.github.skyfire_lee.hellocodeforces.bean.itemBean;
import io.github.skyfire_lee.hellocodeforces.init.InitUserThread;

/**
 * Created by SkyFire on 2016/5/18.
 */
public class UserActivity extends AppCompatActivity {
    public List<itemBean> account = new ArrayList<>();
    public List<itemBean> info = new ArrayList<>();

    public ListView accountLV;
    public ListView infoLV;

    public InfoAdapter accountAdapter;
    public InfoAdapter infoAdapter;

    public String handle;
    public String _handle;

    public Handler handler = new Handler();

    public EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findView();

        updateData();   //加载更新数据

        accountLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    new AlertDialog.Builder(UserActivity.this)
                        .setTitle("请输入账户")
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences pref = getSharedPreferences("CodeForcePref", MODE_PRIVATE);
                                SharedPreferences.Editor edit = pref.edit();
                                edit.putString("handler", String.valueOf(editText.getText()));
                                edit.commit();
                                _handle = String.valueOf(editText.getText());
                                UserActivity.this.updateData();
                            }
                        })
                        .show();
                }
            }
        });
    }

    private void findView()
    {
        accountLV = (ListView) findViewById(R.id.lv_account);
        infoLV = (ListView) findViewById(R.id.lv_info);

        accountAdapter = new InfoAdapter(this, account);
        infoAdapter = new InfoAdapter(this, info);

        accountLV.setAdapter(accountAdapter);
        infoLV.setAdapter(infoAdapter);

        editText = new EditText(UserActivity.this);

        handle = (getIntent().getExtras()).getCharSequence("handle").toString();    //获取数据
    }

    private void updateData()
    {
        if((_handle != null && _handle.equals(handle) == false) || (_handle == null))
        {
            if(_handle != null)    handle = _handle;

            account.clear();
            info.clear();

            (new InitUserThread(this)).start();
            _handle = handle;
        }
    }
}

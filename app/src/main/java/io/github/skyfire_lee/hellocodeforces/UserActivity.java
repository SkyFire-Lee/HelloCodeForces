package io.github.skyfire_lee.hellocodeforces;

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

import io.github.skyfire_lee.hellocodeforces.userAction.InfoAdapter;
import io.github.skyfire_lee.hellocodeforces.bean.itemBean;
import io.github.skyfire_lee.hellocodeforces.userAction.InitUserThread;

/**
 * Created by SkyFire on 2016/5/18.
 */
public class UserActivity extends AppCompatActivity {
    private List<itemBean> account = new ArrayList<>();
    private List<itemBean> info = new ArrayList<>();

    private ListView lv_account;
    private ListView lv_info;

    private InfoAdapter accountAdapter;
    private InfoAdapter infoAdapter;

    private String nickname;
    private String _handler;
    private Handler handler;

    private EditText editText;

    private void updateData()
    {
        if(_handler != null && _handler.equals(nickname) == false)
        {
            nickname = _handler;
            account.clear();
            info.clear();
            (new InitUserThread(account, info, handler,accountAdapter,infoAdapter, nickname, this)).start();
        }
        else if(_handler == null)
        {
            account.clear();
            info.clear();
            (new InitUserThread(account, info, handler,accountAdapter,infoAdapter, nickname, this)).start();
            _handler = nickname;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        lv_account = (ListView) findViewById(R.id.lv_account);
        lv_info = (ListView) findViewById(R.id.lv_info);

        accountAdapter = new InfoAdapter(this, account);
        infoAdapter = new InfoAdapter(this, info);

        lv_account.setAdapter(accountAdapter);
        lv_info.setAdapter(infoAdapter);

        editText = new EditText(UserActivity.this);

        handler = new Handler();
        Bundle bundle = getIntent().getExtras();
        nickname = bundle.getCharSequence("handler").toString();
        updateData();

        lv_account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                                _handler = String.valueOf(editText.getText());
                                UserActivity.this.updateData();
                            }
                        })
                        .show();
                }
            }
        });
    }
}

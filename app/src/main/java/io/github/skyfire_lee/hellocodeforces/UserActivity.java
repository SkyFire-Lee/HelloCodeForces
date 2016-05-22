package io.github.skyfire_lee.hellocodeforces;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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

    private String mhandler;
    private String xhandler;
    private Handler handler;

    private EditText editText;

    private void updateData()
    {
        if(xhandler != null && xhandler.equals(mhandler) == false)
        {
            mhandler = xhandler;
            account.clear();
            info.clear();
            (new InitUserThread(account, info, handler,accountAdapter,infoAdapter, mhandler, this)).start();
        }
        else if(xhandler == null)
        {
            account.clear();
            info.clear();
            (new InitUserThread(account, info, handler,accountAdapter,infoAdapter, mhandler, this)).start();
            xhandler = mhandler;
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
        mhandler = bundle.getCharSequence("handler").toString();
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
                                    SharedPreferences pref = getSharedPreferences("cfPref", MODE_PRIVATE);
                                    SharedPreferences.Editor edit = pref.edit();
                                    edit.putString("handler", String.valueOf(editText.getText()));
                                    edit.commit();
                                    xhandler = String.valueOf(editText.getText());
                                    UserActivity.this.updateData();
                                }
                            })
                            .show();

                }
            }
        });
    }


}

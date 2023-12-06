package com.example.myapplication.ui.strategy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Base64;

import com.example.myapplication.Bean.StrategyBean;
import com.example.myapplication.Bean.UserBean;
import com.example.myapplication.Dao.StrategyDao;
import com.example.myapplication.Dao.UserDao;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StrategyCreate extends AppCompatActivity implements View.OnClickListener {
    // 用于发布一个攻略

    private EditText et_title;
    private EditText et_content;
    private Button btn_image;
    private Button btn_submit;

    private int SELECT_IMAGE_RESULT_CODE = 11;

    private String image_tmp; // 暂存上传的图片

    private boolean hasImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

        et_title = findViewById(R.id.title_strategy);
        et_content = findViewById(R.id.content_strategy);
        btn_image = findViewById(R.id.btn_image_strategy);
        btn_submit = findViewById(R.id.btn_submit_strategy);

        et_title.addTextChangedListener(new HideTextWatcher());
        et_content.addTextChangedListener(new HideTextWatcher());
        btn_submit.setOnClickListener(this);
        btn_image.setOnClickListener(this);

        image_tmp = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_image_strategy) {
            Toast.makeText(this, "提交图片", Toast.LENGTH_SHORT).show();

            hasImage = true;
            // 启动用户相册
            Intent getImage = new Intent(Intent.ACTION_PICK, null);
            getImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//这是图片类型
            startActivityForResult(getImage, SELECT_IMAGE_RESULT_CODE);


        } else if (view.getId() == R.id.btn_submit_strategy) {
            String dis = "";
            String title = et_title.getText().toString();
            String content = et_content.getText().toString();
            String account = getSharedPreferences("user", MODE_PRIVATE).getString("account", "none");
            if (title.equals("") || content.equals("")) {
                Toast.makeText(this, "请勿提交空的攻略", Toast.LENGTH_SHORT).show();
            } else if (checkSensitiveWords(content)) {
                Toast.makeText(this, "包含敏感词", Toast.LENGTH_SHORT).show();
            } else {
                if (!account.equals("none")) {
                    UserDao userDao = new UserDao(this);
                    List<UserBean> userBeans = userDao.queryByAccount(account);
                    for (UserBean userBean : userBeans) {
                        StrategyBean strategyBean = new StrategyBean(title, content, null, userBean);
                        if (hasImage) {
                            strategyBean.setImage(image_tmp);
                        }
                        StrategyDao strategyDao = new StrategyDao(this);
                        strategyDao.insert(strategyBean);
                    }
                    Toast.makeText(this, "尊敬的用户：" + account + "，您已成功分享运动攻略", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private boolean checkSensitiveWords(String content) {
        Set<String> sensitiveWordSet = new HashSet<>();

        sensitiveWordSet.add("fuck");


        SensitiveWordsUtils.init(sensitiveWordSet);
        return SensitiveWordsUtils.contains(content);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri image_uri = data.getData();
                Bitmap bitmap;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image_uri);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                image_tmp = new String(Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT));
                hasImage = true;
            }
        }
    }

    private class HideTextWatcher implements TextWatcher {
        public HideTextWatcher() {
            super();
        }

        // 在编辑框的输入文本变化前触发
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        // 在编辑框的输入文本变化时触发
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        // 在编辑框的输入文本变化后触发
        public void afterTextChanged(Editable s) {

        }
    }
}